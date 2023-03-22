package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class moleDAO implements DAOinterface<MoleBean>{
	
private static final String TABLE_NAME = "mole" ;  
	
	@Override
	public MoleBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		
		MoleBean mole = new MoleBean() ; 
		String selectSQL ;  
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + "WHERE Quantita = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setString(1, code);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next() ; 
				mole.setQuantita(rs.getString("Quantita")); 
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return mole ;
	}

	@Override
	public Collection<MoleBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		List<MoleBean> mole = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(order == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+" ORDER BY ? ;  ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			if(order != null)
				preparedstatement.setString(1, order);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) 
				mole.add(new MoleBean(rs.getString("Quantita"))) ; 
			
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return mole ; 
		}	

	@Override
	public void doSave(MoleBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String insertSQL = "INSERT INTO "+TABLE_NAME+" VALUES (?)" ; 

		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(insertSQL) ; 
			preparedstatement.setString(1, product.getQuantita());
			preparedstatement.executeUpdate() ; 
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doUpdate(MoleBean product) throws SQLException {
			}

	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE Quantit� = ? " ; 
		int number = 0 ; 
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(deleteSQL) ;
			preparedstatement.setString(1, code);
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

