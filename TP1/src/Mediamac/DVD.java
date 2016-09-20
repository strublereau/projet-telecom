package Mediamac;

public class DVD extends Media{
	

	private int zone;
	// contructors
	public DVD(String titre, String auteur, int zone) {
		super(titre,auteur);
		if((zone < 0 || zone > 8)) {
			System.out.println("Erreur probleme de zone pour " + titre + " et " + auteur );
			System.exit(1);
		}
		else {
			this.zone = zone;
		}
	}
	// getter
	public int getZone() {
		return zone;
	}
	public boolean readable(int[] zones) {
		if (this.zone == 0){
			return true;
		}
		for (int i : zones){
			if (i == this.zone){
				return true;
			}
		}
		return false;
	}
	
	public boolean estTrouve (String critere, String valeur){
		   if (critere.equals("zone")){
			  int zone = Integer.parseInt(valeur);
			  if(this.readable(new int[]{ zone})){
				  return true;  
			  }
		   } 
		   else {
			   return super.estTrouve(critere, valeur);
		   }
		   return false;
	   }
	
	public String toString() {
		return super.toString() + " [" + this.zone + "]";
	}
}
