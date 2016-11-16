package default_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class threadSuffling implements Runnable {
	private String  machine;
	ArrayList<String> listenomFichier;
	private String mot;
	private String fichierRM;
	private String nomFichier;
	//ArrayList<String> lignemot;
	private String lignemot;
	
	// constructeur pour récuperer la macine de l'appelant
	public threadSuffling(String machine, String nomFichier, String mot, ArrayList<String> listenomFichier) {
		super();
		this.machine = machine;
		this.listenomFichier = listenomFichier;
		this.nomFichier = nomFichier;
		this.mot = mot;
	//	this.lignemot = new ArrayList<String> ();
		this.lignemot = "pb recuperation comptage RM";
		this.fichierRM = "pb fichier RM";
	}
	// methode recuperation mot recherche
	public String getMot() {
		return this.mot;
	}
	// methode recuperation de la ligne renvoyé par le slave
	public String getligneMot() {
		return this.lignemot;
	}
	// methode recuperation machine
	public String getMachine() {
		return this.machine;
	}
	//methode recuperation fichier RM
	public String getfichierRM() {
		return this.fichierRM;
	}

	public String lancementMachine(String machine, String nomFichier, String mot,  ArrayList<String> listenomFichier) {
		String mode = "modeUMXSMX";
		// faire une boucle pour alimenter le paramettre transmis 
		String parametre = mode + " " + nomFichier + " " + mot;
		for (String nomF : listenomFichier) {
			parametre = parametre + " " + nomF;
		}
		System.out.println("parametre : " + parametre);
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
		         // ligne contient une ligne de sortie normale : a priori le mot et son nombre
		    	  System.out.println("retour thread lancé: "+ ligne);
		    	 // this.lignemot.add(ligne);
		    	  this.lignemot = ligne;
		      }
		      // on récupère le dernier element pour récuperer le fichier UM créer (return de la fonction remove)
		      // et enlever cet élément de la liste des mots
	    	  //this.fichierRM = this.lignemot.remove(this.lignemot.size() - 1);
		      System.out.println("Tread fini " + nomFichier);
		      } catch (IOException e) {
		    	  System.out.println("Problème dans classe ThreadMappinig ");
		      } 	
			return "Execution";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		lancementMachine(machine, nomFichier, mot, listenomFichier);
	}

}
