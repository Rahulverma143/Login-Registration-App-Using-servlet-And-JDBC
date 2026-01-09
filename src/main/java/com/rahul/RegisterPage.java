package com.rahul;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterPage")
public class RegisterPage extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String DBURL = "jdbc:mysql://localhost:3306/student";
		String DBUSERNAME = "root";
		String DBPASSWORD = "root";

		String sql = "Insert into userDetails values(?, ?, ? )";
		response.setContentType("text/html");
		PrintWriter pt = response.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DBURL, DBUSERNAME, DBPASSWORD);

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);

			int rowsaffected = pstmt.executeUpdate();
			if (rowsaffected > 0) {
				System.out.println("Register successfully........");
				pt.print("<h2>Welcome</h2>");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
