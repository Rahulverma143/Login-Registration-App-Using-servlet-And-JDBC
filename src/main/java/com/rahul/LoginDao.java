package com.rahul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	public boolean valid(String email, String password) {
		boolean check = false;
		String DBURL = "jdbc:mysql://localhost:3306/student";
		String DBUSERNAME = "root";
		String DBPASSWORD = "root";

		final String sql = "Select * from userDetails where email=? and password=? ";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DBURL, DBUSERNAME, DBPASSWORD);

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				check = true;
			}
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return check;
	}
}
