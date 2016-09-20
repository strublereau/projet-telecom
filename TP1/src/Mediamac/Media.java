package Mediamac;

abstract class Media {
	private String titre;
	private String auteur;
	private double notes;
	private double nombreVote;

	/*
	 * public Media() { this.titre = "non renseigné"; this.auteur =
	 * "non renseigné"; this.typemedia = "non connu"; }
	 */

	public Media(String titre, String auteur) {
		this.titre = titre;
		this.auteur = auteur;
		this.notes = 0.0;
		this.nombreVote = 0.0;
	}
   
	 public double moyenneNotes() {
			if (this.nombreVote == 0) {
			    return 0.0;
			} else {
			    return this.notes / this.nombreVote;
			}
         }

	 public void vote(double note) {
			if (note < 0 || note > 5) {
			    return;
			}
			this.notes += note;
			this.nombreVote ++;
			 System.out.println("note :" + this.notes);
			 System.out.println("nombre.vote :" + this.nombreVote);
			 
   }
	
	
	public String getTitre() {
		return titre;
	}
	public String getAuteur() {
		return auteur;
	}
    public boolean estTrouve (String critere, String valeur){
	   if (critere.equals("titre")){
		  if(this.titre.equals(valeur)){
			  return true;  
		  }
	   } 
	   else {
		   if (critere.equals("auteur")) {
			   if(this.auteur.equals(valeur)){
				   return true;
			   }   
		   }
	   }
	   return false;
   }
	// "Titre" par Auteur
	public String toString() {
		return "Titre : " + this.titre + "  par " + this.auteur;
	}
}
