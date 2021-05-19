package fr.eni.papeterie.bo;

public class Ligne {

	private int qte;
	private Article article;

	public Ligne(Article article, int qte) {
		this.qte = qte;
		this.article = article;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQte() {
		return qte;
	}

	public float getPrix() {
		return qte * article.getPrixUnitaire();
	}
	
	public void setQte(int qte) {
		this.qte = qte;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " " + article.toString() + " quantité : " + getQte();
	}
}
