package fr.eni.papeterie.bo;

import java.util.ArrayList;

public class Panier {
	
	private float montant;
	private ArrayList<Ligne> lignesPanier;
	
	public Panier() {
		montant = 0;
		lignesPanier = new ArrayList<>();
	}

	public float getMontant() {
		return montant;
	}

	public Ligne getLigne(int index) {
		return lignesPanier.get(index);
	}
	
	public ArrayList<Ligne> getLignesPanier() {
		return lignesPanier;
	}
	
	public void addLigne(Article article, int qte) {
		Ligne nouvelle = new Ligne(article, qte);
		lignesPanier.add(nouvelle);
		montant += nouvelle.getPrix();
	}
	
	public void updateLigne(int index, int newQte) {
		montant -= lignesPanier.get(index).getPrix();
		lignesPanier.get(index).setQte(newQte);
		montant += lignesPanier.get(index).getPrix();
	}
	
	public void removeLigne(int index) {
		montant -= lignesPanier.get(index).getPrix();
		lignesPanier.remove(index);
	}
	
	public String toString() {
		StringBuilder panierStr = new StringBuilder("");
		for (Ligne ligne : lignesPanier) {
			if(ligne != null) {panierStr.append(ligne.toString() + "\n");} else {panierStr.append("");};
		}
		panierStr.append("Montant : " + getMontant() +"€");
		return panierStr.toString();
	}
}
