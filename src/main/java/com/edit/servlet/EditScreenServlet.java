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
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","Root");
			PreparedStatement ps = con.prepareStatement("select ID,BOOKNAME,BOOKEDITION,BOOKPRICE from bookdata where  id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<form action='editurl?id ="+id+"' method = 'post'>");
			pw.println("<table align = 'center' style = 'border-collapse:collapse'>");
			pw.println("<tr>");
			pw.println("<td>Bood ID</td>");
			pw.println("<td><input type='number' name = 'bookId' value = '"+rs.getInt(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<td>Bood Name</td>");
			pw.println("<td><input type='text' name = 'bookName' value = '"+rs.getString(2)+"'></td>");
			pw.println("</tr>");
			pw.println("<td>Bood Edition</td>");
			pw.println("<td><input type='text' name = 'bookEdition' value = '"+rs.getString(3)+"'></td>");
			pw.println("</tr>");
			pw.println("<td>Bood Price</td>");
			pw.println("<td><input type='number' name = 'bookPrice' value = '"+rs.getFloat(4)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><input type ='submit' value = 'edit'></td>");
			pw.println("<td><input type ='reset' value = 'cancel'></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
			}
		 catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
				pw.println("<h1>"+se.getMessage()+"</h1>");
			}	catch (ClassNotFoundException e) {
				e.printStackTrace();
				pw.println("<h1>"+e.getMessage()+"</h1>");
				}
	}

}
