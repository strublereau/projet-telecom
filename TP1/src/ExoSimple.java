// type Array tableau dynamique hashMap clé valeur
import java.util.ArrayList;
import java.util.HashMap;


public class ExoSimple {
    // recherche d'un maximum dans un tableau de int
	// on retourne un int
	public static int maximum(int[] t) {
	     int max = t[0];
	     for (int el : t) {
		    if (max < el) {
			    max = el;
		                  }
	     }
         return max;
	}
    
	public static double moyenne(int[] t) {
    // calcul de la moyenne dans un tableau de int
    // attention le résultat doit être un double		
		double moy = 0;
    	double somme = 0;
    	for (int el1 : t) {
           somme += el1;
    //	   somme = el1 + somme;
    		}
    	moy = somme / t.length;  
    return moy;
    }
 
    //   Trouver les triplets d'un tableau de int dont la somme = 0
	// Le retour est une ArrayList d'int
    public static ArrayList<int[]> Triplet(int[] t) {
    	 ArrayList<int[]> r = new ArrayList<>();
    	 for (int i = 0; i < t.length; i++) {
    		 for (int j = i + 1; j < t.length; j++) {
    			 for (int k = j + 1; k < t.length; k++) {
    		          if (t[i] + t[j] + t[k] == 0) {
    			          System.out.println("trip : (" + i + "," + j + "," + k +")");
    			          // méthode add à l'Arraylist
                          r.add(new int[] {i, j, k});
                          System.out.println("r : " + r);
                          // Attention le resultat n'est pas lisible via system.out mais utilisable par l'appellant
    		          }
    			 }     
    	     }
         }
    	 return r;
    }		 
    
    // retourner un int nombre de mot different   
    public static int vocabularySize(String s)
    {
    	// on initialise un arrayList de mot
    	ArrayList<String> uniqWords = new ArrayList<>();
    	// on boucle en splitant split jusqu'à la chaine de caractère " "
        for (String word : s.split(" "))
        {
        	// on regarge si le mot n'existe pas  dans l'ArrayList des mots
        	if (!uniqWords.contains(word))
        	{
        		//on regarde si ce n'est pas la fin
        		if (word.equals("")) {
        			// on sort de la boucle
        			continue;
        		}
        		// on ajoute le mot à l'ArrayList
        		    uniqWords.add(word);
        		//}
        	}	
        }
      return uniqWords.size();
    }
    
    // On veut trouver dans une Hasmap dont le mot qui a la valeur associée le plus grand 
    // 
    public static String trouvermaxKey(HashMap<String, Integer> countMot) {
    // on initialise les max clé et valeur 
    	String maxKey ="";
        int maxValue = 0;
        for (String key: countMot.keySet()) {
        	 if (countMot.get(key) > maxValue) {
        		 maxValue = countMot.get(key);
        		 maxKey = key;
        	 }
        }	 
        return maxKey; 
    }
    //
    // On veut compter les mots reccurents et afficher le mot qui revient le plus souvent 
    public static String reccurenceWord(String s) {
    	HashMap<String, Integer> comptageMot = new HashMap<>();
        for (String word : s.split(" "))
        {
        		if (word.equals("")) {
        			continue;
        		}
        		else {
          		if (comptageMot.containsKey(word)) {
          		//	System.out.println("get:" + comptageMot.get(word));
        		    Integer i = comptageMot.get(word) + 1;
        		//    System.out.println("word : " + word );
        		//    System.out.println("i : " + i);
        		    comptageMot.put(word, i);
          		}
        		    else {
                     comptageMot.put(word, 1);        		    	
        		    }
        		}
        		}
        return trouvermaxKey(comptageMot); 
    }
       
    // methode main de la classe ExoSimple
    
    public static void main(String[] s) {
	    int[] t = {1, -1, 0, -2, 1};
	    String liste = "a b c a b c d c v c a" ;
	    System.out.println("max : " + maximum(t));
	    System.out.println("moy : " + moyenne(t));
	    System.out.println("triplet : " + Triplet(t));
	    System.out.println("nombre de mot : " + vocabularySize(liste));
        System.out.println("taille : " + reccurenceWord(liste));	
    }
}