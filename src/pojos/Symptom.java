package pojos;

import java.io.Serializable;

public class Symptom implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4773372781875368145L;
	private int ID;
	private String name;
	private String description;
	private Disease disease;
	
	public Symptom(int id2, String name2, String description2) {
		this.setID(id2);
		this.setName(name2);
		this.setDescription(description2);
	}
	
	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Symptom(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Symptom() {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symptom other = (Symptom) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ID=" + ID + ", name=" + name + ", description=" + description + "]";
	}
	
	
}