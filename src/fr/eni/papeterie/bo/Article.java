package fr.eni.papeterie.bo;

public abstract class Article {
	/*	idArticle Primary 	int(11) 								No 	None 		AUTO_INCREMENT
		reference 			char(10) 		latin1_swedish_ci 		No 	None
	 	marque 				varchar(200) 	latin1_swedish_ci 		No 	None
	 	designation 		varchar(250) 	latin1_swedish_ci 		No 	None
	 	prixUnitaire 		float 									No 	None
	 	qteStock 			int(11) 								No 	None
	 	grammage 			int(11) 								Yes NULL
	 	couleur 			varchar(50) 	latin1_swedish_ci 		Yes NULL
		type				char(10) 		latin1_swedish_ci 		No 	None 	
	*/
	
	private Integer idArticle;
	private String reference;
	private String marque;
	private String designation;
	private float prixUnitaire;
	private int qteStock;
	
	public Article(Integer idArticle, String reference, String marque, String designation, float prixUnitaire,
			int qteStock) {
		this.idArticle = idArticle;
		this.reference = reference;
		this.marque = marque;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.qteStock = qteStock;
	}

	public Article(String reference, String marque, String designation, float prixUnitaire, int qteStock) {
		this.idArticle=null;
		this.reference = reference;
		this.marque = marque;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.qteStock = qteStock;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public String getReference() {
		return reference;
	}

	public String getMarque() {
		return marque;
	}

	public String getDesignation() {
		return designation;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public int getQteStock() {
		return qteStock;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" "+ (getIdArticle() != null ? "id : " + getIdArticle() + ", " : "")
				+ (getReference() != null ? "reference : " + getReference() + ", " : "")
				+ (getMarque() != null ? "marque : " + getMarque() + ", " : "")
				+ (getDesignation() != null ? "designation : " + getDesignation() + ", " : "") + "prixUnitaire : " + getPrixUnitaire()
				+ ", qteStock : " + getQteStock();
	}

}
