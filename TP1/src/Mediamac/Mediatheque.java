package Mediamac;

import java.util.ArrayList;
import java.util.HashMap;

class Mediatheque {
	
	
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
	private ArrayList<Media> listeMedia;
	
	public Mediatheque() {
		this.listeMedia = new ArrayList<Media> (); 
	}
    // ajout un media à l'objet mediatheque 
	public void add(Media m){
		this.listeMedia.add(m);
		
	}
	// filtre de la mediateque sur un critere et une valeur de ce citere 
	
	public ArrayList<Media> filtre(String critere, String valeur){
		ArrayList<Media> resultat = new ArrayList<Media>();
		for (Media element : this.listeMedia){
		// recherche si l'élément type Media correspond au critère transmis 
			if( element.estTrouve(critere, valeur)){
				resultat.add(element);
			}
		}
		return resultat;
	}
	// 
	public static void main(String[] a) {
		Mediatheque m = new Mediatheque();
		
		m.add(new Livre("titrelivre a", "auteur b"));
		m.add(new DVD("titre dvd a", "auteur b", 0));
		m.add(new DVD("titre dvd a", "auteur b", 1));
		m.add(new DVD("titre dvd c", "auteur c", 8));
		m.add(new CD("titre CD a", "auteur b", "MP3"));
		for (Media el : m.filtre("zone", "8")) {
			double note = 5.0;
			el.vote(note);
			note = 2.0;
			el.vote(note);
		    System.out.println(el);
		    double moyenne = el.moyenneNotes();
		    System.out.println(" moyenne : " + moyenne);
		}
    }

}