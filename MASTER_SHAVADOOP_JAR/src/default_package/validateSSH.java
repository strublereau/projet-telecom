package default_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class validateSSH {

	public static ArrayList<String> accessMachines(ArrayList<String> listeMachines) {

		ArrayList<String> listMachines_OK = new ArrayList<String>();
		String[] args1 = { "/usr/bin/ssh",  "strublereau@<host>", "echo $((2+3))" };
		ProcessBuilder pb = new ProcessBuilder(args1);
		pb = pb.redirectErrorStream(true); // on mélange les sorties du
												// processus	
		// lecture des machines transmises
		for (String machine : listeMachines) {
			System.out.println("machine testée " + machine);
			args1[1]= "strublereau@" + machine;
			try {
				// passage de la commande
				pb.command(args1);
				//
				Process p = pb.start();
				InputStream is = p.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				System.out.println("Tests : " + machine);				
				p.waitFor();
				if (p.exitValue()==0){
					listMachines_OK.add(machine);
					System.out.println("Le résultat OK " + machine);
				}
				else {
					String ligne;
					while ((ligne = br.readLine()) != null) {
						// ligne contient une ligne de sortie normale ou d'erreur
						System.out.println("Le résultat KO " + ligne);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Pas connecté" + machine);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listMachines_OK;
	}
}