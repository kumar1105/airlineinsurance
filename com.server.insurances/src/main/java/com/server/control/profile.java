package com.server.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.servlet.jdbc.JDBC;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class profile extends HttpServlet {
	Connection connect=JDBC.getInstance();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String Aviation_id = request.getParameter ("Aviation_id");
		try {
		
				PreparedStatement prep= connect.prepareStatement("select * from Cargo where Aviation_id=?  union select * from Passengers Where Aviation_id=? union select * from Jets Where Aviation_id=?;");
				prep.setString(1, Aviation_id);
				prep.setString(2, Aviation_id);
				prep.setString(3, Aviation_id);
				ResultSet rs= prep.executeQuery();
				response.setContentType ("text/html");
		        PrintWriter out = response.getWriter ();
				out.print("<html><head><title>Claim Details</title></head><body>");
				out.print("<style>table {\r\n"
						+ "  width: 100%;\r\n"
						+ "  border-collapse: collapse;\r\n"
						+ "  margin-bottom: 20px;\r\n"
						+ "}</style>");
				out.print("<style>th {\r\n"
						+ "  background-color: #f2f2f2;\r\n"
						+ "  color: #333;\r\n"
						+ "  font-weight: bold;\r\n"
						+ "  padding: 10px;\r\n"
						+ "  text-align: left;\r\n"
						+ "}</style>");
				out.print("<style>tr {\r\n"
						+ "  border-bottom: 1px solid #ddd;\r\n"
						+ "}\r\n"
						+ "</style>");
				out.print("<style>tr:nth-child(even) {\r\n"
						+ "  background-color: #f9f9f9;\r\n"
						+ "}\r\n"
						+ "</style>");
				out.print("<style>td {\r\n"
						+ "  padding: 10px;\r\n"
						+ "}</style>");
				out.print("<style>tr:hover {\r\n"
						+ "  background-color: #e0e0e0;\r\n"
						+ "}</style>");
				
				out.print("<style>body {\r\n"
						+ " background-image: url('https://easbcn.com/wp-content/uploads/2020/07/256409_1-1000x423.jpg');\r\n"	
						+ "background-repeat : no-repeat;\r\n"
						+ "background-size : 100% 100%;\r\n"
						+ "background-attachment: fixed;\r\n"
						+ "}</style>");
				
				out.print ("</br></br>");
	            ResultSetMetaData rsmd = rs.getMetaData ();
	            int total = rsmd.getColumnCount ();
	            System.out.println("total");
	            out.print("<h1>Policy Details of respective Avation id </h1>");
	     
	            out.print ("<table><tr>");
	            for (int i = 1; i <= total; i++)
	         {
	             out.print ("<th>" + rsmd.getColumnName (i) + "</th>");
	         }
	            out.print ("</tr></br>");
	            /* Printing result */
	            while (rs.next ())
	         {
	             out.print ("<tr><td>" + rs.getInt (1) + "</td><td>" +  rs.getInt (2) + " </td><td>" + rs.getString (3) + "</td><td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5)+"</td></tr>");
	              
	         }
	            out.print ("</table>");
	            
	            out.print("<button onclick=\\\"history.back()\\\">Go Back</button></body></html>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}

}
