import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SignUp 
{
	private String firstName;
	private String lastName;
	private String email;
	private String IDNumber;
	
	public SignUp(String firstName, String lastName, String email, String IDNumber)
	{
		
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setIDNumber(IDNumber);
		writeToFile();
	}
	
	/**
	 * Method to set the first name
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * Method to set the last name
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * Method to set the email
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * Method to set the ID Number
	 * @param IDNumber
	 */
	public void setIDNumber(String IDNumber)
	{
		this.IDNumber = IDNumber;
	}

	public void writeToFile()
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter("Students Information.txt", true));
			out.newLine();
			out.write(IDNumber + " " + firstName + " " + lastName + " " + email);
			out.close();
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}
