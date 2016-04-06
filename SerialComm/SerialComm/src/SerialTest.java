import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.Enumeration;
import java.util.Scanner;

public class SerialTest implements SerialPortEventListener {
	static Codes codes;
	SerialPort serialPort;
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { "/dev/tty.usbserial-A9007UX1", // Mac
																				// OS
																				// X
			"/dev/ttyACM0", // Raspberry Pi
			"/dev/ttyUSB0", // Linux
			"COM4", // Windows
	};
	/**
	 * A BufferedReader which will be fed by a InputStreamReader converting the
	 * bytes into characters making the displayed results codepage independent
	 */
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
		// the next line is for Raspberry Pi and
		// gets us into the while loop and was suggested here was suggested
		// http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
		// System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
		// //TODO: Only uncomment for the RASPBERYY PI

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		// First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
					.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other
		// ones.
	}
	
	public BufferedReader getInput() {
		return input;
	}
	
	public OutputStream getOutput() {
		return output;
	}

	public static void main(String[] args) throws Exception {
		SerialTest main = new SerialTest(); // opens the port to anything we
											// want to
		codes = new Codes();
		main.initialize();
		Thread t = new Thread() {
			public void run() {

				// the following line will keep this app alive for 1000 seconds,
				// waiting for events to occur and responding to them (printing
				// incoming messages to console).
				try {
					Thread.sleep(10000);
				} catch (InterruptedException ie) {
				} // How long the port is remained open
				// HOW LONG IS PORT OPEN: 5 Sec
				byte input0[] = { '0', '0' }; // Turn off all lights

				/** The try-catch works as a way of turning everything off */
				try {
					main.output.write(input0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/** -------------------------------------------------------- */
				main.close(); // Close the port\
				System.out.println("closed");

			}
		};
		t.start();
		System.out.println("Started");
		try {
			boolean in = true;
			while (in) {
				String inputLine = main.input.readLine();
				// System.out.println("---"+ inputLine + "---"); //You need at
				// have at least one line of input for the
				// program to work
				// The --- on both sides is to see if there are any hiddle
				// characters

				// How to input to the stream
				// LIST OF COMMANDS FOR THE ARDUINO
				byte input1[] = { '0', '7', '0', '1' };
				byte input2[] = { '0', '9', '0', '1' };
				byte input3[] = { '1', '1', '0', '1' };
				byte input4[] = { '1', '3', '0', '1' };
				
				codes.setACode(1, input1);
				codes.setACode(2, input2);
				codes.setACode(3, input3);
				codes.setACode(4, input4);
				
				main.output.write(codes.getACode(1));
				main.output.write(codes.getACode(2));
				main.output.write(codes.getACode(3));
				main.output.write(codes.getACode(4));
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}