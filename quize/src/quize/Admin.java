package quize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;  


public class Admin {
	public void admin(String email) throws SQLException, IOException {
		boolean chooise=true;
		while(chooise) {
		//connection
		SqlReg obj = new SqlReg();
        Connection con = obj.reg();
        String cat=null;
        String type=null;
        String que=null;
        String ans=null;
        String exp=null;
        String A=null;
        String B=null;
        String C=null;
        String D=null;
        Scanner sc=new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter 1.Create your Quiz \t 2.Feed back\t3.exit");
		int op=sc.nextInt();
		switch(op) {
		
		case 1:
			System.out.println("-------Create your Quiz-------");	
			System.out.println("Choose your category");
			System.out.println("Enter \n1.Java\n2.Python\n3.DataStructure");
			
			int choice=sc.nextInt();
			if(choice==1) {
				System.out.println("you choosed Java");
				cat="java";
			}
			else if(choice==2) {
				System.out.println("you choosed Python");
				cat="python";
			}
			else if(choice==3) {
				System.out.println("you choosed DataStructure");
				cat="DataStructure";
			}
			for(int i=1;i<=10;i++) {
				System.out.println("enter quetion type");
				System.out.println("1.mcq\t2.fill in blank\t3.true or false");
				int qt=sc.nextInt();
				if (qt==1) {
					type="mcq";
					System.out.println("you choosed mcq");
					System.out.println("---Enter mcq quetion---");
					que=  reader.readLine();				  
					System.out.println("---Enter options---");
					System.out.println("option A");
					A= reader.readLine();	
					System.out.println("option B");
					B= reader.readLine();	
					System.out.println("option C");
					C= reader.readLine();	
					System.out.println("option D");
					D= reader.readLine();	
					System.out.println("Enter correct option");
					ans= reader.readLine();	
					System.out.println("Explanation to your answer");
					exp= reader.readLine();	
				}
				else if(qt==2) {
					type="blanks";
					A=null;
					B=null;
					C=null;
					D=null;
					System.out.println("you choosed fill in the blank");
					System.out.println("---Enter quetion---");
					System.out.println("Note:Enter blank as' _____'");
					que=  reader.readLine();				  
					System.out.println("Enter answer");
					ans= reader.readLine();	
					System.out.println("Explanation to your answer");
					exp= reader.readLine();	
							
				}
				else if(qt==3) {
					type="true or false";
					A=null;
					B=null;
					C=null;
					D=null;
					System.out.println("you choosed true or false");
					System.out.println("---Enter quetion---");
					 que=  reader.readLine();	
					System.out.println("Enter answer");
					 ans= reader.readLine();	
					System.out.println("Explanation to your answer");
					 exp= reader.readLine();	
					
				
				}
				String Queary="insert into admin values(?,?,?,?,?,?,?,?,?,?);";
				
				PreparedStatement ps=con.prepareStatement(Queary);
				ps.setString(1, email);
				ps.setString(2, cat);
				ps.setString(3, type);
				ps.setString(4, que);
				ps.setString(5, A);
				ps.setString(6, B);
				ps.setString(7, C);
				ps.setString(8, D);
				ps.setString(9, ans);
				ps.setString(10, exp);
				ps.executeUpdate();
			}
		
		case 2:
			System.out.println("Welcome to the Feedback System!");
            System.out.print("Please provide your feedback: ");
        	String feedback=reader.readLine();

            System.out.println("\nThank you for your feedback!");
        
    		
            System.out.println("Feedback received: " + feedback);
            break;
		case 3:
			System.out.println("Thank you");
			chooise=false;
			System.exit(0); 
			break;
		 default:
	        	System.out.println(op+" not avaliable");
	        	Admin o=new Admin();
	        	o.admin(email);
	}
	}
}}