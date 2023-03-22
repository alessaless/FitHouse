package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDAO implements DAOinterface<UserBean>{
	
private static final String TABLE_NAME = "utente" ; 
	
	@Override
	public UserBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		int id = Integer.parseInt(code) ; 
		
		UserBean user; 
		String selectSQL ;  
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE id = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setInt(1, id);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				user = new UserBean(rs.getInt("id"),rs.getString("nome"),rs.getString("cognome"),rs.getString("email"),rs.getString("password")) ;
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return user ;
	}

	@Override
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		List<UserBean> allUser = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(order == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+" ORDER BY ? ; " ;
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			if(order != null) 
				preparedstatement.setString(1, order);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) 
				allUser.add(new UserBean(rs.getInt("id"),rs.getString("nome"),rs.getString("cognome"),rs.getString("email"),rs.getString("password"))) ;
			
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return allUser ; 
		}	

	@Override
	public void doSave(UserBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String insertSQL = "INSERT INTO "+TABLE_NAME+ " VALUES (?,?,?,?,?); " ; 
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ;
			connection.setAutoCommit(true);
			preparedstatement = connection.prepareStatement(insertSQL) ; 
			preparedstatement.setInt(1, product.getId());
			preparedstatement.setString(2, product.getNome());
			preparedstatement.setString(3, product.getCognome());
			preparedstatement.setString(4, product.getEmail());
			preparedstatement.setString(5, product.getPassword());
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
	public void doUpdate(UserBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		String deleteSQL = "UPDATE utente SET email=?, password=? WHERE id = ?;";
		System.out.println(product.getEmail() + " "+ product.getId());
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			connection.setAutoCommit(true);
			preparedstatement = connection.prepareStatement(deleteSQL) ; 
			preparedstatement.setString(1, product.getEmail());
			preparedstatement.setString(2, product.getPassword());
			preparedstatement.setInt(3, product.getId());
			preparedstatement.executeUpdate();
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
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		int id = Integer.parseInt(code) ; 
		
		String deleteSQL = "DELETE FROM "+ TABLE_NAME +  " WHERE id = ? ; " ; 
		int number = 0 ; 
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(deleteSQL) ; 
			preparedstatement.setInt(1, id);
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
	
	public UserBean doRetrieveByEmail(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		
		UserBean user; 
		String selectSQL ;  
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE email = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setString(1, code);
				System.out.println("qua");
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				user = new UserBean(rs.getInt("id"),rs.getString("nome"),rs.getString("cognome"),rs.getString("email"),rs.getString("password")) ;
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return user ;
	}

	public boolean isRegistrato(String e) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		
		UserBean user; 
		String selectSQL ;  
		
		if(e == null)
			selectSQL = "select count(*) as numUtenti FROM " + TABLE_NAME ; 
		else
			selectSQL = "select count(*) as numUtenti FROM " + TABLE_NAME + " WHERE email = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setString(1, e);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				if(rs.getInt("numUtenti") > 0) {
					return true;
				}
				return false;
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}