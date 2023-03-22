package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class IndirizzoDAO implements DAOinterface<IndirizzoBean>{
	
	private static final String TABLE_NAME = "indirizzo" ; 
	
	@Override
	public IndirizzoBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		IndirizzoBean indirizzo = new IndirizzoBean(); 
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
				indirizzo = new IndirizzoBean(rs.getInt("ID"),rs.getString("Nome"),rs.getString("Cognome"),
							rs.getString("Telefono"),rs.getString("Citta"),rs.getString("Provincia"),rs.getString("Via"),
							rs.getString("Nazione"),rs.getInt("CAP"),rs.getString("Note"),rs.getInt("ID_utente"));
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return indirizzo ;
	}

	@Override
	public Collection<IndirizzoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		ArrayList<IndirizzoBean> indirizzi = new ArrayList<>() ; 
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
				indirizzi.add(new IndirizzoBean(rs.getInt("ID"),rs.getString("Nome"),rs.getString("Cognome"),
						rs.getString("Telefono"),rs.getString("Citta"),rs.getString("Provincia"),rs.getString("Via"),
						rs.getString("Nazione"),rs.getInt("CAP"),rs.getString("Note"),rs.getInt("ID_utente")));
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
	public void doSave(IndirizzoBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String insertSQL; 
		insertSQL = "INSERT INTO "+TABLE_NAME+ " (Nome,Cognome,Telefono,Citta,Provincia,Via,CAP,Nazione,Note,ID_utente) VALUES (?,?,?,?,?,?,?,?,?,?) ; " ; 
		
		preparedstatement = connection.prepareStatement(insertSQL); 
		preparedstatement.setString(1, product.getNome());
		preparedstatement.setString(2, product.getCognome());
		preparedstatement.setString(3, product.getTelefono());
		preparedstatement.setString(4, product.getCitta());
		preparedstatement.setString(5, product.getProvincia());
		preparedstatement.setString(6, product.getVia());
		preparedstatement.setInt(7, product.getCAP());
		preparedstatement.setString(8, product.getNazione()) ;
		preparedstatement.setString(9, product.getNote()) ;
		preparedstatement.setInt(10, product.getID_utente());
		preparedstatement.executeUpdate();  
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
		
	}

	@Override
	public void doUpdate(IndirizzoBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String updateSQL; 
		
		updateSQL = "UPDATE "+TABLE_NAME+ " SET Nome=? , Cognome=? , Telefono=? , Citta=? , Provincia=? , Via=? , CAP=? , Nazione = ? , Note = ? , ID_utente = ?  WHERE ID_prod = ? ; " ; 
		
		preparedstatement = connection.prepareStatement(updateSQL); 
		preparedstatement.setInt(1, product.getID());
		preparedstatement.setString(2, product.getNome());
		preparedstatement.setString(3, product.getCognome());
		preparedstatement.setString(4, product.getTelefono());
		preparedstatement.setString(5, product.getCitta());
		preparedstatement.setString(7, product.getProvincia());
		preparedstatement.setString(8, product.getVia());
		preparedstatement.setInt(9, product.getCAP());
		preparedstatement.setString(10, product.getNazione()) ;
		preparedstatement.setString(11, product.getNote()) ;
		preparedstatement.setInt(12, product.getID_utente());
		preparedstatement.setInt(13, product.getID());
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
	}

	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		int id = Integer.parseInt(code) ; 
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE ID_prod = ? ; " ; 
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
	
	// restituisce tutti gli indirizzi di un utente
	public Collection<IndirizzoBean> doRetrieveAllByUser(int ID_cliente) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		ArrayList<IndirizzoBean> indirizzi = new ArrayList<>() ; 
		String selectSQL ; 
		
		selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE ID_utente = ? ;" ; 
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			preparedstatement.setInt(1, ID_cliente);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) {
				indirizzi.add(new IndirizzoBean(rs.getInt("ID"),rs.getString("Nome"),rs.getString("Cognome"),
						rs.getString("Telefono"),rs.getString("Citta"),rs.getString("Provincia"),rs.getString("Via"),
						rs.getString("Nazione"),rs.getInt("CAP"),rs.getString("Note"),rs.getInt("ID_utente")));
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
	
	// restituisce l'id dell' ultimo inidirizzo inserito
	public int GetLastInsertId(IndirizzoBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement2 = null ;
		PreparedStatement preparedstatement = null ;
		int id ; 
		String insertSQL; 
		insertSQL = "INSERT INTO "+TABLE_NAME+ " (Nome,Cognome,Telefono,Citta,Provincia,Via,CAP,Nazione,Note,ID_utente) VALUES (?,?,?,?,?,?,?,?,?,?) ; " ; 
		
		preparedstatement = connection.prepareStatement(insertSQL); 
		preparedstatement.setString(1, product.getNome());
		preparedstatement.setString(2, product.getCognome());
		preparedstatement.setString(3, product.getTelefono());
		preparedstatement.setString(4, product.getCitta());
		preparedstatement.setString(5, product.getProvincia());
		preparedstatement.setString(6, product.getVia());
		preparedstatement.setInt(7, product.getCAP());
		preparedstatement.setString(8, product.getNazione()) ;
		preparedstatement.setString(9, product.getNote()) ;
		preparedstatement.setInt(10, product.getID_utente());
		preparedstatement.executeUpdate();  
		preparedstatement.close();
		
		
		String selectSQL ; 
		selectSQL = "SELECT LAST_INSERT_ID()" ;
		try {
			preparedstatement2 = connection.prepareStatement(selectSQL) ;
			ResultSet rs = preparedstatement2.executeQuery() ; 
			rs.next() ; 
			id = rs.getInt(1) ; 
		}finally {
			try {
				if(preparedstatement2 != null)
					preparedstatement2.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
	
		return id ;
	
	}
	
	// controlla se l'indirizzo già esiste
	public boolean Equal(IndirizzoBean indirizzo) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String selectSQL ; 
		selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE Nome = ? AND Cognome = ? AND Telefono = ? AND Citta = ? AND Provincia = ? AND Via = ? AND CAP = ? AND Nazione = ? AND Note = ? ; " ; 
 
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			preparedstatement.setString(1, indirizzo.getNome());
			preparedstatement.setString(2, indirizzo.getCognome());
			preparedstatement.setString(3, indirizzo.getTelefono());
			preparedstatement.setString(4, indirizzo.getCitta());
			preparedstatement.setString(5, indirizzo.getProvincia());
			preparedstatement.setString(6, indirizzo.getVia());
			preparedstatement.setInt(7, indirizzo.getCAP());
			preparedstatement.setString(8, indirizzo.getNazione());
			preparedstatement.setString(9, indirizzo.getNote());
			ResultSet rs = preparedstatement.executeQuery() ; 
			if( !(rs.next()) )
				return false ; 
			else 
				return true ; 
			}finally {
				try {
					if(preparedstatement != null)
						preparedstatement.close(); 
				}finally {
					DriverManagerConnectionPool.releaseConnection(connection);
					}
			}
	}
	
	public int GetKey(IndirizzoBean indirizzo) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String selectSQL ; 
		selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE Nome = ? AND Cognome = ? AND Telefono = ? AND Citta = ? AND Provincia = ? AND Via = ? AND CAP = ? AND Nazione = ? AND Note = ? ; " ; 
 
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			preparedstatement.setString(1, indirizzo.getNome());
			preparedstatement.setString(2, indirizzo.getCognome());
			preparedstatement.setString(3, indirizzo.getTelefono());
			preparedstatement.setString(4, indirizzo.getCitta());
			preparedstatement.setString(5, indirizzo.getProvincia());
			preparedstatement.setString(6, indirizzo.getVia());
			preparedstatement.setInt(7, indirizzo.getCAP());
			preparedstatement.setString(8, indirizzo.getNazione());
			preparedstatement.setString(9, indirizzo.getNote());
			ResultSet rs = preparedstatement.executeQuery() ; 
			rs.next() ; 
			return rs.getInt("ID") ; 
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
