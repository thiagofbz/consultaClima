package br.com.clima.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static ConnectionFactory instance;
	
	String USUARIO = "root";
	String SENHA = "admin";
	String URL = "jdbc:mysql://localhost:3306/dev?useTimezone=true&serverTimezone=UTC&useSSL=false";
	
	public static ConnectionFactory getInstance() {
		if(instance == null) {
			instance = new ConnectionFactory();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(URL,USUARIO,SENHA);
		}catch (ClassNotFoundException e){
			throw new SQLException(e.getMessage());
		}
	}
}
