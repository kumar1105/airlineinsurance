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
//createpassengerServlet 
public class createpassengerServlet extends HttpServlet{
	Connection connect = JDBC.getInstance();

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		int result =0;
		String Aviation_id = request.getParameter("Aviation_id");
		int aviationid = 0;
		try {
			aviationid = Integer.parseInt(Aviation_id);
		}catch(Exception e) {
			aviationid = 0;
		}
		
		String Plane_id = request.getParameter("Plane_id");
		int planeid = 0;
		try {
			planeid = Integer.parseInt(Plane_id);
		}catch(Exception e) {
			planeid = 0;
		}
		
		String Company_name = request.getParameter("Company_name");
		String Amount = request.getParameter("Amount");
		int famount = 0;
		try {
			famount = Integer.parseInt(Amount);
		}catch(Exception e) {
			famount = 0;
		}
		
		String Tenure = request.getParameter("Tenure");
		int ftenure = 0;
		try {
			ftenure = Integer.parseInt(Tenure);
		}catch(Exception e) {
			ftenure = 0;
		}
		try {
			PreparedStatement prep = connect.prepareStatement("insert into passengers values(?,?,?,?,?)");
			prep.setInt(1, aviationid);
			prep.setInt(2, planeid);
			prep.setString(3, Company_name);
			prep.setInt(4, famount);
			prep.setInt(5, ftenure);
			result = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher sucess = request.getRequestDispatcher("Successfile.jsp");
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	}}