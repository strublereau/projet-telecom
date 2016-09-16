package media;

class Media {
	private String titre;
	private String auteur;
	private String typemedia;
	
/*	public Media() {
		this.titre = "non renseigné";
		this.auteur = "non renseigné";
		this.typemedia = "non connu";
	}*/
	
	public Media(String titre) {
		this.titre = titre;
		this.auteur = "non renseigné";
		this.typemedia = "non connu";
	}
}
