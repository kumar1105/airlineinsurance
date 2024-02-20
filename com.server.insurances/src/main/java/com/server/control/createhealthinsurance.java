package com.server.control;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.servlet.jdbc.JDBC;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class createhealthinsurance extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	Connection connect = JDBC.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	int result = 0;
		String policyid = request.getParameter("policyid");
		int fpolicyid=0;
		try {
			fpolicyid= Integer.parseInt(policyid);
			
		}catch (Exception e) {
			fpolicyid=0;
		}
		String name = request.getParameter("name");
		String amount = request.getParameter("amount");
		int famount = 0;
		try {
			famount=Integer.parseInt(amount);
			
		}catch (Exception e) {
			famount=0;
		}
		String tenureyears = request.getParameter("tenureyears");
		int ftenureyears = 0;
		try {
			ftenureyears= Integer.parseInt(tenureyears);
			
		}catch (Exception e) {
			ftenureyears=0;
		}
		
		try {
			PreparedStatement prep = connect.prepareStatement("insert into insurance values(?,?,?,?)");
			prep.setInt(1,fpolicyid);
			prep.setString(2,name);
			prep.setInt(3, famount);
			prep.setInt(4, ftenureyears);
			
			result = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher sucess = request.getRequestDispatcher("success.html");
		RequestDispatcher fail = request.getRequestDispatcher("fail.html");

		if (result > 0) {
			try {
				sucess.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				fail.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		}
		}

}