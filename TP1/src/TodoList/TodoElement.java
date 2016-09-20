package TodoList;

import java.util.HashMap;

class TodoElement {

	private String dateLimite;
	private Integer niveauImportance;
	private String description;
	
	public TodoElement(String dateLimite,
			Integer niveauImportance, String description) {
		this.dateLimite = dateLimite;
		this.niveauImportance = niveauImportance;
		this.description = description;
	}

	public String getDateLimite() {
		return dateLimite;
	}

	public Integer getNiveauImportance() {
		return niveauImportance;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		   return "datelimite : " + this.dateLimite +
			  " : niveauImportance : " + this.niveauImportance +
			  ", description : " + this.description;
		}
}
