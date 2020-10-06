import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GeneralQuestionCS extends GeneralQuestion
{
	//USER QUESTION INTO KEYWORDS
	private ArrayList<String> questionUserKeywords;

	//GENERAL QUESTION STRING
	private String question;

	//GENERAL QUESTION ARRAY
	private String[] questionInArray;

	//GENERAL QUESTION INDEX
	private int answerIndex;

	//GUESS QUESTION ANSWER INDEX
	private int guessQuestionAnswerIndex;

	//USELESS WORD KEYWORD
	private ArrayList<String> uselessWord;

	//STORE FILE ARRAYLIST
	private ArrayList<Integer> questionFileIndex;
	private ArrayList<String> questionFileSentence;
	private ArrayList<Integer> answerFileIndex;
	private ArrayList<String> answerFileSentence;
	private ArrayList<Integer> genericQuestionFileIndex;
	private ArrayList<String> genericQuestionFileSentence;

	public GeneralQuestionCS(String question)
	{
		//USER QUESTION INTO KEYWORDS
		questionUserKeywords = new ArrayList<String>();

		try 
		{
			readAndStoreFile();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		setQuestion(question);
		convertQuestionToLowerCase();
		removeUnnecessaryCharacterFromQuestion();
	}

	/**
	 * Method to read and store the file into arrayList
	 * @throws FileNotFoundException 
	 */
	@Override
	public void readAndStoreFile() throws FileNotFoundException 
	{
		uselessWord = new ArrayList<String>();
		questionFileIndex = new ArrayList<Integer>();
		questionFileSentence = new ArrayList<String>();
		answerFileIndex = new ArrayList<Integer>();
		answerFileSentence = new ArrayList<String>();
		genericQuestionFileIndex = new ArrayList<Integer>();
		genericQuestionFileSentence = new ArrayList<String>();

		File uselessWords = new File("UselessWords.txt");
		Scanner inUselessFile = new Scanner(uselessWords);

		//store useless word into array list to compare it later
		while (inUselessFile.hasNext())
			uselessWord.add(inUselessFile.next());

		File questionFile = new File("CSQuestionFile.txt");
		Scanner inQuestionFile = new Scanner(questionFile);

		//store question file into array list
		while (inQuestionFile.hasNext())
		{
			questionFileIndex.add(inQuestionFile.nextInt());
			questionFileSentence.add(inQuestionFile.nextLine().trim());
		}

		File answerFile = new File("CSAnswerFile.txt");
		Scanner inAnswerFile = new Scanner(answerFile);

		while (inAnswerFile.hasNext())
		{
			answerFileIndex.add(inAnswerFile.nextInt());
			answerFileSentence.add(inAnswerFile.nextLine().trim());
		}

		File genericQuestion = new File("GenericQuestionFile.txt");
		Scanner inGenericQuesFile = new Scanner(genericQuestion);

		while (inGenericQuesFile.hasNext())
		{
			genericQuestionFileIndex.add(inGenericQuesFile.nextInt());
			genericQuestionFileSentence.add(inGenericQuesFile.nextLine().trim());
		}

	}

	/**
	 * Method to set user's question
	 * @param question
	 */
	public void setQuestion(String question)
	{
		this.question = question;
	}

	/**
	 * Method to convert the question to all lower case
	 */
	@Override
	public void convertQuestionToLowerCase() 
	{
		question = question.toLowerCase();

	}

	/**
	 * Method to remove all unnecessary words from question such as comma, question mark, etc
	 */
	@Override
	public void removeUnnecessaryCharacterFromQuestion() 
	{
		questionInArray = question.split("[, ?.@/]+");
	}

	/**
	 * Method to remove useless words from the question such as what, why, if, the, etc.
	 */
	@Override
	public void removeUselessWordsFromUserQuestion() 
	{
		for (int i=0; i<questionInArray.length; i++)
			for (int j=0; j<uselessWord.size(); j++)
				if(questionInArray[i].equals(uselessWord.get(j)))
					questionInArray[i] = "-1";

		for (int i=0; i<questionInArray.length; i++)
			if (!questionInArray[i].equals("-1"))
				questionUserKeywords.add(questionInArray[i]);
	}

	/**
	 * Method to check the keywords (parsed final question) with the question file keywords
	 */
	@Override
	public ArrayList<String> checkKeywordsWithQuestionFile() 
	{
		int occurrenceCounter[] = new int[questionFileIndex.size()];

		//find the number of occurrence of the keywords on the question file
		for (int i=0; i<questionFileIndex.size(); i++)
		{
			int counter = 0;
			for (int j=0; j<questionUserKeywords.size(); j++)
				if (questionFileSentence.get(i).contains(questionUserKeywords.get(j)))
					counter++;
			occurrenceCounter[i] = counter;
		}

		ArrayList<Integer> occurrenceCounterSorted = new ArrayList<Integer>();

		//store the position element to an array
		int max = occurrenceCounter[0]; //largest is the first index
		for (int i=1; i<occurrenceCounter.length; i++)
		{
			if (occurrenceCounter[i] >= max) 
			{
				max = occurrenceCounter[i]; //find next largest
				int position = i; //position of occurrence in the array
				occurrenceCounterSorted.add(position); //add position element of occurrence to array
			}
		}

		//sort the position element in descending order
		Collections.sort(occurrenceCounterSorted, Collections.reverseOrder());

		Random rand = new Random();
		ArrayList<String> guessQuestion = new ArrayList<String>();

		//print possible question
		int sizeCounter = 0;
		for (int i=0; i<4; i++)
		{
			if (sizeCounter != occurrenceCounterSorted.size())
			{
				guessQuestion.add(questionFileSentence.get(occurrenceCounterSorted.get(i)));
				sizeCounter++;
			}
			//print generic question if question file sentence has reach the end
			else if (sizeCounter == occurrenceCounterSorted.size())
			{	
				int random = rand.nextInt(genericQuestionFileIndex.size());
				guessQuestion.add(genericQuestionFileSentence.get(random));
			}
		}

		return guessQuestion;
	}

	/**
	 * Method to find the guess question answer
	 */
	public String findGuessAnswer(String pickedGuessQuestion, String userOriginalQuestion)
	{
		//QUESTION FILE
		for (int i=0; i<questionFileIndex.size(); i++)
			if (pickedGuessQuestion.equals(questionFileSentence.get(i)))
				guessQuestionAnswerIndex = questionFileIndex.get(i);

		//GENERIC QUESTION FILE
		for (int i=0; i<genericQuestionFileIndex.size(); i++)
			if (pickedGuessQuestion.equals(genericQuestionFileSentence.get(i)))
				guessQuestionAnswerIndex = genericQuestionFileIndex.get(i);

		System.out.println("ORIGINAL QUESTION: " + userOriginalQuestion);
		System.out.println("GUESS QUESTION INDEX: " + guessQuestionAnswerIndex);
		
		try 
		{
			appendQuestionMachineLearning(userOriginalQuestion, guessQuestionAnswerIndex);
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}

		//SET GENERIC QUESTION FILE
		for (int i=0; i<genericQuestionFileIndex.size(); i++)
			if (genericQuestionFileIndex.get(i) == guessQuestionAnswerIndex)
				return genericQuestionFileSentence.get(i);

		//SET QUESTION ANSWER FILE
		for (int i=0; i<answerFileIndex.size(); i++)
			if (answerFileIndex.get(i) == guessQuestionAnswerIndex)
				return answerFileSentence.get(i);

		return ("Answer Not Found");
	}

	public void appendQuestionMachineLearning(String userQuestion, int guessAnswerIndex) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter("CSQuestionFile.txt", true));
		out.newLine();
		String index = Integer.toString(guessAnswerIndex); //convert int to string since BufferedWriter only works with string
		out.write(index);
		out.write(" " + userQuestion);
		out.close();
	}

	/**
	 * Method to check if the inputed question is the exact same match as in questions list file
	 * @return true if it's the same, false if it's not the same
	 * @throws FileNotFoundException
	 */
	@Override
	public boolean findExactQuestion()
	{
		boolean flag = false;

		for (int i=0; i<questionFileIndex.size(); i++)
			if (question.equals(questionFileSentence.get(i)))
			{
				answerIndex = questionFileIndex.get(i);
				flag = true;
			}
		return flag;
	}

	/**
	 * Method to find the exact answer based on the index of question
	 * @param in is the Scanner
	 * @throws FileNotFoundException 
	 */
	@Override
	public String findExactAnswer() 
	{
		for (int i=0; i<answerFileIndex.size(); i++)
			if (answerFileIndex.get(i) == answerIndex)
				return answerFileSentence.get(i);

		return ("Answer Not Found!");
	}
}
