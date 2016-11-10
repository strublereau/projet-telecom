package default_package;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class SLAVESHAVADOOP {
	// Ecriture des fichiers sorties UMX
	public static void writeUMX(String[] line, String namefichier,
			String dirwork) throws FileNotFoundException {
		HashMap<String, Integer> comptageMot = new HashMap<>();
		FileOutputStream fos = new FileOutputStream(dirwork + namefichier);
		PrintWriter pw = new PrintWriter(fos);
		Integer i = 0;
		for (String mot : line) {
			pw.println(mot + " 1");
			// System.out.println("Ecriture : " + mot);
			if (mot.equals("")) {
				continue;
			} else {
				if (comptageMot.containsKey(mot)) {
					i = comptageMot.get(mot) + 1;
				} else {
					comptageMot.put(mot, i);
					System.out.println(mot);
				}
			}
		}
		System.out.println(namefichier);
		pw.close();
	}

	public static void writeSMX(String[] line, String namefichier,
			String dirwork) throws FileNotFoundException {
		// HashMap<String, Integer> comptageMot = new HashMap<>();
		FileOutputStream fos = new FileOutputStream(dirwork + namefichier);
		PrintWriter pw = new PrintWriter(fos);
		// Integer i = 0;
		for (String mot : line) {
			pw.println(mot + " 1");
			// // System.out.println("Ecriture : " + mot);
			// if (mot.equals("")) {
			// continue;
			// } else {
			// if (comptageMot.containsKey(mot)) {
			// i = comptageMot.get(mot) + 1;
			// } else {
			// comptageMot.put(mot, i);
			// System.out.println(mot);
			// }
			// }
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
					comptageMot.put(word, i);
				}
			}
		}
		return comptageMot;
	}

	public static String mapping(String nomInput, String indice)
			throws InterruptedException, FileNotFoundException {
		// Récuperation du parametre en entrée nom du fichier Input
		// System.out.println(indice);
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
			// System.out.println(line);
			// découper la ligne en mot
			String[] listemot = line.split(" ");
			// for (String mot : listemot) {
			// System.out.println(mot);
			// }
			nameSortie = nameS + indice;
			writeUMX(listemot, nameSortie, dirwork);
			i = i + 1;
		}
		return "OK";
	}

	public static void main(String[] args) throws InterruptedException,
			FileNotFoundException {
		// Récuperation du parametre en entrée nom du fichier Input
		if (args.length < 2) {
			System.out.println("Usage : <progname> <param1>" + args);
			System.exit(1);
		}
		// System.out.println("Argument 2 : " + args[0]);
		// Traitement de l'argument
		// premier argument de la liste est le mode
		if (args[0].equals("modeSXUMX")) {
			String nomInput = args[1];
			String indice = args[1].substring(1);
			// System.out.println(indice);
			// Lecture du fichier input
			mapping(nomInput, indice);
		} else if (args[0].equals("modeUMXSMX")) {
			Integer i = 0;
			System.out.println(" On entre n modeUMXSMX " + args[1]);
//			ArrayList<String> listeLigneSMX = new ArrayList<String>();
			String nameSM = "";
			String motUMX = "";
			// XXXXXXXXXX
			// preparation écriture fichier sortie
			String dirwork = "/cal/homes/strublereau/workspace/";
			FileOutputStream fos = new FileOutputStream(dirwork + nameSM);
			PrintWriter pw = new PrintWriter(fos);
			for (String argument : args) {
				if (i > 0) {
					if (i == 1) {
						// récupération du mot à récuperer dans les fichiers
						motUMX = argument;
					} else {
						if (i == 2) {
							// récupération du nom de fichiers
							nameSM = argument;
						} else {
							ArrayList<String> listeLigne = new ArrayList<String>();
							listeLigne = readFile.readLines(dirwork + argument);
							// creation des fichiers pour les masters
							for (String line : listeLigne) {
								// System.out.println(line);
								// découper la ligne en mot (ici le mot + 1
								String[] listemot = line.split(" ");
								if (listemot[0].equals(motUMX)) {
									// alimentation d'un arrayList listeLigneSM
									pw.println(line);
								}
							}
							// lecture du fichier

							// Lecture du fichier input
							// alimentation
							// shuffling(mot, nomInput, indice);
						}
					}
				}
				i = i + 1;
			}
			pw.close();
		}
	}
}