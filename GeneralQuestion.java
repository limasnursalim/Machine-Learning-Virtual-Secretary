import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class GeneralQuestion 
{		
	public abstract void readAndStoreFile() throws FileNotFoundException;
	
	public abstract void setQuestion(String question);
	public abstract void convertQuestionToLowerCase();
	public abstract void removeUnnecessaryCharacterFromQuestion();
	public abstract void removeUselessWordsFromUserQuestion();
	public abstract ArrayList<String> checkKeywordsWithQuestionFile();
	public abstract String findGuessAnswer(String pickedGuessQuestion, String userOriginalQuestion);
	public abstract void appendQuestionMachineLearning(String userQuestion, int guessAnswerIndex) throws IOException;
	
	public abstract boolean findExactQuestion();
	public abstract String findExactAnswer();
}
