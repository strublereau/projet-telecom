package default_package;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
public class SAVESLAVESHAVADOOP {


	// Ecriture des fichiers sorties
	public static void writeUMX(String[] line, String namefichier, String dirwork)
			throws FileNotFoundException {
		HashMap<String, Integer> comptageMot = new HashMap<>();
		FileOutputStream fos = new FileOutputStream(dirwork + namefichier);
		PrintWriter pw = new PrintWriter(fos);
		Integer i = 0;
		for (String mot : line) {
			pw.println(mot + " 1");
		//	System.out.println("Ecriture : " + mot);
			if (mot.equals("")) {
				continue;
			} else {
				if (comptageMot.containsKey(mot)) {
					 i = comptageMot.get(mot) + 1;
				} else {
					comptageMot.put(mot,  i);
					System.out.println(mot);
				}
			}
		}
		System.out.println(namefichier);
		pw.close();
	}
	
	public static HashMap<String, Integer> listeWord(String s) {
		HashMap<String, Integer> comptageMot = new HashMap<>();
		Integer i = 0;
		for (String word : s.split(" ")) {
			if (word.equals("")) {
				continue;
			} else {
				if (comptageMot.containsKey(word)) {
					 i = comptageMot.get(word) + 1;
				} else {
					comptageMot.put(word,  i);
				}
			}
		}
		return comptageMot;
	}
	
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		// Récuperation du parametre en entrée nom du fichier Input
		if (args.length != 1) {
			System.out.println("Usage : <progname> <param1>");
			System.exit(1);
		}
		//System.out.println("Argument 1 : " + args[0]);
		String nomInput = args[0];
		String indice = args[0].substring(1);
		//System.out.println(indice);
		// Lecture du fichier input
		String dirwork = "/cal/homes/strublereau/workspace/";
		String fichier1 = dirwork + nomInput;
		ArrayList<String> resultat_entrant = new ArrayList<String>();
		resultat_entrant = readFile.readLines(fichier1);
		int i = 0;
		String nameS = "UM";
		String nameSortie = "";

		// creation des fichiers pour les masters 
		for (String line : resultat_entrant) {
		//	System.out.println(line);
			// découper la ligne en mot
			String [] listemot  = line.split(" ");
			//for (String mot : listemot) {
			//	System.out.println(mot);
			//}
			nameSortie = nameS + indice;
			writeUMX(listemot,nameSortie, dirwork);
			i = i + 1;
		}
	
	}
}

