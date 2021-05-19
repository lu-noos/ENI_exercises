package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;
import fr.eni.papeterie.dal.Settings;

public class ArticleDAOJdbcImpl implements DAO<Article> {

	Connection connect=null;
	PreparedStatement prepStatement=null;
	Statement statement=null;
	
	public ArticleDAOJdbcImpl() throws DALException {
		
	}
	
	/*
	 * suppression d'un article
	 */
	@Override
	public void delete(Article article) throws DALException {
		String QUERY ="DELETE FROM articles WHERE idArticle=?";
		try {
			//Chargement du driver
			Class.forName(Settings.getProperty("driverjdbc"));
			//Obtenir connexion
			connect = JdbcTools.getConnection();
			//Créer le statement
			prepStatement = connect.prepareStatement(QUERY);
			prepStatement.setInt	(1, article.getIdArticle());
			
			//execution
			prepStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (prepStatement !=null) {
				try {
					prepStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				JdbcTools.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * insertion d'un article dans la bdd
	 */
	@Override
	public void insert(Article article) throws DALException {
		String QUERY ="INSERT INTO articles (reference, marque, designation, prixUnitaire, qteStock) VALUES(?, ?, ?, ?, ?)";
		Integer nouvelId=null;
		if (article instanceof Ramette) {
			QUERY ="INSERT INTO articles (reference, marque, designation, prixUnitaire, qteStock, grammage) VALUES(?, ?, ?, ?, ?, ?)";
		} else {
			QUERY ="INSERT INTO articles (reference, marque, designation, prixUnitaire, qteStock, couleur) VALUES(?, ?, ?, ?, ?, ?)";
		}
		try {
			//Chargement du driver
			Class.forName(Settings.getProperty("driverjdbc"));
			
			//Obtenir connexion
			connect = JdbcTools.getConnection();
			
			//Créer le statement
			prepStatement = connect.prepareStatement(QUERY);
			prepStatement.setString	(1, article.getReference());
			prepStatement.setString	(2, article.getMarque());
			prepStatement.setString	(3, article.getDesignation());
			prepStatement.setFloat	(4, article.getPrixUnitaire());
			prepStatement.setInt	(5, article.getQteStock());
			if (article instanceof Ramette) {
				prepStatement.setInt	(6, ((Ramette)article).getGrammage());
			} else {
				prepStatement.setString	(6, ((Stylo)article).getCouleur());
			}
			
			//execution
			prepStatement.execute();
			
			QUERY="SELECT MAX(idArticle) FROM articles";
			statement = connect.createStatement();
			ResultSet resultat = statement.executeQuery(QUERY);
			while (resultat.next()) {
				nouvelId = resultat.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (prepStatement !=null) {
				try {
					prepStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				try {
					JdbcTools.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		//get l'id et actualiser l'instance de article
		article.setIdArticle(nouvelId);
	}
	
	/*
	 * selection de tous les articles
	 */
	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> sortie = new ArrayList<>();
		String QUERY = "SELECT * FROM articles";
		try {
			//Chargement du driver
			Class.forName(Settings.getProperty("driverjdbc"));
			//Obtenir connexion
			connect = JdbcTools.getConnection();
			//Créer le statement
			statement = connect.createStatement();
			
			ResultSet resultat = statement.executeQuery(QUERY);
			while (resultat.next()) {
				if(resultat.getString(8) != null) {
					sortie.add(new Stylo(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), resultat.getFloat(5), resultat.getInt(6), resultat.getString(8)));
				} else {
					sortie.add(new Ramette(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), resultat.getFloat(5), resultat.getInt(6), resultat.getInt(7)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (statement !=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				JdbcTools.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	return sortie;
	}
	
	/*
	 * selection d'un article par id
	 */
	@Override
	public Article selectById(int index) throws DALException {
		Article sortie=null;
		String QUERY ="SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM articles WHERE idArticle = ?";
		try {
			//Chargement du driver
			Class.forName(Settings.getProperty("driverjdbc"));
			//Obtenir connexion
			connect = JdbcTools.getConnection();
			//Créer le statement
			prepStatement = connect.prepareStatement(QUERY);
			prepStatement.setInt	(1, index);
			
			//execution
			
			ResultSet resultat = prepStatement.executeQuery();
			while (resultat.next()) {
				if(resultat.getString(8) != null) {
					sortie = new Stylo(resultat.getInt("idArticle"), resultat.getString("reference"), resultat.getString("marque"), resultat.getString("designation"), resultat.getFloat("prixUnitaire"), resultat.getInt("qteStock"), resultat.getString("couleur"));
				} else if (resultat.getString(8) == null) {
					sortie = new Ramette(resultat.getInt("idArticle"), resultat.getString("reference"), resultat.getString("marque"), resultat.getString("designation"), resultat.getFloat("prixUnitaire"), resultat.getInt("qteStock"), resultat.getInt("grammage"));
				} else {
					System.err.println("Le type n'a pas pu être identifié");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (prepStatement !=null) {
				try {
					prepStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				JdbcTools.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return sortie;
	}

	/*
	 * modification d'un article dans la bdd
	 */
	@Override
	public void update(Article article) throws DALException {
		String QUERY ="UPDATE articles SET reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=? VALUES(?, ?, ?, ?, ?) WHERE idArticle=?";
		if (article instanceof Ramette) {
			QUERY ="UPDATE articles SET reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?, grammage=? WHERE idArticle=?";
		} else {
			QUERY ="UPDATE articles SET reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?, couleur=? WHERE idArticle=?";
		}
		try {
			//Chargement du driver
			Class.forName(Settings.getProperty("driverjdbc"));
			//Obtenir connexion
			connect = JdbcTools.getConnection();
			//Créer le statement
			prepStatement = connect.prepareStatement(QUERY);
			prepStatement.setString	(1, article.getReference());
			prepStatement.setString	(2, article.getMarque());
			prepStatement.setString	(3, article.getDesignation());
			prepStatement.setFloat	(4, article.getPrixUnitaire());
			prepStatement.setInt	(5, article.getQteStock());
			prepStatement.setInt	(7, article.getIdArticle());
			if (article instanceof Ramette) {
				prepStatement.setInt	(6, ((Ramette)article).getGrammage());
			} else {
				prepStatement.setString	(6, ((Stylo)article).getCouleur());
			}
			//execution
			prepStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (prepStatement !=null) {
				try {
					prepStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				JdbcTools.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Article> selectByMarque(String marque) throws DALException {
		List<Article> sortie = new ArrayList<>();
		String QUERY = "SELECT * FROM articles WHERE marque=?";
		try {
			//Chargement du driver
			Class.forName(Settings.getProperty("driverjdbc"));
			//Obtenir connexion
			connect = JdbcTools.getConnection();
			//Créer le statement
			prepStatement = connect.prepareStatement(QUERY);
			prepStatement.setString	(1, marque);
			
			ResultSet resultat = prepStatement.executeQuery();
			while (resultat.next()) {
				if(resultat.getString(8) != null) {
					sortie.add(new Stylo(resultat.getInt("idArticle"), resultat.getString("reference"), resultat.getString("marque"), resultat.getString("designation"), resultat.getFloat("prixUnitaire"), resultat.getInt("qteStock"), resultat.getString("couleur")));
				} else if (resultat.getString(8) == null) {
					sortie.add(new Ramette(resultat.getInt("idArticle"), resultat.getString("reference"), resultat.getString("marque"), resultat.getString("designation"), resultat.getFloat("prixUnitaire"), resultat.getInt("qteStock"), resultat.getInt("grammage")));
				} else {
					System.err.println("Le type n'a pas pu être identifié");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (statement !=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				JdbcTools.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	return sortie;
	}

	@Override
	public List<Article> selectByMotCle(String motcle) throws DALException {
		List<Article> sortie = new ArrayList<>();
		String QUERY = "SELECT * FROM articles WHERE marque=? OR designation LIKE '{$?}'";
		try {
			//Chargement du driver
			Class.forName(Settings.getProperty("driverjdbc"));
			//Obtenir connexion
			connect = JdbcTools.getConnection();
			//Créer le statement
			prepStatement = connect.prepareStatement(QUERY);
			prepStatement.setString	(1, motcle);
			
			ResultSet resultat = prepStatement.executeQuery();
			while (resultat.next()) {
				if(resultat.getString(8) != null) {
					sortie.add(new Stylo(resultat.getInt("idArticle"), resultat.getString("reference"), resultat.getString("marque"), resultat.getString("designation"), resultat.getFloat("prixUnitaire"), resultat.getInt("qteStock"), resultat.getString("couleur")));
				} else if (resultat.getString(8) == null) {
					sortie.add(new Ramette(resultat.getInt("idArticle"), resultat.getString("reference"), resultat.getString("marque"), resultat.getString("designation"), resultat.getFloat("prixUnitaire"), resultat.getInt("qteStock"), resultat.getInt("grammage")));
				} else {
					System.err.println("Le type n'a pas pu être identifié");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (statement !=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				JdbcTools.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	return sortie;
	}
}
