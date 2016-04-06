/**
 * Use this program to figure out what ports are on
 * the computer that you are on.
 * 
 * @author zrjones
 */

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JSlider;
import com.fazecast.jSerialComm.*;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		JSlider slider = new JSlider();
		slider.setMaximum(1023);
		window.add(slider);
		window.pack();
		window.setVisible(true);

		
		//This part finds all of the ports and lets you choose one
		//=================================================================
		SerialPort[] ports = SerialPort.getCommPorts();
		System.out.println("Select a port:");
		int i = 1;
		for(SerialPort port : ports)
			System.out.println(i++ +  ": " + port.getSystemPortName());
		Scanner s = new Scanner(System.in);
		int chosenPort = s.nextInt();
		//==================================================================

		//Tells you whether the port you choose works or not
		//==================================================================
		SerialPort serialPort = ports[chosenPort - 1];
		if(serialPort.openPort())
			System.out.println("Port opened successfully.");
		else {
			System.out.println("Unable to open the port.");
			return;
		}
		//=================================================================
		//serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);

		
		//Makes a scanner out of data from Serial port
		//=================================================================
		Scanner data = new Scanner(serialPort.getInputStream()); //This is the point of the slow down 12 - 13 seconds
																 //With the slider do the 6-7 seconds lag
		int value = 0;
		while(data.hasNextLine()){
			try{
				value = Integer.parseInt(data.nextLine());}catch(Exception e){}
			System.out.println(value);
			//slider.setValue(value);
		}
		System.out.println("Done.");
		//================================================================
	}

}
