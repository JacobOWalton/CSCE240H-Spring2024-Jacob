import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project4 {
	
	//This company string is to help denote which company is being looked at. This is useful as it will let the program know if it needs to switch the array it is using.
	public static String company = "N/A";
	//The file string is a compliment to the company string, and will let the program know which file to call
	public static String FILE = "N/A";
	
	
	//This is the prompt that appears whenever a request for information has been made. It's up here so it can easily be called to without taking up to much line space.
	public static void prompt(String company) {
		System.out.println("Look in your output file for the information requested on "+company+".\nPlease enter another command if you wish.\n"
				+ "Or enter \"Quit\", \"quit\", or \"q\" to exit chatbot.");
	}
	
	public static void main(String[] args){
		System.out.println("Hello and welcome to this chatbot, what would you like to know?");
		//A while loop runs so that the user can keep choosing new prompts until they decide they want to quit
		while(true)
		{
		//The companyChecker gets input from the user and sees if it mentions a company and acts accordingly
		String request = companyChecker();
		System.out.println("Loading... Please do not check output file until notified.");
		
		//This reads the users input and company and has UserSelections determine what their choice was
		UserSelections userChoice = new UserSelections(request, company);
		
		if(userChoice.getSpecialPrompt().equals("none"))
		{
			//No choice is for when the input is about getting information
			readFile(FILE,userChoice.getStartSection(),userChoice.getEndSection(),company);
			prompt(company);
		}
		//These are the special prompts that cannot be done in the UserSelctions class
		
		else if(userChoice.getSpecialPrompt().equals("Ending program."))
		{
			//This ends the while loop.
			System.out.println("Ending program.");
			break;
		}
		else if(userChoice.getSpecialPrompt().equals("Unknown"))
		{
			//This is for small talk and incorrect inputs
			System.out.println("Hello! I apologise but I am not sure if you are making small talk or have a request.\n"
					+ "\""+request+"\" - I do not know this information. If you were trying to obtain information, please enter a different prompt.");
		}
		
		}
		
	}
	
	public static String companyChecker()
	{
		//This will loop until the user has a valid input
		while(true)
		{
			//This gets the input from the user
			Scanner keyboard = new Scanner(System.in);	
			String input = keyboard.nextLine();
			//Checker is at bottom of code for information about what it does (TLDR; see's if the second item is found in first item)
			//If both companies are listed in input, the user must do it again with only one
			if((checker(input, "PepsiCo") || checker(input, "Pepsi")) && (checker(input, "Coca-Cola") || checker(input, "Coca Cola") || checker(input, "Coke")))
			{
				System.out.println("Both companies were listed in your imput, please restrict it to just one company.");
			}
			//If the user includes Pepsio then company  and FILE will be set to PepsiCo
			else if(checker(input, "PepsiCo") || checker(input, "Pepsi"))
			{
				company = "PepsiCo";
				FILE = "PepsiCo-2023.txt";
				return input;
			}
			//If the user includes Coke then company  and FILE will be set to The Coca-Cola Company
			else if(checker(input, "Coca-Cola") || checker(input, "Coca Cola") || checker(input, "Coke"))
			{
				company = "The Coca-Cola Company";
				FILE = "The_COCA-COLA_Company-2023.txt";
				return input;
			}
			//This informs the user if they try to get multiple things that they need to limit it to just one
			else if(checker(input, "both"))
			{
				System.out.println("Please narrow your selection down to just one.");
			}
			else
			{
				//If company is N/A that means this their first input, so the program informs the user they are mechanically doing nothing (whether its chit chat or incorrect input)
				if(company.equals("N/A"))
				{
					System.out.println("Hello! I apologise but I am not sure if you are making small talk or have a request.\n"
							+ "If you have a request, no company was listed in your imput, please include a company.");
				}
				//If company is not equal to N/A, then the user has yet to change companies, and as such will infer that the user wants more on the current company
				else
				{
					return input;
				}
			}
		}
	}
	
	public static void readFile(String aFile, String startSection, String endSection, String company)
	{		
		//Figures out how many lines are in the file.
		int lineCount = lineCount(aFile);
		File myFile = new File("output.txt");
			
		try {
			//Creating both a filewriter and printwriter to edit the new text file.
			FileWriter myWriter = new FileWriter(myFile, false);
			PrintWriter myPrinter = new PrintWriter(myWriter);
			//This creates a variable that acts as indicator on when to print.
			boolean doPrint = false;
			//This goes through every line of the companies' 10-K report. And when the section that was requested is reached, doPrint is made true and lines are begun to be copied over to an output file.
			//Once the program reaches the following section, it stops printing.
			for(int i=0;i<lineCount;i++)
			{
				String fileLine = getWords(i,aFile);
				if(fileLine.equals(startSection))
					{
					doPrint = true;
					}
				if(fileLine.equals(endSection))
					{
					doPrint = false;
					}
				
				if(doPrint)
				{
					myPrinter.println(fileLine);
				}				
			}
			//Item 16 needs to be hard coded in as there is no consistent end point for it
			if(endSection.equals("[End of 10-K Summary]"))
			{
				myPrinter.println("ITEM 16.  FORM 10-K SUMMARY");
				myPrinter.println("None.");
			}
			
			//DO NOT FORGET TO CLOSE!!!
			myPrinter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	//This code gets a count of number of lines in text file.
	public static int lineCount(String aFile)
	{
		//System.out.println("I AM IN LINECOUNT");
		//System.out.println(aFile);
		int ret = 0;
		try
		{
			//Finds the file in the src folder
			Scanner fileScanner = new Scanner(new File("./"+aFile));
			//While there is a next line in the file, this while loop will continue to run, upping a counter of how many lines there are
			while(fileScanner.hasNextLine())
			{
				String next = fileScanner.nextLine();			
				ret++;
			}
		}
		//Simple code to catch for errors
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//This returns the number of lines
		return ret;
	}
	
	public static String getWords(int aLine, String aFile)
	{
		//System.out.println("I AM IN GETWORDS");
		String word = "IF YOU SEE THIS SOMETHING IS WRONG";
		try
		{
			//This looks for a file in the src folder
			Scanner fileScanner = new Scanner(new File("./"+aFile));
			//This is why we needed the line count. As this will go through every line in that file and copy its output
			for(int i=0; i<=aLine;i++)
			{
				//Gets the contents of the line of inputed line.
				String next = fileScanner.nextLine();
				
				word = next;				
			}
		}
		//Simple code to catch for errors
		catch(IOException e)
		{
			System.out.println(e);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//This returns the output
		return word;
	}
	
	//This following segment is altered code from W3 schools
	//It sees if itemWithin can be found in the input string
			public static boolean checker(String input, String itemWithin)
			{
				Pattern pattern = Pattern.compile(itemWithin, Pattern.CASE_INSENSITIVE);
			    Matcher matcher = pattern.matcher(input);
			    boolean matchFound = matcher.find();
			    //It returns true if itemWithin is indeed within input, false if not
			    if(matchFound) {
			      return true;
			    } else {
			      return false;
				}
			}

}
