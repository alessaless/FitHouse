package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {
	
	private static List<Connection> freeDbConnections;
		static {
					// lista delle connessioni
					freeDbConnections = new LinkedList<Connection>();
					try {
							Class.forName("com.mysql.cj.jdbc.Driver");
						} catch (ClassNotFoundException e) {
							System.out.println("DB driver not found!") ;
						}
		}
		
		// crea e restituisce la connessione al db
		private static Connection createDBConnection() throws SQLException {
			Connection newConnection = null;
			String db = "fithouse";
			String username = "root";
			String password = "caffedesign57";
			
			newConnection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/"+db, username, password);
			newConnection.setAutoCommit(false);
			
			return newConnection;
			}
		
		// ottiene una connessione dalla lista
		public static synchronized Connection getConnection() throws SQLException {
			
			Connection connection;
			
			// se la lista non è vuota la assegna e la rimuove dalla lista delle libere
			if (! freeDbConnections.isEmpty()) {
				connection = (Connection) freeDbConnections.get(0);
				DriverManagerConnectionPool.freeDbConnections.remove(0);
				try {
						// se la connessione è chiusa, richiama il metodo getConnection
						if (connection.isClosed())
							connection = DriverManagerConnectionPool.getConnection();
				}catch (SQLException e) {
					connection = DriverManagerConnectionPool.getConnection();
				}
				
			} else //se la lista è vuota (non esistono connessioni libere) ne creiamo una nuova 
				connection = DriverManagerConnectionPool.createDBConnection();
			
			return connection;
		}
		
		// rilascia la connessione aggiungendola alla lista delle libere
		public static synchronized void releaseConnection(Connection connection) {
			DriverManagerConnectionPool.freeDbConnections.add(connection);
			}
	}
