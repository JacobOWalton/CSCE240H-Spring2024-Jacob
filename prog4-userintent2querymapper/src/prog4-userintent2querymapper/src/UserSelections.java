import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Jacob Walton
 */
public class UserSelections{
	
	//UserSelections.java is intended to keep the process of determining which part of the form that needs to be looked at SEPERATE from the rest of the code
	//While this class cannot be picked up and dropped into another program without any edits to the core class, it helps keep things neat and does make the process
	//of reusing it a lot easier.
	
	//This class takes in the user input, sees what items or parts it aligns to and sends that information back to the core class to be processed through fileWriter
	
	//The way this program is set up, the user can choose which item they want to look at. This will be done by extracting the code starting from where the file says the title of the item the user is looking for, up until it reaches the title of the item that would come next.
		//While it is labeled as "sections", this is the notation used for Coca Cola. I did not change the name in case either I made a mistake or someone wanted to use a different company, allowing for easier understanding and editability.
		static String[] sections = {"ITEM 1.  BUSINESS","ITEM 1A. RISK FACTORS","ITEM 1B.  UNRESOLVED STAFF COMMENTS","ITEM 2.  PROPERTIES",
				"ITEM 3.  LEGAL PROCEEDINGS","ITEM 4.  MINE SAFETY DISCLOSURES","ITEM 5.  MARKET FOR REGISTRANT’S COMMON EQUITY, RELATED STOCKHOLDER MATTERS AND ISSUER PURCHASES OF EQUITY SECURITIES",
				"ITEM 7.  MANAGEMENT’S DISCUSSION AND ANALYSIS OF FINANCIAL CONDITION AND RESULTS OF OPERATIONS","ITEM 7A.  QUANTITATIVE AND QUALITATIVE DISCLOSURES ABOUT MARKET RISK",
				"ITEM 8.  FINANCIAL STATEMENTS AND SUPPLEMENTARY DATA","ITEM 9.  CHANGES IN AND DISAGREEMENTS WITH ACCOUNTANTS ON ACCOUNTING AND FINANCIAL DISCLOSURE",
				"ITEM 9A.  CONTROLS AND PROCEDURES","ITEM 9B.  OTHER INFORMATION","ITEM 9C.  DISCLOSURE REGARDING FOREIGN JURISDICTIONS THAT PREVENT INSPECTIONS","ITEM 10.  DIRECTORS, EXECUTIVE OFFICERS AND CORPORATE GOVERNANCE",
				"ITEM 11.  EXECUTIVE COMPENSATION","ITEM 12.  SECURITY OWNERSHIP OF CERTAIN BENEFICIAL OWNERS AND MANAGEMENT AND RELATED STOCKHOLDER MATTERS",
				"ITEM 13.  CERTAIN RELATIONSHIPS AND RELATED TRANSACTIONS, AND DIRECTOR INDEPENDENCE","ITEM 14.  PRINCIPAL ACCOUNTANT FEES AND SERVICES","ITEM 15.  EXHIBITS AND FINANCIAL STATEMENT SCHEDULES",
				"ITEM 16.  FORM 10-K SUMMARY",""
						+ ""};
		//The last element is a bit odd, but since there's not exactly another item after item 16, this does the job well.
		//Since each 10-K report has a different way of being written, the syntax between the two is not identical. As such, a second array is created with it's versions of the item titles. This also means new companies can easily be added.
		static String[] PepsiCoSections = {"Item 1. Business.","Item 1A. Risk Factors.","Item 1B. Unresolved Staff Comments.","Item 2. Properties.","Item 3. Legal Proceedings.",
				"Item 4. Mine Safety Disclosures.","Item 5. Market for Registrant’s Common Equity, Related Stockholder Matters and Issuer Purchases of Equity Securities.","Item 7. Management’s Discussion and Analysis of Financial Condition and Results of Operations.",
				"Item 7A. Quantitative and Qualitative Disclosures About Market Risk.","Item 8. Financial Statements and Supplementary Data.","Item 9. Changes in and Disagreements with Accountants on Accounting and Financial Disclosure.",
				"Item 9A. Controls and Procedures.","Item 9B. Other Information.","Item 9C. Disclosure Regarding Foreign Jurisdictions that Prevent Inspections.","Item 10. Directors, Executive Officers and Corporate Governance.",
				"Item 11. Executive Compensation.","Item 12. Security Ownership of Certain Beneficial Owners and Management and Related Stockholder Matters.","Item 13. Certain Relationships and Related Transactions, and Director Independence.",
				"Item 14. Principal Accounting Fees and Services.","Item 15. Exhibits and Financial Statement Schedules.","Item 16. Form 10-K Summary.",""
						+ ""};
		//The last element is a bit odd, but since there's not exactly another item after item 16, this does the job well.
	
