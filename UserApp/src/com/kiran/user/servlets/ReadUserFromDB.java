package com.kiran.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreatUserServlet Marks CreatUserServlet
 * with @WebServlet Annotation and assigns uri ==readServlet
 */
/**
 * Readind db credentials from web.xml Here @WebServlet will be overriden so
 * commenting the code
 * 
 */
// @WebServlet("/readServlet")
// When u use this below code no need to mention url-pattern in WEB.xml file
@WebServlet(urlPatterns = "/readServlet")
public class ReadUserFromDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Created Global Level Connection Filed
	private Connection connection;

	/**
	 * Creating init() Good place to create DB Connection since it is called
	 * only once
	 */
	public void init(ServletConfig config) {
		try {
			try {
				// Loading class `com.mysql.jdbc.Driver'. This is deprecated.
				// The new driver class is `com.mysql.cj.jdbc.Driver'. The
				// driver is automatically registered via the SPI and manual
				// loading of the driver class is generally unnecessary.
				Class.forName("com.mysql.jdbc.Driver");
				// calling context-params

				ServletContext servletContext = config.getServletContext();
				connection = DriverManager.getConnection(servletContext.getInitParameter("dbUrl"),
						servletContext.getInitParameter("dbUser"), servletContext.getInitParameter("dbPassword"));
				System.out.println("onINit() ");
				
				Enumeration<String> initParameterNames = config.getInitParameterNames();
				while (initParameterNames.hasMoreElements()) {
					String eachElement = initParameterNames.nextElement();
					System.out.println("Context Param Name " + eachElement);
					System.out.println("Context Param Name " + servletContext.getInitParameter(eachElement));
				}
				
				// Since using Servlet-Context concept commenting below code
				// INIT Params
				/*
				 * connection =
				 * DriverManager.getConnection(config.getInitParameter("dbUrl"),
				 * config.getInitParameter("dbUser"),
				 * config.getInitParameter("dbPassword"));
				 */

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user");
			PrintWriter writer = response.getWriter();
			writer.print("<table>");
			writer.print("<tr>");
			writer.print("<th>");
			writer.print("First Name");
			writer.print("</th>");
			writer.print("<th>");
			writer.print("Last Name");
			writer.print("</th>");
			writer.print("<th>");
			writer.print("Email");
			writer.print("</th>");
			writer.print("</tr>");
			while (resultSet.next()) {
				writer.print("<tr>");
				writer.print("<td>");
				writer.print(resultSet.getString(1));
				writer.print("</td>");
				writer.print("<td>");
				writer.print(resultSet.getString(2));
				writer.print("</td>");
				writer.print("<td>");
				writer.print(resultSet.getString(3));
				writer.print("</td>");
				writer.print("</tr>");
			}
			writer.print("</table>");

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
