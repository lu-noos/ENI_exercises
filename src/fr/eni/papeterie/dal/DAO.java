package fr.eni.papeterie.dal;

import java.util.List;

public interface DAO<T> {

	public void insert(T s)throws DALException;
	public void update(T s)throws DALException;
	public void delete(T s)throws DALException;
	public List<T> selectAll()throws DALException;
	public T selectById(int idArticle)throws DALException;
	public List<T> selectByMarque(String marque)throws DALException;
	public List<T> selectByMotCle(String motcle)throws DALException;
	
}
