import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class project2 {
	
	//This company string is to help denote which company is being looked at. This is useful as it will let the program know if it needs to switch the array it is using.
	public static String company = "N/A";
	//The way this program is set up, the user can choose which item they want to look at. This will be done by extracting the code starting from where the file says the title of the item the user is looking for, up until it reaches the title of the item that would come next.
	//While it is labeled as "sections", this is the notation used for Coca Cola. I did not change the name in case either I made a mistake or someone wanted to use a different company, allowing for easier understanding and editability.
	static String[] sections = {"ITEM 1.  BUSINESS","ITEM 1A. RISK FACTORS","ITEM 1B.  UNRESOLVED STAFF COMMENTS","ITEM 2.  PROPERTIES",
			"ITEM 3.  LEGAL PROCEEDINGS","ITEM 4.  MINE SAFETY DISCLOSURES","ITEM 5.  MARKET FOR REGISTRANT’S COMMON EQUITY, RELATED STOCKHOLDER MATTERS AND ISSUER PURCHASES OF EQUITY SECURITIES",
			"ITEM 7.  MANAGEMENT’S DISCUSSION AND ANALYSIS OF FINANCIAL CONDITION AND RESULTS OF OPERATIONS","ITEM 7A.  QUANTITATIVE AND QUALITATIVE DISCLOSURES ABOUT MARKET RISK",
			"ITEM 8.  FINANCIAL STATEMENTS AND SUPPLEMENTARY DATA","ITEM 9.  CHANGES IN AND DISAGREEMENTS WITH ACCOUNTANTS ON ACCOUNTING AND FINANCIAL DISCLOSURE",
			"ITEM 9A.  CONTROLS AND PROCEDURES","ITEM 9B.  OTHER INFORMATION","ITEM 9C.  DISCLOSURE REGARDING FOREIGN JURISDICTIONS THAT PREVENT INSPECTIONS","ITEM 10.  DIRECTORS, EXECUTIVE OFFICERS AND CORPORATE GOVERNANCE",
			"ITEM 11.  EXECUTIVE COMPENSATION","ITEM 12.  SECURITY OWNERSHIP OF CERTAIN BENEFICIAL OWNERS AND MANAGEMENT AND RELATED STOCKHOLDER MATTERS",
			"ITEM 13.  CERTAIN RELATIONSHIPS AND RELATED TRANSACTIONS, AND DIRECTOR INDEPENDENCE","ITEM 14.  PRINCIPAL ACCOUNTANT FEES AND SERVICES","ITEM 15.  EXHIBITS AND FINANCIAL STATEMENT SCHEDULES",
			"ITEM 16.  FORM 10-K SUMMARY"};
	//Since each 10-K report has a different way of being written, the syntax between the two is not identical. As such, a second array is created with it's versions of the item titles. This also means new companies can easily be added.
	static String[] PepsiCoSections = {"Item 1. Business.","Item 1A. Risk Factors.","Item 1B. Unresolved Staff Comments.","Item 2. Properties.","Item 3. Legal Proceedings.",
			"Item 4. Mine Safety Disclosures.","Item 5. Market for Registrant’s Common Equity, Related Stockholder Matters and Issuer Purchases of Equity Securities.","Item 7. Management’s Discussion and Analysis of Financial Condition and Results of Operations.",
			"Item 7A. Quantitative and Qualitative Disclosures About Market Risk.","Item 8. Financial Statements and Supplementary Data.","Item 9. Changes in and Disagreements with Accountants on Accounting and Financial Disclosure.",
			"Item 9A. Controls and Procedures.","Item 9B. Other Information.","Item 9C. Disclosure Regarding Foreign Jurisdictions that Prevent Inspections.","Item 10. Directors, Executive Officers and Corporate Governance.",
			"Item 11. Executive Compensation.","Item 12. Security Ownership of Certain Beneficial Owners and Management and Related Stockholder Matters.","Item 13. Certain Relationships and Related Transactions, and Director Independence.",
			"Item 14. Principal Accounting Fees and Services.","Item 15. Exhibits and Financial Statement Schedules.","Item 16. Form 10-K Summary."};	
	
	//This is the prompt that appears whenever a request for information has been made. It's up here so it can easily be called to without taking up to much line space.
	public static void prompt(String company, String otherCompany) {
		System.out.println("Look in your output file for the information requested on "+company+".\nPlease enter another command if you wish.\n"
				+ "Or enter \"Switch to "+otherCompany+"\" to change companies.\n"
				+ "Or enter \"End Program\" to exit chatbot.");
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
		if(request.equals("Show me "+company+"'s Business"))
		{
			//This sends the information about the user's question to readFile
			readFile(FILE,sections[0],sections[1],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Risk Factors"))
		{
			readFile(FILE,sections[1],sections[2],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Unresolved Staff Comments"))
		{
			readFile(FILE,sections[2],sections[3],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Properties"))
		{
			readFile(FILE,sections[3],sections[4],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Legal Proceedings"))
		{
			readFile(FILE,sections[4],sections[5],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Mine Safety Disclosures"))
		{
			readFile(FILE,sections[5],sections[6],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Market for Registrant’s Common Equity, Related Stockholder Matters and Issuer Purchases of Equity Securities"))
		{
			readFile(FILE,sections[6],sections[7],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Management’s Discussion and Analysis of Financial Condition and Results of Operations"))
		{
			readFile(FILE,sections[7],sections[8],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Quantitative and Qualitative Disclosures About Market Risk"))
		{
			readFile(FILE,sections[8],sections[9],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Financial Statements and Supplementary Data"))
		{
			readFile(FILE,sections[9],sections[10],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Changes in and Disagreements with Accountants on Accounting and Financial Disclosure"))
		{
			readFile(FILE,sections[10],sections[11],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Controls and Procedures"))
		{
			readFile(FILE,sections[11],sections[12],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me some of the excess information about "+company))
		{
			readFile(FILE,sections[12],sections[13],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Disclosure Regarding Foreign Jurisdictions that Prevent Inspections"))
		{
			readFile(FILE,sections[13],sections[14],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Directors, Executive Officers and Corporate Governance"))
		{
			readFile(FILE,sections[14],sections[15],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Executive Compensation"))
		{
			readFile(FILE,sections[15],sections[16],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Security Ownership of Certain Beneficial Owners and Management and Related Stockholder Matters"))
		{
			readFile(FILE,sections[16],sections[17],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Certain Relationships and Related Transactions, and Director Independence"))
		{
			readFile(FILE,sections[17],sections[18],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Principal Accounting Fees and Services"))
		{
			readFile(FILE,sections[18],sections[19],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Exhibits and Financial Statement Schedules"))
		{
			readFile(FILE,sections[19],sections[20],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me "+company+"'s Form 10-K Summary"))
		{
			//Form 10-K Summary is a little different as it needs to be hard-coded in as there is no item that actually comes after it.
			readFile(FILE,sections[20],sections[20],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Show me everything about "+company))
		{
			readFile(FILE,sections[1],sections[20],company);
			//Since 10-K Summary needs to be hard coded in, it needs to be a separate function call.
			readFile(FILE,sections[20],sections[20],company);
			prompt(company, otherCompany);
		}
		else if(request.equals("Switch to PepsiCo"))
		{
			//This switches the company being looked at and reruns main2 with the new company.
			company = "PepsiCo";
			System.out.println("Switching to PepsiCo");
			main2("PepsiCo-2023.txt", "PepsiCo", "The Coca Cola Company");
			break;
		}
		else if(request.equals("Switch to The Coca Cola Company"))
		{
			//This switches the company being looked at and reruns main2 with the new company.
			company = "The Coca Cola Company";
			System.out.println("Switching to The Coca Cola Company");
			main2("The_COCA-COLA_Company-2023.txt", "The Coca Cola Company", "PepsiCo");
			//Calling main2 again creates another while loop. If that while loop ends, it means they want the program to end, so there is a break here as well for when it cycles back to this while loop.
			break;
		}
		else if(request.equals("End Program"))
		{
			//This ends the while loop.
			System.out.println("Ending program.");
			break;		
		}
		else
		{
			System.out.println("I apologise, but that command is unknown, please choose one of our currently approved commands.");
		}
		}
		
	}
	
	public static void readFile(String aFile, String startSection, String endSection, String company)
	{		
		//Figures out how many lines are in the file.
		int lineCount = lineCount(aFile);
		File myFile = new File("output.txt");
		//This checks to see if the company is not Coca-Cola (which is consider the "base" company in this program).
		//If it is PepsiCo (or whatever other company), it goes through the array to find the index of the sections submitted and switch them with PepsiCo's version (this is why both arrays need to be equal).
		if(company.equals("PepsiCo"))
		{
			for(int i=0;i<sections.length;i++)
			{
				if(sections[i].equals(startSection))
				{
					startSection = PepsiCoSections[i];
				}
			}
			for(int i=0;i<sections.length;i++)
			{
				if(sections[i].equals(endSection))
				{
					endSection = PepsiCoSections[i];
				}
			}
		}
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
			//Item 16 needs to be hard
			if(startSection.equals("ITEM 16.  FORM 10-K SUMMARY") || startSection.equals("Item 16. Form 10-K Summary."))
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
