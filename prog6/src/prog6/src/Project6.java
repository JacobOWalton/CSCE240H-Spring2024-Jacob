import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math; 

public class Project6 {
	
	//This company string is to help denote which company is being looked at. This is useful as it will let the program know if it needs to switch the array it is using.
	public static String company = "N/A";
	//The file string is a compliment to the company string, and will let the program know which file to call
	public static String FILE = "N/A";
	public static int userUtterances = 0;
	//Thank you to Jarett for letting me borrow some of his code for this part
	//It is so we can print all the chat logs to a new file at the end
	public static String chatLog = "";
	
	
	//This is the prompt that appears whenever a request for information has been made. It's up here so it can easily be called to without taking up to much line space.
	public static void prompt(String company) {
		System.out.println("Look in your output file for the information requested on "+company+".\nPlease enter another command if you wish.\n"
				+ "Or enter \"Quit\", \"quit\", or \"q\" to exit chatbot.");
	}
	
	public static void main(String[] args){
		
		//Time code from Geeksforgeeks.og
		// Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Create a formatter to format the date and time in 12-hour clock format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM_dd_yyyy_hh_mma");
		//Starts a clock to track how long program as has been open
    	long startTime = System.nanoTime();
    	
    	// Format the date and time using the formatter
        String formattedDateTime = currentDateTime.format(formatter);
        
        // Print the formatted date and time
        String endFileName = ("PepsiCokeData_" + formattedDateTime);
		
		String fileName= "./chat_sessions/chat_statistics.csv";
        File file= new File(fileName);
        
        File logFile = new File("./chat_sessions/"+endFileName+".txt");
        
        //The following code has been borrowed and modified from the stack overflow forms
        //This gives you a 2-dimensional array of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                //This adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //The following code is meant to find out how many lines and columns exist
        int lineNum = 1;
        int columnNum = 1;
        for(List<String> line: lines) {
            columnNum = 1;
            for (String value: line) {
                columnNum++;                
            }
            lineNum++;
        }
        //One less because index is one less than length
        lineNum--;
        //System.out.println(lineNum);
        //System.out.println(columnNum);
        
        //This matrix will be used to grab any and all information needed from the csv file
        //The csv file will be copied over into this matrix
        String[][] matrix = new String[lineNum][columnNum];
        //The following code lets you iterate through the 2-dimensional array and copies it into the matrix
        int lineNo = 1;
        for(List<String> line: lines) {
            int columnNo = 1;
            for (String value: line) {
            	//System.out.println(value);
            	//System.out.println(lineNo-1);
            	//System.out.println(columnNo-1);
            	matrix[lineNo-1][columnNo-1] = value;
                //System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                columnNo++;
            }
            lineNo++;
        }
        
        
        String promptMessage = "Hello and welcome to this chatbot, what would you like to know?";
		System.out.println(promptMessage);
		//This is for the file that will reside in chat_sessions, it is filling the String while also moving it down a line
		//Thanks again to Jarett
		chatLog += "\n" + promptMessage;
		//A while loop runs so that the user can keep choosing new prompts until they decide they want to quit
		while(true)
		{
		//The companyChecker gets input from the user and sees if it mentions a company and acts accordingly
		String request = companyChecker();
		//Takes note of the user utterance 
		userUtterances++;
		chatLog += "\n" + request;
		
		String loadingMessage = "Loading... Please do not check output file until notified.";
		System.out.println(loadingMessage);
		chatLog += "\n" + loadingMessage;
		
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
			chatLog += "\n" + "Ending program.";
			break;
		}
		else if(userChoice.getSpecialPrompt().equals("Companies Supported"))
		{
			///This is for informing what companies are supported
			String companyResponse = "Hello! This chatbot works with PepsiCo and The Coca-Cola Company";
			System.out.println(companyResponse);
			chatLog += "\n" + companyResponse;
		}
		else if(userChoice.getSpecialPrompt().equals("info"))
		{
			//This is for informing what items and parts are supported
			String companyResponse = "Hello! This chatbot supports the following items:\n"+
					"Item 1. Business., Item 1A. Risk Factors., Item 1B. Unresolved Staff Comments., Item 2. Properties., Item 3. Legal Proceedings.,\n" +
					"Item 4. Mine Safety Disclosures., Item 5. Market for Registrant’s Common Equity, Related Stockholder Matters and Issuer Purchases of Equity Securities., Item 7. Management’s Discussion and Analysis of Financial Condition and Results of Operations.,\n"+
					"Item 7A. Quantitative and Qualitative Disclosures About Market Risk., Item 8. Financial Statements and Supplementary Data., Item 9. Changes in and Disagreements with Accountants on Accounting and Financial Disclosure.,\n"+
					"Item 9A. Controls and Procedures., Item 9B. Other Information., Item 9C. Disclosure Regarding Foreign Jurisdictions that Prevent Inspections., Item 10. Directors, Executive Officers and Corporate Governance.,\n"+
					"Item 11. Executive Compensation., Item 12. Security Ownership of Certain Beneficial Owners and Management and Related Stockholder Matters., Item 13. Certain Relationships and Related Transactions, and Director Independence.,\n"+
					"Item 14. Principal Accounting Fees and Services., Item 15. Exhibits and Financial Statement Schedules., Item 16. Form 10-K Summary.";
			System.out.println(companyResponse);
			chatLog += "\n" + companyResponse;
		}
		else if(userChoice.getSpecialPrompt().equals("Unknown"))
		{
			//This is for small talk and incorrect inputs
			String unknownResponse = "Hello! I apologise but I am not sure if you are making small talk or have a request.\n"
					+ "\""+request+"\" - I do not know this information. If you were trying to obtain information, please enter a different prompt.";
			System.out.println(unknownResponse);
			chatLog += "\n" + unknownResponse;
		}
		else if(userChoice.getSpecialPrompt().equals("showchat/summary"))
		{		
			//Sees if the user is looking for summary
			if(request.equalsIgnoreCase("summary") || request.equalsIgnoreCase("-summary") || checker(request,"stats"))
			{		
				//If the user is then the program will call adding elements to add up all the information from each chat session
				String summary = "There are "+(lineNum-1)+" chats to date with the user asking "+addingElements(matrix,lineNum,2)+
						" times and system responded "+addingElements(matrix,lineNum,3)+" times. Total duration is "+
						addingElements(matrix,lineNum,4)+" seconds.";
				System.out.println(summary);
				chatLog += "\n" +  summary;
			}
			//The same is done for if the user is looking for showchat-summary
			else if(checker(request,"showchat-summary "))
			{
				//If the user inputs showchat-summary but does not include an i that is valid (meaning that chat does not exist),
				//then the boolean invalid will go through, and let the user know that said chat does not exist
				boolean invalid = true;
				for(int i=1;i<lineNum;i++)
				{
					if(checker(request,"showchat-summary "+i))
					{
						invalid = false;
						String showchatSummary = "Chat "+i+" has the user asking "+matrix[i][2]+
								" times and system responded "+matrix[i][3]+" times. Total duration is "+
								matrix[i][4]+" seconds.";
						System.out.println(showchatSummary);
						chatLog += "\n" +  showchatSummary;
					}
					
				}
				if(invalid)
				{
					String invalidResponse = "ERROR: There are only "+(lineNum-1)+" chat sessions. Please choose a valid number.";
					System.out.println(invalidResponse);
					chatLog += "\n" +  invalidResponse;
				}
				
			}
			//The same is done for if the user is looking for showchat
			else if(checker(request,"showchat "))
			{
				boolean invalid = true;
				for(int i=1;i<lineNum;i++)
				{
					if(checker(request,"showchat "+i))
					{
						invalid = false;
						String showchat = ("Chat "+i+"'s chat is big, so I put it in the output text file. You can check it for the chat.");
						readFileAlt("./chat_sessions/"+matrix[i][1]+".txt");
						System.out.println(showchat);
						chatLog += "\n" +  showchat;
					}
				}
				if(invalid)
				{
					String errorMessage = ("ERROR: There are only "+(lineNum-1)+" chat sessions. Please choose a valid number.");
					System.out.println(errorMessage);
					chatLog += "\n" +  errorMessage;
				}
			}
			else {
				String errorMessage = ("ERROR: Incorrect format, try again.");
				System.out.println(errorMessage);
				chatLog += "\n" +  errorMessage;
			}
			prompt(company);
		}
		
		}
		fileOutput(logFile, chatLog);
		//The rest of the time code from Geeksforgeeks.og                
        long endTime = System.nanoTime();
        float executionTimeFloat = (endTime - startTime) / 1000000000;
        int executionTime = Math.round(executionTimeFloat);
        
