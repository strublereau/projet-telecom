package exercicesj2;

import java.util.ArrayList;

class Calculatrice {

	public static double calculate(String calcul) {
		double resultatCalcul = 0.0;
		// boolean operateurnombre = true;
		// attention cet algorythme ne vérifie pas le premier opérateur
		String[] tableauChaine = calcul.split(" ");
		// prévoir l'elimination du premier element si operation
		ArrayList<Double> nombres = new ArrayList<Double>();
		ArrayList<String> operateurs = new ArrayList<String>();
		for (String i : tableauChaine) {
			if (i.matches("[0-9]+")) {
				nombres.add(Double.parseDouble(i));
			} else {
				if (nombres.size() > 0) {
					operateurs.add(i);
				}
			}
		}
		double temporaire = nombres.get(0);
		String o = "";
		for (int i = 0; i < operateurs.size(); i = i + 1) {
			o = operateurs.get(i);
			if (o.equals("+")) {
				resultatCalcul = resultatCalcul + temporaire;
				temporaire = nombres.get(i + 1);
			} else {
				temporaire = temporaire * nombres.get(i + 1);
			}
			if (i + 1 == operateurs.size()) {
				resultatCalcul = resultatCalcul + temporaire;
			}
		}
		return resultatCalcul;
	}

	public static double calculate2(String[] args, int i, double temp) {
		// double resultatCalcul = 0.0;
		// String[] args1 = args;
		if (i == args.length) {
			return temp;
		}
		if (args[i].matches("[0-9]+")) {
			double number = 0.0;
			if (temp == 0.0) {
     			number = Double.parseDouble(args[i]);
			}
			else {
				number = temp * Double.parseDouble(args[i]);
			}
			return calculate2(args, i + 1, number);
		} else {
			switch (args[i]) {
			case "+":
				return temp + calculate2(args, i + 1, 0.0);
			case "*":
				//if (i == 0) {
				//	i = i + 1;
				//	double number = Double.parseDouble(args[i]) + calculate2(args, i + 1, 0.0);
				//	return number;
				//} else {
				//	i = i + 1;
					double number = calculate2(args, i + 1, temp);
					// return number + calculate2(args,i+1,0.0);
				//	System.out.println("Valeur : " +  );
					return number;
				//}
			default:
				System.out.println("Valeur : " + i + " = " + " valeur args " + args[i] + " valeur args " + args[i + 1]);
				return 999.0;
			}
		}
	}

	// }
	public static void main(String[] args) {
		String cal = "+ 5 + 2 * 3 * 5 * 6 + 1";
		String[] tableauChaine = cal.split(" ");
		System.out.println("Calculer : " + cal + " = " + calculate(cal));
		int indice = 0;
		double temporaire = 0.0;
		System.out.println("Calculer 2 : " + cal + " = " + calculate2(tableauChaine, indice, temporaire));

	}
}
