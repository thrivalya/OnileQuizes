package quize;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {

	public void user(String email) throws SQLException, IOException, URISyntaxException {
		SqlReg obj = new SqlReg();
        Connection con = obj.reg();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Scanner sc=new Scanner(System.in);
        boolean chooice=true;
        while(chooice) {
        System.out.println("Enter 1.Go to quize\t2.Feed back\t3.additional videos\t4.Leader board\t5.exit");
        int option=sc.nextInt();
        switch(option) {
        case 1:
        	System.out.println("Starting quize ");
        	UserQuize u=new UserQuize();
        	u.userq(con,email);
        case 2:
        	
       	 System.out.println("Welcome to the Feedback System!");
            System.out.print("Please provide your feedback: ");
        	String feedback=reader.readLine();

            System.out.println("\nThank you for your feedback!");
            
    		
            System.out.println("Feedback received: " + feedback);
       	break;
        case 3:
        	System.out.println("additional concepts");
        	System.out.println("Choose course");
        	System.out.println("enter 1.Java\t2.Python\t3.DataStructure");
        	int c=sc.nextInt();
        	if (c==1) {
        		System.out.println("Click the below link for java online quize");
        		Desktop desktop = java.awt.Desktop.getDesktop();
            	
  			    URI oURL1 = new URI("https://www.youtube.com/watch?v=eIrMbAQSU34");
  			    desktop.browse(oURL1);
                

        	}
        	else if(c==2) {
        		System.out.println("Click the below link for java online quize");
        		Desktop desktop = java.awt.Desktop.getDesktop();
            	
  			    URI oURL1 = new URI("https://www.youtube.com/watch?v=kqtD5dpn9C8");
  			    desktop.browse(oURL1);
        		
        	}
        	else if(c==3) {
        		System.out.println("Click the below link for java online quize");
        		Desktop desktop = java.awt.Desktop.getDesktop();
            	
  			    URI oURL1 = new URI("https://www.youtube.com/watch?v=BBpAmxU_NQo");
  			    desktop.browse(oURL1);
        	}
        	break;
  
        case 4:
        	System.out.println("Leader Board");
        	   System.out.printf("%-20s %-20s %-10s \n", "Email", "Category", "Score", "Rank");
               System.out.println("------------------------------------------------------------");
            
        	String sqlQuery = "WITH RankedUser AS ( " +
                     "  SELECT " +
                     "    email, " +
                     "    category, " +
                     "    score, " +
                     "    ROW_NUMBER() OVER (PARTITION BY category ORDER BY score DESC) as a " +
                     "  FROM " +
                     "    user " +
                     ") " +
                     "SELECT * " +
                     "FROM " +
                     "    RankedUser " +
                     "WHERE " +
                     "    a <= 3";
        	 PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
        	 ResultSet resultSet = preparedStatement.executeQuery();
        	 while (resultSet.next()) {
        	 String emails = resultSet.getString("email");
             String category = resultSet.getString("category");
             int score = resultSet.getInt("score");
             System.out.printf("%-20s %-20s %-10d \n", emails, category, score);
        	 }
        	 break;
        case 5:
        	System.out.println("Thank you");
        	System.exit(0);
        	chooice=false;
        	break;
        default:
        	System.out.println(option+" not avaliable");
        	User o=new User();
        	o.user(email);
        }
	}
}
}