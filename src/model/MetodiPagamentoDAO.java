package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MetodiPagamentoDAO implements DAOinterface<MetodiPagamentoBean>{
	private static final String TABLE_NAME = "metodi_pagamento" ; 
	
	@Override
	public MetodiPagamentoBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		MetodiPagamentoBean metodi = new MetodiPagamentoBean(); 
		String selectSQL ; 
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE NumCarta = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ; 
				preparedstatement.setString(1, code);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				metodi = new MetodiPagamentoBean(rs.getString("NumCarta"),rs.getString("Intestatario"),
							rs.getString("Data_Scadenza"),rs.getInt("CVV"));
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return metodi ;
	}

	@Override
	public Collection<MetodiPagamentoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		ArrayList<MetodiPagamentoBean> metodi = new ArrayList<>() ; 
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
				metodi.add(new MetodiPagamentoBean(rs.getString("NumCarta"),rs.getString("Intestatario"),
						rs.getString("Data_Scadenza"),rs.getInt("CVV")));
			}
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return metodi ; 
		}	

	@Override
	public void doSave(MetodiPagamentoBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String insertSQL; 
		insertSQL = "INSERT INTO "+TABLE_NAME+ " VALUES (?,?,?,?); " ; 
		
		preparedstatement = connection.prepareStatement(insertSQL); 
		preparedstatement.setString(1, product.getNCarta());
		preparedstatement.setString(2, product.getIntestatario());
		preparedstatement.setString(3, product.getData_Scadenza());
		preparedstatement.setInt(4, product.getCVV());
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
		
	}

	@Override
	public void doUpdate(MetodiPagamentoBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String updateSQL; 
		
		updateSQL = "UPDATE "+TABLE_NAME+ " SET ID=? , Nome=? , Cognome=? , Telefono=? , Citta=? , Provincia=? , Via=? , CAP=? , Nazione = ? , ID_utente = ?  WHERE ID_prod = ? ; " ; 
		
		preparedstatement = connection.prepareStatement(updateSQL); 
		preparedstatement.setString(1, product.getNCarta());
		preparedstatement.setString(2, product.getIntestatario());
		preparedstatement.setString(3, product.getData_Scadenza());
		preparedstatement.setInt(4, product.getCVV());
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
	}

	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE ID_prod = ? ; " ; 
		int number = 0 ; 
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(deleteSQL) ; 
			preparedstatement.setString(1, code) ;
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
}