		//startSection is used to denote the title of the item the user is looking at,
		public String startSection;
		//endSection is used to denote the title of the item AFTER the information is looking at. It acts as "finish line" for the fileWriter.
		public String endSection;
		//Not every user input is about getting information from the file, like with ending the program or switching companies. A lot of these activities
		//cannot be done while in another class. So, the class returns the information that a special prompt was made and runs it in the core class.
		//That's why the core class has an if-else function, to run these special prompts.
		public String specialPrompt;

				
		public UserSelections()
		{
			this.startSection = "none";			
			this.endSection = "none";
			this.specialPrompt = "none";
		}
		
		public void inputDeterminationCompany(String input)
		{
			if(input.toLowerCase().equals("PepsiCo"))
			{
				
			}
		}
		
		public void inputDeterminationRequest(String input)
		{
			
		}
		
		public UserSelections(String input, String company)
		{
			//Whenever a UserSelctions class is created, the two things that need to be known is the input and current company.
			//Each intent has one or multiple key words. The program checks to see if those unique key words are found within the user's input
			//Some intents have multiple options for their key words, while some have it that you need a combination of key words as
			//the intent may be similar to another intent, such "risk" and "factor" both being required for the program to output the risk factor.
			//The code is flexible though, so the order and if there is extra punctuation is not a concern.
			if((checker(input,"buisness")))
			{
				//If the user input DOES match a prompt, it denotes what the startSection, endSection, and specialPrompt (if there is one) are.
				this.setStartSection(sections[0]);			
				this.setEndSection(sections[1]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"risk") && checker(input, "factor")))
			{
				this.setStartSection(sections[1]);			
				this.setEndSection(sections[2]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"staff") || checker(input, "comments")))
			{
				this.setStartSection(sections[2]);			
				this.setEndSection(sections[3]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"properties")))
			{
				this.setStartSection(sections[3]);			
				this.setEndSection(sections[4]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"legal") && checker(input, "proceedings")))
			{
				this.setStartSection(sections[4]);			
				this.setEndSection(sections[5]);
				this.setSpecialPrompt("none");
			}
			else if(((checker(input,"mine") || checker(input,"disclosures")) && checker(input,"safety")))
			{
				this.setStartSection(sections[5]);			
				this.setEndSection(sections[6]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"common equity") || checker(input,"equity secruities")))
			{
				this.setStartSection(sections[6]);			
				this.setEndSection(sections[7]);
				this.setSpecialPrompt("none");
			}
			else if((((checker(input,"financial") && checker(input, "condition"))) || (checker(input,"results") && checker(input,"operation"))))
			{
				this.setStartSection(sections[7]);			
				this.setEndSection(sections[8]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"risk") && checker(input, "market")))
			{
				this.setStartSection(sections[8]);			
				this.setEndSection(sections[9]);
				this.setSpecialPrompt("none");
			}
			else if((((checker(input,"financial") || checker(input,"finance")) && checker(input, "statement")) || (checker(input,"supplementary") && checker(input,"data"))))
			{
				this.setStartSection(sections[9]);			
				this.setEndSection(sections[10]);
				this.setSpecialPrompt("none");
			}
			else if( ((checker(input,"financ") && checker(input,"disclosure")) || checker(input,"change") || checker(input, "disagreement")) && (checker(input,"account") || checker(input, "financ")) )
			{
				this.setStartSection(sections[10]);			
				this.setEndSection(sections[11]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"control") || checker(input, "procedure")))
			{
				this.setStartSection(sections[11]);			
				this.setEndSection(sections[12]);
				this.setSpecialPrompt("none");
			}
			else if(((checker(input,"excess") || checker(input,"extra") || checker(input,"other")) && checker(input, "information")))
			{
				this.setStartSection(sections[12]);			
				this.setEndSection(sections[13]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"jurisdiction") && checker(input, "inspection")))
			{
				this.setStartSection(sections[13]);			
				this.setEndSection(sections[14]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"director") || checker(input, "executive officer") || checker(input, "corporate governance")))
			{
				this.setStartSection(sections[14]);			
				this.setEndSection(sections[15]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"executive") && checker(input, "compensation")))
			{
				this.setStartSection(sections[15]);			
				this.setEndSection(sections[16]);
				this.setSpecialPrompt("none");
			}
			else if( (checker(input,"security") && checker(input, "ownership")) || (checker(input,"beneficial") && (checker(input, "owners") || checker(input,"management"))) )
			{
				this.setStartSection(sections[16]);			
				this.setEndSection(sections[17]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"relationship") && checker(input, "transaction") || (checker(input,"director") && checker(input,"independence"))))
			{
				this.setStartSection(sections[17]);			
				this.setEndSection(sections[18]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"accounting") || checker(input, "fee") || checker(input, "service")))
			{
				this.setStartSection(sections[18]);			
				this.setEndSection(sections[19]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"exhibit") || ((checker(input, "finac") || checker(input, "statement")) && checker(input,"schedule"))))
			{
				this.setStartSection(sections[19]);			
				this.setEndSection(sections[20]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"10-K") && checker(input,"summary")))
			{
				//Form 10-K Summary is a little different as it needs to be hard-coded in as there is no item that actually comes after it.
				this.setStartSection(sections[20]);			
				this.setEndSection(sections[21]);
				this.setSpecialPrompt("none");
			}
			//These next four are in regards to Parts 1 - 4 in the 10-K form, they are found by grouping the items together
			else if((checker(input,"operation") || checker(input,"disclosure")))
			{
				this.setStartSection(sections[6]);			
				this.setEndSection(sections[14]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"structure")))
			{
				this.setStartSection(sections[14]);			
				this.setEndSection(sections[19]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"financ") && checker(input,"statement")))
			{
				this.setStartSection(sections[19]);			
				this.setEndSection(sections[21]);
				this.setSpecialPrompt("none");
			}
			//This next one shows everything in the document
			else if((checker(input,"everything")))
			{
				//Since I want this to show everything, by adding in a dummy variable to the end, it will go to the end of the file since no line has just the word "Nothing" in it.
				this.setStartSection(sections[1]);			
				this.setEndSection("Nothing");
				this.setSpecialPrompt("none");
			}
			//While it may seem weird that Part 1 is at the end, it's to make sure they aren't asking to be told about any ofthe other sections first
			else if((checker(input,"tell me about")))
			{
				this.setStartSection(sections[0]);			
				this.setEndSection(sections[6]);
				this.setSpecialPrompt("none");
			}
			else if((checker(input,"quit") || input.equalsIgnoreCase("q")))
			{
				this.setSpecialPrompt("Ending program.");
			}
			//The catch for any unknown input.
			else
			{
				this.setSpecialPrompt("Unknown");
			}
			
			
			
			
			//This checks to see if the company is not Coca-Cola (which is consider the "base" company in this program).
			//If it is PepsiCo (or whatever other company), it goes through the array to find the index of the sections submitted and switch them with PepsiCo's version (this is why both arrays need to be equal).
					if(company.equals("PepsiCo"))
					{
						for(int i=0;i<sections.length;i++)
						{
							if(sections[i].equals(this.startSection))
							{
								this.startSection = PepsiCoSections[i];
							}
						}
						for(int i=0;i<sections.length;i++)
						{
							if(sections[i].equals(this.endSection))
							{
								this.endSection = PepsiCoSections[i];
							}
						}
					}	
			
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

		//These are standard getters and setters, so I won't elaborate on them
		public String getStartSection()
		{
			return this.startSection;
		}
		public String getSpecialPrompt()
		{
			return this.specialPrompt;
		}
		public String getEndSection()
		{
			return this.endSection;
		}
		 
		public void setStartSection(String startSection)
		{
			if(startSection != null)
				this.startSection = startSection; 
			else
				this.startSection = "none";
		}
		public void setEndSection(String endSection)
		{
			if(endSection != null)
				this.endSection = endSection; 
			else
				this.endSection = "none";
		}	
		public void setSpecialPrompt(String specialPrompt)
		{
			if(specialPrompt != null)
				this.specialPrompt = specialPrompt; 
			else
				this.specialPrompt = "none";
		}
		
		
		
	}
