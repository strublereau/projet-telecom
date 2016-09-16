import java.util.HashMap; 
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;

public class Molecule {

    public static HashMap<String, Integer> readInventory(String content) {
	HashMap<String, Integer> inventory = new HashMap<String, Integer>();

	for (String line : content.split("\n")) {
	    if (line.equals("")) {
		continue;
	    }

	    if (line.startsWith("#")) {
		continue;
	    }

	    line = line.split("#")[0];

	    String name = line.split(" ")[0];
	    Integer number = Integer.parseInt(line.split(" ")[1]);

	    inventory.put(name, number);
	}

	return inventory;
    }

    public static HashMap<String, HashMap<String, Integer>> readFormulas(String content) {
	HashMap<String, HashMap<String, Integer>> formulas = new HashMap<String, HashMap<String, Integer>>();
	
	for (String line : content.split("\n")) {

	    if (line.equals("")) {
		continue;
	    }

	    if (line.startsWith("#")) {
		continue;
	    }

	    line = line.split("#")[0];

	    String molName = line.split(" : ")[0];
	    String formulaStr = line.split(" : ")[1];
	    HashMap<String, Integer> formula = new HashMap<String, Integer>();
	
	    Iterator<String> it = Arrays.asList(formulaStr.split(" ")).iterator();

	    while (it.hasNext()) {
		String atomName = it.next();
		Integer number = Integer.parseInt(it.next());
		formula.put(atomName, number);
	    }
	    formulas.put(molName, formula);
	}

	return formulas;
    }

    public static ArrayList<String> makeable(HashMap<String, Integer> inv,  HashMap<String, HashMap<String, Integer>> formulas) {
	ArrayList<String> res = new ArrayList<String>();

	for (String molName : formulas.keySet()) {
	    HashMap<String, Integer> f = formulas.get(molName);
	    boolean canMakeIt = true;

	    for (String atomName : f.keySet()) {
		Integer neededAtoms = f.get(atomName);
		Integer availableAtoms = inv.get(atomName);
		
		if (availableAtoms == null) {
		    availableAtoms = 0;
		}

		canMakeIt = canMakeIt && neededAtoms <= availableAtoms;
	    }

	    if (canMakeIt) {
		res.add(molName);
	    }
	}

	return res;
    }

    public static void main(String[] s) {

	String invContent;
	invContent = "# Once again, comments and blank lines are allowed.";
	invContent = "\n";
	invContent += "\nHe 3                        # atom name, number available";
	invContent += "\nH 2";
	invContent += "\nN 5";
	invContent += "\nLi 1";

	HashMap<String, Integer> inv = readInventory(invContent);
	for (String atomName : inv.keySet()) {
	    System.out.println(atomName + " ==> " + inv.get(atomName));
	}
	System.out.println();
	
	String recipeContent;
        recipeContent = "# Comments start with '#' and go to the end of the line.";
	recipeContent += "\n# Blank lines (like the one below) are allowed.";
	recipeContent += "\n";
	recipeContent += "\nhelium : He 1               # molecule name, colon, atom type, number of atoms";
	recipeContent += "\nammonia : N 1 H 3           # molecules may contain many types of atoms";
	recipeContent += "\nsalt : Na 1 Cl 1            # atom names may be one or two characters long";
	recipeContent += "\nlithium hydride : Li 1 H 1  # molecule names may be several words long";

	HashMap<String, HashMap<String, Integer>> recipes = readFormulas(recipeContent);
	for (String molName : recipes.keySet()) {
	    System.out.println(molName);
	    for (int i = 0; i < molName.length(); i++) {
		System.out.print("-");
	    }
	    System.out.println();
	    for (String atomName : recipes.get(molName).keySet()) {
		System.out.println(atomName + " ==> " + recipes.get(molName).get(atomName));
	    }
	    System.out.println();
	}

	System.out.println("");
	System.out.println("Molécules réalisables : ");
	for (String molName : makeable(inv, recipes)) {
	    System.out.println("\t- " + molName);
	}
    }
}
