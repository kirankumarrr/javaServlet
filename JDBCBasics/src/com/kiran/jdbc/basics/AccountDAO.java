package com.kiran.jdbc.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "yg88vw");
			System.out.println(connection);
			// Adding records into Database
			Statement statement = connection.createStatement();
			// passing sql query to bd INSERTION
			/*
			 * int result = statement.executeUpdate(
			 * "insert into account values(1,'reddy','kiran kumar',999)");
			 * System.out.println(result + " rows got inserted");
			 */

			// Updating Existing Records "
			/*
			 * int updateQuery = statement.executeUpdate(
			 * "update account set bal=45 where accno=1");
			 * System.out.println(updateQuery+"Query Updated");
			 */
			//DELETE RECORD
			int resultDelete = statement.executeUpdate("delete from account where accno=1");
			System.out.println(resultDelete +" Rows got Deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
