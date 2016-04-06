/**
 * My first applet
 * 
 * @author zrjones
 *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JButton;

public class OrangeTurboApplet extends Applet implements ActionListener{
	static SerialTest main;
	static Codes codes;
	static BufferedReader input;
	static OutputStream output;
	private byte[] data;
	
	// Window and button strings
	private final static String TITLE = "BananaTurbo Control Center";
	private final static String ALGS = "Algorithms";
	private final static String FORWARD = "Forward";
	private final static String STOP = "Stop";
	private final static String REVERSE = "Reverse";
	private final static String HEADLIGHTS = "HeadLights";
	private final static String TAILLIGHTS = "TailLights";
	private final static String BLINKERLEFT = "Blinker Left";
	private final static String BLINKERRIGHT = "Blinker Right";
	private final static String CIRCLE = "Circle";
	private final static String SPIN = "Spin";
	private final static String LEFT = "Left";
	private final static String RIGHT = "Right";
	private final static String HORN = "Horn";
	private final static String LEDSHOW = "LED Show";
	private final static String QUIT = "Quit";
	
	// Buttons
	private Button btnForward = new Button(FORWARD);
	private Button btnStop = new Button(STOP);
	private Button btnReverse = new Button(REVERSE);
	private Button btnHeadLights = new Button(HEADLIGHTS);
	private Button btnTailLights = new Button(TAILLIGHTS);
	private Button btnBlinkerLeft = new Button(BLINKERLEFT);
	private Button btnBlinkerRight = new Button(BLINKERRIGHT);
	private Button btnCircle = new Button(CIRCLE);
	private Button btnSpin = new Button(SPIN);
	private Button btnLeft = new Button(LEFT);
	private Button btnRight = new Button(RIGHT);
	private Button btnHorn = new Button(HORN);
	private Button btnLEDShow = new Button(LEDSHOW);
	private Button btnQuit = new Button(QUIT);
    
     public void init() 
     { 
        // Set the layout to GridLayout that is a 4 x 4 block
        setLayout(new GridLayout(4, 4)); //The main panel

        // now that all is set we can add these components to the applet 
        add(btnForward);
		add(btnStop);
		add(btnReverse);
		add(btnHeadLights);
		add(btnTailLights);
		add(btnBlinkerLeft);
		add(btnBlinkerRight);
		add(btnCircle);
		add(btnSpin);
		add(btnLeft);
		add(btnRight);
		add(btnHorn);
		add(btnLEDShow);
		add(btnQuit);
		
		btnForward.addActionListener(this);
		btnStop.addActionListener(this);
		btnReverse.addActionListener(this);
		btnHeadLights.addActionListener(this);
		btnTailLights.addActionListener(this);
		btnBlinkerLeft.addActionListener(this);
		btnBlinkerRight.addActionListener(this);
		btnCircle.addActionListener(this);
		btnSpin.addActionListener(this);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
		btnHorn.addActionListener(this);
		btnLEDShow.addActionListener(this);
		btnQuit.addActionListener(this);
		//The action listeners
		
     }

	public void paint(Graphics g) {
		
		repaint(); //Lets the applet show that message
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnForward)) {
			System.out.println("Moves Car Forward");
			data[0] = 'f';
			sendData(data);

		} else if (e.getSource().equals(btnStop)) {
			System.out.println("Car is Stopped");
			data[0] = 's';
			sendData(data);

		} else if (e.getSource().equals(btnReverse)) {
			System.out.println("Moves Car Backwards");
			data[0] = 'r';
			sendData(data);

		} else if (e.getSource().equals(btnHeadLights)) {
			System.out.println("HeadLights Toggle");
			data[0] = 'l';
			sendData(data);

		} else if (e.getSource().equals(btnTailLights)) {
			System.out.println("TailLights Toggle");
			data[0] = 't';
			sendData(data);

		} else if (e.getSource().equals(btnBlinkerLeft)) {
			System.out.println("Blinker Left Toggle");
			data[0] = '0';
			sendData(data);

		} else if (e.getSource().equals(btnBlinkerRight)) {
			System.out.println("BlinkerRight Toggle");
			data[0] = '1';
			sendData(data);

		} else if (e.getSource().equals(btnCircle)) {
			System.out.println("Move In A Circle");
			data[0] = 'c';
			sendData(data);

		} else if (e.getSource().equals(btnSpin)) {
			System.out.println("Car Spins In Place");
			data[0] = 'S';
			sendData(data);

		} else if (e.getSource().equals(btnLeft)) {
			System.out.println("Car Turns Left");
			data[0] = 'L';
			sendData(data);

		} else if (e.getSource().equals(btnRight)) {
			System.out.println("Car Turns Right");
			data[0] = 'R';
			sendData(data);

		} else if (e.getSource().equals(btnHorn)) {
			System.out.println("Horn!!!!!");
			data[0] = 'h';
			sendData(data);

		} else if (e.getSource().equals(btnLEDShow)) {
			System.out.println("LED Strip Light Show");
			data[0] = 'z';
			sendData(data);

		} else if (e.getSource().equals(btnQuit)) {
			System.out.println("Quit");
			System.exit(0);

		}

	}
	
	/**
	 * This method is for sending data to the Ardiuno
	 */
	private void sendData(byte[] data) {
		codes.setACode(1, data);
		try {
			output.write(codes.getACode(1));
		} catch (IOException e1) {
			//TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Startup method for the application. Creates the GUI.
	 * 
	 * @param args
	 *            args[0] is the name of the input text file.
	 */
	public static void main(String[] args) {
		new OrangeTurboApplet();

		//-----------------------------------------------------------------------------------------------
		//--------------------------    Everything from the Serial Test Main  ---------------------------
		//-----------------------------------------------------------------------------------------------
		// want to
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
					output.write(input0);
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
				String inputLine = input.readLine();

		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
	}
	
	
}