import java.util.*;
import javax.swing.*;

public class Demo 
{
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	
	public static void main(String[] args)
	{
		JFrame frame = new WindowOneComponent();
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		frame.setTitle("Virtual Secretary");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
