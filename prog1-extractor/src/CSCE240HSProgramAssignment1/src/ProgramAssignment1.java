import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProgramAssignment1 {
	
	
	public static int lineCount = 0;
	public static int wordCount = 0;
	public static int charCount = 0;
	public static int partCount = 0;
	static GenLL<String> fileList = new GenLL<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Please enter the name of the company or he URL link to its 10-K form.");
		Scanner keyboard = new Scanner(System.in);
		
		
		
		String url = keyboard.nextLine();
		if(url.equals("The Coca Cola Company") || url.equals("https://www.sec.gov/ixviewer/ix.html?doc=/Archives/edgar/data/21344/000002134423000060/ko-20230929.htm"))
		{
			main2("The_COCA-COLA_Company-2023.txt");
		}
		else if(url.equals("PepsiCo") || url.equals("https://www.sec.gov/ixviewer/ix.html?doc=/Archives/edgar/data/77476/000007747623000058/pep-20230909.htm"))
		{
			main2("PepsiCo-2023.txt");
		}
		
	
	}
	public static void main2(String FILE){
		
		readFile(FILE);
		System.out.println("Line count is " + lineCount);
		System.out.println("Word count is " + wordCount);
		System.out.println("Character count is " + charCount);
		System.out.println("Part count is " + partCount);
		
		
		
		
		
	}
	
	public static int countParts(String fileLine)
	{
		if (fileLine.toUpperCase().contains("PART I") && partCount != 2 && partCount != 3 && partCount != 4)
			partCount = 1;
		if (fileLine.toUpperCase().contains("PART II") && partCount != 3 && partCount != 4)
			partCount = 2;
		if (fileLine.toUpperCase().contains("PART III") && partCount != 4)
			partCount = 3;
		if (fileLine.toUpperCase().contains("PART IV"))
			partCount = 4;
		return partCount;
	}
	
	public static int countWords(String s){

	    int wordCount = 0;

	    boolean word = false;
	    int endOfLine = s.length() - 1;

	    for (int i = 0; i < s.length(); i++) {
	        // if the char is a letter, word = true.
	        if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
	            word = true;
	            // if char isn't a letter and there have been letters before,
	            // counter goes up.
	        } else if (!Character.isLetter(s.charAt(i)) && word) {
	            wordCount++;
	            word = false;
	            // last word of String; if it doesn't end with a non letter, it
	            // wouldn't count without this.
	        } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
	            wordCount++;
	        }
	    }
	    return wordCount;
	} //code borrowed from user koool on stack overflow forms
	
	public static void readFile(String aFile)
	{
		//System.out.println("I AM IN READFILE");
		//Figures out how many lines are in the file.
		int lineCount = lineCount(aFile);
		File myFile = new File("output.txt");
		//System.out.println("I AM IN READFILE"+lineCount);
		//Checks to see if each line fits the format before adding it to gameList if it does.
		
		try {
			//Creating both a filewriter and printwriter to edit the new text file.
			//The difference between the new file and this one, is the true in FileWriter. It makes it append instead of replace.
			FileWriter myWriter = new FileWriter(myFile, false);
			PrintWriter myPrinter = new PrintWriter(myWriter);
			
			for(int i=0;i<lineCount;i++)
			{
				String fileLine = getWords(i,aFile);
				myPrinter.println(fileLine);
					fileList.add(fileLine);
					charCount += fileLine.length();
					wordCount += countWords(fileLine);
					countParts(fileLine);
			}
			/*
			while(outputList.getCurrent() != null)
			{
				myPrinter.println(outputList.getCurrent());
				outputList.gotoNext();
			}
			*/
			
			//DO NOT FORGET TO CLOSE!!!
			myPrinter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		
		
		
	}
	
	//This code is reused from last assignment. Gets a count of number of lines in text file.
	public static int lineCount(String aFile)
	{
		//System.out.println("I AM IN LINECOUNT");
		//System.out.println(aFile);
		int ret = 0;
		try
		{
			Scanner fileScanner = new Scanner(new File("./"+aFile));
			while(fileScanner.hasNextLine())
			{
				String next = fileScanner.nextLine();			
				ret++;
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//System.out.println(ret);
		lineCount = ret;
		return ret;
	}
	
	//This code is reused from last assignment. It reads the line of a file depending on the number given.
	public static String getWords(int aLine, String aFile)
	{
		//System.out.println("I AM IN GETWORDS");
		String word = "IF YOU SEE THIS SOMETHING IS WRONG";
		try
		{
			Scanner fileScanner = new Scanner(new File("./"+aFile));
			for(int i=0; i<=aLine;i++)
			{
				//Gets the contents of the line of inputed line.
				//I will say there is probably an easier way to do this.
				String next = fileScanner.nextLine();
				
				word = next;				
			}
		}
		catch(IOException e)
		{
			System.out.println(e);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return word;
	}

}
