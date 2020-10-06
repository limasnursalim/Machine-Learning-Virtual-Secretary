import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class AppointmentTypeCSWindowComponent extends JFrame
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	private static final int FIELD_WIDTH = 40;

	//DROP DOWN MENU
	private JMenu fileMenu;
	private JMenuItem exitMenuItem;

	//PERSON COMBO BOX
	private JComboBox personComboBox;
	
	//APPOINTMENT TIME
	private JTextField dayTextField;
	private JTextField timeTextField;
	
	//APPOINTMENT PANEL
	private JPanel dayPanel;
	private JPanel timePanel;

	//SEE APPOINTMENT BUTTON PANEL
	private JPanel seeAppointmentButtonPanel;
	
	//BOOK APPOINTMENT BUTTON PANEL
	private JPanel bookAppointmentButtonPanel;
	
	//PERSON PANEL
	private JPanel personPanel;

	public AppointmentTypeCSWindowComponent()
	{
		//PERSON PANEL
		personPanel = new JPanel();

		//CONTINUE BUTTON PANEL
		seeAppointmentButtonPanel = new JPanel();
		
		//BOOK APPOINTMENT BUTTON PANEL
		bookAppointmentButtonPanel = new JPanel();
		
		//APPOINTMENT PANEL
		dayPanel = new JPanel();
		timePanel = new JPanel();

		createTitle();
		createFileMenu();
		createSeeAppointmentButton();
		createBookAppointmentButton();
		createPersonComboBox();
		createDayTextField();
		createControlPanel();

		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Method of central panel that is in charge of controlling the display of all panel
	 */
	public void createControlPanel()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(6,1));
		centerPanel.setBorder(new TitledBorder(new EtchedBorder(), "Appointment"));
		centerPanel.add(personPanel);
		centerPanel.add(dayPanel);
		centerPanel.add(timePanel);
		add(centerPanel, BorderLayout.CENTER);

		//SOUTH PANEL
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,2));
		southPanel.setBorder(new EtchedBorder());
		southPanel.add(bookAppointmentButtonPanel);
		southPanel.add(seeAppointmentButtonPanel);

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

	class seeAppointmentButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			//DOWNCASTING
			Object person = personComboBox.getSelectedItem();
			String personChoice = (String) person;
			
			String day = dayTextField.getText();
			String time = timeTextField.getText();
			
			AppointmentCS appointCS = new AppointmentCS(personChoice, day, time);
			
			if (personChoice.equals("Chief of Department"))
			{
				if (appointCS.checkSchedule())
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time"); //ketuker
				else
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is not booked at desired time");
			}
			else if (personChoice.equals("Dean"))
			{
				if (appointCS.checkSchedule())
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				else
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is not booked at desired time");
			}
			else if (personChoice.equals("Dave Smith"))
			{
				if (appointCS.checkSchedule())
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				else
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is not booked at desired time");
			}
			else if (personChoice.equals("Jamal Ashraf"))
			{
				if (appointCS.checkSchedule())
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				else
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is not booked at desired time");
			}
			else if (personChoice.equals("Sassan Barkeshli"))
			{
				if (appointCS.checkSchedule())
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				else
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is not booked at desired time");
			}
		}
	}
	
	class bookAppointmentButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//DOWNCASTING
			Object person = personComboBox.getSelectedItem();
			String personChoice = (String) person;
			
			String day = dayTextField.getText();
			String time = timeTextField.getText();
			
			AppointmentCS appointCS = new AppointmentCS(personChoice, day, time);
			
			if (personChoice.equals("Chief of Department"))
			{
				if (appointCS.checkSchedule()) //ini bisa buat jadi method trus downcast
				{
					
					try 
					{
						appointCS.compareSchedule();
						appointCS.writeFile();
						sendEmail();
						JOptionPane.showMessageDialog(new JFrame(), "Your appointment has been booked. An email has been sent.");
					} 
					catch (IOException e1) 
					{
						System.out.println(e);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				}
			}
			else if (personChoice.equals("Dean"))
			{
				if (appointCS.checkSchedule())
				{
					
					try 
					{
						appointCS.compareSchedule();
						appointCS.writeFile();
						sendEmail();
						JOptionPane.showMessageDialog(new JFrame(), "Your appointment has been booked. An email has been sent.");
					} 
					catch (IOException e1) 
					{
						System.out.println(e);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				}
			}

			else if (personChoice.equals("Dave Smith"))
			{
				if (appointCS.checkSchedule())
				{
					
					try 
					{
						appointCS.compareSchedule();
						appointCS.writeFile();
						sendEmail();
						JOptionPane.showMessageDialog(new JFrame(), "Your appointment has been booked. An email has been sent.");
					} 
					catch (IOException e1) 
					{
						System.out.println(e);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				}
			}
			else if (personChoice.equals("Jamal Ashraf"))
			{
				if (appointCS.checkSchedule())
				{
					try 
					{
						appointCS.compareSchedule();
						appointCS.writeFile();
						sendEmail();
						JOptionPane.showMessageDialog(new JFrame(), "Your appointment has been booked. An email has been sent.");
					} 
					catch (IOException e1) 
					{
						System.out.println(e);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				}
			}
			else if (personChoice.equals("Sassan Barkeshli"))
			{
				if (appointCS.checkSchedule())
				{
					try 
					{
						appointCS.compareSchedule();
						appointCS.writeFile();
						sendEmail();
						JOptionPane.showMessageDialog(new JFrame(), "Your appointment has been booked. An email has been sent.");
					} 
					catch (IOException e1) 
					{
						System.out.println(e);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "Teacher is booked at desired time");
				}
			}
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
	
	/**
	 * Method to create person name combo box
	 */
	public void createPersonComboBox()
	{
		personComboBox = new JComboBox();
		personComboBox.addItem("-");
		personComboBox.addItem("Chair of Department");
		personComboBox.addItem("Dean");
		personComboBox.addItem("Jamal Ashraf");
		personComboBox.addItem("Dave Smith");
		personComboBox.addItem("Sassan Barkeshli");

		personPanel.add(personComboBox);
	}

	
	/**
	 * Method to create text field
	 */
	public void createDayTextField()
	{
		JLabel day = new JLabel("Day: ");
		JLabel time = new JLabel("Time: ");
		dayTextField = new JTextField(FIELD_WIDTH);
		timeTextField = new JTextField(FIELD_WIDTH);
		timeTextField.setText("Enter in this format, ex: 07:00-08:00");
		
		dayPanel.setLayout(new GridLayout(1,1));
		dayPanel.add(day);
		dayPanel.add(dayTextField);
		
		timePanel.setLayout(new GridLayout(1,1));
		timePanel.add(time);
		timePanel.add(timeTextField);
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
	 * Method to create the see appointment button
	 */
	public void createSeeAppointmentButton()
	{
		JButton seeAppointmentButton = new JButton("See Appointment");
		ActionListener listener = new seeAppointmentButtonListener();
		seeAppointmentButton.addActionListener(listener);

		seeAppointmentButtonPanel.add(seeAppointmentButton);
	}
	
	/**
	 * Method to create book appointment button
	 */
	public void createBookAppointmentButton()
	{
		JButton bookAppointmentButton = new JButton("Book Appointment");
		ActionListener listener = new bookAppointmentButtonListener();
		bookAppointmentButton.addActionListener(listener);

		bookAppointmentButtonPanel.add(bookAppointmentButton);
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
