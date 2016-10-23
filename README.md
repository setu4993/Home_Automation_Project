# Home_Automation_Project

A home automation system for automating lights in the passageways and in the rooms, a key-less door lock system and leakage detection for LPG cooking gas. Using an Android app to monitor and control all of the above. Using a local RasPi server for local data logging and connecting all the appliances to the internet.

This repository contains the code for every module of the described project. The project was completed as a part of my curriculum and was accompanied by hardware modules.

Light module: Automated lights for the passageways and rooms which turn on when the sensors detect movement in the vicinity. PIR sensor detects larger movements, whereas ultrasonic sensor ensures that the light stays on even when there are small movements. It sends the on-off status to the local server.

1. Microcontroller: Espressif ESP 8266 based Wemos D1 Mini (Programmed with Arduino Studio)
2. Sensors: Ultrasonic sensor, PIR sensor
3. Output: LEDs, relays

LPG Gas Module: LPG gas detector continually measures the amount of LPG in the air. It indicates LPG leakage by a combination of LED and buzzer warnings. Also sends the detected status values to the local server.

1. Microcontroller: Espressif ESP 8266 based Wemos D1 Mini (Programmed with Arduino Studio)
2. Sensors: MQ-6 gas sensor
3. Output: LEDs, buzzer

Door Module: A key-less door lock system with a motor-operated rack and pinion lock that replaces traditional door locks. The door opens when a 4-digit key is entered. Each key is associated with a user. The action initiated by the user is recorded and stored on the local server.

1. Microcontrollers: Espressif ESP 8266 based Wemos D1 Mini (Programmed with Arduino Studio), ATtiny85
2. Sensors: Button
3. Output: Buzzer, LED, Motor control

Local server: A local server is maintained to communicate among all the modules and to forward the data to the web server periodically.

Android app: An Android app that displays the current status and the past operations that have taken place for all of the above devices. Also allows creation of new users. Can be used to monitor the status of the home from another location.

Web server: A web server handles all the app-related communication, and is responsible for logging the data sent by the local server.
