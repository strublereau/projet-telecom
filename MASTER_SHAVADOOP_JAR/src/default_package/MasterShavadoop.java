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
	//
	// Fonction triant les éléments d'un dicttionnaire et printant les n premiers
	//
	public static HashMap<String, Integer> triDico(HashMap<String, Integer> dico, Integer nombre ) {
		Set<Entry<String, Integer>> set = dico.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        Integer i=0;
        for(Map.Entry<String, Integer> entry:list){
            System.out.println(entry.getKey()+" ==== "+entry.getValue());
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
	public static String writeMachineOK(ArrayList<String> listemachineOK)
			throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("listemachineOK.txt");
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
	public static String writeS(String ligne, String namefichier)
			throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(namefichier);
		PrintWriter pw = new PrintWriter(fos);
		pw.println(ligne);
		System.out.println(" Ecriture fichier : " + namefichier + " ligne " + ligne);
		pw.close();
		return "Ecriture fichier vers slave ";
	}
	//
	// Récupération des machines à verifier le fonctionnement dans le fichier nommachines
	//
	public static ArrayList<String> getMachineOK(String dirwork) throws FileNotFoundException{
		String fichierNomsMachine = "nommachines";
		String fichier = dirwork +  fichierNomsMachine;
		ArrayList<String> resultat = new ArrayList<String>();
		resultat = readFile.readLines(fichier);
		System.out.println("=======================================================");
		System.out.println(" Liste de machines à vérifier le fonctionnement : ");
		for (String line : resultat) {
			System.out.println(" - " + line);
		}
		System.out.println("=======================================================");
		// A partir d'array des noms machines 
		// on verifie l'acces ssh et l'on constitue un arrayList des machine_OK  
		ArrayList<String> resultat_ssh_OK = new ArrayList<String>();
		resultat_ssh_OK = validateSSH.accessMachines(resultat);
		System.out.println("=======================================================");
		System.out.println(" Liste de machines OK : ");
		//for (String machine_OK : resultat_ssh_OK) {
		//	System.out.println(machine_OK);
		//}
		// Ecriture fichier sortie contenant l'ensemble des machines actives
		String retour = writeMachineOK(resultat_ssh_OK);
		System.out.println(" " + retour);
		System.out.println("=======================================================");
		return resultat_ssh_OK;
	}
	
	//
	// Lecture du fichier à traiter en entrée 
	//
	public static ArrayList<String> getLignes(String dirwork, String nomInput) {
		// Lecture du fichier input
		String fichier1 = dirwork + nomInput;
		ArrayList<String> resultat_entrant = new ArrayList<String>();
		resultat_entrant = readFile.readLines(fichier1);
		// les lignes sont dans un ArraysList resultat_entrant
		System.out.println("===========Liste ligne =======================================");
		for (String line : resultat_entrant) {
			System.out.println(" " + line);	
		}
		System.out.println("===========Fin Liste ligne ===================================");
	return resultat_entrant;
	}
	
	public static HashMap<String, ArrayList<String>> getDicoMotUMX(ArrayList<String> resultat_entrant, ArrayList<String> resultat_ssh_OK) throws InterruptedException{
		// on veut constituer un dictionnaire de mot et de fichiers
		HashMap<String, ArrayList<String>> dicoMotFichierM = new HashMap<>();
		Integer i = 0;
		String c_S = "S";
		String c_UMX = "UMX";
		String nom_UMX = "";
		//
		ArrayList<Thread> threadlistS = new ArrayList<Thread>();
		//
		// création dico UMx machine pour premettre de retrouver l'UMX creer lors du retour slave
		//
		HashMap<String, String> dicUMXmachine = new HashMap<>();
		
		// Creation de l'arraylist des instances threadmapping afin de permettre l'appel de la methode getListeMot machine
		// qu'il faudra transformer en nom fichier 
		ArrayList<threadMapping> threadmappinglistS = new ArrayList<threadMapping>();
		
		// on boucle sur les lignes correspondant au fichier S crée
		//
		String nom_fichierS = "";
		Integer j = 0; 
		while (j < resultat_entrant.size()) {
		//for (result : resultat_entrant) {
			//String lineS = resultat_entrant.get(j);
			//System.out.println("j : " + j);
			for (String host : resultat_ssh_OK){
				if (j < resultat_entrant.size()){
					nom_UMX = c_UMX + i;
					nom_fichierS = c_S + i;
			//		System.out.println("nom fichier : " + nom_fichierS);
			//		System.out.println("host mapping: " + host);
			// Ajout des objet dans le tableau associatif
			// recuperation du nom de la/cal/homes/strublereau/workspace machine vers qui envoyé
			//nom_machine = resultat_ssh_OK.get(i); 
					dicUMXmachine.put(nom_UMX, host);
			// instanciation de l'objet runnable de type threadMachine en lui
			// passant la machine (constructor de threadMachine lors du new)
					threadMapping runnable = new threadMapping(host, nom_fichierS);
					threadmappinglistS.add(runnable);	
					Thread thread = new Thread(runnable);
					thread.start();
			// on conserve l'instance du thread dans liste threadlist
					threadlistS.add(thread);
					i = i + 1;
					j = j + 1;
					//System.out.println("j : " + j);
				}
			}
		}
		for (Thread threadi : threadlistS) {
			threadi.join();
		}
	
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
		return dicoMotFichierM;
	}
	
	public static void main(String[] args) throws FileNotFoundException,
			InterruptedException {
	// debut programme
		System.out.println("============= Début du programme          ====================");
	// parametre d'execution
		String dirwork = "/cal/homes/strublereau/workspace/";
		
	// Récuperation du parametre en entrée nom du fichier Input contenant le texte à étudier
		if (args.length != 1) {
            System.out.println("Usage : <progname> <param1>");
            System.exit(1);
        }
		
		System.out.println("Argument 1 : "+args[0]);
		
		String nomInput = args[0];
		
	// Récupération des machines OK
	// Dans ArrayList resultat_ssh_OK	
		ArrayList<String> resultat_ssh_OK = new ArrayList<String>();
		resultat_ssh_OK = getMachineOK(dirwork);
		
	// Lecture du fichier input
	// les lignes sont dans un ArraysList resultat_entrant
		ArrayList<String> resultat_entrant = new ArrayList<String>();
		resultat_entrant = getLignes(dirwork,nomInput);
		
		System.out.println("============= Ecriture des fichiers SX====================");

		String nameS = "S";
		String nameSortie = "";

		// Creation des fichiers pour les slaves. A partir des lignes de mot
		int i = 0;
		//String retour = "";
		for (String line : resultat_entrant) {
			// Ecriture ligne qui va creer le fichier S
			// System.out.println(line);
			// determination du nom
			nameSortie = dirwork + nameS + i;
			writeS(line,nameSortie); 
			i = i + 1;	
		}
		System.out.println("===== Début lancement des threads  MAPPING====================");	
		//      MAPPING
		// 27 - lancement de tous les threads en parallele pour le MAPPING
		i = 0;
		//String c_S = "S";
		//String c_UMX = "UMX";
		//String nom_UMX = "";
		// on veut constituer un dictionnaire de mot et de fichiers
		HashMap<String, ArrayList<String>> dicoMotFichierM = new HashMap<>();
		dicoMotFichierM = getDicoMotUMX(resultat_entrant, resultat_ssh_OK);
		

		System.out.println("============ Dictionnaire de mot liste UMX               ===================");
		System.out.println(dicoMotFichierM);
 
		// affichage de la fin de tous les traitements
		System.out.println("============ Tous les traitements Mapping  sont terminés ===================");
		System.out.println("");	
		System.out.println("============================================================================");	
		System.out.println("===== Début lancement des threads shuffling             ====================");
		System.out.println("============================================================================");	
		// 33 - lancement de tous les threads en parallele pour le suffling
		// initialisation 
		i = 0;
		
		ArrayList<threadSuffling> threadsufflingList = new ArrayList<threadSuffling>();
		ArrayList<Thread> threadlistR = new ArrayList<Thread>();
		//creation d'un iterator
	    Iterator<Entry<String, ArrayList<String>>> iterator = dicoMotFichierM.entrySet().iterator();
	 // création dico RMx machine pour premettre de retrouver l'RMX creer lors du retour slave
	 		//
	 	HashMap<String, String> dicRMXmachine = new HashMap<>();
	 	String c_RMX = "RM";
	 	String nom_RMX = "";
		while (iterator.hasNext()) {
			// recuperation du nom de la machine vers qui envoyé
			//
			// debut du code remplacé
			// 
			//nom_machine = resultat_ssh_OK.get(i);
			for (String host : resultat_ssh_OK){
				if (iterator.hasNext()) {
					System.out.println("nom machine pour RMX : " + host);
					
			        Entry<String, ArrayList<String>> mapentry = iterator.next();
			        System.out.println("clé: "+ mapentry.getKey()
			                            + " | valeur: " + mapentry.getValue());
			        // determination du nom SM
			        String nom_fichierSM = "SM" + i;
					System.out.println("nom fichier SM : " + nom_fichierSM);
					nom_RMX = c_RMX + i;
					dicRMXmachine.put(nom_RMX, host);
					//myMap.put(nom_UMX, nom_machine)
//					System.out.println("nom RMX : " + nom_RMX + "-" + host);
					// instanciation de l'objet runnable de type threadMachine en lui
					// passant la machine (constructor de threadMachine lors du new)
					threadSuffling runnable = new threadSuffling(host, nom_fichierSM, mapentry.getKey(), mapentry.getValue());
					threadsufflingList.add(runnable);	
					Thread thread = new Thread(runnable);
					thread.start();
					// on conserve l'instance du thread dans liste threadlist
					threadlistR.add(thread);
					i = i + 1;
				} 
				//else {
				//	break;
				//}
			}	
			// fin code remplacé
			//
			
	    } 
		for (Thread threadR : threadlistR) {
			threadR.join();
		}
		
		HashMap<String, Integer> dicoMotCount = new HashMap<>();
		Integer nombre = 0;
 		for (threadSuffling threadr : threadsufflingList) {
 			String LigneMot = "vide";
 			//String nomFichierUM = "";
			LigneMot = threadr.getligneMot();
			System.out.println("Ligne mot thread shuffling/reducing : " + LigneMot);
			String[] word = LigneMot.split(" ");
			nombre = Integer.parseInt(word[1]);
			System.out.println("mot : " + word[0] + " nombre : " + word[1]);
			dicoMotCount.put(word[0],nombre);
		}
 		System.out.println("============ Dictionnaire de mot count            ===================");
		System.out.println(dicoMotCount);
		triDico(dicoMotCount,20);
		//HashMap<String, Integer> dicoTrie = triDico(dicoMotCount,20);
		//System.out.println(dicoTrie);
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
		
	}
}