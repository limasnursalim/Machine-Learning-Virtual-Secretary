import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SignUpWindowComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	private static final int FIELD_WIDTH = 10;

	//SIGN UP PANEL
	private JPanel firstNamePanel;
	private JPanel lastNamePanel;
	private JPanel IDPanel;
	private JPanel emailPanel;
	
	//SIGN UP INFO
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField IDField;
	private JTextField emailField;
	
	
	//CONTINUE BUTTON PANEL
	private JPanel continueButtonPanel;
	
	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;

	public SignUpWindowComponent()
	{
		//SIGN UP PANEL
		firstNamePanel = new JPanel();
		lastNamePanel = new JPanel();
		IDPanel = new JPanel();
		emailPanel = new JPanel();
		
		//CONTINUE BUTTON PANEL
		continueButtonPanel = new JPanel();
		
		createTitle();
		createFileMenu();
		createTextField();
		createContinueButton();
		createControlPanel();
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Class to control the exit button so it could exit the program
	 * @author limasnursalim
	 *
	 */
	class ExitMenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}
	
	class continueButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			System.out.print("Continue to Department Success\n");
			setVisible(false);
			DepartmentWindowComponent department = new DepartmentWindowComponent();
			
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String email = emailField.getText();
			String IDNumber = IDField.getText();
			
			SignUp signUp = new SignUp(firstName, lastName, email, IDNumber);
			
			department.setVisible(true);
		}
	}
	
	/**
	 * Method of central panel that is in charge of controlling the display of all panel
	 */
	public void createControlPanel()
	{
		// SIGN UP PANEL
		JPanel signUpPanel = new JPanel();
		signUpPanel.setLayout(new GridLayout(4,1));
		signUpPanel.setBorder(new TitledBorder(new EtchedBorder(), "Sign Up"));

		signUpPanel.add(firstNamePanel);
		signUpPanel.add(lastNamePanel);
		signUpPanel.add(IDPanel);
		signUpPanel.add(emailPanel);
		
		add(signUpPanel, BorderLayout.CENTER);
		
		//CONTINUE BUTTON
		JPanel continuePanel = new JPanel();
		continuePanel.setLayout(new GridLayout(1,1));
		continuePanel.setBorder(new EtchedBorder());
		continuePanel.add(continueButtonPanel);
		
		add(continuePanel, BorderLayout.SOUTH);
		
		/** DROP DOWN MENU **/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
	}
	
	/**
	 * Method to create the title
	 */
	public void createTitle()
	{
		JLabel title = new JLabel("Virtual Secretary", JLabel.CENTER);
		title.setFont(new Font("Serif", Font.PLAIN, 25));
		add(title, BorderLayout.NORTH);

	}
	
	/**
	 * Method to create all the text field
	 */
	public void createTextField()
	{
		/** SIGN UP PANEL **/
		//FIRST NAME PANEL
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameField = new JTextField(FIELD_WIDTH);
		firstNamePanel.add(firstNameLabel);
		firstNamePanel.add(firstNameField);

		//SECOND NAME PANEL
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameField = new JTextField(FIELD_WIDTH);
		lastNamePanel.add(lastNameLabel);
		lastNamePanel.add(lastNameField);

		//ID LABEL
		JLabel IDLabel = new JLabel("ID Number: ");
		IDField = new JTextField(FIELD_WIDTH);
		IDPanel.add(IDLabel);
		IDPanel.add(IDField);

		//EMAIL LABEL
		JLabel emailLabel = new JLabel("Email:  ");
		emailField = new JTextField(FIELD_WIDTH);
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
	}
	
	/**
	 * Method to create the continue button to continue to the next frame
	 */
	public void createContinueButton()
	{
		JButton continueButton = new JButton("Continue");
		ActionListener listener = new continueButtonListener();
		continueButton.addActionListener(listener);

		continueButtonPanel.add(continueButton);
	}
	
	/**
	 * Method to create the file drop down menu
	 */
	public void createFileMenu()
	{
		fileMenu = new JMenu("File");
		createExitMenu();
		fileMenu.add(exitMenuItem);
	}

	/**
	 * Method to create the exit menu under file drop down menu
	 */
	public void createExitMenu()
	{
		exitMenuItem = new JMenuItem("Exit");
		ActionListener listener = new ExitMenuListener();
		exitMenuItem.addActionListener(listener);
	}
}
