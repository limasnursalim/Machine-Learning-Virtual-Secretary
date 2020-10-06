import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignIn 
{
	private int IDNumber;
	
	public SignIn(String IDNumber)
	{
		setIDNumber(IDNumber);
	}
	
	/**
	 * Empty constructor
	 */
	public SignIn() {}
	
	/**
	 * Method to set the ID
	 * @param IDNumber
	 */
	public void setIDNumber(String IDNumber)
	{
		
		this.IDNumber = Integer.parseInt(IDNumber);
	}
	
	/**
	 * Method to get the ID
	 * @return
	 */
	public String getIDNumber()
	{
		String ID = Integer.toString(IDNumber);
		return ID;
	}
	
	/**
	 * Method to check if the user's ID match with database
	 * @return true if there exist user's ID
	 * @throws FileNotFoundException
	 */
	public boolean readFile() throws FileNotFoundException
	{
		File studentsInfo = new File("Students Information.txt");
		Scanner inStudentsInfo = new Scanner(studentsInfo);
		
		int tempID = 0;
		boolean flag = false;
		
		while (inStudentsInfo.hasNext())
		{
			tempID = inStudentsInfo.nextInt();
			
			if (this.IDNumber == tempID)
			{
				flag = true;
				break;
			}
			else
				inStudentsInfo.nextLine();
		}
		
		return flag;
	}
}
