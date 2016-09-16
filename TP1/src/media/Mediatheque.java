package media;
import java.util.ArrayList;
import java.util.HashMap;

class Mediatheque {
	
	public static void main(String[] args) {
		
	}
	
	/*
	 * Dans une classe Mediatheque, la base de données de la médiathèque est une
	 * ArrayList de Medium. 1. Écrivez une méthode add(Media m) qui permet
	 * d’ajouter un média à la collection. 2. Écrivez une méthode filtre(String
	 * critere, String valeur qui retourne un ArrayList<Media> contenant
	 * l’ensemble des média vérifiant le critère passé en paramètre. Ce critère
	 * peut porter : — sur l’auteur ; — sur le titre ; — sur le type de média
	 * (on pourra utiliser l’instruction nomObjet instanceof nomClasse qui
	 * retourne true si l’objet nomObjet est une instance de la classe nomClasse
	 * ou de l’une de ses classes filles) Par exemple : m.filtre("titre",
	 * "toto") renverra l’ensemble des média dont le titre est toto et
	 * m.filtre("media", "CD") renverra l’ensemble des CD de la collection.
	 */

	// construteur
	private ArrayList<Media> media;
	
	public Mediatheque(ArrayList<Media> medis) {
		this.media = medis; 
	}

	public Mediatheque add(Media m){
		ArrayList<Media> creamedium = new ArrayList<>();
		creamedium.add(m);
		return this;
	}

}
