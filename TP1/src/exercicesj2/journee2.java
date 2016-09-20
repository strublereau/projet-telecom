import java.util.ArrayList;
//import java.util.HashMap;
public class journee2 {
	public static boolean verifierAdresseIP(String averifierIP) {
	   boolean result = true;
	   String [] chaineAverifier = averifierIP.split("\\.");
	   if (chaineAverifier.length == 4) { 
		   for (int element = 0; element < chaineAverifier.length; element++) {
			   String s = chaineAverifier[element];
			   try {
				   Integer a = Integer.parseInt(s);
				   if (a>255 || a<0){
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
		boolean operateurnombre = true;
		String[] tableauChaine = calcul.split(" ");
		ArrayList<Double> nombres = new ArrayList<Double>();
		ArrayList<String> operateurs = new ArrayList<String>();
		for (String i : tableauChaine) {
			if (i.matches("[0-9]+")) {
				if (operateurnombre = true) {
					nombres.add(Double.parseDouble(i));
					operateurnombre = false;
				}
				else {
					resultatCalcul = 9999.99;
					return resultatCalcul;
				}
			}
			else {
				if (operateurnombre = false) {
					operateurs.add(i);
					operateurnombre = true;
				}
				else {
					resultatCalcul = 9999.99;
					return resultatCalcul;
				}
			}
		}
		double temporaire = nombres.get(0);
		String o = "";
		for (int i = 0; i < operateurs.size(); i=i+1){
			o = operateurs.get(i); 
			if (o.equals("+")) {
				resultatCalcul = resultatCalcul + temporaire;
				temporaire = nombres.get(i+1);
			}
			else {
				temporaire = temporaire * nombres.get(i+1);	
			}
			if (i+1 == operateurs.size()){
				resultatCalcul = resultatCalcul + temporaire;
			}	
		}
		return resultatCalcul;
	}
	
	//}
	public static void main(String[] args) {
		String adresseIP = "255.85.0.134"; 
		System.out.println("verifier adresseIP" + verifierAdresseIP(adresseIP));
	//	String cal = "17 * 2 + 5 + 2 * 6";
	//	System.out.println("Calculer : " + cal + " = " + calculate(cal) );
	}
}
