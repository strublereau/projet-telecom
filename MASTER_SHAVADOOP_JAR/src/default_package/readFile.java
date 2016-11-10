package default_package;

import java.io.*;
import java.util.ArrayList;

class readFile {

	public static ArrayList<String> readLines(String nomfichier) {

		ArrayList<String> listLine = new ArrayList<String>();
		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(nomfichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				listLine.add(ligne);
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return listLine;
	}

}