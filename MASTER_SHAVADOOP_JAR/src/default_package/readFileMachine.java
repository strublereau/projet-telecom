package default_package;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Map;


public class readFileMachine {

	public static String writeMachineOK(ArrayList<String> listemachineOK)
			throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("listemachineOK.txt");
		PrintWriter pw = new PrintWriter(fos);
		for (String machine_OK : listemachineOK) {
			pw.println(machine_OK);
			System.out.println("Ecriture : " + machine_OK);
		}
		pw.close();
		return "ecriture fichier des hostnames actifs OK ";
	}
	
	// Ecriture des fichiers sorties
	public static String writeS(String ligne, String namefichier)
			throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(namefichier);
		PrintWriter pw = new PrintWriter(fos);
		pw.println(ligne);
		System.out.println("Ecriture fichier : " + namefichier + " ligne " + ligne);
		pw.close();
		return "ecriture fichier vers slave ";
	}
	
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			InterruptedException {
		String dirwork = "/cal/homes/strublereau/workspace/";
		// Récuperation du parametre en entrée nom du fichier Input
		if (args.length != 1) {
            System.out.println("Usage : <progname> <param1>");
            System.exit(1);
        }
		System.out.println("Argument 1 : "+args[0]);
		String nomInput = args[0]; 
		
		// En attendant lecture argument , on force le nom de l'input du texte a etudier
		//String nomInput = "Input.txt";
		
		String fichier = dirwork +  "nommachines";
		ArrayList<String> resultat = new ArrayList<String>();
		resultat = readFile.readLines(fichier);
		for (String line : resultat) {
			System.out.println(line);
		}
		
		// A partir d'array des noms machines 
		// on verifie l'acces ssh et l'on constitue un arrayList des machine_OK  
		ArrayList<String> resultat_ssh_OK = new ArrayList<String>();
		resultat_ssh_OK = validateSSH.accessMachines(resultat);
		for (String machine_OK : resultat_ssh_OK) {
			System.out.println(machine_OK);
		}
	
		// Ecriture fichier sortie contenant l'ensemble des machines actives
		String retour = writeMachineOK(resultat_ssh_OK);
		System.out.println(retour);
		
		
		// 20- et 21 - lancement à distance sur la premiere machine distante OK
		// de calculsleep
		// A décommenter pour vérifier
//		System.out.println("Début appel distant caclulsleep");
//		String resultat2 = runDistCalculSleep.runDist(resultat_ssh_OK);
//		System.out.println(resultat2);
		

		// 22 - lancement de tous les threads en parallele
		// A décommenter pour vérifier
//		ArrayList<Thread> threadlist = new ArrayList<Thread>();
//		for (String machine_OK : resultat_ssh_OK) {
//			System.out.println("lancement sur machine : " + machine_OK);
//			// instanciation de l'objet runnable de type threadMachine en lui
//			// passant la machine (constructor de threadMachine lors du new)
//			threadMachine runnable = new threadMachine(machine_OK);
//			Thread thread = new Thread(runnable);
//			thread.start();
//			// on conserve l'instance du thread dans liste threadlist
//			threadlist.add(thread);
//		}
		// attente que tous les threads soient terminés (boucle sur les
		// threadlist
		// A décommenter pour vérifier
//		for (Thread threadi : threadlist) {
//			threadi.join();
//		}
		// affichage de la fin de tous les traitements
//		System.out.println("Tous les traitements sont terminés : ");
		
		// Lecture du fichier input
		String fichier1 = nomInput;
		ArrayList<String> resultat_entrant = new ArrayList<String>();
		resultat_entrant = readFile.readLines(fichier1);
		// les lignes sont dans un ArraysList resultat_entrant
		int i = 0;
		String nameS = "S";
		String nameSortie = "";
		for (String line : resultat_entrant) {
			System.out.println(line);	
		}
		System.out.println("============= Ecriture des fichiers SX====================");	
		String c_UMX = "UMX";
		String nom_UMX = "";
		String nom_machine = "";
		Map<String, String> myMap=new java.util.HashMap<String, String>();
		// création hashmap des UMx  : Créer un tableau associatif UMX machine
		// A faire plus tard
		
		// Creation des fichiers pour les slaves
	
		for (String line : resultat_entrant) {
			// Ecriture ligne qui va creer le fichier S
			System.out.println(line);
			// determination du nom

			nameSortie = dirwork + nameS + i;
			nom_UMX = c_UMX + i;
			// Ajout des objet dans le tableau associatif
			// recuperation du nom de la/cal/homes/strublereau/workspace machine vers qui envoyé
			nom_machine = resultat_ssh_OK.get(i); 
			myMap.put(nom_UMX, nom_machine);
			System.out.println(nom_UMX + "-" + nom_machine);
			System.out.println(writeS(line,nameSortie));
			//"
			// lancement des threads par paquets ? en effet si j'ai 40 lignes(fichiers) mais seulement 10 slaves
			// doit boucler par paquet de slaves après chaque join ?
			// 
			i = i + 1;	
		}
		System.out.println("===== Début lancement des threads  MAPPING====================");	
		//      MAPPING
		// 27 - lancement de tous les threads en parallele pour le MAPPING
		i = 0;
		String c_S = "S";
		ArrayList<Thread> threadlistS = new ArrayList<Thread>();
		//
		// création dico UMx machine pour premettre de retrouver l'UMX creer lors du retour slave
		//
		HashMap<String, String> dicUMXmachine = new HashMap<>();
		// Creation de l'arraylist des instances threadmapping afin de permettre l'appel de la methode getListeMot machine
		// qu'il faudra transformer en nom fichier 
		ArrayList<threadMapping> threadmappinglistS = new ArrayList<threadMapping>();
		for (int j = 0; j < resultat_entrant.size(); j++) {
			//String lineS = resultat_entrant.get(j);
			nom_UMX = c_UMX + i;
			String nom_fichierS = c_S + i;
			System.out.println("nom fichier : " + nom_fichierS);
			// Ajout des objet dans le tableau associatif
			// recuperation du nom de la/cal/homes/strublereau/workspace machine vers qui envoyé
			nom_machine = resultat_ssh_OK.get(i); 
			dicUMXmachine.put(nom_UMX, nom_machine);
			//myMap.put(nom_UMX, nom_machine)
//			System.out.println(nom_UMX + "-" + nom_machine);
//			System.out.println("lancement sur machine : " + nom_machine);
			// instanciation de l'objet runnable de type threadMachine en lui
			// passant la machine (constructor de threadMachine lors du new)
			threadMapping runnable = new threadMapping(nom_machine, nom_fichierS);
			threadmappinglistS.add(runnable);	
			Thread thread = new Thread(runnable);
			thread.start();
			// on conserve l'instance du thread dans liste threadlist
			threadlistS.add(thread);
			i = i + 1;
		}
		for (Thread threadi : threadlistS) {
			threadi.join();
		}
		//Integer j = 0;
		HashMap<String, ArrayList<String>> dicoMotFichierM = new HashMap<>();
	
 		for (threadMapping threadl : threadmappinglistS) {
 			ArrayList<String> ListeMot = new ArrayList<String>();
 			String nomFichierUM = "";
			ListeMot = threadl.getListeMot();
			System.out.println("Liste mot thread : " + ListeMot);
			nomFichierUM = threadl.getfichierM();
			System.out.println("nomFichierUM : " + nomFichierUM);
			for (String mot : ListeMot){
				if (mot.equals ("")) {
					continue;
				}	
				else {
					ArrayList<String> ListeUMX = new ArrayList<String>();
					if (dicoMotFichierM.containsKey(mot)) {
						ListeUMX = dicoMotFichierM.get(mot);
						ListeUMX.add(nomFichierUM);
						dicoMotFichierM.put(mot,ListeUMX);
						//System.out.println("Liste UMX du Mot : " + mot + " liste : " + ListeUMX);
					}
					else {
						ListeUMX.add(nomFichierUM);
						dicoMotFichierM.put(mot,ListeUMX);
						//System.out.println("Liste UMX du Mot : " + mot + " liste : " + ListeUMX);
					}
				}
			}
		}
		System.out.println("============ Dictionnaire de mot liste UMX               ===================");
		System.out.println(dicoMotFichierM);
		// affichage de la fin de tous les traitements
		System.out.println("============ Tous les traitements Mapping  sont terminés ===================");
		System.out.println("");	
		System.out.println("============================================================================");	
		System.out.println("===== Début lancement des threads shuffling             ====================");
		System.out.println("============================================================================");	
		
	}
}