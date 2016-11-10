package default_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class threadMapping implements Runnable {
	private String  machine;
	String nomFichier;
	private ArrayList<String> listemot;
	private String fichierM;
	
	// constructeur pour récuperer la macine de l'appelant
	public threadMapping(String machine, String nomFichier) {
		super();
		this.machine = machine;
		this.nomFichier = nomFichier;
		this.listemot = new ArrayList<String> ();
		this.fichierM = "pb fichier M";
	}
	public ArrayList<String> getListeMot() {
		return this.listemot;
	}
	public String getMachine() {
		return this.machine;
	}
	public String getfichierM() {
		return this.fichierM;
	}

	public String lancementMachine(String machine, String nomFichier) {
		String mode = "modeSXUMX";
		String parametre = mode + " " + nomFichier;
		String[] args1 = { "/usr/bin/ssh",  "strublereau@" + machine , " java -jar ~/workspace/projet-telecom/TPdistrib/slaveshavadoop.jar " + parametre };
		   try {
			  //args1[1]= "strublereau@" + machine;
			  //System.out.println("args1 : " + args1[0] + " "+ args1[1]);
			  ProcessBuilder pb = new ProcessBuilder(args1);
		      pb = pb.redirectErrorStream(true); // on mélange les sorties du processus
		      Process p = pb.start();
		      InputStream is = p.getInputStream(); 
		      InputStreamReader isr = new InputStreamReader(is);
		      BufferedReader br = new BufferedReader(isr);
		      String ligne; 
		      while (( ligne = br.readLine()) != null) { 
		         // ligne contient une ligne de sortie normale ou d'erreur
		    	  System.out.println("retour thread lancé: "+ ligne);
		    	  this.listemot.add(ligne);
		      }
		      // on récupère le dernier element pour récuperer le fichier UM créer (return de la fonction remove)
		      // et enlever cet élément de la liste des mots
	    	  this.fichierM = this.listemot.remove(this.listemot.size() - 1);
		      System.out.println("Tread fini " + nomFichier);
		      } catch (IOException e) {
		    	  System.out.println("Problème dans classe ThreadMappinig ");
		      } 	
			return "Execution";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		lancementMachine(machine, nomFichier);
	}
}
