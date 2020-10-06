import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentMath 
{
	private String available;
	private String day;
	private String time;
	private String teacher;
	private String IDNumber;

	//ARRAY LIST OF FILE
	private ArrayList<String> dayList;
	private ArrayList<String> timeList;
	private ArrayList<String> availableList;

	public AppointmentMath(String teacher, String day, String time)
	{

		setIDNumber();
		setTeacher(teacher);
		setDay(day);
		setTime(time);
		try 
		{
			readFile();
			compareSchedule();
			writeFile();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
		
	}

	/**
	 * Method to set the teacher's name according to user's input
	 * @param teacher is the teacher name
	 */
	public void setTeacher(String teacher)
	{
		this.teacher = teacher;
	}

	/**
	 * Method to set the time that user wanted to appoint
	 * @param time is the time that user wanted to see the teacher
	 */
	public void setTime(String time)
	{
		this.time = time;
	}

	/**
	 * Method to set the day that user wanted to appoint
	 * @param day is the day user wanted to appoint
	 */
	public void setDay(String day)
	{
		this.day = day;
	}

	/**
	 * Method to check change the set the ID number
	 * @param obj
	 */
	public void setIDNumber()
	{
		SignIn ID = new SignIn();
		IDNumber = ID.getIDNumber();
	}

	/**
	 * Method read file to change the availability of teacher during the time
	 * @throws FileNotFoundException
	 */
	public void readFile() throws FileNotFoundException
	{
		File CSAppointment = new File(teacher + " Math Appointment.txt");
		Scanner inCSAppoint = new Scanner(CSAppointment);

		dayList = new ArrayList<String>();
		timeList = new ArrayList<String>();
		availableList = new ArrayList<String>();

		while (inCSAppoint.hasNext())
		{			
			availableList.add(inCSAppoint.next().trim()); //ada masalah waktu di ambil first available
			dayList.add(inCSAppoint.next().trim());
			timeList.add(inCSAppoint.nextLine().trim());
		}
	}

	/**
	 * Method to compare the appointment schedule if it's available
	 */
	public void compareSchedule()
	{
		for (int i=0; i<dayList.size(); i++)
			if (dayList.get(i).equals(day) && timeList.get(i).equals(time))
				if (availableList.get(i).equals("available"))
					availableList.set(i, "booked");
	}
	
	/**
	 * Method to check schedule if it's available
	 * @return true if it's available
	 */
	public boolean checkSchedule()
	{	
		for (int i=0; i<dayList.size(); i++)
		{
			if (dayList.get(i).equals(day) && timeList.get(i).equals(time) && availableList.get(i+1).equals("available"))//ini salah yg i plus satu
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to write the new file after appointment has been made
	 * @throws IOException
	 */
	public void writeFile() throws IOException
	{
		PrintWriter out = new PrintWriter(new File(teacher + " Math Appointment.txt"));
		
		for (int i=0; i<dayList.size(); i++)
		{
			out.print(availableList.get(i) + " ");
			out.print(dayList.get(i) + " ");
			out.print(timeList.get(i));
			out.println();
		}
		out.close();
	}
}
