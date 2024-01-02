package quize;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Login {
	public void logins() throws SQLException, IOException, URISyntaxException {
		String email;
	    String password;
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Your Email : ");
		email = sc.next();
		System.out.println("Enter Your Password : ");
		password = sc.next();
		//connection
		SqlReg obj=new SqlReg();
		Connection connection=obj.reg();
		
		String query="select * from reg";
		PreparedStatement view=connection.prepareStatement(query);
		int user=1;
		ResultSet rs=view.executeQuery();
		while(rs.next()) {
            if(email.equals(rs.getString("email")) && password.equals(rs.getString("password"))) {
				
            	user=2;
				System.out.println("login successfull");
				boolean type=rs.getBoolean("userType");
				if(type==true) {
					Admin a=new Admin();
					a.admin(email);
				}
				else if(type==false) {
					User u=new User();
					u.user(email);
				}
				break;
			
            }
		}
            if (user==1) {
				System.out.println("invalied email or password ");
				System.out.println("try again");
				Login o=new Login();
				o.logins();
			}
		}
	}

