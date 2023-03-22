package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrdineDAO implements DAOinterface<OrdineBean>{
	
private static final String TABLE_NAME = "ordine" ;  
	
	@Override
	public OrdineBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		int id = Integer.parseInt(code) ;
		
		OrdineBean ord = new OrdineBean() ; 
		String selectSQL ;  
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE Num_Ordine = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setInt(1, id);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next() ; 
				ord = new OrdineBean(rs.getInt("Num_Ordine"), rs.getDate("Data_ordine"),rs.getString("Num_Carta"),rs.getString("Intestatario_Carta"),rs.getString("Email"),
						rs.getDouble("Totale"),rs.getInt("Fattura"),rs.getString("Stato"),rs.getInt("ID_indirizzo"),rs.getInt("ID_utente")) ; 
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ord ;
	}
	
	public Collection<OrdineBean> doRetrieveByUtente(int code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		List<OrdineBean> mole = new ArrayList<>() ; 
		String selectSQL ; 
		selectSQL = "SELECT * FROM "+TABLE_NAME+"  WHERE ID_Utente = ? ;  ";
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			preparedstatement.setInt(1, code);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) 
				mole.add(new OrdineBean(rs.getInt("Num_Ordine"), rs.getDate("Data_ordine"),rs.getString("Num_Carta"),rs.getString("Intestatario_Carta"),rs.getString("Email"),
						rs.getDouble("Totale"),rs.getInt("Fattura"),rs.getString("Stato"),rs.getInt("ID_indirizzo"),rs.getInt("ID_utente"))) ; 
			
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

	public Collection<OrdineBean> doRetrieveByDate(String data1, String data2) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		List<OrdineBean> mole = new ArrayList<>() ; 
		String selectSQL ; 
		selectSQL = "SELECT * FROM "+TABLE_NAME+"  WHERE Data_Ordine BETWEEN ? AND ? ;  ";
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			preparedstatement.setString(1, data1);
			preparedstatement.setString(2, data2);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) 
				mole.add(new OrdineBean(rs.getInt("Num_Ordine"), rs.getDate("Data_ordine"),rs.getString("Num_Carta"),rs.getString("Intestatario_Carta"),rs.getString("Email"),
						rs.getDouble("Totale"),rs.getInt("Fattura"),rs.getString("Stato"),rs.getInt("ID_indirizzo"),rs.getInt("ID_utente"))) ; 
			
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
	public Collection<OrdineBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		List<OrdineBean> mole = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(order == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+"ORDER BY ? ;  ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			if(order != null)
				preparedstatement.setString(1, order);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) 
				mole.add(new OrdineBean(rs.getInt("Num_Ordine"), rs.getDate("Data_ordine"),rs.getString("Num_Carta"),rs.getString("Intestatario_Carta"),rs.getString("Email"),
						rs.getDouble("Totale"),rs.getInt("Fattura"),rs.getString("Stato"),rs.getInt("ID_indirizzo"),rs.getInt("ID_utente"))) ; 
			
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
	public void doSave(OrdineBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String insertSQL = "INSERT INTO "+TABLE_NAME+" (Data_ordine,Num_Carta,Intestatario_Carta,Email,Totale,Fattura,Stato,ID_indirizzo,ID_utente) VALUES (?,?,?,?,?,?,?,?,?)" ; 

		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(insertSQL) ; 
			preparedstatement.setDate(1, product.getData_Ordine());
			preparedstatement.setString(2,product.getNum_Carta()) ; 
			preparedstatement.setString(3,product.getIntestatario());
			preparedstatement.setString(4,product.getEmail());
			preparedstatement.setDouble(5,product.getTotale());
			preparedstatement.setInt(6,product.getNum_Fattura());
			preparedstatement.setString(7,product.getStato());
			preparedstatement.setInt(8, product.getID_indirizzo());
			preparedstatement.setInt(9,product.getID_utente()) ; 
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
	public void doUpdate(OrdineBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String updateSQL; 
		
		updateSQL = "UPDATE "+TABLE_NAME+ " SET Data_ordine = ? ,Num_Carta = ?,Intestatario_Carta = ?,Email = ?,Totale = ?,Fattura = ?,Stato = ?,ID_indirizzo = ?,ID_utente = ? WHERE Num_Ordine = ? ; " ; 
		
		preparedstatement = connection.prepareStatement(updateSQL); 
		preparedstatement.setDate(1, product.getData_Ordine());
		preparedstatement.setString(2,product.getNum_Carta()) ; 
		preparedstatement.setString(3,product.getIntestatario());
		preparedstatement.setString(4,product.getEmail());
		preparedstatement.setDouble(5,product.getTotale());
		preparedstatement.setInt(6,product.getNum_Fattura());
		preparedstatement.setString(7,product.getStato());
		preparedstatement.setInt(8, product.getID_indirizzo());
		preparedstatement.setInt(9,product.getID_utente()) ; 
		preparedstatement.setInt(10, product.getNum_Ordine());
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
	}

	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		int id = Integer.parseInt(code) ;
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE Num_Ordine = ? " ; 
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
	
	public int CountFatture() throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		int count = 0 ; 
		
		String selectSQL = "Select COUNT(*) AS Numero_Ordini FROM "+TABLE_NAME ; 
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			ResultSet rs = preparedstatement.executeQuery() ;
			rs.next() ;
			count = rs.getInt("Numero_Ordini") ; 
	}finally {
		try {
			if(preparedstatement != null)
				preparedstatement.close();
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return count ; 
	}
	
	public int doSaveReturn(OrdineBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;  
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String insertSQL ; 
		
		int row = 0 ; 
		if(product.getID_utente() != -1)
			insertSQL = "INSERT INTO "+TABLE_NAME+" (Data_ordine,Num_Carta,Intestatario_Carta,Email,Totale,Fattura,Stato,ID_indirizzo,ID_utente) VALUES (?,?,?,?,?,?,?,?,?)" ; 
		else
			insertSQL = "INSERT INTO "+TABLE_NAME+" (Data_ordine,Num_Carta,Intestatario_Carta,Email,Totale,Fattura,Stato,ID_indirizzo) VALUES (?,?,?,?,?,?,?,?)" ; 
		try {
			preparedstatement = connection.prepareStatement(insertSQL) ; 
			preparedstatement.setDate(1, product.getData_Ordine());
			preparedstatement.setString(2,product.getNum_Carta()) ; 
			preparedstatement.setString(3,product.getIntestatario());
			preparedstatement.setString(4,product.getEmail());
			preparedstatement.setDouble(5,product.getTotale());
			preparedstatement.setInt(6,product.getNum_Fattura());
			preparedstatement.setString(7,product.getStato());
			preparedstatement.setInt(8, product.getID_indirizzo());
			if(product.getID_utente() != -1)
				preparedstatement.setInt(9,product.getID_utente()) ; 
			row = preparedstatement.executeUpdate() ; 
			System.out.println(row) ; 
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return row ; 
	}
	
	public int GetLastInsertId(OrdineBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement2 = null ;
		PreparedStatement preparedstatement = null ;
		int id ; 
		String insertSQL; 
		if(product.getID_utente() != -1)
			insertSQL = "INSERT INTO "+TABLE_NAME+" (Data_ordine,Num_Carta,Intestatario_Carta,Email,Totale,Fattura,Stato,ID_indirizzo,ID_utente) VALUES (?,?,?,?,?,?,?,?,?)" ; 
		else
			insertSQL = "INSERT INTO "+TABLE_NAME+" (Data_ordine,Num_Carta,Intestatario_Carta,Email,Totale,Fattura,Stato,ID_indirizzo) VALUES (?,?,?,?,?,?,?,?)" ; 
		try {
			preparedstatement = connection.prepareStatement(insertSQL) ; 
			preparedstatement.setDate(1, product.getData_Ordine());
			preparedstatement.setString(2,product.getNum_Carta()) ; 
			preparedstatement.setString(3,product.getIntestatario());
			preparedstatement.setString(4,product.getEmail());
			preparedstatement.setDouble(5,product.getTotale());
			preparedstatement.setInt(6,product.getNum_Fattura());
			preparedstatement.setString(7,product.getStato());
			preparedstatement.setInt(8, product.getID_indirizzo());
			if(product.getID_utente() != -1)
				preparedstatement.setInt(9,product.getID_utente()) ;
			
			preparedstatement.executeUpdate() ;
		}finally {
				try {
					if(preparedstatement != null)
						preparedstatement.close();
				}finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
		}
		
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
}
