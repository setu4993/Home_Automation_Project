#include <ESP8266WiFi.h>
#include <Ultrasonic.h>

int calibrationTime = 15;
long unsigned int lowIn;  //the time when the sensor outputs a low impulse
long unsigned int pause = 3000;  //the amount of milliseconds the sensor has to be low before we assume all motion has stopped
long unsigned int lowPr = 0;
long unsigned int highPr = 0;

const char* ssid = "ssid";
const char* password = "password";
const char* host = "192.168.0.100"; //IP Address of RasPi server
WiFiServer server(80);

int pirPin = 16; //D0
int ledPin = 2; //D4
int value = LOW;
String request;

boolean serverflag = false;

Ultrasonic ultrasonic(12, 14, 17400);
//(Trig PIN,Echo PIN, custom time-out (max distance * 58)), D6, D5

void setup()
{
  //Serial and IO
  Serial.begin(115200);
  pinMode(pirPin, INPUT);
  pinMode(ledPin, OUTPUT);
  digitalWrite(pirPin, LOW);
  
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
  
  //Sensor calibration
  Serial.print("Calibrating sensor");
  for(int i = 0; i < calibrationTime; i++)
  {
    Serial.print(".");
    delay(1000);
  }
  Serial.println("Calibration done. Sensor active.");
  delay(50);
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
  client.print(String("GET /active.php?dt=l&di=1&st=") + st + " HTTP/1.1\r\n" + "Host: "
              + host + "\r\n" + "Connection: close\r\n\r\n");
  delay(10);
}

//Post data to MySQL
void sendHTTPData(int val)
{
  WiFiClient client;
  const int httpPort = 80;
  if (!client.connect(host, httpPort))
  {
    Serial.println("connection failed");
    return;
  } 
  // This will send the request to the server
  client.print(String("GET /light.php?vl=") + val + "&id=1" + " HTTP/1.1\r\n" + "Host: "
              + host + "\r\n" + "Connection: close\r\n\r\n");
  delay(10);
}

void wrctrl()
{
  WiFiClient client = server.available();
  if(client.available())
  {
    while(!client.available())
    {
      delay(1);
    }
    request = client.readStringUntil('\r');
    Serial.println(request);
    if (request.indexOf("/LIGHT=TOGGLE") != -1)
    {
      serverflag = true;
    }
  
    client.flush();
    
    // Return the response
    client.println("HTTP/1.1 200 OK");
    client.println("Content-Type: text/html");
    client.println(""); //  do not forget this one
    client.println("<!DOCTYPE HTML>");
    client.println("<html>");
     
    client.print("Light is now: ");
     
    if(value == LOW)
    {
      client.print("Off.");  
    }
    else
    {
      client.print("On.");
    }
    client.println("<br><br>");
    client.println("Click <a href=\"/LIGHT=TOGGLE\">here</a> to toggle the light.<br>");
    client.println("</html>");
  }
}

void loop()
{
  //WiFiClient client = server.available();

  
  
  int x = ultrasonic.Ranging(CM);
  
  if((digitalRead(pirPin) == HIGH) || (x < 50) || (serverflag == true))
  {
    digitalWrite(ledPin, HIGH);
    value = HIGH;
    delay(1);
    if(highPr == 0)
    {
      Serial.println("---");
      Serial.print("Motion detected at ");
      Serial.print(millis()/1000);
      Serial.println(" sec");
      sendHTTPData(1);
      delay(50);
      highPr = 1;
      lowPr = 0;
      delay(5);
      serverflag = false;
    }
    delay(1000);
  }

  else if((digitalRead(pirPin) == LOW) && (x >= 50) && (serverflag == false))
  {
    digitalWrite(ledPin, HIGH);
    lowIn = millis();
    delay(1);
    while((digitalRead(pirPin) == LOW) && (x >= 50) && (serverflag == false))
    {
      x = ultrasonic.Ranging(CM);
      wrctrl();
      delay(50);
      if((millis() - lowIn) > pause)
      {
        digitalWrite(ledPin, LOW);
        value = LOW;
        delay(1);
        if(lowPr == 0)
        {
          Serial.print("Motion ended at ");
          Serial.print((millis() - pause)/1000);
          Serial.println(" sec");
          sendHTTPData(0);
          lowPr = 1;
          highPr = 0;
          delay(5);
        }
        delay(50);
      }
    }
  }
}
