package com.scale.bat.framework.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class PostgresSqlConnection {

	private Logger log = Log.getLogger(PostgresSqlConnection.class);
	private String dataBase, user, password, port, server;
	ConfigurationReader reader = new ConfigurationReader();

	public PostgresSqlConnection() {
		port = reader.postgressqlPort();
		dataBase = reader.postgressqlDataBaseName();
		user = reader.postgressqlUser();
		server = reader.postgressqlServer();
		password = reader.postgressqlPassword();
		//log.info("Trying to connect postgresDb....");
	}

	public Connection getConnections() {

		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			StringBuilder connectionString = new StringBuilder();
			connectionString.append("jdbc:postgresql://").append(server);
			connectionString.append(":").append(port).append("/").append(dataBase);
			connection = DriverManager.getConnection(connectionString.toString(), user, password);
			log.info(connection);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		log.info("Opened database successfully");
		return connection;
	}

	public ResultSet getData(String query) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnections().createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return resultSet;		
	}
	
	public void closeConnection() {
		try {
			getConnections().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("Connection closed .. .");
	}
}