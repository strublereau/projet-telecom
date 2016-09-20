import java.util.ArrayList;

public class essai {
boolean fou;
	public static int countVowels(String word) {
		word = word.toLowerCase();
		int count = 0;
		for (char c : word.toCharArray()) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y') {
				count += 1;
			}
		}
		return count;
	}

	//public static double squareRootApproximation(double n) {
	//double r = n / 2;
	//while (abs( r - (n/r) ) > t) {
	//    r = 0.5 * (r + (n/r));
	//  }
	//return r; 
    //}
	public static boolean verifieAdresseIP(String averifierIP) {
	   boolean result = true;
	    String [] chaineAverifier = averifierIP.split("\\.");
	    if (chaineAverifier.length == 4) { 
	        for (int element = 0; element < chaineAverifier.length ; element++) {
	        	String s = chaineAverifier[element];
	        	try {
             	 Integer a = Integer.parseInt(s);
	  	    	 if (a > 255 || a < 1) {
	    	    		result = false;
	    	    		break;
	    	    	}
	        	}
	            catch(NumberFormatException nfe) {
	        		result = false;
	        		break;
	        	}
	    	  }
	     }
        else {
	        result = false;	
	        } 
	 return result;
	 }
	public static double calculate(String calcul) {
	    double resultatCalcul = 0.0;
	    String [] tableauChaine = calcul.split(" ");
	    ArrayList<Double> nombres = new ArrayList<Double>();
	    ArrayList<String> operateurs = new ArrayList<String>();
	    for (String i : tableauChaine) {
	    	if (i.matches("[0-9]+")) {
	    		nombres.add(Double.parseDouble(i));
	    	}
	   		else {	
	   			operateurs.add(i);
	    	}
	    }
	    double temporaire = 0.0;
	    double n1 = nombres.get(0);
	    double n2 = nombres.get(1);
	    String o1 = operateurs.get(0);
	    String o2 = operateurs.get(1);
	    switch (o1) {
	    case "+" : 
	    	if (o2 == "+") {
	    	 resultatCalcul = n1 + n2;
	    	 temporaire = nombres.get(2);
	        }
	    	else {
	    		// cas o2 = "*"
	    		resultatCalcul = n1;
	    		temporaire = n2 * nombres.get(2);
	    	}
	    	break;
	    case "*" : 
	        if (o2 == "+") {
	        	resultatCalcul = n1 * n2;
	        	temporaire = nombres.get(2);
	        }
	        else {
	        		//cas ou o2 ="*"
	        		resultatCalcul = 0.0;
	        		temporaire = n1 * n2 * nombres.get(2);
	        }
	        break;
	    }
	    
	    for (int j = 0; j+1 < operateurs.size(); j=j+1) {
	    	o1 = operateurs.get(j+1);
	    	if (j+1 < operateurs.size()) { 
	    	    o2 = operateurs.get(j+1);
	    	    switch (o1) {
		        case "+" : 
		    	   if (o2 == "+") {
		    	      resultatCalcul = resultatCalcul + nombres.get(j+1);
		    	      temporaire = 0.0;
		    	   break;
		           }
		    	   else {
		    		// cas o2 = "*"
		    		   resultatCalcul = resultatCalcul;
		    		   temporaire = nombres.get(j+1);
		    	   break;	
		    	   }
		        case "*" : 
		           if (o2 == "+") {
		        	    resultatCalcul = resultatCalcul + temporaire * nombres.get(j+1);
		        	    temporaire = 0.0;	
		           }
		           else {
		        		//cas ou o2 ="*"
		        		resultatCalcul = resultatCalcul;
		        		temporaire = temporaire * nombres.get(j+1); 
		           }
		           break;
		        }
	    	} 
	    	  else { 
	 		      	resultatCalcul = resultatCalcul + temporaire;
	 		      }
	    	    }	
		return resultatCalcul;
	}
	//for (int i = 0; i < temp.length; i++)
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String planete = "pluton";
		String moon = "charon";
		String p = moon;
		String e = "charon";
		moon = "Nix";
		System.out.println("planete : " + planete + " moon : " + moon + " p: " + p + " e:" + e);

		String doc = "Vainqueur  ́etape 3 : Tyler Farrar\nVainqueur  ́etape 3 : Foo Bar\nVainqueur  ́etape 4 : Toto Tutu";
		// cf. cours prochain pour ne pas avoir a` fixer la taille
		String[] res = new String[3];
		String[] lines = doc.split("\n");
		for (int i = 0; i < 3; i++) {
			String stageLine = lines[i];
			String[] words = stageLine.split(" ");
			String winnerLastName = words[words.length - 1];
			// int pos = stageLine.length() - 1;
			// String winnerLastName = stageLine.split(" ")[pos];
			res[i] = winnerLastName.toUpperCase();
		}
		for (String name : res) {
			System.out.println(name);
		}
	//	System.out.println(countVowels("A b i d v j"));
		//double r;
		//System.out.println("r = " + squareRootApproximation(r));
        // adresse IP
		
	//	String adresseIP = "212.85.250.134";
	//	System.out.println("verifier adresseIP" + verifieAdresseIP(adresseIP));
		System.out.println("resultat calcul : " + calculate("17 + 3 * 5") );
	}
}