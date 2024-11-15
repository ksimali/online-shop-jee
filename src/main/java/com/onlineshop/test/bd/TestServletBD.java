package com.onlineshop.test.bd;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * Servlet implementation class TestServletBD
 */
@WebServlet("/TestServletBD")
public class TestServletBD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	// définir le DataSource
	@Resource(name="jdbc/onlineshop_bd")
	private DataSource dataSource;
	
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		// Get mes connexion a la base de donnée
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String sql = "SELECT * FROM categorie";
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String description = resultSet.getString("description");
				out.println(id);
				out.println(nom);
				out.println(description);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
				
	}


}
