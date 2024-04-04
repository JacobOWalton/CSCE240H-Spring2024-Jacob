import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Project5 {
		
	static String test = "chat_statistics.csv";
		
	public static void main(String[] args){


		String fileName= "./chat_sessions/chat_statistics.csv";
        File file= new File(fileName);
        
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
        columnNum--;
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
            	matrix[lineNo-1][columnNo-1] = value;
                //System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                columnNo++;
            }
            lineNo++;
        }
    
		//Gets user input
		System.out.println("Enter one the pre-approved arguments.");
		Scanner keyboard = new Scanner(System.in);	
		String input = keyboard.nextLine();
		
		//Sees if the user is looking for summary
		if(input.equalsIgnoreCase("summary"))
		{		
			//If the user is then the program will call adding elements to add up all the information from each chat session
			System.out.println("There are "+(lineNum-1)+" chats to date with the user asking "+addingElements(matrix,lineNum,2)+
					" times and system responded "+addingElements(matrix,lineNum,3)+" times. Total duration is "+
					addingElements(matrix,lineNum,4)+" seconds.");
		}
		//The same is done for if the user is looking for showchat-summary
		else if(checker(input,"showchat-summary "))
		{
			//If the user inputs showchat-summary but does not include an i that is valid (meaning that chat does not exist),
			//then the boolean invalid will go through, and let the user know that said chat does not exist
			boolean invalid = true;
			for(int i=1;i<lineNum;i++)
			{
				if(input.equalsIgnoreCase("showchat-summary "+i))
				{
					invalid = false;
					System.out.println("Chat "+i+" has the user asking "+matrix[i][2]+
							" times and system responded "+matrix[i][3]+" times. Total duration is "+
							matrix[i][4]+" seconds.");
				}
			}
			if(invalid)
			{
				System.out.println("ERROR: There are only "+(lineNum-1)+" chat sessions. Please choose a valid number.");
			}
			
		}
		//The same is done for if the user is looking for showchat
		else if(checker(input,"showchat "))
		{
			boolean invalid = true;
			for(int i=1;i<lineNum;i++)
			{
				if(input.equalsIgnoreCase("showchat "+i))
				{
					invalid = false;
					System.out.println("Chat "+i+"'s chat is big, so I put it in the output text file. You can check it for the chat.");
					readFile("./chat_sessions/"+matrix[i][1]+".txt");
				}
			}
			if(invalid)
			{
				System.out.println("ERROR: There are only "+(lineNum-1)+" chat sessions. Please choose a valid number.");
			}
		}
		System.out.println("Program complete.");

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
	
	//This following segment was based on code from W3 schools
	public static boolean checker(String input, String itemWithin)
	{
		Pattern pattern = Pattern.compile(itemWithin, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(input);
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	      return true;
	    } else {
	      return false;
		}
	}
	
	public static void readFile(String aFile)
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