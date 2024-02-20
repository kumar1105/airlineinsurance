package com.servlet.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
static JDBC mysql=new JDBC();
static Connection connect;
private JDBC() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/innovators", "root", "Lakshmi@830");
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static Connection getInstance() {
	return mysql.connect;
}
public static void main(String[] args) {
	System.out.println(JDBC.getInstance());
	
}
}
