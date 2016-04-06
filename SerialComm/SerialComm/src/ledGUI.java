import java.awt.event.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

import javax.swing.*;

/**
 * This is the GUI interface for the functions of the BananaTurbo Car
 * @author Zachary Jones
 *
 */
public class ledGUI extends JFrame implements ActionListener {
	
	static SerialTest main;
	static Codes codes;
	static BufferedReader input;
	static OutputStream output;
	private byte[] data;

	//Window and button strings
	private final static String TITLE = "7 LED Control Center";
	private final static String LIGHTS = "Lights";
	private final static String ALGS = "Algorithms";
	private final static String PIN07 = "Pin 07";
	private final static String PIN08 = "Pin 08";
	private final static String PIN09 = "Pin 09";
	private final static String PIN10 = "Pin 10";
	private final static String PIN11 = "Pin 11";
	private final static String PIN12 = "Pin 12";
	private final static String PIN13 = "Pin 13";
	private final static String TURNON = "ON";
	private final static String TURNOFF = "OFF";
	private final static String TURNALLON = "Turn All On";
	private final static String TURNALLOFF = "Turn All Off";
	private final static String RAINBOW = "Raindow";
	private final static String QUIT = "Quit";
	
	//Size constants for the window
	private final static int FRAME_WIDTH = 730;
	private final static int FRAME_HEIGHT = 500;
	
	// Buttons
	private JButton btn7On = new JButton(TURNON);
	private JButton btn7Off = new JButton(TURNOFF);
	private JButton btn8On = new JButton(TURNON);
	private JButton btn8Off = new JButton(TURNOFF);
	private JButton btn9On = new JButton(TURNON);
	private JButton btn9Off = new JButton(TURNOFF);
	private JButton btn10On = new JButton(TURNON);
	private JButton btn10Off = new JButton(TURNOFF);
	private JButton btn11On = new JButton(TURNON);
	private JButton btn11Off = new JButton(TURNOFF);
	private JButton btn12On = new JButton(TURNON);
	private JButton btn12Off = new JButton(TURNOFF);
	private JButton btn13On = new JButton(TURNON);
	private JButton btn13Off = new JButton(TURNOFF);
	private JButton btnTurnAllOn = new JButton(TURNALLON);
	private JButton btnTurnAllOff = new JButton(TURNALLOFF);
	private JButton btnRainbow = new JButton(RAINBOW);
	private JButton btnQuit = new JButton(QUIT);
	
	//Panels
	//GridLayout layout = new GridLayout(3,3);
	private JPanel pnlLights = new JPanel(new GridLayout(4,4)); //Main left Panel
	private JPanel pnlAlgs = new JPanel(new GridLayout(4,4)); //Main right Panel
	
