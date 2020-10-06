import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SignInWindowComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	private static final int FIELD_WIDTH = 10;

	//ID TEXT FIELD
	private JTextField IDLoginField;

	//SIGN IN PANEL
	private JPanel IDLoginPanel;

	//CONTINUE BUTTON PANEL
	private JPanel continueButtonPanel;

	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;

	public SignInWindowComponent()
	{
		//SIGN IN PANEL
		IDLoginPanel = new JPanel();

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
			String ID = IDLoginField.getText();
			SignIn signIn = new SignIn(ID);

			try 
			{
				if (signIn.readFile())
				{
					setVisible(false);
					DepartmentWindowComponent department = new DepartmentWindowComponent();
					department.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "User ID doesn't exist!");
				}
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println(e);
			}
		}
	}

	/**
	 * Method to create the main panel that controls all panel
	 */
	public void createControlPanel()
	{
		// LOGIN PANEL
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(1,1));
		loginPanel.setBorder(new TitledBorder(new EtchedBorder(), "Log In"));

		loginPanel.add(IDLoginPanel);
		add(loginPanel, BorderLayout.CENTER);

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
	 * Method to create the text field of ID number
	 */
	public void createTextField()
	{
		/** SIGN IN PANEL **/
		//ID LOGIN LABEL
		JLabel IDLoginLabel = new JLabel("ID Number: ");
		IDLoginField = new JTextField(FIELD_WIDTH);
		IDLoginPanel.add(IDLoginLabel);
		IDLoginPanel.add(IDLoginField);
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
