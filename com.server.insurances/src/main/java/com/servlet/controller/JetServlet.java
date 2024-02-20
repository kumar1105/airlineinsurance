package com.servlet.controller;
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

public class JetServlet extends HttpServlet{
	Connection connect = JDBC.getInstance();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JetServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("Get triggered");
		String planeid = request.getParameter("Plane_id");
		String damage=request.getParameter("Damage");
		try {
			PreparedStatement prep= connect.prepareStatement("select * from Jets where Plane_id=?");
			prep.setString(1, planeid);
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
					+ " background-image: url('https://bsmedia.business-standard.com/_media/bs/img/article/2017-12/16/full/1513444738-5472.jpg?im=FeatureCrop,size=(826,465)');\r\n"	
					+ "background-repeat : no-repeat;\r\n"
					+ "background-size : 100% 100%;\r\n"
					+ "background-attachment: fixed;\r\n"
					+ "h3{\r\n"
					+"color:#401641;\r\n"
					+ "}\r\n"
					+ "}</style>");
			
			out.print ("</br></br>");
            ResultSetMetaData rsmd = rs.getMetaData ();
            int total = rsmd.getColumnCount ();
            System.out.println("total");
            out.print("<h1>Policy Details of respective Plane Id </h1>");
            double number = 0;
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
              number= rs.getInt(4);
         }
            out.print ("</table>");
            if (damage.equalsIgnoreCase("Minor")) {
            	number=number*0.40;
            }if (damage.equalsIgnoreCase("Partial")) {
            	number=number*0.60;
            }else {
            	number=number*1;
            }
            
            out.print("<h3>Final Amount to be Claim</h3>");
            out.print("<h2>"+"Rs."+number+"</h2>");
            out.print("<button onclick=\\\"history.back()\\\">Go Back</button></body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