        //Due to the nature of how this program is coded, the system will ALWAYS have one more utterance than the user
        int systemUtterances = userUtterances + 1;
		csvInput(endFileName, userUtterances, systemUtterances, executionTime);
		
		
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
				String errorMessage = "Both companies were listed in your input, please restrict it to just one company.";
				System.out.println(errorMessage);
				chatLog += "\n" + errorMessage;
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
				String errorMessage = "Please narrow your selection down to just one.";
				System.out.println(errorMessage);
				chatLog += "\n" + errorMessage;
			}
			else
			{
				//If company is N/A that means this their first input, so the program informs the user they are mechanically doing nothing (whether its chit chat or incorrect input)
				if(company.equals("N/A"))
				{
					if((checker(input,"quit") || input.equalsIgnoreCase("q")))
					{
						return input;
					}
					else if((checker(input,"showchat") || checker(input,"summary") || checker(input,"stats")))
					{
						return input;
					}
					else if((checker(input,"companies")))
					{
						return input;
					}
					else if((checker(input,"info") || checker(input,"item") || checker(input,"parts")))
					{
						return input;
					}
					else if((checker(input,"everything")))
					{
						String errorMessage = "Hello! I apologise but it seems that you want all information about both companies.\n"
								+ "That is unfortunately out of the scope of this chatbot, please include a company in your question.";
						System.out.println(errorMessage);
						chatLog += "\n" + errorMessage;
					}
					else
					{
						String errorMessage = "Hello! I apologise but I am not sure if you are making small talk or have a request.\n"
								+ "If you have a request, no company was listed in your input, please include a company.";
						System.out.println(errorMessage);
						chatLog += "\n" + errorMessage;
					}
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
					chatLog += "\n" + fileLine;
				}				
			}
			//Item 16 needs to be hard coded in as there is no consistent end point for it
			if(endSection.equals("[End of 10-K Summary]"))
			{
				myPrinter.println("ITEM 16.  FORM 10-K SUMMARY");
				chatLog += "\n" + "ITEM 16.  FORM 10-K SUMMARY";
				myPrinter.println("None.");
				chatLog += "\n" + "ITEM 16.  FORM 10-K SUMMARY";
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
			
			
	public static void csvInput(String name, int userUtterances, int systemUtterances, int duration)
	{
		String filePath = "./chat_sessions/chat_statistics.csv"; 
		
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            reader.close();
            
            //Thank to Cody for letting me reuse the following code, it is much more efficient and compact than mine was
        	File csvFile = new File(filePath);
			PrintWriter pw = new PrintWriter(new FileOutputStream(csvFile,true /* append = true */));
			
			pw.write(String.valueOf(lineCount)+","); //Write the chat no
			pw.write(name+","); //Write the chat file name
			pw.write(userUtterances+","); //Write the chat # user utterance
			pw.write(systemUtterances+","); //Write the chat # computer utterance
			pw.write(duration+"\n"); //Write the chat time taken
			
			pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//Really simple function that just adds up an entire column of the matrix (sans the title)
		//Its for the summary section
		public static int addingElements(String[][] matrix, int lineNum, int column) 
		{
			int total = 0;
			for(int i=1;i<lineNum;i++)
			{
				total += Integer.parseInt(matrix[i][column]);
			}
			return total;
		}
		
	//This readFile is different as its for printing to the csv file and has different code to it
	public static void readFileAlt(String aFile)
	{		
		//Figures out how many lines are in the file.
		int lineCount = lineCount(aFile);
		File myFile = new File("output.txt");
			
		try {
			//Creating both a filewriter and printwriter to edit the new text file.
			FileWriter myWriter = new FileWriter(myFile, false);
			PrintWriter myPrinter = new PrintWriter(myWriter);
			for(int i=0;i<lineCount;i++)
			{
				String fileLine = getWords(i,aFile);
				myPrinter.println(fileLine);
							
			}		
			//DO NOT FORGET TO CLOSE!!!
			myPrinter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	//This prints out the file that will reside in chat_sessions
	//Thanks again to Jarett for lending the idea and a lot of the code for this
	public static void fileOutput(File myFile, String output)
    {
    		try {
    				//Creating both a filewriter and printwriter to edit the new text file.
    				FileWriter myWriter = new FileWriter(myFile, false);
    				PrintWriter myPrinter = new PrintWriter(myWriter);
    				myPrinter.println(output);
    						
    				//DO NOT FORGET TO CLOSE!!!
    				myPrinter.close();
    			}
    			catch(Exception e)
    			{
    				e.printStackTrace();
    			}	
    	
    }

}
