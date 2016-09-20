package exercicesj2;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Molecule1 {
	//
	// methode de lecture de fichier generique qui produit une ArrayList
	//
	public static ArrayList<String> readlines(String filename) {
		ArrayList<String> Liste = new ArrayList<String>();
		FileReader fr;
		try {
			fr = new FileReader(filename);
			int i = 0;
			String s;

			BufferedReader br = new BufferedReader(fr);

			while ((s = br.readLine()) != null) {
				// System.out.println(s);
				if (s.startsWith("#")) {
					continue;
				}
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

	//
	// Récupération de l'inventaire contenu dans le fichier transmis
	//
	public static HashMap<String, Integer> readInventory(String fileName) {
		//
		ArrayList<String> listeAtomes = readlines(fileName);
		HashMap<String, Integer> inventory = new HashMap<>();
		String s = " ";
		//
		// Parcourt de l'ArrayList pour ne mettre dns la hashmap que les atomes
		// et leur quantité
		//
		for (int i = 0; i < listeAtomes.size(); i = i + 1) {
			// lire un enregistrement
			s = listeAtomes.get(i);
			// récupération du nom
			String[] w = s.split(" ");
			// Récupération du nom
			String name = w[0];
			// Récupération de la quantité
			Integer number = Integer.parseInt(w[1]);
			System.out.println("name : " + name);
			System.out.println("number : " + number);
			inventory.put(name, number);
		}
		return (HashMap<String, Integer>) inventory;
	}

	//
	// Récupération du dictionnaire des formules Liste formules (nom formule,
	// Liste(atome, quantité)) à partir du contenu dans le fichier transmis
	//
	public static HashMap<String, HashMap<String, Integer>> readFormulas(String fileName) {
		// Mise en arraylist du fichier à lire
		ArrayList<String> listeFormulesRead = readlines(fileName);
		HashMap<String, HashMap<String, Integer>> listeFormules = new HashMap<String, HashMap<String, Integer>>();
		String s = " ";
		//
		// boucle sur la liste pour alimenter une hasmap(nom molecule, hasmap(composant,quantite)
		//
		for (int i = 0; i < listeFormulesRead.size(); i = i + 1) {

			// lire un element de la liste 
			s = listeFormulesRead.get(i);

			// récupération du nom
			// 1- elimination du blanc si il existe en début et fin de la ligne
			// récupéré et des nota après le #
			String[] chaine = s.trim().split("#");

			// récupération du nom de la formule en premier avant les deux
			// points
			String[] w = chaine[0].split(":");
			String name = w[0];

			// récupération des composants de la formule
			String[] listeFormule = w[1].trim().split(" ");

			// Boucle sur les elements de liste formule
			// Récupération du nom de l'atome et sa quantité
			HashMap<String, Integer> hlistFormules = new HashMap<String, Integer>();
			String molecule = "";
			int quantite = 0;
			for (int j = 0; j < listeFormule.length; j = j + 1) {
				molecule = listeFormule[j];
				// System.out.print("nombre atme: " + molecule);
				j = j + 1;
				quantite = Integer.parseInt(listeFormule[j]);
				// System.out.println("composant : " + quantite);
				hlistFormules.put(molecule, quantite);
			}
			// Ecriture de la hasmap principale(contenant string, hasmap)
			listeFormules.put(name, hlistFormules);
			// System.out.println("name : " + name);
			// System.out.println("hlistFormules : " + hlistFormules);
		}
		return listeFormules;
	}
	//
	// determination des formules realisable par rapport à ce qui est disponible dans le stocke 
	// afin de produire une liste de molecule pour permettre choix de production 
	//
	public static ArrayList<String> makeable(HashMap<String, Integer> invontry,
			HashMap<String, HashMap<String, Integer>> formula) {

		ArrayList<String> list = new ArrayList<String>();

		for (String molecule : formula.keySet()) {
			/*
			 * appel à une methode qui regarde si disponible en stoke on
			 * transmet la hasmap des composants et leur quantit de la
			 * molecule(formule)
			 * 
			 */
			if (disponibleInvontry(formula.get(molecule), invontry)) {
				list.add(molecule);
			}
		}
		// on retourne la list des molecule
		return list;

	}
	// methode qui regarde si disponible en stoke en entree
	// une hasmap des composants et leur quantite de la
	// molecule(formule) et l'inventaire liste  (atome, quantite)
	// retourne un boolean 
	public static boolean disponibleInvontry(HashMap<String, Integer> composition, HashMap<String, Integer> invontry) {

		boolean ok = true;
		// parcours des composants de la formule transmises et comporaison  
		for (String composant : composition.keySet()) {
			if (invontry.get(composant) != null && composition.get(composant) <= invontry.get(composant)) {
				ok = true;
			} else {
				ok = false;
			}
		}
		return ok;
	}

	public static void main(String[] args) {

		// lecture inventaire et mise en hashmap provenant du fichier atomes.txt
		String nomFichier = "atomes.txt";
		HashMap<String, Integer> listeInvontry = readInventory(nomFichier);

		// print liste des atomes et quantité
		System.out.println(" ////////////////// inventaire ///////////////////////////////////////////");
		System.out.println(listeInvontry);

		// lecture des formules dans catlogue et mise en hashmap provenant du
		// fichier formules.txt
		nomFichier = "formules.txt";

		HashMap<String, HashMap<String, Integer>> listeformules = readFormulas(nomFichier);
		// print liste formules et composition de chaque composant
		System.out.println("//////////////// Fourmules et compositions  ///////////////////////////////");
		System.out.println(listeformules);

		// determination des formules possibles avec le stock disponible
		ArrayList<String> list_molecule = new ArrayList<String>();
		list_molecule = makeable(listeInvontry, listeformules);
		// print liste des formules possibles
		System.out.println(" ///////////////   PRODUCTION \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");

		for (String s : list_molecule) {

			System.out.println(s);
		}
	}
}
