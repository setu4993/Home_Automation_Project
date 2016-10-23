#include <ESP8266WiFi.h>

const char* ssid = "ssid";
const char* password = "password";
const char* host = "192.168.0.100"; //IP Address of RasPi server

int gas;
int ledPinG = 15; //D8
int ledPinR = 14; //D5
int buzzPin = 16; //D0
int mintime = 0;
int modtime = 0;

void setup()
{
  Serial.begin(115200);
  pinMode(ledPinG, OUTPUT);
  pinMode(ledPinR, OUTPUT);
  pinMode(buzzPin, OUTPUT);
  digitalWrite(ledPinG, HIGH);
  digitalWrite(ledPinR, LOW);
  digitalWrite(buzzPin, LOW);
  Serial.println("Initialized.");

  // Connect to WiFi network
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
   
  WiFi.begin(ssid, password);
   
  while (WiFi.status() != WL_CONNECTED)
  {
    digitalWrite(ledPinG, HIGH);
    delay(250);
    Serial.print(".");
    digitalWrite(ledPinG, LOW);
    delay(250);
  }
  
  Serial.println("");
  Serial.println("WiFi Connected. IP Address: ");
  Serial.println(WiFi.localIP());
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
  client.print(String("GET /active.php?dt=g&di=1&st=") + st + " HTTP/1.1\r\n" + "Host: "
              + host + "\r\n" + "Connection: close\r\n\r\n");
  delay(10);
}

//Post data to MySQL
void sendHTTPData(int val, char* wl)
{
  WiFiClient client;
  const int httpPort = 80;
  if (!client.connect(host, httpPort))
  {
    Serial.println("connection failed");
    return;
  } 
  // This will send the request to the server
  client.print(String("GET /gas.php?vl=") + val + "&wl=" + wl + " HTTP/1.1\r\n" + "Host: "
              + host + "\r\n" + "Connection: close\r\n\r\n");
  delay(10);
}

void loop()
{
  digitalWrite(ledPinG, HIGH);
  gas = analogRead(17); // read A0
  Serial.println(gas);
  delay(2);
  if(gas > 250)
  {
    digitalWrite(ledPinG, LOW);
    if(gas > 550)
    {
      if(gas > 850)
      {
        digitalWrite(ledPinR, HIGH);
        digitalWrite(buzzPin, LOW);
        delay(25);
        digitalWrite(ledPinR, LOW);
        digitalWrite(buzzPin, HIGH);
        delay(25);
        sendHTTPData(gas, "high");
      }
      else
      {
        digitalWrite(ledPinR, HIGH);
        digitalWrite(buzzPin, HIGH);
        delay(100);
        digitalWrite(ledPinR, LOW);
        digitalWrite(buzzPin, LOW);
        delay(100);
        if((modtime - millis() < 500))
        {
          modtime = millis();
          sendHTTPData(gas, "moderate");
        }
      }
    }
    else
    {
      digitalWrite(ledPinR, HIGH);
      delay(250);
      digitalWrite(ledPinR, LOW);
      delay(250);
      if((mintime - millis() < 1000))
      {
        sendHTTPData(gas, "minor");
        mintime = millis();
      }
    }
  }
  else
  {
    digitalWrite(ledPinG, HIGH);
    delay(100);
  }
  delay(1);
}
