package quize;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlReg {
	public Connection reg() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
