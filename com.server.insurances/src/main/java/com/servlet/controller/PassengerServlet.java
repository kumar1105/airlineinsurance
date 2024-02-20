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

public class PassengerServlet extends HttpServlet {
	Connection connect = JDBC.getInstance();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PassengerServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("Get triggered");
		String planeid = request.getParameter("Plane_id");
		String damage=request.getParameter("Damage");
		try {
			PreparedStatement prep= connect.prepareStatement("select * from Passengers where Plane_id=?");
			prep.setString(1, planeid);
			ResultSet rs= prep.executeQuery();
			response.setContentType ("text/html");
	        PrintWriter out = response.getWriter ();
			out.print("<html><head><title>Claim Details</title>");
			
			out.println("<style>");
	        out.println(".styled-table {");
	        out.println("  width: 100%;");
	        out.println("  border-collapse: collapse;");
	        out.println("  margin: 20px 0;");
	        out.println("}");
	        out.println(".styled-table th, .styled-table td {");
	        out.println("  border: 1px solid #ddd;");
	        out.println("  padding: 8px;");
	        out.println("  text-align: left;");
	        out.println("}");
	        out.println(".styled-table th {");
	        out.println("  background-color: #f2f2f2;");
	        out.println("}");
	        out.println(".styled-table tbody tr:nth-child(even) {");
	        out.println("  background-color: #f9f9f9;");
	        out.println("}");
	        out.println(".styled-table tbody tr:hover {");
	        out.println("  background-color: #e5e5e5;");
	        out.println("}");
	        out.println("</style>");
	        out.print("</head><body>");
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
            out.print("<h1>Policy Details of respective Plane Id </h1>");
            double number = 0;
            out.print ("<table class=\"styled-table\"><tr>");
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