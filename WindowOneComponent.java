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

public class WindowOneComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	
	//BUTTON PANEL
	private JPanel signInButtonPanel;
	private JPanel signUpButtonPanel;

	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	
	public WindowOneComponent()
	{
		//BUTTON PANEL
		signInButtonPanel = new JPanel();
		signUpButtonPanel = new JPanel();

		createTitle();
		createSignInButton();
		createSignUpButton();
		createFileMenu();
		createControlPanel(); 
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class SignInListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			System.out.print("Sign In Success\n");
			setVisible(false);
			SignInWindowComponent signIn = new SignInWindowComponent();
			signIn.setVisible(true);
		}
	}
	
	class SignUpListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			System.out.print("Sign Up Success\n");
			setVisible(false);
			SignUpWindowComponent signUp = new SignUpWindowComponent();
			signUp.setVisible(true);
		}
	}

	class ExitMenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}

	/**
	 * Method of central panel that is in charge of controlling the display of all panel
	 */
	public void createControlPanel()
	{
		/** CENTER PANEL **/
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		centerPanel.setBorder(new EtchedBorder());
		centerPanel.add(signInButtonPanel);
		centerPanel.add(signUpButtonPanel);

		add(centerPanel, BorderLayout.CENTER);

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
	 * Method to create sign in button
	 */
	public void createSignInButton()
	{
		JButton signInButton = new JButton("Sign In");
		ActionListener listener = new SignInListener();
		signInButton.addActionListener(listener);

		JLabel empty = new JLabel(" ");
		JLabel empty2 = new JLabel(" ");
		JLabel empty3 = new JLabel(" ");
		JLabel empty4 = new JLabel(" ");
		
		signInButtonPanel.setBackground(Color.LIGHT_GRAY);
		signInButtonPanel.setLayout(new GridLayout(5,1));
		signInButtonPanel.add(empty);
		signInButtonPanel.add(empty2);
		signInButtonPanel.add(signInButton);
		signInButtonPanel.add(empty3);
		signInButtonPanel.add(empty4);
	}
	
	/**
	 * Method to create sign up button
	 */
	public void createSignUpButton()
	{
		JButton signUpButton = new JButton("Sign Up");
		ActionListener listener = new SignUpListener();
		signUpButton.addActionListener(listener);
		
		JLabel empty = new JLabel(" ");
		JLabel empty2 = new JLabel(" ");
		JLabel empty3 = new JLabel(" ");
		JLabel empty4 = new JLabel(" ");
		
		signUpButtonPanel.setBackground(Color.LIGHT_GRAY);
		signUpButtonPanel.setLayout(new GridLayout(5,1));
		signUpButtonPanel.add(empty);
		signUpButtonPanel.add(empty2);
		signUpButtonPanel.add(signUpButton);
		signUpButtonPanel.add(empty3);
		signUpButtonPanel.add(empty4);
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