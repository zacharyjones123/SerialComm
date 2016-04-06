import java.awt.event.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.swing.*;

/**
 * This is the GUI interface for the functions of the BananaTurbo Car
 * 
 * @author Zachary Jones
 *
 */
public class BananaTurboGUI extends JFrame implements ActionListener {
	static SerialTest main;
	static Codes codes;
	static BufferedReader input;
	static OutputStream output;
	private byte[] data = new byte[1];

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

	// Size constants for the window
	private final static int FRAME_WIDTH = 730;
	private final static int FRAME_HEIGHT = 500;

	// Buttons
	private JButton btnForward = new JButton(FORWARD);
	private JButton btnStop = new JButton(STOP);
	private JButton btnReverse = new JButton(REVERSE);
	private JButton btnHeadLights = new JButton(HEADLIGHTS);
	private JButton btnTailLights = new JButton(TAILLIGHTS);
	private JButton btnBlinkerLeft = new JButton(BLINKERLEFT);
	private JButton btnBlinkerRight = new JButton(BLINKERRIGHT);
	private JButton btnCircle = new JButton(CIRCLE);
	private JButton btnSpin = new JButton(SPIN);
	private JButton btnLeft = new JButton(LEFT);
	private JButton btnRight = new JButton(RIGHT);
	private JButton btnHorn = new JButton(HORN);
	private JButton btnLEDShow = new JButton(LEDSHOW);
	private JButton btnQuit = new JButton(QUIT);

	// Panels
	// GridLayout layout = new GridLayout(3,3);
	private JPanel pnlAlgs = new JPanel(new GridLayout(4, 4)); // Main panel

	// --------- Constructors and GUI Set Up -------
	/**
	 * Constructor: Creates a new GUI
	 */
	public BananaTurboGUI() {
		//Set Up All The Variables
		main = new SerialTest(); // opens the port to anything we
		main.initialize();
		codes = new Codes();
		input = main.getInput();
		output = main.getOutput();
		setUpGUI();
	}

	/**
	 * Sets up the GUI framework, adds listeners.
	 */
	private void setUpGUI() {	
		// Construct the main window
		setTitle(TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
		setUpPanels();

		c.add(pnlAlgs);
		addListeners();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Sets up the panels that comprise the GUI
	 */
	private void setUpPanels() {

		pnlAlgs.add(btnForward);
		pnlAlgs.add(btnStop);
		pnlAlgs.add(btnReverse);
		pnlAlgs.add(btnHeadLights);
		pnlAlgs.add(btnTailLights);
		pnlAlgs.add(btnBlinkerLeft);
		pnlAlgs.add(btnBlinkerRight);
		pnlAlgs.add(btnCircle);
		pnlAlgs.add(btnSpin);
		pnlAlgs.add(btnLeft);
		pnlAlgs.add(btnRight);
		pnlAlgs.add(btnHorn);
		pnlAlgs.add(btnLEDShow);
		pnlAlgs.add(btnQuit);

		pnlAlgs.setBorder(BorderFactory.createTitledBorder(ALGS));
	}

	/**
	 * Adds action listeners to all the buttons
	 */
	private void addListeners() {
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
	}

	@Override
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
		new BananaTurboGUI();

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
					Thread.sleep(1000000);
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
