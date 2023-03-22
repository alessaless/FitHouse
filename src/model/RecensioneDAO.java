package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class RecensioneDAO implements DAOinterface<RecensioneBean>{
	
		private static final String TABLE_NAME = "recensione" ; 
		
		@Override
		public RecensioneBean doRetrieveByKey(String code) throws SQLException {
			Connection connection = null ; 
			PreparedStatement preparedstatement = null ; 
			RecensioneBean rec = new RecensioneBean(); 
			String selectSQL ; 
			int id = Integer.parseInt(code) ; 
			
			if(code == null)
				selectSQL = "select * FROM " + TABLE_NAME ; 
			else
				selectSQL = "select * FROM " + TABLE_NAME + " WHERE ID = ? ; "; 
			
			try {
					connection = DriverManagerConnectionPool.getConnection() ; 
					preparedstatement = connection.prepareStatement(selectSQL) ; 
					preparedstatement.setInt(1, id);
					ResultSet rs = preparedstatement.executeQuery() ; 
					rs.next();
					rec = new RecensioneBean(rs.getInt("ID"),rs.getInt("Voto"),rs.getString("Titolo"),
								rs.getString("Testo"),rs.getDate("Data"),rs.getString("ID_prodotto"),rs.getInt("ID_utente"),rs.getString("Nome_utente")) ;
					
			}finally {
				try {
					if(preparedstatement != null)
						preparedstatement.close();
				}finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return rec ;
		}

		@Override
		public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null ; 
			PreparedStatement preparedstatement = null ;
			
			ArrayList<RecensioneBean> indirizzi = new ArrayList<>() ; 
			String selectSQL ; 
			
			if(order == null)
				selectSQL = "SELECT * FROM "+TABLE_NAME ; 
			else
				selectSQL = "SELECT * FROM "+TABLE_NAME+"ORDER BY ?  ;";
			
			try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setString(1, order);
				
				ResultSet rs = preparedstatement.executeQuery() ; 
				while(rs.next()) {
					indirizzi.add(new RecensioneBean(rs.getInt("ID"),rs.getInt("Voto"),rs.getString("Titolo"),
							rs.getString("Testo"),rs.getDate("Data"),rs.getString("ID_prodotto"),rs.getInt("ID_utente"),rs.getString("Nome_utente")));
				}
			}finally {
				try {
					if(preparedstatement != null)
						preparedstatement.close(); 
				}finally {
					DriverManagerConnectionPool.releaseConnection(connection);
					}
				}
			
			return indirizzi ; 
			}	

		@Override
		public void doSave(RecensioneBean product) throws SQLException {
			Connection connection = DriverManagerConnectionPool.getConnection() ;
			connection.setAutoCommit(true);
			PreparedStatement preparedstatement = null ;
			String insertSQL; 
			insertSQL = "INSERT INTO "+TABLE_NAME+ " (ID,Voto,Titolo,Testo,Data,ID_prodotto,ID_utente,Nome_utente) VALUES (?,?,?,?,?,?,?,?) ; " ; 
			
			preparedstatement = connection.prepareStatement(insertSQL); 
			preparedstatement.setInt(1, product.getId());
			preparedstatement.setInt(2, product.getVoto());
			preparedstatement.setString(3, product.getTitolo());
			preparedstatement.setString(4, product.getTesto());
			preparedstatement.setDate(5, (java.sql.Date) product.getData());
			preparedstatement.setString(6, product.getID_prod());
			preparedstatement.setInt(7, product.getID_utente());
			preparedstatement.setString(8, product.getNome_u());
			preparedstatement.executeUpdate();  
			preparedstatement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
			
		}

		@Override
		public void doUpdate(RecensioneBean product) throws SQLException {
			Connection connection = DriverManagerConnectionPool.getConnection() ;
			connection.setAutoCommit(true);
			PreparedStatement preparedstatement = null ;
			String updateSQL; 
			
			updateSQL = "UPDATE "+TABLE_NAME+ " Voto=? , Titolo=? , Testo=? , Data=? , ID_prod=? , ID_utente=? , Nome_utente = ? WHERE ID = ? ; " ; 
			
			preparedstatement = connection.prepareStatement(updateSQL); 
			
			preparedstatement.setInt(1, product.getVoto());
			preparedstatement.setString(2, product.getTitolo());
			preparedstatement.setString(3, product.getTesto());
			preparedstatement.setDate(4, (java.sql.Date) product.getData());
			preparedstatement.setString(5, product.getID_prod());
			preparedstatement.setInt(6, product.getID_utente());
			preparedstatement.setString(7, product.getNome_u());
			preparedstatement.setInt(8, product.getId());
			
			preparedstatement.executeUpdate();
			preparedstatement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}

		@Override
		public boolean doDelete(String code) throws SQLException {
			Connection connection = null ; 
			PreparedStatement preparedstatement = null ;
			int id = Integer.parseInt(code) ; 
			
			String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE ID = ? ; " ; 
			int number = 0 ; 
			try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(deleteSQL) ; 
				preparedstatement.setInt(1, id) ;
				number = preparedstatement.executeUpdate() ;
			}finally {
				try {
					if(preparedstatement != null)
						preparedstatement.close();
				}finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			if(number == 0)
				return false ; 
			else
				return true ; 
		}
		
		public Collection<RecensioneBean> doRetrieveAllByID(String order) throws SQLException {
			Connection connection = null ; 
			PreparedStatement preparedstatement = null ;
			
			ArrayList<RecensioneBean> recensioni = new ArrayList<>() ; 
			String selectSQL ; 
			
			selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE ID_prodotto = ?  ;";
			
			try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setString(1, order);
				
				ResultSet rs = preparedstatement.executeQuery() ; 
				while(rs.next()) {
					recensioni.add(new RecensioneBean(rs.getInt("ID"),rs.getInt("Voto"),rs.getString("Titolo"),
							rs.getString("Testo"),rs.getDate("Data"),rs.getString("ID_prodotto"),rs.getInt("ID_utente"),rs.getString("Nome_utente")));
				}
			}finally {
				try {
					if(preparedstatement != null)
						preparedstatement.close(); 
				}finally {
					DriverManagerConnectionPool.releaseConnection(connection);
					}
				}
			
			return recensioni ; 
			}	
		
		
}
