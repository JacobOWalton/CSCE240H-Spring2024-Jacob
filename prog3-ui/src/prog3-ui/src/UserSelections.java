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
		
		public UserSelections(String request, String company)
		{
			//Whenever a UserSelctions class is created, the two things that need to be known is the user request and current company.
			//The program checks to see if the request matches one of the currently approved prompts.
			if(request.equals("Show me "+company+"'s Business"))
			{
				//If the user input DOES match a prompt, it denotes what the startSection, endSection, and specialPrompt (if there is one) are.
				this.setStartSection(sections[0]);			
				this.setEndSection(sections[1]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Risk Factors"))
			{
				this.setStartSection(sections[1]);			
				this.setEndSection(sections[2]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Unresolved Staff Comments"))
			{
				this.setStartSection(sections[2]);			
				this.setEndSection(sections[3]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Properties"))
			{
				this.setStartSection(sections[3]);			
				this.setEndSection(sections[4]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Legal Proceedings"))
			{
				this.setStartSection(sections[4]);			
				this.setEndSection(sections[5]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Mine Safety Disclosures"))
			{
				this.setStartSection(sections[5]);			
				this.setEndSection(sections[6]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Market for Registrant’s Common Equity, Related Stockholder Matters and Issuer Purchases of Equity Securities"))
			{
				this.setStartSection(sections[6]);			
				this.setEndSection(sections[7]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Management’s Discussion and Analysis of Financial Condition and Results of Operations"))
			{
				this.setStartSection(sections[7]);			
				this.setEndSection(sections[8]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Quantitative and Qualitative Disclosures About Market Risk"))
			{
				this.setStartSection(sections[8]);			
				this.setEndSection(sections[9]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Financial Statements and Supplementary Data"))
			{
				this.setStartSection(sections[9]);			
				this.setEndSection(sections[10]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Changes in and Disagreements with Accountants on Accounting and Financial Disclosure"))
			{
				this.setStartSection(sections[10]);			
				this.setEndSection(sections[11]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Controls and Procedures"))
			{
				this.setStartSection(sections[11]);			
				this.setEndSection(sections[12]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me some of the excess information about "+company))
			{
				this.setStartSection(sections[12]);			
				this.setEndSection(sections[13]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Disclosure Regarding Foreign Jurisdictions that Prevent Inspections"))
			{
				this.setStartSection(sections[13]);			
				this.setEndSection(sections[14]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Directors, Executive Officers and Corporate Governance"))
			{
				this.setStartSection(sections[14]);			
				this.setEndSection(sections[15]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Executive Compensation"))
			{
				this.setStartSection(sections[15]);			
				this.setEndSection(sections[16]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Security Ownership of Certain Beneficial Owners and Management and Related Stockholder Matters"))
			{
				this.setStartSection(sections[16]);			
				this.setEndSection(sections[17]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Certain Relationships and Related Transactions, and Director Independence"))
			{
				this.setStartSection(sections[17]);			
				this.setEndSection(sections[18]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Principal Accounting Fees and Services"))
			{
				this.setStartSection(sections[18]);			
				this.setEndSection(sections[19]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Exhibits and Financial Statement Schedules"))
			{
				this.setStartSection(sections[19]);			
				this.setEndSection(sections[20]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me "+company+"'s Form 10-K Summary"))
			{
				//Form 10-K Summary is a little different as it needs to be hard-coded in as there is no item that actually comes after it.
				this.setStartSection(sections[20]);			
				this.setEndSection(sections[21]);
				this.setSpecialPrompt("none");
			}
			//These next four are in regards to Parts 1 - 4 in the 10-K form, they are found by grouping the items together
			else if(request.equals("Tell me about "+company))
			{
				this.setStartSection(sections[0]);			
				this.setEndSection(sections[6]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me the operations and disclosures of "+company))
			{
				this.setStartSection(sections[6]);			
				this.setEndSection(sections[14]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("What is the company structure of "+company))
			{
				this.setStartSection(sections[14]);			
				this.setEndSection(sections[19]);
				this.setSpecialPrompt("none");
			}
			else if(request.equals("Show me the financial statements of "+company))
			{
				this.setStartSection(sections[19]);			
				this.setEndSection(sections[21]);
				this.setSpecialPrompt("none");
			}
			//This next one shows everything in the document
			else if(request.equals("Show me everything about "+company))
			{
				//Since I want this to show everything, by adding in a dummy variable to the end, it will go to the end of the file since no line has just the word "Nothing" in it.
				this.setStartSection(sections[1]);			
				this.setEndSection("Nothing");
				this.setSpecialPrompt("none");
			}
			//The following else if statements are for the special prompts, in which the activity that needs to be done cannot be done in this class.
			else if(request.equals("Switch to PepsiCo"))
			{
				this.setSpecialPrompt("Switching to PepsiCo");
			}
			else if(request.equals("Switch to The Coca Cola Company"))
			{
				this.setSpecialPrompt("Switching to The Coca Cola Company");
			}
			else if(request.equals("Quit") || request.equals("quit") || request.equals("q"))
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
