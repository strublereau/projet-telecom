package default_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class threadMachine implements Runnable {
	String machine;
	// constructeur pour récuperer la macine de l'appelant
	public threadMachine(String machine) {
		super();
		this.machine = machine;
	}

	public static String lancementMachine(String machine) {

		String[] args1 = { "/usr/bin/ssh",  "strublereau@<host>", "java -jar ~/workspace/projet-telecom/TPdistrib/calculsleep.jar" };
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
		    	  System.out.println("Calcul lancé: "+ ligne);
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
