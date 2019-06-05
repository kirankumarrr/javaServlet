package com.kiran.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreatUserServlet Marks CreatUserServlet
 * with @WebServlet Annotation and assigns uri ==deletedServlet
 */
@WebServlet("/deletedServlet")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Created Global Level Connection Filed
	private Connection connection;

	/**
	 * Creating init() Good place to create DB Connection since it is called
	 * only once
	 */
	public void init() {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "yg88vw");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) Generates DOPOST METHOD
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Statement statement = connection.createStatement();
			String name = request.getParameter("name");
			ResultSet resultSet = statement.executeQuery("delete from user where name='" + name + "'");
			PrintWriter writer = response.getWriter();
			writer.print("<h1>This user=='" + name + "' is deleted successfully</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creating destory() closing DB connection
	 */
	public void destory() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
