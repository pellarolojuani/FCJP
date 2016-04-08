package com.fcjp.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConfigurationDataBase {	
	final String url = "jdbc:mysql://127.0.0.1:3306/";
	final String name = "fcjp";
	final String driver = "com.mysql.jdbc.Driver";
	final String userName = "root";
	final String password = "root";
	
	Connection conn = null;
	
	private static ConfigurationDataBase config;
	
	
	public static ConfigurationDataBase newConnection() {
		
		if (config == null){
			config = new ConfigurationDataBase();
		}
		return config;
			}

	private ConfigurationDataBase(){
		System.out.println("Configurando base de datos..");
		try {
			Class.forName(driver).newInstance();
			this.conn = DriverManager.getConnection(url + name, userName, password);
			
			if (conn == null)
				System.out.println("Error de coneccion!");
	
			if (!conn.isClosed())
				System.out.println("DataBase connection working using TCP/IP...");
	
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

	}

	public Connection getConn() {
		return conn;
	}


	public void setConn(Connection conn) {
		this.conn = conn;
	}


	public String getUrl() {
		return url;
	}


	public String getName() {
		return name;
	}


	public String getDriver() {
		return driver;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}
}