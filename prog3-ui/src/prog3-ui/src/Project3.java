import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Project3 {
	
	//This company string is to help denote which company is being looked at. This is useful as it will let the program know if it needs to switch the array it is using.
	public static String company = "N/A";
	
	
	//This is the prompt that appears whenever a request for information has been made. It's up here so it can easily be called to without taking up to much line space.
	public static void prompt(String company, String otherCompany) {
		System.out.println("Look in your output file for the information requested on "+company+".\nPlease enter another command if you wish.\n"
				+ "Or enter \"Switch to "+otherCompany+"\" to change companies.\n"
				+ "Or enter \"Quit\", \"quit\", or \"q\" to exit chatbot.");
	}
	
	public static void main(String[] args) {
		//This is just asking the user what company they want to ask questions about.
		System.out.println("Please enter the name of the company or he URL link to its 10-K form.");
		Scanner keyboard = new Scanner(System.in);	
		String url = keyboard.nextLine();
		//Depending on their choice, it will set the company variable to their respective choice while also activating main2 with the proper information reflecting their choice.
		if(url.equals("The Coca Cola Company") || url.equals("https://www.sec.gov/ixviewer/ix.html?doc=/Archives/edgar/data/21344/000002134423000011/ko-20221231.htm"))
		{
			company = "The Coca Cola Company";
			main2("The_COCA-COLA_Company-2023.txt", "The Coca Cola Company", "PepsiCo");
		}
		else if(url.equals("PepsiCo") || url.equals("https://www.sec.gov/ixviewer/ix.html?doc=/Archives/edgar/data/0000077476/000007747623000007/pep-20221231.htm"))
		{
			company = "PepsiCo";
			main2("PepsiCo-2023.txt", "PepsiCo", "The Coca Cola Company");
		}	
		else //This will change for a later assignment in which user interface is the focus.
		{
			System.out.println("Unknown command, ending program.");
		}
		
	}
	
	public static void main2(String FILE, String company, String otherCompany){
		System.out.println("You have choosen "+company+". As this chat-bot is still being created, please enter one of the pre-approved prompts.");
		//A while loop runs so that the user can keep choosing new prompts until they decide they want to quit
		while(true)
		{
		Scanner keyboard = new Scanner(System.in);	
		String request = keyboard.nextLine();
		//Each if / else if statement will detect to see if the user has inputed one of the requests that reflect obtaining a certain item in the 10-K.
		//Because the variable company reflects the current company they are looking at, this code works for either choice.
		
		UserSelections userChoice = new UserSelections(request, company);
		
		if(userChoice.getSpecialPrompt().equals("none"))
		{
			readFile(FILE,userChoice.getStartSection(),userChoice.getEndSection(),company);
			prompt(company, otherCompany);
		}
		//These are the special prompts that cannot be done in the UserSelctions class
		else if(userChoice.getSpecialPrompt().equals("Switching to PepsiCo"))
		{
			//This switches the company being looked at and reruns main2 with the new company.
			company = "PepsiCo";
			System.out.println("Switching to PepsiCo");
			main2("PepsiCo-2023.txt", "PepsiCo", "The Coca Cola Company");
			break;
		}
		else if(userChoice.getSpecialPrompt().equals("Switching to The Coca Cola Company"))
		{
			//This switches the company being looked at and reruns main2 with the new company.
			company = "The Coca Cola Company";
			System.out.println("Switching to The Coca Cola Company");
			main2("The_COCA-COLA_Company-2023.txt", "The Coca Cola Company", "PepsiCo");
			//Calling main2 again creates another while loop. If that while loop ends, it means they want the program to end, so there is a break here as well for when it cycles back to this while loop.
			break;
		}
		else if(userChoice.getSpecialPrompt().equals("Ending program."))
		{
			//This ends the while loop.
			System.out.println("Ending program.");
			break;
		}
		else if(userChoice.getSpecialPrompt().equals("Unknown"))
		{
			System.out.println("\""+request+"\" - I do not know this information. Try entering another prompt.");
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

}
