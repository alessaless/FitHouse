package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ProdottoBean.TipoProdotto;

public class ComprendeDAO implements DAOinterface<ComprendeBean>{

private static final String TABLE_NAME = "Comprende" ; 
	
	//metodo che restituisce il bean richiesto tramite una query fatta sulla chiave primaria della tabella
	@Override
	public ComprendeBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		ComprendeBean prodotto = new ComprendeBean(); 
		String selectSQL ;  
		int id = Integer.parseInt(code) ;
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE Num_ordine = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ; 
				preparedstatement.setInt(1, id);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				prodotto = new ComprendeBean(rs.getString("ID_prod"),rs.getInt("Num_ordine"),rs.getString("Mole"),rs.getString("Aroma"),rs.getDouble("Prezzo"),
						rs.getString("Url_Foto"),rs.getInt("Quantita"));
				
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prodotto ;
	}

	// metodo che restituisce tutti i bean eventualmente ordinati per un campo
	@Override
	public Collection<ComprendeBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		ArrayList<ComprendeBean> prodotti = new ArrayList<>() ; 
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
			while(rs.next()) {
				prodotti.add(new ComprendeBean(rs.getString("ID_prod"),rs.getInt("Num_ordine"),rs.getString("Mole"),rs.getString("Aroma"),rs.getDouble("Prezzo"),
						rs.getString("Url_Foto"),rs.getInt("Quantita"))); 
			}
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return prodotti ; 
		}
	
	// restituisce tutti quanti i prodotti di un ordine in base al numero dell'ordine
	public Collection<ComprendeBean> DoRetrieveAllByKey(int num_ordine) throws SQLException{
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		ArrayList<ComprendeBean> prodotti = new ArrayList<>() ; 
		String selectSQL ; 
		
		selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE Num_ordine = ? ;" ; 
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			preparedstatement.setInt(1, num_ordine);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) {
				prodotti.add(new ComprendeBean(rs.getString("ID_prod"),rs.getInt("Num_ordine"),rs.getString("Mole"),rs.getString("Aroma"),rs.getDouble("Prezzo"),
						rs.getString("Url_Foto"),rs.getInt("Quantita"))); 
			}
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		return prodotti ;
	}
	
	// metodo che salva i dati di un oggetto bean nel db
	@Override
	public void doSave(ComprendeBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String insertSQL; 
		insertSQL = "INSERT INTO "+TABLE_NAME+ " VALUES (?,?,?,?,?,?,?); " ; 
		
		preparedstatement = connection.prepareStatement(insertSQL); 
		preparedstatement.setString(1, product.getID_prod() );
		preparedstatement.setInt(2, product.getNum_Ordine());
		preparedstatement.setString(3, product.getMole());
		preparedstatement.setString(4, product.getAroma());
		preparedstatement.setDouble(5, product.getPrezzo());
		preparedstatement.setString(6, product.getUrl_foto());
		preparedstatement.setInt(7, product.getQuantita());
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
		
	}

	// metodo che aggiorna un'istanza nel db del bean corrispondente
	@Override
	public void doUpdate(ComprendeBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String insertSQL; 
		
		insertSQL = "UPDATE "+TABLE_NAME+ " SET ID_prod=? , Mole=? , Aroma=? , Prezzo=? , Url_Foto=?, Quantita = ? WHERE Num_ordine = ? ; " ; 
		
		preparedstatement = connection.prepareStatement(insertSQL); 
		preparedstatement.setString(1, product.getID_prod() );
		preparedstatement.setString(2, product.getMole());
		preparedstatement.setString(3, product.getAroma());
		preparedstatement.setDouble(4, product.getPrezzo());
		preparedstatement.setString(5, product.getUrl_foto());
		preparedstatement.setInt(6, product.getQuantita());
		preparedstatement.setInt(7, product.getNum_Ordine());
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
	}

	// metodo che elimina un'istanza nel db tramite la chiave primaria
	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		int id = Integer.parseInt(code) ;
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE Num_ordine = ? " ; 
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
}