	private JPanel pnlpin07 = new JPanel(); //Pin 7
	private JPanel pnlpin08 = new JPanel(); //Pin 8
	private JPanel pnlpin09 = new JPanel(); //Pin 9
	private JPanel pnlpin10 = new JPanel(); //Pin 10
	private JPanel pnlpin11 = new JPanel(); //Pin 11
	private JPanel pnlpin12 = new JPanel(); //Pin 12
	private JPanel pnlpin13 = new JPanel(); //Pin 13
	
	
	//--------- Constructors and GUI Set Up -------
	/**
	 * Constructor: Creates a new GUI
	 */
	public ledGUI() {
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
		c.add(pnlLights);
		addListeners();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Sets up the panels that comprise the GUI
	 */
	private void setUpPanels() {
		btn7On.setBounds(0,100,25,25);
		btn7Off.setBounds(100, 100, 25, 25);
		btn8On.setBounds(200, 100, 25, 25);
		
		pnlpin07.add(btn7On);
		pnlpin07.add(btn7Off);
		pnlpin08.add(btn8On);
		pnlpin08.add(btn8Off);
		pnlpin09.add(btn9On);
		pnlpin09.add(btn9Off);
		pnlpin10.add(btn10On);
		pnlpin10.add(btn10Off);
		pnlpin11.add(btn11On);
		pnlpin11.add(btn11Off);
		pnlpin12.add(btn12On);
		pnlpin12.add(btn12Off);
		pnlpin13.add(btn13On);
		pnlpin13.add(btn13Off);
		
		pnlpin07.setBorder(BorderFactory.createTitledBorder(PIN07));
		pnlpin08.setBorder(BorderFactory.createTitledBorder(PIN08));
		pnlpin09.setBorder(BorderFactory.createTitledBorder(PIN09));
		pnlpin10.setBorder(BorderFactory.createTitledBorder(PIN10));
		pnlpin11.setBorder(BorderFactory.createTitledBorder(PIN11));
		pnlpin12.setBorder(BorderFactory.createTitledBorder(PIN12));
		pnlpin13.setBorder(BorderFactory.createTitledBorder(PIN13));
		
		pnlpin07.setBounds(0, 0,300, 300);
		pnlpin08.setBounds(0, 300, 300, 300);
		pnlpin09.setBounds(300,0, 300, 300);
		pnlpin13.setBounds(300, 300, 300, 300);
		
		pnlLights.add(pnlpin07);
		pnlLights.add(pnlpin08);
		pnlLights.add(pnlpin09);
		pnlLights.add(pnlpin10);
		pnlLights.add(pnlpin11);
		pnlLights.add(pnlpin12);
		pnlLights.add(pnlpin13);
		
		pnlAlgs.add(btnTurnAllOff);
		pnlAlgs.add(btnTurnAllOn);
		pnlAlgs.add(btnRainbow);
		pnlAlgs.add(btnQuit);
			
		pnlLights.setBorder(BorderFactory.createTitledBorder(LIGHTS));
		pnlAlgs.setBorder(BorderFactory.createTitledBorder(ALGS));
	}
	
	/**
	 * Adds action listeners to all the buttons
	 */
	private void addListeners() {
		btn7On.addActionListener(this);
		btn7Off.addActionListener(this);
		btn8On.addActionListener(this);
		btn8Off.addActionListener(this);
		btn9On.addActionListener(this);
		btn9Off.addActionListener(this);
		btn10On.addActionListener(this);
		btn10Off.addActionListener(this);
		btn11On.addActionListener(this);
		btn11Off.addActionListener(this);
		btn12On.addActionListener(this);
		btn12Off.addActionListener(this);
		btn13On.addActionListener(this);
		btn13Off.addActionListener(this);
		btnTurnAllOn.addActionListener(this);
		btnTurnAllOff.addActionListener(this);
		btnRainbow.addActionListener(this);
		btnQuit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn7On)) {
			//System.out.println("Turn On Led 7!");
			data = buildCharArray('0', '7', ' ', '1');
			byte[] home = {'0', '7', ' ', '1'};
			sendData(home);
			
		} else if (e.getSource().equals(btn7Off)) {
			//System.out.println("Turn Off Led 7!");
			data = buildCharArray('0', '7', ' ', '0');
			sendData(data);
			
		} else if (e.getSource().equals(btn8On)) {
			//System.out.println("Turn On Led 8!");
			data = buildCharArray('0', '8', ' ', '1');
			sendData(data);
			
		} else if (e.getSource().equals(btn8Off)) {
			//System.out.println("Turn Off Led 8!");
			data = buildCharArray('0', '8', ' ', '0');
			sendData(data);
			
		} else if (e.getSource().equals(btn9On)) {
			//System.out.println("Turn On Led 9!");
			data = buildCharArray('0', '9', ' ', '1');
			sendData(data);
			
		} else if (e.getSource().equals(btn9Off)) {
			//System.out.println("Turn Off Led 9!");
			data = buildCharArray('0', '9', ' ', '1');
			sendData(data);
			
		} else if (e.getSource().equals(btn10On)) {
			//System.out.println("Turn On Led 10!");
			data = buildCharArray('1', '0', ' ', '1');
			sendData(data);
			
		} else if (e.getSource().equals(btn10Off)) {
			//System.out.println("Turn Off Led 10!");
			data = buildCharArray('1', '0', ' ', '0');
			sendData(data);
			
		} else if (e.getSource().equals(btn11On)) {
			//System.out.println("Turn On Led 11!");
			data = buildCharArray('1', '1', ' ', '1');
			sendData(data);
			
		} else if (e.getSource().equals(btn11Off)) {
			//System.out.println("Turn Off Led 11!");
			data = buildCharArray('1', '1', ' ', '0');
			sendData(data);
			
		} else if (e.getSource().equals(btn12On)) {
			//System.out.println("Turn On Led 12!");
			data = buildCharArray('1', '2', ' ', '1');
			sendData(data);
			
		} else if (e.getSource().equals(btn12Off)) {
			//System.out.println("Turn Off Led 12!");
			data = buildCharArray('1', '2', ' ', '0');
			sendData(data);
			
		} else if (e.getSource().equals(btn13On)) {
			//System.out.println("Turn On Led 13!");
			data = buildCharArray('1', '3', ' ', '1');
			sendData(data);
			
		} else if (e.getSource().equals(btn13Off)) {
			//System.out.println("Turn Off Led 13!");
			data = buildCharArray('1', '3', ' ', '0');
			sendData(data);
			
		} else if (e.getSource().equals(btnTurnAllOn)) {
			//System.out.println("Turn On All Leds!");
			data = buildCharArray('0', '1', ' ', ' ');
			sendData(data);
			
		} else if (e.getSource().equals(btnTurnAllOff)) {
			//System.out.println("Turn Off All Leds!");
			data = buildCharArray('0', '0', ' ', ' ');
			sendData(data);
			
		} else if (e.getSource().equals(btnRainbow)) {
			//System.out.println("Rainbow Activate!");
			data = buildCharArray('0', '2', ' ', ' ');
			sendData(data);
			
		} else if (e.getSource().equals(btnQuit)) {
			//System.out.println("Quit");
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
	
	private byte[] buildCharArray(char a, char b, char c, char d) {
		byte[] array = {(byte) a, (byte) b, (byte) c, (byte) d};
		return array;
	}
	
	/**
	 * Startup method for the application. Creates the GUI.
	 * 
	 * @param args args[0] is the name of the input text file.
	 */
	public static void main(String[] args) {
		new ledGUI();
		
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
