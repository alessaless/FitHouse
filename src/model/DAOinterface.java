package model;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOinterface<T> {
	public T doRetrieveByKey(String code) throws SQLException ; 
	public Collection<T> doRetrieveAll(String order) throws SQLException ; 
	public void doSave(T product) throws SQLException; 
	public void doUpdate(T product) throws SQLException ;
	public boolean doDelete(String code) throws SQLException ; 
}
