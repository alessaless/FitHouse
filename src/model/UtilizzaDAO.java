package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UtilizzaDAO implements DAOinterface<UtilizzaBean>{
private static final String TABLE_NAME = "utilizza" ; 
	
	@Override
	public UtilizzaBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		UtilizzaBean utilizzo = new UtilizzaBean(); 
		String selectSQL ; 
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE ID_utente = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ; 
				preparedstatement.setString(1, code);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				utilizzo = new UtilizzaBean(rs.getInt("ID_utente"),rs.getString("ID_carta"));
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utilizzo ;
	}

	@Override
	public Collection<UtilizzaBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		ArrayList<UtilizzaBean> utilizzi = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(order == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+" ORDER BY ?  ;";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			if(order != null)
				preparedstatement.setString(1, order);
			
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) {
				utilizzi.add(new UtilizzaBean(rs.getInt("ID_utente"),rs.getString("ID_carta")));
			}
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return utilizzi ; 
		}	

	@Override
	public void doSave(UtilizzaBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String insertSQL; 
		insertSQL = "INSERT INTO "+TABLE_NAME+ " VALUES (?,?); " ; 
		
		preparedstatement = connection.prepareStatement(insertSQL); 
		preparedstatement.setInt(1, product.getID_utente());
		preparedstatement.setString(2, product.getID_carta());
		
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
		
	}

	@Override
	public void doUpdate(UtilizzaBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String updateSQL; 
		
		updateSQL = "UPDATE "+TABLE_NAME+ " SET ID_carta=?  WHERE ID_utente = ? ; " ; 
		
		preparedstatement = connection.prepareStatement(updateSQL); 
		preparedstatement.setString(1, product.getID_carta());
		preparedstatement.setInt(2, product.getID_utente());
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
	}
	
	public void doUpdateByCarta(UtilizzaBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String updateSQL; 
		
		updateSQL = "UPDATE "+TABLE_NAME+ " SET ID_utente=? WHERE ID_carta = ? ; " ; 
		
		preparedstatement = connection.prepareStatement(updateSQL); 
		preparedstatement.setInt(1, product.getID_utente());
		preparedstatement.setString(2, product.getID_carta());
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
	}
	
	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		int id = Integer.parseInt(code) ; 
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE ID_utente = ? ; " ; 
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
	
	public boolean doDeleteByALL(int id_utente , String id_carta) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE ID_utente = ? & ID_carta = ? ; " ; 
		int number = 0 ; 
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(deleteSQL) ; 
			preparedstatement.setInt(1, id_utente) ;
			preparedstatement.setString(2, id_carta);
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
	
	public Collection<UtilizzaBean> doRetrieveAllByKey(int code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		ArrayList<UtilizzaBean> utilizzi = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(code == 0)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE ID_utente = ? ; "; 
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			if(code != 0)
				preparedstatement.setInt(1, code);
			
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) {
				utilizzi.add(new UtilizzaBean(rs.getInt("ID_utente"),rs.getString("ID_carta")));
			}
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return utilizzi ; 
		}	
}


