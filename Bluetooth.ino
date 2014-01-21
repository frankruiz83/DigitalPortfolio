#include <SoftwareSerial.h>

//Reference:
//  http://bellcode.wordpress.com/2012/01/02/android-and-arduino-bluetooth-communication/
//The code comes from this website. Also contains a wiring
//diagram for the modem.

//To actually read what the modem outputs in a console, you
//need to do the following:
//  1.  Get putty
//  2.  Make sure that Windows will pair with the modem. Note,
//      on Windows 8 (maybe 7) it will say the device is offline
//      even though it is connected. At this point the modem
//      should have a blinking red light.
//  3.  Open the Control Panel. Search for "bluetooth." Open the
//      "Change bluetooth settings" dialog. Go to the COM
//      Ports tab.
//  4.  Note the "Outgoing" COM port. This is the port that
//      originates from the modem, and data comes to your
//      computer.
//  5.  Open putty. Go to the Serial area. Change the port to
//      the "Outgoing" COM Port. Set Flow control to "None".
//      Click on the Session item and select the serial radio
//      button. Finally click the Open button.
//  6.  The Modem should now have a green light. Enter some
//      text in the Arduino serial console, it should appear
//      in the putty window.

int bluetoothTx = 2;  //Transmitter pin
int bluetoothRx = 3;  //Receiver pin

//Create a software serial connections.
//Basically allows for two serial consoles, the
//normal one and the bluetooth modem.
SoftwareSerial bluetooth(bluetoothTx, bluetoothRx);

void setup()
{
  //Setup usb serial connection to computer
  Serial.begin(9600);

  //Setup Bluetooth serial connection
  //This is an incantation, just repeat it exactly.
  //May take the modem up to 15 seconds to actually
  //be usable.
  bluetooth.begin(115200);
  bluetooth.print("$$$");
  delay(100);
  bluetooth.println("U,9600,N");
  bluetooth.begin(9600);
}

void loop()
{
  //Read from bluetooth and write to usb serial
  if(bluetooth.available())
  {
    char toSend = (char)bluetooth.read();
    Serial.print(toSend);
    bluetooth.write("ECHO: ");
    bluetooth.write(toSend);
    bluetooth.write("\r\n");
  }

  //Read from usb serial console and write it
  //to bluetooth modem. Also echos the input
  //to the serial console for debugging purposes
  if(Serial.available())
  {
    char toSend = (char)Serial.read();
    bluetooth.print(toSend);
    Serial.write("ECHO: ");
    Serial.write(toSend);
    Serial.write("\r\n");
  }
}
