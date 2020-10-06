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

public class QuestionTypeCSWindowComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;

	//QUESTION PANEL
	private JPanel questionPanel;

	//CONTINUE BUTTON PANEL
	private JPanel continueButtonPanel;
	
	//QUESTION TYPE RADIO BUTTON
	private JRadioButton generalQuestionButton;
	private JRadioButton makeAppointmentButton;

	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;

	public QuestionTypeCSWindowComponent()
	{
		//QUESTION PANEL
		questionPanel = new JPanel();

		//CONTINUE BUTTON PANEL
		continueButtonPanel = new JPanel();

		createTitle();
		createRadioButtons();
		createContinueButton();
		createFileMenu();
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
			if (generalQuestionButton.isSelected())
			{
				setVisible(false);
				GeneralQuestionCSWindowComponent generalQues = new GeneralQuestionCSWindowComponent();
				generalQues.setVisible(true);
			}
			else if (makeAppointmentButton.isSelected())
			{
				setVisible(false);
				AppointmentTypeCSWindowComponent appointCS = new AppointmentTypeCSWindowComponent();
				appointCS.setVisible(true);
			}
		}
	}

	/**
	 * Method of central panel that is in charge of controlling the display of all panel
	 */
	public void createControlPanel()
	{
		//QUESTION PANEL
		JPanel questionPanel = new JPanel();
		questionPanel.setLayout(new GridLayout(1,1));
		questionPanel.setBorder(new TitledBorder(new EtchedBorder(), "Question Type"));
		questionPanel.add(this.questionPanel);

		add(questionPanel, BorderLayout.CENTER);

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
	 * Method to create the radio buttons for question type
	 */
	public void createRadioButtons()
	{
		JLabel questionLabel = new JLabel("Please Choose Your Question Type:");
		generalQuestionButton = new JRadioButton("General Question");
		makeAppointmentButton = new JRadioButton("Make Appointment");
		
		//add radio buttons to ButtonGroup so that only one button in the group is selected at any time
		ButtonGroup group = new ButtonGroup();
		group.add(generalQuestionButton);
		group.add(makeAppointmentButton);
		
		
		
		//add all the label and button to the panel
		//set the layout of the panel to 3 row 1 column
		questionPanel.setLayout(new GridLayout(3,1));
		questionPanel.add(questionLabel);
		questionPanel.add(generalQuestionButton);
		questionPanel.add(makeAppointmentButton);
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
