package quize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;




public class Registration {
	boolean usertype;
	Scanner sc=new Scanner(System.in);
	String email;
	String name;
	
	public void regd() throws SQLException {
		
		System.out.println("For user registration enter your name");
		name=sc.next();
		System.out.println("enter your email");
		email=sc.next();
		System.out.println("enter your password");
		
		String pwd=sc.next();
		
		Registration o=new Registration();
		usertype=o.userType();
		
		
	

		//connection
		SqlReg obj = new SqlReg();
        Connection con = obj.reg();
		String reginsert="insert into reg (name,email,password,userType) values(?,?,?,?);";
		PreparedStatement ps1 = con.prepareStatement(reginsert);
	    ps1.setString(1, name);
		ps1.setString(2,email );
		ps1.setString(3,pwd);
		ps1.setBoolean(4, usertype);
		int rows1=0;
	    rows1=ps1.executeUpdate();
		if(rows1>0)
			System.out.println("Your Registration is successfull");

	}
	public boolean userType() {
		System.out.println("Enter user type");
		System.out.println("1.Admin\t2.User");
		int u=sc.nextInt();
		
		if(u==1) {
			usertype=true;
			
		}
		else if(u==2) {
			usertype=false;
		}
		else {
			System.out.println("Enter correct option");
			Registration o=new Registration();
			o.userType();
		}
		return usertype;
	}
	
}

