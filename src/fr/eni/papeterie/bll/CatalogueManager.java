package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {

	private DAO<Article> daoArticles;
	
	public CatalogueManager() throws BLLException {
		try {
			daoArticles = DAOFactory.getArticleDAO();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public List<Article> getCatalogue() {
		List<Article> articles = null;
		try {
			articles = daoArticles.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	public void addArticle(Article article) throws BLLException {
		try {
			daoArticles.insert(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public void updateArticle(Article article) throws BLLException {
		try {
			daoArticles.update(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	public void removeArticle(Article article) throws BLLException {
		try {
			daoArticles.delete(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * valide les données d'un article
	 */
	public void validerArticle(Article article) throws BLLException {
		StringBuilder sortieExc = new StringBuilder();
		boolean valide = true;
		
		if(article==null) {
			throw new BLLException("Article null");
		}
		//Les attributs des articles sont obligatoires
		if(article.getReference()==null || article.getReference().trim().length()==0) {
			sortieExc.append("Pas de référence\n");
			valide = false;
		}
		if(article.getMarque()==null || article.getMarque().trim().length()==0) {
			sortieExc.append("Pas de marque\n");
			valide = false;
		}
		if(article.getDesignation()==null || article.getDesignation().trim().length()==0) {
			sortieExc.append("Pas de designation\n");
			valide = false;
		}
		if(article instanceof Ramette && ((Ramette)article).getGrammage()<=0) {
			sortieExc.append("Grammage inférieur à 1\n");
			valide = false;
		}
		if(article instanceof Stylo ) {
			Stylo s = (Stylo) article;
			if(s.getCouleur()==null || s.getCouleur().trim().length()==0) {
				sortieExc.append("Pas de couleur\n");
				valide = false;
			}
		}
		
		if(!valide){
			throw new BLLException(sortieExc.toString());
		}
	}
	
	public Article getArticle(int index) throws BLLException {
		Article article = null;
		try {
			article = daoArticles.selectById(index);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public List<Article> getMarque(String marque) throws BLLException {
		List<Article> sortie = null;
		try {
			sortie = daoArticles.selectByMarque(marque);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return sortie;
	}
}
