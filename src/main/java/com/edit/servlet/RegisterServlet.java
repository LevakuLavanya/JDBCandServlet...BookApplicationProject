package com.edit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query = "insert into bookdata(ID,BOOKNAME,BOOKEDITION,BOOKPRICE) values(?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		int bookId = Integer.parseInt(req.getParameter("bookId"));
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float  bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","Root");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			ps.setString(2, bookName);
			ps.setString(3, bookEdition);
			ps.setFloat(4, bookPrice);
			int count = ps.executeUpdate();
			if(count ==1) {
				pw.println("<h2>Record is registered Successfully</h2>");
			}
			else {
				pw.println("<h2>Record is not registered Successfully</h2>");
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h1>");
		}	catch (ClassNotFoundException e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
			}
		pw.println("<a href = 'Home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href = 'booklist'>Book</a>");
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
