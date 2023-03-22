package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SottocategoriaDAO implements DAOinterface<SottocategoriaBean> {
	
private static final String TABLE_NAME = "sottocategoria" ; 
	
	@Override
	public SottocategoriaBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		SottocategoriaBean sottocategoria = new SottocategoriaBean() ; 
		String selectSQL ;  
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + "WHERE Nome = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ; 
				preparedstatement.setString(1, code) ;
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				sottocategoria.setNome(rs.getString("Nome")); 
				sottocategoria.setID_categoria(rs.getString("ID_categoria"));
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return sottocategoria ;
	}

	@Override
	public Collection<SottocategoriaBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		List<SottocategoriaBean> sottocategorie = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(order == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+"ORDER BY ? ; ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			if(order != null)
				preparedstatement.setString(1, order) ;
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next())
				sottocategorie.add(new SottocategoriaBean(rs.getString("Nome"),rs.getString("ID_categoria"))) ; 
			
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return sottocategorie ; 
		}	
	
	public Collection<SottocategoriaBean> doRetrieveAllByCat(String cat) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		List<SottocategoriaBean> sottocategorie = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(cat == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE ID_categoria = ? ; ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			preparedstatement.setString(1, cat) ;
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next())
				sottocategorie.add(new SottocategoriaBean(rs.getString("Nome"),rs.getString("ID_categoria"))) ; 
			
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return sottocategorie ; 
		}	

	@Override
	public void doSave(SottocategoriaBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String insertSQL = "INSERT INTO"+ TABLE_NAME + " VALUES (?,?)" ; 

		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(insertSQL) ; 
			preparedstatement.setString(1, product.getNome());
			preparedstatement.setString(2, product.getID_categoria());
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
	public void doUpdate(SottocategoriaBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String deleteSQL = "UPDATE"+ TABLE_NAME +"SET ID_categoria = ? WHERE Nome = ? ; " ;
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(deleteSQL) ; 
			preparedstatement.setString(1, product.getID_categoria());
			preparedstatement.setString(2, product.getNome());
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
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE Nome = ? ; " ; 
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

