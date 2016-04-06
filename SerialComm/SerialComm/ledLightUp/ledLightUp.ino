/**
 * This program usings a circuit of 7 LEDS connected
 * to pins 7-13, which I will use with Serial Communication
 * to perform tasks as shown below
 * PROTOCOl
 * pin 1 = turn pin on
 * pin 0 = turn pin off
 * 02     = rainbow
 * 01     = turn all LEDs on
 * 00     = turn all LEDs off
 */

int val = 0;
int power = 0;

void setup() {
  Serial.begin(9600);
  for (int i = 7; i <= 13; i++) {
    pinMode(i, OUTPUT);
  }
  Serial.println("a");
  Serial.println("b");
  Serial.println("c");
  Serial.println("d");
}

void loop() {
  delay(100);

}

void serialEvent() { //To check if there is any data on the Serial Line
  while (Serial.available()) {
    char input[4];
    Serial.readBytes(input, 4);
    val = ((input[0]-48) * 10) + input[1] - 48;
    //val = Serial.parseInt(); //Can change the delay time, right now 2 second delay
    power = input[3] - 48;
    //Serial.println(input[0]);
    //Serial.println(input[1]);
    //Serial.println(input[2]);
    //Serial.println(input[3]);
    //Serial.println(val);
    //Serial.println(power);
    if (val == 0) {
      turnAllOff();
    } else if (val == 1) {
      turnAllOn();
    } else if (val == 2) {
      rainbow();
    } else if (val == 7) { //Switch on the LED
      onOff(power, 7);
    } else if (val == 8) { //Switch off the LED
      onOff(power, 8);
    } else if (val == 9) {
      onOff(power, 9);
    } else if (val == 10) {
      onOff(power, 10);
    } else if (val == 11) {
      onOff(power, 11);
    } else if (val == 12) {
      onOff(power, 12);
    } else if (val == 13) {
      onOff(power, 13);
    }
  }
  //Serial.println("Successfully received.");
}

void onOff(int onOff, int pin) {
        if (onOff == 1) {
        digitalWrite(pin, HIGH);
      } else if (onOff == 0) {
        digitalWrite(pin, LOW);
      }
}

void turnAllOff() {
    for (int pin = 7; pin <= 13; pin++) {
    digitalWrite(pin,LOW);
  }
}

void turnAllOn() {
  for (int pin = 7; pin <= 13; pin++) {
    digitalWrite(pin,HIGH);
  }
}

void alternate() {
    //Can not do unless know voltages of pins
}

void rainbow() {
  turnAllOff();
  for (int pin = 7; pin <= 13; pin++) {
    digitalWrite(pin, HIGH);
    delay(1000);
    turnAllOff();
  }
  turnAllOn();
  delay(2500);
  turnAllOff();
}

