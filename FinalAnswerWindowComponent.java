import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class FinalAnswerWindowComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	private static final int FIELD_WIDTH = 20;
	
	//ANSWER OF QUESTION
	private String answer;
	
	//RETURN BUTTON PANEL
	private JPanel returnButtonPanel;
	
	//ANSWER PANEL
	private JPanel answerPanel;
	
	//ANSWER TEXT FIELD
	private JTextField answerTextField;
	
	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	
	public FinalAnswerWindowComponent(String answer)
	{
		//CONTINUE BUTTON PANEL
		returnButtonPanel = new JPanel();
		
		//ANSWER PANEL
		answerPanel = new JPanel();
		
		createTitle();
		createFileMenu();
		setAnswer(answer);
		createReturnButton();
		createExactAnswerTextField();
		createControlPanel();
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Method of central panel that is in charge of controlling the display of all panel
	 */
	public void createControlPanel()
	{
		//ANSWER PANEL
		JPanel answerPanel = new JPanel();
		answerPanel.setLayout(new GridLayout(1,1));
		answerPanel.setBorder(new TitledBorder(new EtchedBorder(), "Exact Answer"));
		
		answerPanel.add(this.answerPanel, BorderLayout.CENTER);
		add(answerPanel);
		
		//RETURN BUTTON
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,2));
		southPanel.setBorder(new EtchedBorder());
		southPanel.add(returnButtonPanel);
		
		add(southPanel, BorderLayout.SOUTH);
		
		/** DROP DOWN MENU **/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
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
	
	class returnButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			setVisible(false);
			QuestionTypeCSWindowComponent questionType = new QuestionTypeCSWindowComponent();
			questionType.setVisible(true);
		}
	}
	
	/**
	 * Method to set the answer
	 * @param answer
	 */
	public void setAnswer(String answer)
	{
		this.answer = answer;
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
	 * Method to create exact answer text field
	 */
	public void createExactAnswerTextField()
	{
		JLabel answer = new JLabel("Answer:");
		answerTextField = new JTextField(FIELD_WIDTH);
		answerTextField.setText(this.answer);
		answerTextField.setEditable(false);
		
		answerPanel.add(answer);
		answerPanel.add(answerTextField);
	}
	
	/**
	 * Method to create the continue button to continue to the next frame
	 */
	public void createReturnButton()
	{
		JButton returnButton = new JButton("Return");
		ActionListener listener = new returnButtonListener();
		returnButton.addActionListener(listener);

		returnButtonPanel.add(returnButton);
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
