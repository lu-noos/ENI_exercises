package fr.eni.papeterie.ihm;

import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;

public class Controller implements IPanelBoutonsObserver {

	private WindowArticles fenetre;
	private CatalogueManager mngr;
	private int index;
	private List<Article> articles;
	private static Controller instance;
	
	private Controller() {
		try {
			mngr = new CatalogueManager();
			articles = mngr.getCatalogue();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
	public static synchronized Controller get() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public void startApp(){
		fenetre = new WindowArticles();
		afficherPremierArticle();
		fenetre.setVisible(true);
	}
	
	public void afficherPremierArticle(){
		System.out.println("Démarrage de l'application");
		index=0;
		fenetre.afficher(articles.get(index));
	}
	
	public void precedent(){
		System.out.println("Précédent...");
		if (index>0) {
			index--;
			System.out.println(index);
				fenetre.afficher(articles.get(index));
		} else {
			System.err.println("Premier article du catalogue - retour impossible");
		}
	}
	
	public void suivant() {
		System.out.println("Suivant...");
		if (index<articles.size()-1) {
			index++;
			System.out.println(index);
				fenetre.afficher(articles.get(index));
		} else {
		System.err.println("Dernier article du catalogue - avancée impossible");
		}
	}
	
	public void nouveau() {
		System.out.println("Nouveau...");
		articles.add(new Ramette(0, "Reference", "Marque", "Designation", 0.0f, 0, 80));
		index = articles.size()-1;
		System.out.println(index);
		
		fenetre.afficher(articles.get(index));
	}
	
	public void enregistrer() {
		Article articleAffiche = fenetre.getArticle();
		System.out.println("Enregistrer...");
		System.out.println(index);
		try {
			if (mngr.getArticle(articles.get(index).getIdArticle())==null) {
				mngr.addArticle(articleAffiche);
			} else {
				mngr.updateArticle(articleAffiche);
			}
			articles = mngr.getCatalogue();
			fenetre.afficher(articles.get(index));
		} catch (BLLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void supprimer() {
		System.out.println("Supprimer...");
		try {
			if (mngr.getArticle(articles.get(index).getIdArticle())!=null) {
					System.out.println("Tentative de suppression...");
					mngr.removeArticle(articles.get(index));
					System.out.println("Suppression effectuée");
			}
		} catch (BLLException e1) {
			e1.printStackTrace();
		}
		articles.remove(index);
		if (index>0) {
			index--;
		}
		System.out.println(index);
		fenetre.afficher(articles.get(index));
	}
	
	
}













