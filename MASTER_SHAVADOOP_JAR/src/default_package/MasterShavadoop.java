package default_package;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public class MasterShavadoop {

	public static boolean isInteger(String s) {
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(s);
	 
	         // s is a valid integer
	 
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         // s is not an integer
	      }
	 
	      return isValidInteger;
	   }
	
	//
	// Fonction triant les éléments d'un dictionnaire et printant les n premiers
	//

	public static HashMap<String, Integer> triDico(
			HashMap<String, Integer> dico, Integer nombre) {

		Set<Entry<String, Integer>> set = dico.entrySet();

		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
				set);

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Integer i = 0;

		for (Map.Entry<String, Integer> entry : list) {

			System.out.println(entry.getKey() + " => " + entry.getValue());
			i = i + 1;
			if (i > nombre) {
				break;
			}

		}

		return dico;
	}

	//
	// Ecriture sur fichier des machines OK
	//

	public static String writeMachineOK(ArrayList<String> listemachineOK,
			String dirwork) throws FileNotFoundException {

		String nomFichier = dirwork + "listemachineOK.txt";

		FileOutputStream fos = new FileOutputStream(nomFichier);
		PrintWriter pw = new PrintWriter(fos);

		for (String machine_OK : listemachineOK) {
			pw.println(machine_OK);
			System.out.println(" - " + machine_OK);
		}

		pw.close();

		return "Ecriture fichier des hostnames actifs OK ";
	}

	//
	// Ecriture des fichiers sorties SX
	//

	public static String writePackS(ArrayList<String> listligne,
			String namefichier) throws FileNotFoundException {

		FileOutputStream fos = new FileOutputStream(namefichier);
		PrintWriter pw = new PrintWriter(fos);

		for (String ligne : listligne) {
			pw.println(ligne);
		}

		// System.out.println(" Ecriture fichier : " + namefichier + " ligne " +
		// ligne);

		pw.close();

		return "Ecriture fichier vers slave ";
	}

	//
	// methode pour obtenir les machines OK à
	//

	public static ArrayList<String> getMachinesOK(String dirwork,
			String fichierNomsMachine) throws FileNotFoundException,
			InterruptedException {

		// String fichierNomsMachine = "nommachines";
		String fichier = dirwork + fichierNomsMachine;

		ArrayList<String> resultat = new ArrayList<String>();
		resultat = readFile.readLines(fichier);

		System.out.println("===========================================");
		System.out.println(" Liste de machines à vérifier : ");

		for (String line : resultat) {
			System.out.println(" - " + line);
		}
		System.out.println("===========================================");

		//
		// 22 - lancement de tous les threads en parallele
		//
		ArrayList<threadMachine> threadMachinelist = new ArrayList<threadMachine>();
		ArrayList<Thread> threadlist = new ArrayList<Thread>();

		for (String machine : resultat) {

			// instanciation de l'objet runnable de type threadMachine en lui
			// passant la machine (constructor de threadMachine lors du new)

			threadMachine runnable = new threadMachine(machine);
			threadMachinelist.add(runnable);
			Thread thread = new Thread(runnable);
			thread.start();
			// on conserve l'instance du thread dans liste threadlist
			threadlist.add(thread);
		}

		//Thread.sleep(2000);
		// attente que tous les threads soient terminés (boucle sur les
		// threadlist

		for (Thread threadi : threadlist) {
			threadi.join();
		}

		// A partir d'array des noms machines
		// on verifie l'acces ssh et l'on constitue un arrayList des machine_OK
		ArrayList<String> resultat_OK = new ArrayList<String>();

		for (threadMachine threadl : threadMachinelist) {

			String nomMachine = threadl.getMachine();
			// System.out.println("nommachine : " + nomMachine);

			boolean Etat = threadl.getEtat();

			if (Etat == true) {
				resultat_OK.add(nomMachine);
				// System.out.println("nommachine OK " + nomMachine);
				// } else {
				// System.out.println("nommachine KO " + nomMachine);
			}
		}

		System.out.println(" Liste de machines OK : ");

		// Ecriture fichier sortie contenant l'ensemble des machines actives
		String retour = writeMachineOK(resultat_OK, dirwork);
		System.out.println(" " + retour);
		System.out.println("===========================================");
		return resultat_OK;
	}

	//
	// Lecture du fichier à traiter en entrée alimentation arrayList
	//

	public static ArrayList<String> getLignes(String dirwork, String nomInput) {
		// Lecture du fichier input
		String fichier1 = dirwork + nomInput;

		ArrayList<String> resultat_entrant = new ArrayList<String>();

		resultat_entrant = readFile.readLines(fichier1);

		// les lignes sont dans un ArraysList resultat_entrant que
		// l'on va filtrer
		ArrayList<String> resultat = new ArrayList<String>();

		// elimination des lignes blanches
		for (String line : resultat_entrant) {
			if (line.length() > 0) {
				resultat.add(line);
			}
		}
		return resultat;
	}

	//
	// LAncement du mapping et gestion du dictionaire des UMX
	//

	public static HashMap<String, ArrayList<String>> getDicoMotUMX(
			ArrayList<String> resultat_entrant,
			ArrayList<String> resultat_ssh_OK) throws InterruptedException {
		// on veut constituer un dictionnaire de mot et de fichiers
		HashMap<String, ArrayList<String>> dicoMotFichierM = new HashMap<>();
		Integer i = 0;
		String c_S = "S";
		String c_UMX = "UMX";
		String nom_UMX = "";
		//
		ArrayList<Thread> threadlistS = new ArrayList<Thread>();
		//
		// création dico UMx machine pour premettre de retrouver l'UMX creer
		// lors du retour slave
		//
		HashMap<String, String> dicUMXmachine = new HashMap<>();

		// Creation de l'arraylist des instances threadmapping afin de permettre
		// l'appel de la methode getListeMot machine
		// qu'il faudra transformer en nom fichier
		ArrayList<threadMapping> threadmappinglistS = new ArrayList<threadMapping>();

		// on boucle sur les lignes correspondant au fichier S crée
		//
		String nom_fichierS = "";
		Integer j = 0;

		while (j < resultat_entrant.size()) {

			for (String host : resultat_ssh_OK) {
				if (j < resultat_entrant.size()) {
					nom_UMX = c_UMX + i;
					nom_fichierS = c_S + i;
					// System.out.println("nom fichier : " + nom_fichierS);
					// System.out.println("host mapping: " + host);

					dicUMXmachine.put(nom_UMX, host);

					// instanciation de l'objet runnable de type threadMachine
					// en lui passant la machine
					// (constructor de threadMachine lors du new)
					threadMapping runnable = new threadMapping(host,
							nom_fichierS);
					threadmappinglistS.add(runnable);
					Thread thread = new Thread(runnable);
					thread.start();
					// on conserve l'instance du thread dans liste threadlist
					threadlistS.add(thread);
					i = i + 1;
					j = j + 1;
					// System.out.println("j : " + j);
				}
			}
			for (Thread threadi : threadlistS) {
				threadi.join();
			}
			// System.out.println("j : " + j);
			// System.out.println("i : " + i);
		}

		for (threadMapping threadl : threadmappinglistS) {

			ArrayList<String> ListeMot = new ArrayList<String>();

			String nomFichierUM = "";

			ListeMot = threadl.getListeMot();
			// System.out.println("Liste mot thread : " + ListeMot);

			nomFichierUM = threadl.getfichierM();
			// System.out.println("nomFichierUM : " + nomFichierUM);

			for (String mot : ListeMot) {
				if (mot.equals("")) {
					continue;
				} else {
					ArrayList<String> ListeUMX = new ArrayList<String>();
					if (dicoMotFichierM.containsKey(mot)) {
						ListeUMX = dicoMotFichierM.get(mot);
						ListeUMX.add(nomFichierUM);
						dicoMotFichierM.put(mot, ListeUMX);
						// System.out.println("Liste UMX du Mot : " + mot +
						// " liste : " + ListeUMX);
					} else {
						ListeUMX.add(nomFichierUM);
						dicoMotFichierM.put(mot, ListeUMX);
						// System.out.println("Liste UMX du Mot : " + mot +
						// " liste : " + ListeUMX);
					}
				}
			}
		}
		return dicoMotFichierM;
	}

	public static ArrayList<threadSuffling> getThreadSuffling(
			ArrayList<String> ssh_OK,
			HashMap<String, ArrayList<String>> dicoMotFichierM)
			throws InterruptedException {
		//
		ArrayList<Thread> threadlistR = new ArrayList<Thread>();
		// creation d'un iterator
		Iterator<Entry<String, ArrayList<String>>> iterator = dicoMotFichierM
				.entrySet().iterator();
		// création dico RMx machine pour premettre de retrouver l'RMX creer
		// lors du retour slave
		//
		Integer i = 0;
		ArrayList<threadSuffling> threadsufflingList = new ArrayList<threadSuffling>();
		HashMap<String, String> dicRMXmachine = new HashMap<>();
		String c_RMX = "RM";
		String nom_RMX = "";
		// Integer iterpermachine = 1;
		// Integer k = 0;
		while (iterator.hasNext()) {
			// recuperation du nom de la machine vers qui envoyé
			//
			for (String host : ssh_OK) {
				// while (iterpermachine != k) {
				if (iterator.hasNext()) {
					// System.out.println("nom machine pour RMX : " + host);

					Entry<String, ArrayList<String>> mapentry = iterator.next();
					// System.out.println("clé: "+ mapentry.getKey()
					// + " | valeur: " + mapentry.getValue());
					// determination du nom SM
					String nom_fichierSM = "SM" + i;
					// System.out.println("nom fichier SM : " + nom_fichierSM);
					nom_RMX = c_RMX + i;
					dicRMXmachine.put(nom_RMX, host);
					// myMap.put(nom_UMX, nom_machine)
					// System.out.println("nom RMX : " + nom_RMX + "-" + host);
					// instanciation de l'objet runnable de type threadMachine
					// en lui
					// passant la machine (constructor de threadMachine lors du
					// new)
					threadSuffling runnable = new threadSuffling(host,
							nom_fichierSM, mapentry.getKey(),
							mapentry.getValue());
					threadsufflingList.add(runnable);
					Thread thread = new Thread(runnable);
					thread.start();
					// on conserve l'instance du thread dans liste threadlist
					threadlistR.add(thread);
					i = i + 1;
				}
			}
			for (Thread threadR : threadlistR) {
				threadR.join();
			}
			// System.out.println("i : " + i);
		}
		return threadsufflingList;
	}

	public static void main(String[] args) throws FileNotFoundException,
			InterruptedException {
		// debut programme
		System.out.println("===========================================");
		System.out.println("=      Début du programme SHAVADOOP       =");
		System.out.println("===========================================");

		long startTimeEtape0 = System.currentTimeMillis();
		// parametre d'execution
		String dirwork = "/cal/homes/strublereau/workspace/";

		// Récuperation du parametre en entrée nom du fichier Input contenant le
		// texte à étudier
		if (args.length != 1) {
			System.out.println("Usage : <progname> <param1>");
			System.exit(1);
		}

		System.out.println(" Fichier étudié : " + args[0]);

		String nomInput = args[0];

		// Récupération des machines OK
		// Nom du fichier contenant la liste des machines
		String fichierNomsMachine = "nommachines";

		// Alimentation d'un fichier de machine OK

		ArrayList<String> resultat_ssh_OK = new ArrayList<String>();
		resultat_ssh_OK = getMachinesOK(dirwork, fichierNomsMachine);
		// System.out.println(" resultat_ssh_OK " + resultat_ssh_OK);

		long endTimeEtape0 = System.currentTimeMillis();
		double totalTimeEtape0 = (endTimeEtape0 - startTimeEtape0) / 1000;
		System.out
				.println("    Durée étape 0 : démarrage : " + totalTimeEtape0);
		System.out.println("===========================================");
		System.out.println("======= Ecriture des fichiers SX ==========");
		System.out.println("===========================================");

		// Lecture du fichier input (mots à compter)
		// les lignes sont mis dans un ArraysList resultat_entrant
		long startTimeEtape1 = System.currentTimeMillis();

		ArrayList<String> resultat_entrant = new ArrayList<String>();
		resultat_entrant = getLignes(dirwork, nomInput);

		String nameS = "S";
		String nameSortie = "";

		// Creation des fichiers pour les slaves. A partir des lignes de mot
		int i = 0;

		ArrayList<String> listLigne = new ArrayList<String>();

		ArrayList<String> listFichierS = new ArrayList<String>();

		int j = 1;

		int maxLigne = 100;

		int iter = 0;

		String nameFileS = "";

		for (String line : resultat_entrant) {

			// Ecriture ligne qui va creer le fichier S
			// determination du nom
			// alimentation d'une liste de ligne

			iter = iter + 1;
			// System.out.println(j);

			listLigne.add(line);

			if (j == maxLigne || iter == resultat_entrant.size()) {
				// System.out.println(listLigne);
				nameSortie = dirwork + nameS + i;
				writePackS(listLigne, nameSortie);
				nameFileS = "S" + i;
				listFichierS.add(nameFileS);
				i = i + 1;
				j = 1;
				listLigne.clear();
			} else {
				j = j + 1;
			}
		}
		System.out.println(" Nombre de fichiers Sx : " + i);
		System.out.println(" Noms fichier S        : " + listFichierS);

		long endTimeEtape1 = System.currentTimeMillis();
		double totalTimeEtape1 = (endTimeEtape1 - startTimeEtape1) / 1000;
		System.out.println("===========================================");
		System.out
				.println("*    Durée étape 1 : spliting : " + totalTimeEtape1);

		System.out.println("===========================================");
		System.out.println("=           Début  MAPPING                =");
		System.out.println("===========================================");

		// MAPPING
		// 27 - lancement de tous les threads en parallele pour le MAPPING
		long startTimeEtape2 = System.currentTimeMillis();

		i = 0;

		// on veut constituer un dictionnaire de mot et de fichiers
		HashMap<String, ArrayList<String>> dicoMotFichierM = new HashMap<>();
		// dicoMotFichierM = getDicoMotUMX(resultat_entrant, resultat_ssh_OK);
		dicoMotFichierM = getDicoMotUMX(listFichierS, resultat_ssh_OK);

		System.out.println("==     Dictionnaire de mot liste UMX     ==");
		System.out.println(dicoMotFichierM);

		long endTimeEtape2 = System.currentTimeMillis();
		double totalTimeEtape2 = (endTimeEtape2 - startTimeEtape2) / 1000;
		System.out.println("===========================================");
		System.out.println("*   Durée étape 2 : Mapping : " + totalTimeEtape2);
		System.out.println("===========================================");

		// affichage de la fin de tous les traitements de mapping

		System.out.println("");
		System.out.println("===========================================");
		System.out.println("=      Début SHUFFLING /  REDUCE          =");
		System.out.println("===========================================");

		// 33 - lancement de tous les threads en parallele pour le suffling
		// initialisation

		long startTimeEtape3 = System.currentTimeMillis();
		i = 0;

		ArrayList<threadSuffling> threadsufflingList = new ArrayList<threadSuffling>();
		threadsufflingList = getThreadSuffling(resultat_ssh_OK, dicoMotFichierM);
		// ****

		long endTimeEtape3 = System.currentTimeMillis();
		double totalTimeEtape3 = (endTimeEtape3 - startTimeEtape3) / 1000;
		System.out.println("===========================================");
		System.out.println("* Durée étape 3 : Shuffle/Reduce: "
				+ totalTimeEtape3);
		System.out.println("===========================================");
		System.out.println(" Constitution dico mot sur master : count =");

		long startTimeEtape4 = System.currentTimeMillis();

		HashMap<String, Integer> dicoMotCount = new HashMap<>();

		Integer nombre = 0;

		for (threadSuffling threadr : threadsufflingList) {
			String LigneMot = "vide";
			String Mot = threadr.getMot();
			// String nomFichierUM = "";
			LigneMot = threadr.getligneMot();
//			if (LigneMot == "/usr/bin/xauth:  timeout in locking authority file /cal/homes/strublereau/.Xauthority") {
//				System.out.println("Ligne mot thread shuffling/reducing : "
//						+ LigneMot);
//				Mot = threadr.getMot();
//				System.out.println(" Mot non traité " +  Mot);
//			}	
			String[] word = LigneMot.split(" ");
			boolean isInteger = isInteger(word[1]);
            if (isInteger) {
            	nombre = Integer.parseInt(word[1]);
             } else {
            	nombre = 0;
                System.out.println(word[i] + " is not an integer");
                System.out.println(Mot + " est-il compté ");
             }
			//nombre = Integer.parseInt(word[1]);
			// System.out.println("mot : " + word[0] + " nombre : " + word[1]);
			dicoMotCount.put(Mot, nombre);
			//dicoMotCount.put(word[0], nombre);
		}
		// System.out.println("  Dicto de mot count                      =");
		System.out.println(dicoMotCount);

		long endTimeEtape4 = System.currentTimeMillis();
		double totalTimeEtape4 = (endTimeEtape4 - startTimeEtape4) / 1000;
		System.out.println("===========================================");
		System.out.println(" Durée étape 4 : Assembling final : "
				+ totalTimeEtape4);
		System.out.println("===========================================");

		// System.out.println();

		long startTimeEtape5 = System.currentTimeMillis();
		System.out.println("=   Print des 50 mots les plus nombreux   =");
		triDico(dicoMotCount, 50);
		long endTimeEtape5 = System.currentTimeMillis();
		double totalTimeEtape5 = (endTimeEtape5 - startTimeEtape5) / 1000;
		System.out.println("===========================================");
		System.out.println(" Durée étape 5 : Tri : " + totalTimeEtape5);
		System.out.println();

		System.out.println("=========== Traitement global =============");
		System.out.println(" Durée étape 0 : démarrage : " + totalTimeEtape0);
		System.out.println(" Durée étape 1 : splitting : " + totalTimeEtape1);
		System.out.println(" Durée étape 2 : mapping   : " + totalTimeEtape2);
		System.out.println(" Durée étape 3 : shuffling/reducing : "
				+ totalTimeEtape3);
		System.out.println(" Durée étape 4 : assembling : " + totalTimeEtape4);
		System.out.println(" Durée étape 5 : tri : " + totalTimeEtape5);
		System.out.println("===========================================");

	}
}