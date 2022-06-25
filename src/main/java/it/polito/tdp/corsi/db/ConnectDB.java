package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	public static Connection getConnection() {
		
		try {
			
			//1.Definire la stringa di connessione JDBC:
			String url = "jdbc:mysql://localhost:3306/iscritticorsi?user=root&password=moustache";
		
			//2.Aprire una connessione:
			Connection conn = DriverManager.getConnection(url);
			return conn; 
				
		} catch (SQLException e) {
			
			System.out.println("ERRORE di connessione al database.");
			e.printStackTrace();
			return null;
		}
		
	}

	
}
