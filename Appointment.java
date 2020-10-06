import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Appointment 
{
	public abstract void setTeacher(String teacher);
	public abstract void setTime(String time);
	public abstract void setDay(String day);
	public abstract void setIDNumber();
	public abstract void readFile() throws FileNotFoundException;
	public abstract void compareSchedule();
	public abstract boolean checkSchedule();
	public abstract void writeFile() throws IOException;
}
