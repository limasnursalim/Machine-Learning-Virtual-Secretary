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

public class GuessAnswerWindowComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	
	//GUESS QUESTION
	private ArrayList<String> guessQuestion;
	
	//GUESS QUESTION RADIO BUTTON
	private JRadioButton guessQuestionFirstButton;
	private JRadioButton guessQuestionSecondButton;
	private JRadioButton guessQuestionThirdButton;
	private JRadioButton guessQuestionFourthButton;
	private JRadioButton noneOfTheAboveButton;
	
	//GUESS QUESTION PANEL
	private JPanel guessQuestionPanel;
	
	//ANSWER QUESTION PANEL
	private JPanel answerQuestionPanel;
	
	//GUESS ANSWER STRING
	private String guessAnswer;
	
	//USER ORIGINAL QUESTION
	private String userOriginalQuestion;
	
	//DOWNCASTING
	private GeneralQuestion question;
	
	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	
	//GET ANSWER BUTTON
	private JButton getAnswerButton;
	
	//CONTINUE BUTTON PANEL
	private JPanel returnButtonPanel;
	
	public GuessAnswerWindowComponent(ArrayList<String> guessQuestion, Object obj, String userOriginalQuestion)
	{
		//GUESS QUESTION PANEL
		guessQuestionPanel = new JPanel();
		
		//ANSWER QUESTION PANEL
		answerQuestionPanel = new JPanel();
		
		//GET ANSWER BUTTON
		getAnswerButton = new JButton("Get Answer");
		
		//CONTINUE BUTTON PANEL
		returnButtonPanel = new JPanel();
		
		//DOWNCASTING TO GET THE GUESS ANSWER STRING
		question = (GeneralQuestion) obj;
		
		setGuessQuestion(guessQuestion);
		setUserOriginalQuestion(userOriginalQuestion);
		createTitle();
		createReturnButton();
		createGuessQuestionRadioButtons();
		createGetAnswerButton();
		createFileMenu();
		createControlPanel();
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Method to set the guess answer
	 * @param guessQuestion is the guess question
	 */
	public void setGuessQuestion(ArrayList<String> guessQuestion)
	{
		this.guessQuestion = guessQuestion;
	}
	
	/**
	 * Method to set user's original question for machine learning
	 * @param userOriginalQuestion is the user's original inputted question
	 */
	public void setUserOriginalQuestion(String userOriginalQuestion)
	{
		this.userOriginalQuestion = userOriginalQuestion;
	}
	
	/**
	 * Method of central panel that is in charge of controlling the display of all panel
	 */
	public void createControlPanel()
	{
		//GUESS QUESTION PANEL
		JPanel guessQuestionPanel = new JPanel();
		guessQuestionPanel.setLayout(new GridLayout(1,1));
		guessQuestionPanel.setBorder(new TitledBorder(new EtchedBorder(), "Answer Panel"));
		guessQuestionPanel.add(this.guessQuestionPanel);
		add(guessQuestionPanel, BorderLayout.CENTER);
		
		//GET ANSWER BUTTON
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
	
	public void sendEmail()
	{
			System.out.println("email");
			final String username = "limassnursalim@gmail.com";
	        final String password = "uwhligbvuthfabke";

	        Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("limassnursalim@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse("foodboy1008@gmail.com")
	            );
	            message.setSubject("User follow up questions");
	            message.setText("Dear user,"
	                    + "\n\n I am sorry that we can't answer your questions. We will follow up to you soon if we got the answer!");

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
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
			setVisible(false);
			if (guessQuestionFirstButton.isSelected())
			{
				guessAnswer = question.findGuessAnswer(guessQuestion.get(0), userOriginalQuestion);
				FinalAnswerWindowComponent guessCS = new FinalAnswerWindowComponent(guessAnswer);
				guessCS.setVisible(true);
			}
			else if (guessQuestionSecondButton.isSelected())
			{
				guessAnswer = question.findGuessAnswer(guessQuestion.get(1), userOriginalQuestion);
				FinalAnswerWindowComponent guessCS = new FinalAnswerWindowComponent(guessAnswer);
				guessCS.setVisible(true);
			}
			else if (guessQuestionThirdButton.isSelected())
			{
				guessAnswer = question.findGuessAnswer(guessQuestion.get(2), userOriginalQuestion);
				FinalAnswerWindowComponent guessCS = new FinalAnswerWindowComponent(guessAnswer);
				guessCS.setVisible(true);
			}
			else if (guessQuestionFourthButton.isSelected())
			{
				guessAnswer = question.findGuessAnswer(guessQuestion.get(3), userOriginalQuestion);
				FinalAnswerWindowComponent guessCS = new FinalAnswerWindowComponent(guessAnswer);
				guessCS.setVisible(true);
			}
			else if (noneOfTheAboveButton.isSelected())
			{
				System.out.println("none of the above selected");
				sendEmail();
				setVisible(false);
				JOptionPane.showMessageDialog(new JFrame(), "We are sorry that there is no answer. Email has been sent to follow up!");
				System.exit(0);
			}
		}
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
	 * Method to create guess question radio buttons
	 */
	public void createGuessQuestionRadioButtons()
	{
		JLabel guessQuestionLabel = new JLabel("Did you mean");
		guessQuestionFirstButton = new JRadioButton(guessQuestion.get(0));
		guessQuestionSecondButton = new JRadioButton(guessQuestion.get(1));
		guessQuestionThirdButton = new JRadioButton(guessQuestion.get(2));
		guessQuestionFourthButton = new JRadioButton(guessQuestion.get(3));
		noneOfTheAboveButton = new JRadioButton("None of the above");
		
		//add radio buttons to ButtonGroup so that only one button in the group is selected at any time
		ButtonGroup group = new ButtonGroup();
		group.add(guessQuestionFirstButton);
		group.add(guessQuestionSecondButton);
		group.add(guessQuestionThirdButton);
		group.add(guessQuestionFourthButton);
		group.add(noneOfTheAboveButton);
		
		//add all the label and button to the panel
		//set the layout of the panel to 6 row 1 column
		guessQuestionPanel.setLayout(new GridLayout(6,1));
		guessQuestionPanel.add(guessQuestionLabel);
		guessQuestionPanel.add(guessQuestionFirstButton);
		guessQuestionPanel.add(guessQuestionSecondButton);
		guessQuestionPanel.add(guessQuestionThirdButton);
		guessQuestionPanel.add(guessQuestionFourthButton);
		guessQuestionPanel.add(noneOfTheAboveButton);
	}
	
	/**
	 * Method create a button to get the answer of the user's question
	 */
	public void createGetAnswerButton()
	{
		ActionListener listener = new answerQuestion();
		getAnswerButton.addActionListener(listener);

		answerQuestionPanel.add(getAnswerButton);
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
