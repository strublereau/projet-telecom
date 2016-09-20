package Mediamac;

/* La description du CD comporte également le format du contenu 
 * décrit par une chaine de caractères ("CD musical", "OGG", ou "MP3"). 
 * La méthode toString est modifiée pour indiquer le format et renvoie, 
 * par exemple :
 * "Some Kind Of Trouble" par James Blunt [CD musical]*/
public class CD extends Media {
	String format;
	
	public CD(String titre, String auteur, String format) {
		super(titre,auteur);
		this.format = format;
		if(!(this.format.equals ("CD musical")) && !(this.format.equals("OGG")) && !(this.format.equals("MP3"))) {
				System.out.println("Erreur probleme de format");
				System.exit(1);
		}
	}
	
	public String getFormat() {
		return format;
	}

	public boolean estTrouve (String critere, String valeur){
		   if (critere.equals("format")){
			  if(this.format.equals(valeur)){
				  return true;  
			  }
		   } 
		   else {
			   return super.estTrouve(critere, valeur);
		   }
		   return false;
	   }
	public String toString() {
		return super.toString() + " [" + this.format + "]";
		}
}
