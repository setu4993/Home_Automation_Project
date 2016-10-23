#include <Wire.h>
#include <ESP8266WiFi.h>

#define S1_ADDR 0x14

int buzzPin = 5; //Buzzer D1
int motord1 = 13; //L293D direction 1 D7
int motord2 = 15; //L293D direction 2 D8
int pushPin = 16; //Push button D0
//int ledPin = 12; //Closed LED D6
int starPin1 = 14; //Keypad R4 D5
int starPin2 = 16; //Keypad C1 D0


int keyent = 0;
int keystore = 0;
int keycount = 0;
int keytries = 0;
int keyflag = 0;
int starState1 = 0;
int starState2 = 0;
int piezo = 0;

int uid;
int ac;

boolean dooropen = false;
boolean doorhash = false;
boolean doorflag = false;

const char* ssid = "ssid";
const char* password = "password";
const char* host = "192.168.0.100"; //IP Address of RasPi server
WiFiServer server(80);

String request;

void setup()
{
  Serial.begin(115200);
  Wire.begin(0, 2); //D3, D4
  Serial.println("");
  Serial.println("Door module.");
  pinMode(pushPin, INPUT);
  pinMode(buzzPin, OUTPUT);
  //pinMode(ledPin, OUTPUT);
  pinMode(motord1, OUTPUT);
  pinMode(motord2, OUTPUT);
  pinMode(starPin1, INPUT);
  pinMode(starPin2, INPUT);
  //WiFi Setup
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(250);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
  server.begin();
  Serial.print("Use this URL to connect: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
  HTTPActive(1);
}

void HTTPActive(int st)
{
  WiFiClient client;
  const int httpPort = 80;
  if (!client.connect(host, httpPort))
  {
    Serial.println("connection failed");
    return;
  } 
  // This will send the request to the server
  client.print(String("GET /active.php?dt=d&di=1&st=") + st + " HTTP/1.1\r\n" + "Host: "
              + host + "\r\n" + "Connection: close\r\n\r\n");
  delay(10);
}

//Post data to MySQL
void sendHTTPData()
{
  WiFiClient client;
  const int httpPort = 80;
  if (!client.connect(host, httpPort))
  {
    Serial.println("connection failed");
    return;
  } 
  // This will send the request to the server
  client.print(String("GET /door.php?id=") + uid + "&cd=" + keystore + "&ac=" + ac + " HTTP/1.1\r\n" + "Host: "
              + host + "\r\n" + "Connection: close\r\n\r\n");
  delay(10);
}


void loop()
{
  starState1 = digitalRead(starPin1);
  starState2 = digitalRead(starPin2);

  WiFiClient client = server.available();

  if(client.available())
  {
    while(!client.available())
    {
      delay(1);
    }
    request = client.readStringUntil('\r');
    Serial.println(request);
    if (request.indexOf("/DOOR=TOGGLE") != -1)
    {
      doorflag = true;
    }
  
    client.flush();
    
    // Return the response
    client.println("HTTP/1.1 200 OK");
    client.println("Content-Type: text/html");
    client.println(""); //  do not forget this one
    client.println("<!DOCTYPE HTML>");
    client.println("<html>");
     
    client.print("Door is now: ");
     
    if(dooropen == true)
    {
      client.print("Open.");  
    }
    else
    {
      client.print("Closed.");
    }
    client.println("<br><br>");
    client.println("Click <a href=\"/DOOR=TOGGLE\">here</a> to toggle the door.<br>");
    client.println("</html>");
  }
  
  if ((starState1 == LOW) && (starState2 == LOW))
  {
    longBuzz();
    keyflag = 1;
    keyent = 0;
    keycount = 0;
  }
  if ((keyflag == 1) && (keycount < 4))
  {
    doorhash = false;
    //if((keynos == 0))
    //{
    keyReq();
    delay(500);
    //}

  }
  if (keycount == 4)
  {
    longBuzz();
    keyflag = 0;
    keytries++;
    Serial.println("Key obtained.");
    Serial.println(keyent);
    if (keyent == 1111)
    {
      doorflag = true;
      uid = 1;
      keystore = keyent;
      //Open door from outside
    }
    else if(keyent == 2222)
    {
      uid = 2;
      doorflag = true;
      keystore = keyent;
    }
    else if((keyent == 0) && (doorhash = true) && (dooropen == true))
    {
      doorflag = true;
    }
    else
    {
      wrongKey();
      doorflag = false;
    }
    keycount = 0;
    keyent = 0;
  }
  if ((doorflag == true) && (dooropen == false))
  {
    openDoor();
  }
  else if ((doorflag == true) && (dooropen == true))
  {
    closeDoor();
  }
  delay(50);
}

//Key request function
void keyReq()
{
  Serial.println("Byte requested.");
  Wire.requestFrom(S1_ADDR, 1);
  if (Wire.available())
  {
    byte byteReceived = Wire.read();
    Serial.println(byteReceived);
    if (byteReceived < 10)
    {
      Serial.println(byteReceived);
      keyent = keyent * 10 + byteReceived;
      blinkBuzz();
      Serial.println(keyent);
      keycount++;
    }
    else if (byteReceived == 12)
    {
      doorhash = true;
      blinkBuzz();
      keyent = 0;
      keycount = 4;
    }
  }
}

//Buzzer short beep
void blinkBuzz()
{
  digitalWrite(buzzPin, HIGH);
  delay(50);
  digitalWrite(buzzPin, LOW);
  delay(50);
}

//Buzzer long beep
void longBuzz()
{
  digitalWrite(buzzPin, HIGH);
  delay(500);
  digitalWrite(buzzPin, LOW);
  delay(500);
}

void wrongKey()
{
  digitalWrite(buzzPin, HIGH);
  delay(2000);
  digitalWrite(buzzPin, LOW);
  delay(2);
}

//Opening door
void openDoor()
{
  //digitalWrite(ledPin, HIGH);
  digitalWrite(motord1, HIGH);
  delay(4000);
  digitalWrite(motord1, LOW);
  keytries = 0;
  ac = 1;
  dooropen = true;
  doorflag = false;
  sendHTTPData();
}

//Closing door
void closeDoor()
{
  digitalWrite(motord2, HIGH);
  delay(4000);
  digitalWrite(motord2, LOW);
  keytries = 0;
  dooropen = false;
  doorflag = false;
  ac = 0;
  sendHTTPData();
}