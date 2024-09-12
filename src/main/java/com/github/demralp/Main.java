package com.github.demralp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	
	static Scanner s = new Scanner(System.in);
	static int uid;
	
	public static void main(String[] args) throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://172.26.114.217/yarkin", "root", "1234");
			PreparedStatement userStatement = con.prepareStatement("INSERT INTO user (username, email, password) VALUES (?, ?, ?);");
			System.out.println("Enter Info:");
			userStatement.setString(1, s.nextLine());
			userStatement.setString(2, s.nextLine());
			userStatement.setString(3, s.nextLine());
			userStatement.executeUpdate();
			
			PreparedStatement getUsers = con.prepareStatement("select * from user;");
			ResultSet getUsersResultSet = getUsers.executeQuery();
			while (getUsersResultSet.next()) {
				uid = getUsersResultSet.getInt("uid");
				System.out.println(uid);
				System.out.println(getUsersResultSet.getString(2));
				System.out.println(getUsersResultSet.getString(3));
				System.out.println(getUsersResultSet.getString(4));
				System.out.println(getUsersResultSet.getString(5));
			}
			System.out.println("enter nickname");
			String nickname = s.nextLine();
			BigDecimal length = s.nextBigDecimal();
			BigDecimal radius = s.nextBigDecimal();
			BigDecimal curveAngle = s.nextBigDecimal();
			PreparedStatement toolStatement = con.prepareStatement("INSERT INTO tool VALUES (?, ?, ?, ?, ?);");
			toolStatement.setInt(1, uid);
			toolStatement.setString(2, nickname);
			toolStatement.setBigDecimal(3, length);
			toolStatement.setBigDecimal(4, radius);
			toolStatement.setBigDecimal(5, curveAngle);
			toolStatement.executeUpdate();
			
			PreparedStatement getTools = con.prepareStatement("select * from tool;");
			ResultSet getToolsResultSet = getTools.executeQuery();
			while (getToolsResultSet.next()) {
				System.out.println(getToolsResultSet.getString(1));
				System.out.println(getToolsResultSet.getString(2));
				System.out.println(getToolsResultSet.getString(3));
				System.out.println(getToolsResultSet.getString(4));
				System.out.println(getToolsResultSet.getString(5));
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
}