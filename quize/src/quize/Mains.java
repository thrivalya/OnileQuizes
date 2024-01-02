package quize;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Scanner;



public class Mains {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, URISyntaxException {
		// TODO Auto-generated method stub
		System.out.println("Enter 1.Registration \t 2.Login \t 3.exit");
		Scanner ob = new Scanner(System.in);
		int userlog = ob.nextInt();
		switch(userlog) {
		case 1:
			Registration o=new Registration();
			o.regd();
		case 2:
			Login log=new Login();
			log.logins();
			break;
		case 3:
			System.exit(0); 
		default:
			System.out.println("Enter correct Number");
			Mains.main(args);
			
		}
		

	}

}
