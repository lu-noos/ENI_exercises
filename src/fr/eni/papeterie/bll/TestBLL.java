package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public class TestBLL {

	public static void main(String[] args) {
		List<Article> articles;
		List<Article> reponsemarque = null;
		CatalogueManager mngr = null;
		
		try {
			mngr = new CatalogueManager();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		articles = mngr.getCatalogue();
		try {
			reponsemarque = mngr.getMarque("Sharpie");
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		System.out.println(articles);
		System.out.println(reponsemarque);
	}
	
}
