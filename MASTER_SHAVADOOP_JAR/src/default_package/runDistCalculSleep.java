package default_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class runDistCalculSleep {
	public static String runDist(ArrayList<String> resultat_ssh_OK) {
//	public static String runDistCalculSleep2(ArrayList<String> resultat_ssh_OK) {
		String[] args1 = { "/usr/bin/ssh", "strublereau@<host>","java -jar ~/workspace/projet-telecom/TPdistrib/calculsleep.jar" };
		try {
			args1[1] = "strublereau@" + resultat_ssh_OK.get(0);
			ProcessBuilder pb = new ProcessBuilder(args1);
			pb = pb.redirectErrorStream(true); // on mélange les sorties du
											// processus
		Process p = pb.start();
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String ligne;
		while ((ligne = br.readLine()) != null) {
			// ligne contient une ligne de sortie normale ou d'erreur
			System.out.println("Calcul lancé: " + ligne);
		}
		System.out.println("Tout est fini ");
		} catch (IOException e) {
		}
	return "Fin run distant Calcul sleep";	
}
}