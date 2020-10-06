import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GeneralQuestionMathWindowComponent extends JFrame
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

	public GeneralQuestionMathWindowComponent()
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
			GeneralQuestion MathQuestion = new GeneralQuestionMath(question);

			if (MathQuestion.findExactQuestion()) //if the inputed question is exact same match with file
			{
				setVisible(false);
				exactAnswer = MathQuestion.findExactAnswer();
				//then it will find the corresponding index and prints out the exact answer right away
				FinalAnswerWindowComponent exactMath = new FinalAnswerWindowComponent(exactAnswer);
				exactMath.setVisible(true);
			}
			else if (!MathQuestion.findExactQuestion())
			{
				ArrayList<String> guessQuestion = new ArrayList<String>();
				MathQuestion.removeUselessWordsFromUserQuestion();
				guessQuestion = MathQuestion.checkKeywordsWithQuestionFile();
				setVisible(false);
				GuessAnswerWindowComponent guessMath = new GuessAnswerWindowComponent(guessQuestion, MathQuestion, question);
				guessMath.setVisible(true);
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
