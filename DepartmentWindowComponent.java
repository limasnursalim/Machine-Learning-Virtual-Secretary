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

public class DepartmentWindowComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;

	//DEPARTMENT PANEL
	private JPanel departmentPanel;

	//CONTINUE BUTTON PANEL
	private JPanel continueButtonPanel;

	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;

	//DEPARTMENT COMBO BOX
	private JComboBox departmentBox;

	public DepartmentWindowComponent()
	{
		//DEPARTMENT PANEL
		departmentPanel = new JPanel();

		//CONTINUE BUTTON PANEL
		continueButtonPanel = new JPanel();

		//DEPARTMENT COMBO BOX
		departmentBox = new JComboBox();

		createTitle();
		createComboBox();
		createContinueButton();
		createFileMenu();
		createControlPanel();

		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Method of central panel that is in charge of controlling the display of all panel
	 */
	public void createControlPanel()
	{
		//DEPARTMENT PANEL
		JPanel departmentPanel = new JPanel();
		departmentPanel.setLayout(new GridLayout(1,1));
		departmentPanel.setBorder(new TitledBorder(new EtchedBorder(), "Department"));
		departmentPanel.add(this.departmentPanel);

		add(departmentPanel, BorderLayout.CENTER);

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
			Object obj = departmentBox.getSelectedItem();
			String departmentChoices = (String) obj; //DOWNCAST TO STRING

			if (departmentChoices.equals("Computer Science"));
			{
				setVisible(false);
				QuestionTypeCSWindowComponent questionTypeCS = new QuestionTypeCSWindowComponent();
				questionTypeCS.setVisible(true);
			}
			if (departmentChoices.equals("Math"))
			{
				setVisible(false);
				QuestionTypeMathWindowComponent questionTypeMath = new QuestionTypeMathWindowComponent();
				questionTypeMath.setVisible(true);
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
	 * Method to create the drop down combo box menu
	 */
	public void createComboBox()
	{
		JLabel departmentLabel = new JLabel("Please Choose Department: ");
		departmentBox.addItem("Computer Science");
		departmentBox.addItem("Math");

		//set the layout of the panel to 2 row 1 column
		this.departmentPanel.setLayout(new GridLayout(2,1));
		this.departmentPanel.add(departmentLabel);
		this.departmentPanel.add(departmentBox);
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
