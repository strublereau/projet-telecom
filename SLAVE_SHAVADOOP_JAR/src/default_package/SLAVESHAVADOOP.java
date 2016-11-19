package default_package;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class SLAVESHAVADOOP {

	// Liste des mots à ne pas compter
	private final static Set<String> REJECTED_WORDS;

	static {
		REJECTED_WORDS = new HashSet<String>();
		REJECTED_WORDS.add("le");
		REJECTED_WORDS.add("ou");
		REJECTED_WORDS.add("aux");
		REJECTED_WORDS.add("de");
		REJECTED_WORDS.add("des");
		REJECTED_WORDS.add("la");
		REJECTED_WORDS.add("les");
		REJECTED_WORDS.add("je");
		REJECTED_WORDS.add("l");
		REJECTED_WORDS.add("nous");
		REJECTED_WORDS.add("tu");
		REJECTED_WORDS.add("il");
		REJECTED_WORDS.add("ils");
		REJECTED_WORDS.add("elle");
		REJECTED_WORDS.add("elles");
		REJECTED_WORDS.add("lui");
		REJECTED_WORDS.add("vous");
		REJECTED_WORDS.add("leur");
		REJECTED_WORDS.add("eux");
		REJECTED_WORDS.add("celui");
		REJECTED_WORDS.add("celle");
		REJECTED_WORDS.add("ceux-là");
		REJECTED_WORDS.add("celui-ci");
		REJECTED_WORDS.add("celui-là");
		REJECTED_WORDS.add("celle-ci");
		REJECTED_WORDS.add("celle-là");
		REJECTED_WORDS.add("ceci");
		REJECTED_WORDS.add("cela");
		REJECTED_WORDS.add("ça");
		REJECTED_WORDS.add("celles-ci");
		REJECTED_WORDS.add("celles-là");
		REJECTED_WORDS.add("mien");
		REJECTED_WORDS.add("nôtre");
		REJECTED_WORDS.add("tien");
		REJECTED_WORDS.add("sien");
		REJECTED_WORDS.add("vôtre");
		REJECTED_WORDS.add("leur");
		REJECTED_WORDS.add("mienne");
		REJECTED_WORDS.add("tienne");
		REJECTED_WORDS.add("sienne");
		REJECTED_WORDS.add("miens");
		REJECTED_WORDS.add("tiens");
		REJECTED_WORDS.add("siens");
		REJECTED_WORDS.add("nôtres");
		REJECTED_WORDS.add("vôtres");
		REJECTED_WORDS.add("leurs");
		REJECTED_WORDS.add("miennes");
		REJECTED_WORDS.add("tiennes");
		REJECTED_WORDS.add("siennes");
		REJECTED_WORDS.add("on");
		REJECTED_WORDS.add("personne");
		REJECTED_WORDS.add("rien");
		REJECTED_WORDS.add("aucun");
		REJECTED_WORDS.add("aucune");
		REJECTED_WORDS.add("nul");
		REJECTED_WORDS.add("nulle");
		REJECTED_WORDS.add("un");
		REJECTED_WORDS.add("une");
		REJECTED_WORDS.add("autre");
		REJECTED_WORDS.add("pas");
		REJECTED_WORDS.add("tout");
		REJECTED_WORDS.add("quelqu");
		REJECTED_WORDS.add("quelque");
		REJECTED_WORDS.add("certains");
		REJECTED_WORDS.add("certaine");
		REJECTED_WORDS.add("certain");
		REJECTED_WORDS.add("certaines");
		REJECTED_WORDS.add("plusieurs");
		REJECTED_WORDS.add("tous");
		REJECTED_WORDS.add("qui");
		REJECTED_WORDS.add("que");
		REJECTED_WORDS.add("quoi");
		REJECTED_WORDS.add("dont");
		REJECTED_WORDS.add("où");
		REJECTED_WORDS.add("lequel");
		REJECTED_WORDS.add("laquelle");
		REJECTED_WORDS.add("duquel");
		REJECTED_WORDS.add("auquel");
		REJECTED_WORDS.add("lesquels");
		REJECTED_WORDS.add("desquels");
		REJECTED_WORDS.add("lesquelles");
		REJECTED_WORDS.add("desquelles");
		REJECTED_WORDS.add("auxquelles");
		REJECTED_WORDS.add("laquelle");
		REJECTED_WORDS.add("à");
		REJECTED_WORDS.add("et");
		REJECTED_WORDS.add("ne");
		REJECTED_WORDS.add("par");
		REJECTED_WORDS.add("du");
		REJECTED_WORDS.add("en");
		REJECTED_WORDS.add("ou");
		REJECTED_WORDS.add("où");
		REJECTED_WORDS.add("dans");
		REJECTED_WORDS.add("sur");
		REJECTED_WORDS.add("au");
		REJECTED_WORDS.add("pour");
		REJECTED_WORDS.add("est");
		REJECTED_WORDS.add("es");
		REJECTED_WORDS.add("suis");
		REJECTED_WORDS.add("sommes");
		REJECTED_WORDS.add("sont");
		REJECTED_WORDS.add("qu");
		REJECTED_WORDS.add("ni");
		REJECTED_WORDS.add("se");
		REJECTED_WORDS.add("si");
		REJECTED_WORDS.add("sa");
		REJECTED_WORDS.add("ier");
		REJECTED_WORDS.add("ce");
		REJECTED_WORDS.add("ii");
		REJECTED_WORDS.add("iii");
		REJECTED_WORDS.add("toute");
		REJECTED_WORDS.add("sans");
		REJECTED_WORDS.add("lorsqu");
		REJECTED_WORDS.add("ci");
	}

	// Ecriture des fichiers sorties UMX

	public static void writeUMX(String[] line, String namefichier,
			String dirwork) throws FileNotFoundException {

		HashMap<String, Integer> comptageMot = new HashMap<>();

		FileOutputStream fos = new FileOutputStream(dirwork + namefichier);

		PrintWriter pw = new PrintWriter(fos);

		Integer i = 0;

		for (String mot : line) {
			mot = mot.toLowerCase();
			if (mot.length() > 1 && !REJECTED_WORDS.contains(mot)) {
				pw.println(mot + " 1");
				// System.out.println("Ecriture : " + mot);
				// if (mot.equals("")) {
				// continue;
				// } else {
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

	// methode pour creer les fichiers UMX à partir des lignes SX

	public static void writeUMXList(ArrayList<String[]> listLine,
			String namefichier, String dirwork) throws FileNotFoundException {

		HashMap<String, Integer> comptageMot = new HashMap<>();

		FileOutputStream fos = new FileOutputStream(dirwork + namefichier);

		PrintWriter pw = new PrintWriter(fos);

		Integer i = 0;

		for (String[] line : listLine) {
			for (String mot : line) {
				mot = mot.toLowerCase();
				if (mot.length() > 1 && !REJECTED_WORDS.contains(mot)) {
					pw.println(mot + " 1");
					// System.out.println("Ecriture : " + mot);
					// if (mot.equals("")) {
					// continue;
					// } else {
					if (comptageMot.containsKey(mot)) {
						i = comptageMot.get(mot) + 1;
					} else {
						comptageMot.put(mot, i);
						System.out.println(mot);
					}
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
		}
		System.out.println(namefichier);
		pw.close();
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

		// int i = 0;
		String nameS = "UM";
		String nameSortie = "";

		ArrayList<String[]> listLine = new ArrayList<String[]>();

		// creation des fichiers pour les masters
		for (String line : resultat_entrant) {

			// System.out.println(line);
			// découper la ligne en mot

			String[] listemot = line.split("\\P{L}+");

			listLine.add(listemot);

			// A reactiver les deux lignes suivantes si pb listline
			// nameSortie = nameS + indice;
			// writeUMX(listemot, nameSortie, dirwork);
			// i = i + 1;
		}
		nameSortie = nameS + indice;
		writeUMXList(listLine, nameSortie, dirwork);
		return "OK";
	}

	public static String shuffling(String[] args, String nameSM,
			String dirwork, String motUMX) throws InterruptedException,
			FileNotFoundException {

		Integer i = 0;

		// Ecriture fichier SMX
		//
		// FileOutputStream fos = new FileOutputStream(dirwork + nameSM);
		// PrintWriter pw = new PrintWriter(fos);

		//
		// preparation écriture fichier sortie
		Integer nombreMot = 0;
		for (String argument : args) {

			if (i > 2) {
				// System.out.println(argument);
				ArrayList<String> listeLigne = new ArrayList<String>();
				listeLigne = readFile.readLines(dirwork + argument);

				// System.out.println(listeLigne);

				// creation des fichiers pour les masters
				for (String line : listeLigne) {
					// découper la ligne en mot (ici le mot + 1
					String[] listemot = line.split(" ");
					// System.out.println(listemot[0]);
					if (listemot[0].equals(motUMX)) {
						// Alimentation d'un arrayList listeLigneSM
						// System.out.println(line);
						// Ecriture fichier SMX
						// pw.println(line);
						nombreMot = nombreMot + 1;
					}
				}
			}
			i = i + 1;
		}
		// Ecriture fichier SMX
		// pw.close();

		// Ecriture du RMX
		String ligneRMX = motUMX + " " + nombreMot;
		System.out.println(ligneRMX);
		// ecriture RMX
		// // System.out.println(nameRM);
		String nameRM = "R" + nameSM.substring(1);
		FileOutputStream fosr = new FileOutputStream(dirwork + nameRM);
		PrintWriter pwr = new PrintWriter(fosr);
		pwr.println(ligneRMX);
		pwr.close();

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
		
		// Traitement premier argument de la liste est le mode
		if (args[0].equals("modeSXUMX")) {
			
			String nomInput = args[1];
			String indice = args[1].substring(1);
			// System.out.println(indice);
			// Lecture du fichier input
			mapping(nomInput, indice);
			
		} else if (args[0].equals("modeUMXSMX")) {

			// System.out.println(" On entre en modeUMXSMX " + args);
			// Integer i = 0;
			// ArrayList<String> listeLigneSMX = new ArrayList<String>();
			String nameSM = args[1];
			// System.out.println(nameSM);
			String motUMX = args[2];
			// System.out.println(motUMX);
			String dirwork = "/cal/homes/strublereau/workspace/";
			shuffling(args, nameSM, dirwork, motUMX);
			
		}
	}
}
