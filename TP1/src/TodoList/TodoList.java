package TodoList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class TodoList {
	// Définir une classe fille générique

	private ArrayList<TodoElement> Liste;

	public TodoList() {
		this.Liste = new ArrayList<TodoElement>();
	}

	public ArrayList<String> readlines(String filename) {
		ArrayList<String> Liste = new ArrayList<String>();
		FileReader fr;
		try {
			fr = new FileReader(filename);

			BufferedReader br = new BufferedReader(fr);
			int i = 0;
			String s;
			while ((s = br.readLine()) != null) {
				// System.out.println(s);
				Liste.add(s);
				i = i + 1;
			}
			fr.close();
		} catch (IOException e) {
			System.out.print("Erreur accès fichier");
			System.exit(1);
		}
		return (ArrayList<String>) Liste;
	}

	public void load(String fileName) {

		for (String line : readlines(fileName)) {
			String[] w = line.split(";");

			String dateTodo = w[0];
			Integer niveau = Integer.parseInt(w[1]);
			String description = w[2];

			TodoElement T = new TodoElement(dateTodo, niveau, description);

			this.Liste.add(T);
		}

	}

	public void sortDate(ArrayList<TodoElement> listeTodoNonTri) {

		Collections.sort(listeTodoNonTri, new Comparator<TodoElement>() {

			public int compare(TodoElement tc1, TodoElement tc2) {
				return tc1.getDateLimite().compareTo(tc2.getDateLimite());
			}
		});
	}

	public void sortNiveau(ArrayList<TodoElement> listeTodoNonTri) {

		Collections.sort(listeTodoNonTri, new Comparator<TodoElement>() {

			public int compare(TodoElement tc1, TodoElement tc2) {
				return tc1.getNiveauImportance().compareTo(
						tc2.getNiveauImportance());
			}
		});
	}

	public void setIteratorOrder(String order) {
		
		if (order.equals("date")) {
			sortDate(this.Liste);
		} else
			;
		if (order.equals("niveau")) {
			sortNiveau(this.Liste);
		}
	}

	public static void main(String[] args) {
		
		String order = "niveau";

		TodoList t = new TodoList();
		t.load("TestTodoList.txt");
		t.setIteratorOrder(order);

		for (TodoElement el : t.Liste) {
			System.out.println(el.toString());
		}
		// System.out.println(listeAtomes);
	}
}