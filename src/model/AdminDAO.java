package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminDAO implements DAOinterface<AdminBean>{
	
private static final String TABLE_NAME = "Admin" ; 

	// metodo che restituisce il bean richiesto tramite una query fatta sulla chiave primaria della tabella
	@Override
	public AdminBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		
		AdminBean admin = new AdminBean() ; 
		String selectSQL ;  
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE codice_accesso = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setString(1, code);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				admin = new AdminBean(rs.getString("Password"),rs.getString("codice_accesso")) ;
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return admin ;
	}

	// metodo che restituisce tutti i bean eventualmente ordinati per un campo
	@Override
	public Collection<AdminBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		List<AdminBean> alladmin = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(order == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+" ORDER BY ? ; ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			preparedstatement.setString(1, order);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) 
				alladmin.add(new AdminBean(rs.getString("Password"),rs.getString("codice_accesso"))) ;
			
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return alladmin ; 
		}	
	
	// metodo che salva i dati di un oggetto bean nel db
	@Override
	public void doSave(AdminBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String insertSQL = "INSERT INTO "+TABLE_NAME+ " VALUES (?,?) ; " ;

		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(insertSQL) ; 
			preparedstatement.setString(1, product.getCodice_Accesso());
			preparedstatement.setString(2, product.getPassword());
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

	// metodo che aggiorna un'istanza nel db del bean corrispondente
	@Override
	public void doUpdate(AdminBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String deleteSQL = "UPDATE"+ TABLE_NAME +" SET Password = ? WHERE codice_accesso = ? ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(deleteSQL) ; 
			preparedstatement.setString(1, product.getPassword());
			preparedstatement.setString(2, product.getCodice_Accesso());
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

	// metodo che elimina un'istanza nel db tramite la chiave primaria
	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  " WHERE codice_accesso = ? ; " ; 
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