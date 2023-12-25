package com.edit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","Root");
			PreparedStatement ps = con.prepareStatement("delete from bookdata where id = ?");
			ps.setInt(1, id);
			int count =ps.executeUpdate();
			if(count==1) {
				pw.println("<h1>Record is deleted successfully</h1>");
		}
			else {
				pw.println("<h1>Record is not daleted successfully");
				pw.println("<br>");
			}
		}
		 catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
				pw.println("<h1>"+se.getMessage()+"</h1>");
			}	catch (ClassNotFoundException e) {
				e.printStackTrace();
				pw.println("<h1>"+e.getMessage()+"</h1>");
				pw.println("<br");
				}
		pw.println("<a href='Home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='booklist'>Booklist</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
