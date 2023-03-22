package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import model.ProdottoBean.TipoProdotto;

public class ProdottoDAO implements DAOinterface<ProdottoBean>{

private static final String TABLE_NAME = "prodotto" ; 
	
	@Override
	public ProdottoBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		ProdottoBean prodotto = new ProdottoBean(); 
		String selectSQL ;  
		
		if(code == null)
			selectSQL = "select * FROM " + TABLE_NAME ; 
		else
			selectSQL = "select * FROM " + TABLE_NAME + " WHERE ID_prod = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ; 
				preparedstatement.setString(1, code);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				String tipo = rs.getString("TipoProdotto") ; 
				if(tipo == "GRAMMI")
					prodotto = new ProdottoBean(rs.getString("ID_prod"),rs.getString("Nome"),rs.getString("Descrizione"),
						rs.getString("Ingredienti"),rs.getString("Valori_Nutrizionali"),rs.getBoolean("Cancellato"),TipoProdotto.GRAMMI,
						rs.getInt("IVA"),rs.getString("ID_sottocat"));
				else
					prodotto = new ProdottoBean(rs.getString("ID_prod"),rs.getString("Nome"),rs.getString("Descrizione"),
							rs.getString("Ingredienti"),rs.getString("Valori_Nutrizionali"),rs.getBoolean("Cancellato"),TipoProdotto.PEZZI,
							rs.getInt("IVA"),rs.getString("ID_sottocat"));
				
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

	@Override
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		ProdottoBean prodotto = new ProdottoBean() ; 
		ArrayList<ProdottoBean> prodotti = new ArrayList<>() ; 
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
				String tipo = rs.getString("TipoProdotto") ; 
				if(tipo == "GRAMMI")
					prodotto.setTipoProdotto(TipoProdotto.GRAMMI);
				else
					prodotto.setTipoProdotto(TipoProdotto.PEZZI);
				prodotti.add(new ProdottoBean(rs.getString("ID_prod"),rs.getString("Nome"),rs.getString("Descrizione"),
						rs.getString("Ingredienti"),rs.getString("Valori_Nutrizionali"),rs.getBoolean("Cancellato"),prodotto.getTipoProdotto(),
						rs.getInt("IVA"),rs.getString("ID_sottocat"))); 
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
	
	public Collection<ProdottoBean> doRetriveByName(String nome) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		ProdottoBean prodotto = new ProdottoBean() ; 
		ArrayList<ProdottoBean> prodotti = new ArrayList<>() ; 
		String selectSQL ; 
		
		if(nome == null)
			selectSQL = "SELECT * FROM "+TABLE_NAME ; 
		else
			selectSQL = "SELECT * FROM "+TABLE_NAME+" WHERE Cancellato != 1 AND Nome LIKE CONCAT( '%',?,'%') ; ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			if(nome != null)
				preparedstatement.setString(1, nome);
			ResultSet rs = preparedstatement.executeQuery() ; 
			while(rs.next()) {
				String tipo = rs.getString("TipoProdotto") ; 
				if(tipo == "GRAMMI")
					prodotto.setTipoProdotto(TipoProdotto.GRAMMI);
				else
					prodotto.setTipoProdotto(TipoProdotto.PEZZI);
				prodotti.add(new ProdottoBean(rs.getString("ID_prod"),rs.getString("Nome"),rs.getString("Descrizione"),
						rs.getString("Ingredienti"),rs.getString("Valori_Nutrizionali"),rs.getBoolean("Cancellato"),prodotto.getTipoProdotto(),
						rs.getInt("IVA"),rs.getString("ID_sottocat"))); 
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

	@Override
	public void doSave(ProdottoBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String insertSQL; 
		insertSQL = "INSERT INTO "+TABLE_NAME+ " VALUES (?,?,?,?,?,?,?,?,?); " ; 
		
		preparedstatement = connection.prepareStatement(insertSQL); 
		preparedstatement.setString(1, product.getID_prod());
		preparedstatement.setString(2, product.getNome());
		preparedstatement.setString(3, product.getDescrizione());
		preparedstatement.setString(4, product.getIngredienti());
		preparedstatement.setString(5, product.getValori_Nutrizionali());
		if(product.isCancellato())
			preparedstatement.setInt(6, 1); 
		else
			preparedstatement.setInt(6, 0);
		preparedstatement.setString(7, product.getTipoProdotto().toString());
		preparedstatement.setInt(8, product.getIVA());
		preparedstatement.setString(9, product.getID_sottocategoria());
		
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
		
	}

	@Override
	public void doUpdate(ProdottoBean product) throws SQLException {
		Connection connection = DriverManagerConnectionPool.getConnection() ;
		connection.setAutoCommit(true);
		PreparedStatement preparedstatement = null ;
		String updateSQL; 
		
		updateSQL = "UPDATE "+TABLE_NAME+ " SET Nome=? , Descrizione=? , Ingredienti=? , Valori_Nutrizionali=? , Cancellato=? , TipoProdotto=? , IVA=? , ID_sottocat=?  WHERE ID_prod = ? ; " ; 
		
		preparedstatement = connection.prepareStatement(updateSQL); 
		preparedstatement.setString(1, product.getNome());
		preparedstatement.setString(2, product.getDescrizione());
		preparedstatement.setString(3, product.getIngredienti());
		preparedstatement.setString(4, product.getValori_Nutrizionali());
		
		if(product.isCancellato())
			preparedstatement.setInt(5, 1); 
		else
			preparedstatement.setInt(5, 0);
		
		preparedstatement.setString(6, product.getTipoProdotto().toString());
		preparedstatement.setInt(7, product.getIVA());
		preparedstatement.setString(8, product.getID_sottocategoria());
		preparedstatement.setString(9, product.getID_prod());
		preparedstatement.executeUpdate();
		preparedstatement.close();
		DriverManagerConnectionPool.releaseConnection(connection);
	}

	@Override
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		String deleteSQL = "DELETE FROM"+ TABLE_NAME +  "WHERE ID_prod = ? " ; 
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
	
	public String GetNameByID(String id) throws SQLException {
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ;
		
		ProdottoBean prodotto = new ProdottoBean() ; 
		ArrayList<ProdottoBean> prodotti = new ArrayList<>() ; 
		String selectSQL ; 
		String nome ; 
		
		selectSQL = "SELECT Nome FROM "+TABLE_NAME+" WHERE ID_prod = ? ; " ; 
		try {
			connection = DriverManagerConnectionPool.getConnection() ; 
			preparedstatement = connection.prepareStatement(selectSQL) ;
			preparedstatement.setString(1, id);
			ResultSet rs = preparedstatement.executeQuery() ; 
			rs.next() ; 
			nome = rs.getString("Nome") ; 
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close(); 
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				}
		}
		return nome ; 
	}
}