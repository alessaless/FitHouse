package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DoseDAO implements DAOinterface<DoseBean> {
	
private static final String TABLE_NAME = "dose" ; 
	
	@Override
	public DoseBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		
		DoseBean dose = new DoseBean() ; 
		String selectSQL ;  
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE ID_prodotto = ? ;"; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				if(code != null)
					preparedstatement.setString(1, code);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next() ; 
				dose = new DoseBean(rs.getString("ID_prodotto"),rs.getString("ID_mole"),rs.getString("ID_gusto"),rs.getInt("quantita_disp"),
						rs.getDouble("prezzo"),rs.getDouble("Prezzo_Scontato"),rs.getString("Url_foto")) ;
				
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return dose ;
	}

	@Override
	public Collection<DoseBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		List<DoseBean> dosi = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(order == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+"ORDER BY ? ;   ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			preparedstatement.setString(1, order);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) 
				dosi.add(new DoseBean(rs.getString("ID_prodotto"),rs.getString("ID_mole"),rs.getString("ID_gusto"),rs.getInt("quantita_disp"),
						rs.getDouble("prezzo"),rs.getDouble("Prezzo_Scontato"),rs.getString("Url_foto"))) ;
			
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		
		return dosi ; 
		}	

	@Override
	public void doSave(DoseBean dose) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String insertSQL; 
		insertSQL = "INSERT INTO "+TABLE_NAME+ " VALUES (?,?,?,?,?,?,?) ; " ; 
		
		preparedstatement = connection.prepareStatement(insertSQL); 
		preparedstatement.setString(1, dose.getID_prodotto());
		preparedstatement.setString(2, dose.getID_mole());
		preparedstatement.setString(3, dose.getID_gusto());
		preparedstatement.setInt(4, dose.getQuantita_disp());
		preparedstatement.setDouble(5, dose.getPrezzo());
		preparedstatement.setDouble(6, dose.getPrezzo_Scontato());
		preparedstatement.setString(7, dose.getUrl_foto());
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
		
	}

	@Override
	public void doUpdate(DoseBean product) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String deleteSQL = "UPDATE"+ TABLE_NAME +"SET ID_prodotto = ? , ID_mole = ? , ID_gusto = ? ,quantita_disp = ? , prezzo = ? , Prezzo_Scontato = ? , Url_Foto = ? "
						+ "WHERE ID_prodotto = ? AND ID_mole = ? AND ID_gusto = ? ; " ; 
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(deleteSQL) ; 
			preparedstatement.setString(1, product.getID_prodotto());
			preparedstatement.setString(2, product.getID_mole());
			preparedstatement.setString(3, product.getID_gusto());
			preparedstatement.setInt(4, product.getQuantita_disp());
			preparedstatement.setDouble(5, product.getPrezzo());
			preparedstatement.setDouble(6, product.getPrezzo_Scontato());
			preparedstatement.setString(7, product.getUrl_foto());
			preparedstatement.setString(8, product.getID_prodotto());
			preparedstatement.setString(9, product.getID_mole());
			preparedstatement.setString(10, product.getID_gusto());
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
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE ID_prodotto = ? ;  " ; 
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

// elimina una dose in base ai tre parametri che la contraddistinguono (id, gusto e mole)
public boolean doDeleteFULL(String ID_prod, String ID_gusto, String ID_mole) throws SQLException {
	Connection connection = null ; 
	PreparedStatement preparedstatement = null ;
	
	String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE ID_prodotto = ? AND ID_mole = ? AND ID_gusto = ? ; "; 
	int number = 0 ; 
	try {
		connection = DriverManagerConnectionPool.getConnection() ; 
		preparedstatement = connection.prepareStatement(deleteSQL) ; 
		preparedstatement.setString(1, ID_prod);
		preparedstatement.setString(2, ID_mole);
		preparedstatement.setString(3, ID_gusto);
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

// seleziona una dose in base ai tre parametri (id, mole e gusto)
public DoseBean doRetrieveByKeyFULL(String ID_prod, String ID_mole, String ID_gusto) throws SQLException {
	Connection connection = null ; 
	PreparedStatement preparedstatement = null ; 
	
	DoseBean dose = new DoseBean() ; 
	String selectSQL ;  
	
	selectSQL = "select * FROM " + TABLE_NAME + " WHERE ID_prodotto = ? AND ID_mole = ? AND ID_gusto = ? ; ";  
	
	try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			preparedstatement.setString(1, ID_prod);
			preparedstatement.setString(2, ID_mole);
			preparedstatement.setString(3, ID_gusto);
			ResultSet rs = preparedstatement.executeQuery() ; 
			rs.next() ; 
			dose = new DoseBean(rs.getString("ID_prodotto"),rs.getString("ID_mole"),rs.getString("ID_gusto"),rs.getInt("quantita_disp"),
					rs.getDouble("prezzo"),rs.getDouble("Prezzo_Scontato"),rs.getString("Url_foto")) ;
			
	}finally {
		try {
			if(preparedstatement != null)
				preparedstatement.close();
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return dose ;
	}

// metodo per selezionare tutte i dosaggi afferenti ad un id prodotto
public Collection<DoseBean> doRetrieveAllByKey(String code) throws SQLException {
	Connection connection = null ; 
	PreparedStatement preparedstatement = null ;
	
	List<DoseBean> dosi = new ArrayList<>() ; 
	String selectSQL ; 
	
	if(code == null)
		selectSQL = "SELECT * FROM "+TABLE_NAME ; 
	else
		selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE ID_prodotto = ? ;   ";
	
	try {
		connection = DriverManagerConnectionPool.getConnection() ;
		preparedstatement = connection.prepareStatement(selectSQL) ; 
		preparedstatement.setString(1, code) ; 
		ResultSet rs = preparedstatement.executeQuery() ; 
		while(rs.next()) 
			dosi.add(new DoseBean(rs.getString("ID_prodotto"),rs.getString("ID_mole"),rs.getString("ID_gusto"),rs.getInt("quantita_disp"),
					rs.getDouble("prezzo"),rs.getDouble("Prezzo_Scontato"),rs.getString("Url_foto"))) ;
		
	}finally {
		try {
			if(preparedstatement != null)
				preparedstatement.close(); 
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
	return dosi ; 
	}	

	//metodo per selezionare tutte i dosaggi afferenti ad un id prodotto raggruppati per una colonna
	public Collection<DoseBean> doRetrieveAllByKeyGroupBy(String group, String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		List<DoseBean> dosi = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(code == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME +" GROUP BY = '"+group+"' ; " ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE ID_prodotto = ? GROUP BY "+group+" ;";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ;
			preparedstatement = connection.prepareStatement(selectSQL) ; 
			if(code != null) {
				preparedstatement.setString(1, code) ; 
			}
			
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) {
				dosi.add(new DoseBean(rs.getString("ID_prodotto"),rs.getString("ID_mole"),rs.getString("ID_gusto"),rs.getInt("quantita_disp"),
						rs.getDouble("prezzo"),rs.getDouble("Prezzo_Scontato"),rs.getString("Url_foto"))) ;
			}
			
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			}	
		return dosi ; 
		}	
}
