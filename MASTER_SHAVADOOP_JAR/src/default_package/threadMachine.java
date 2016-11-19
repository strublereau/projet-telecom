package default_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class threadMachine implements Runnable {
	String machine;
	boolean etat;
	// constructeur pour récuperer la machine de l'appelant
	public threadMachine(String machine) {
		super();
		this.etat = false;
		this.machine = machine;
	}
	public boolean getEtat() {
		return this.etat;
	}
	public String getMachine() {
		return this.machine;
	}	
	public String lancementMachine(String machine) {

		//String[] args1 = { "/usr/bin/ssh",  "strublereau@<host>", "java -jar ~/workspace/projet-telecom/TPdistrib/calculsleep.jar" };
		
		String[] args1 = { "/usr/bin/ssh", "strublereau@<host>", "echo $((2+3))" };
		
		   try {
			  args1[1]= "strublereau@" + machine; 
		      ProcessBuilder pb = new ProcessBuilder(args1);
		      pb = pb.redirectErrorStream(true); // on mélange les sorties du processus
		      Process p = pb.start();
		      InputStream is = p.getInputStream(); 
		      InputStreamReader isr = new InputStreamReader(is);
		      BufferedReader br = new BufferedReader(isr); 
		      String ligne;
		      while (( ligne = br.readLine()) != null) { 
		         // ligne contient une ligne de sortie normale ou d'erreur
		    	 //System.out.println("Calcul lancé: "+ " machine : " + machine + "resultat :" +  ligne);
		    	  if(ligne.equals("5")){
		    		  this.etat = true;
		    		  //System.out.println(machine + " Calcul lancé OK : "  +  ligne);
		    	  }
		    	  else {
		    		  this.etat = false;
		    		  //System.out.println(machine + " Calcul lancé KO : " +  ligne);
		    	  }
		      }
		      //System.out.println("Tout est fini " + machine);
		      } catch (IOException e) {
		    	  System.out.println("Problème dans classe ThreadMachine ");
		      } 	
			return "Execution";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		lancementMachine(machine);
	}
}
