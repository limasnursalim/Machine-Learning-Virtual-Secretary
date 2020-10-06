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

public class GeneralQuestionCSWindowComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	private static final int FIELD_WIDTH = 20;

	//GENERAL QUESTION PANEL
	private JPanel generalQuestionPanel;

	//GENERAL QUESTION TEXT FIELD
	private JTextField questionInput;

	//ANSWER QUESTION PANEL
	private JPanel answerQuestionPanel;

	//CONTINUE BUTTON PANEL
	private JPanel returnButtonPanel;
	
	//EXACT ANSWER
	private String exactAnswer;

	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;

	public GeneralQuestionCSWindowComponent()
	{
		//GENERAL QUESTION PANEL
		generalQuestionPanel = new JPanel();

		//CONTINUE BUTTON PANEL
		returnButtonPanel = new JPanel();

		//ANSWER QUESTION PANEL
		answerQuestionPanel = new JPanel();

		createTitle();
		createFileMenu();
		createTextField();
		createReturnButton();
		createGetAnswerButton();
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

	class answerQuestion implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			//store user's input into a string
			String question = questionInput.getText();
			GeneralQuestion CSQuestion = new GeneralQuestionCS(question);

			if (CSQuestion.findExactQuestion()) //if the inputed question is exact same match with file
			{
				setVisible(false);
				exactAnswer = CSQuestion.findExactAnswer();
				//then it will find the corresponding index and prints out the exact answer right away
				FinalAnswerWindowComponent exactCS = new FinalAnswerWindowComponent(exactAnswer);
				exactCS.setVisible(true);
			}
			else if (!CSQuestion.findExactQuestion())
			{
				ArrayList<String> guessQuestion = new ArrayList<String>();
				CSQuestion.removeUselessWordsFromUserQuestion();
				guessQuestion = CSQuestion.checkKeywordsWithQuestionFile();
				setVisible(false);
				GuessAnswerWindowComponent guessCS = new GuessAnswerWindowComponent(guessQuestion, CSQuestion, question);
				guessCS.setVisible(true);
			}
		}
	}

	/**
	 * Method of central panel that is in charge of controlling the display of all panel
	 */
	public void createControlPanel()
	{
		//GENERAL QUESTION PANEL
		JPanel generalQuestionPanel = new JPanel();
		generalQuestionPanel.setLayout(new GridLayout(2,1));
		generalQuestionPanel.setBorder(new TitledBorder(new EtchedBorder(), "General Question"));

		generalQuestionPanel.add(this.generalQuestionPanel, BorderLayout.CENTER);
		add(generalQuestionPanel);

		//RETURN BUTTON AND GET ANSWER BUTTON
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,2));
		southPanel.setBorder(new EtchedBorder());
		southPanel.add(returnButtonPanel);
		southPanel.add(answerQuestionPanel);

		add(southPanel, BorderLayout.SOUTH);

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
	 * Method to get user's input in a text field
	 */
	public void createTextField()
	{
		JLabel info = new JLabel("Please type your question:");
		questionInput = new JTextField(FIELD_WIDTH);

		//add label and text field to the panel
		generalQuestionPanel.add(info);
		generalQuestionPanel.add(questionInput);
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
	 * Method create a button to get the answer of the user's question
	 */
	public void createGetAnswerButton()
	{
		JButton getAnswerButton = new JButton("Get Answer");
		ActionListener listener = new answerQuestion();
		getAnswerButton.addActionListener(listener);

		answerQuestionPanel.add(getAnswerButton);
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
