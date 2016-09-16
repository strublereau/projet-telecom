import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class molecule {

	// public static ArrayList<String> readLines(String filename) {
	// List<String> lines = null;
	// try {
	// lines = Files.readAllLines(Paths.get(filename),
	// Charset.forName("UTF-8"));
	// } catch (IOException e) {
	// System.out.println("Erreur lors de la lecture de " + filename);
	// System.exit(1);
	// }
	// conversation facultative
	// return (ArrayList<String>) lines;
	// }

	// Demonstrate FileReader.

	public static ArrayList<String> readlines(String filename) {
		ArrayList<String> Liste = new ArrayList<String>();
		FileReader fr;
		try {
			fr = new FileReader(filename);

			BufferedReader br = new BufferedReader(fr);
			int i = 0;
			String s;
			while ((s = br.readLine()) != null) {
				//System.out.println(s);
				Liste.add(s);
				i = i + 1;
			}
			fr.close();
		} catch (IOException e) {
			System.out.print("Erreur accès fichier");
			System.exit(1);
		}
		return (ArrayList<String>) Liste;
	}

	public static HashMap<String,Integer> readInventory(String fileName) {
		ArrayList<String> listeAtomes = readlines(fileName);
		HashMap<String, Integer> inventory = new HashMap<>();
		String s = " "; 
		for (int i = 0; i < listeAtomes.size(); i=i+1){
			 s = listeAtomes.get(i); 
			 String[] w = s.split(" ");
			 String name = w[0];
			 Integer number = Integer.parseInt(w[1]);
			 System.out.print("name : " + name );
			 System.out.print("number : " + number );
			 inventory.put(name, number);
		}
		return (HashMap<String, Integer>) inventory;
	}
	
		
	/*	for (String w : listeAtomes.split(" ")) {
			if (word.equals("")) {
				continue;
			} else {
				if (comptageMot.containsKey(word)) {
					// System.out.println("get:" + comptageMot.get(word));
					Integer i = comptageMot.get(word) + 1;
					// System.out.println("word : " + word );
					// System.out.println("i : " + i);
					comptageMot.put(word, i);
				} else {
					comptageMot.put(word, 1);
				}
			}
		}*/

	public static void main(String[] args) {
		String nomFichier = "atomes.txt";
		HashMap<String, Integer> listeAtomes = readInventory(nomFichier);
		System.out.println(listeAtomes);
//		nomFichier = "formules.txt";
//		ArrayList<String> listeformules = readlines(nomFichier);
//		System.out.println(listeformules);
	}
}
