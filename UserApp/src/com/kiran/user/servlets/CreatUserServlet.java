package com.kiran.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreatUserServlet Marks CreatUserServlet
 * with @WebServlet Annotation and assigns uri ==addServlet
 */

/**
 * Instead of hardcoding db credentials we read from web.xml
 * later will move this to web.xml check web.xml implemented for ReadUserFromDB.java
 */
@WebServlet(urlPatterns = "/addServlet", initParams = {
		@WebInitParam(name = "dbUrl", value = "jdbc:mysql://localhost/mydb"),
		@WebInitParam(name = "dbUser", value = "root"), @WebInitParam(name = "dbPassword", value = "yg88vw") })
// Commenting this code
// @WebServlet("/addServlet")
public class CreatUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Created Global Level Connection Filed
	private Connection connection;

	/**
	 * Creating init() Good place to create DB Connection since it is called
	 * only once
	 */
	// this ServletConfig gives access to read that Init parameters
	public void init(ServletConfig config) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(config.getInitParameter("dbUrl"),
						config.getInitParameter("dbUser"), config.getInitParameter("dbPassword"));
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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			Statement statement = connection.createStatement();
			if (firstName != "" && lastName != "" && email != "" && password != "") {
				int result = statement.executeUpdate("insert into user values('" + firstName + "','" + lastName + "','"
						+ email + "','" + password + "')");
				// result is 1 now
				PrintWriter writerOut = response.getWriter();
				if (result > 0) {
					writerOut.print("<h1>New User Created<h1>");
				} else {
					writerOut.print("<h1>Error Creating the User<h1>");
				}
			}

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
