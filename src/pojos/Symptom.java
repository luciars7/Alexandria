package pojos;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "symptom")
public class Symptom implements Serializable {

	private static final long serialVersionUID = 4773372781875368145L;

	@Id
	@GeneratedValue(generator = "symptom")
	@TableGenerator(name = "symptom", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "symptom")
	private int ID;
	private String name;
	private String description;
	@ManyToMany(mappedBy = "symptom")
	private List<Disease> disease;
	public Symptom() {
		this.disease = new ArrayList<Disease>();
	}
	public Symptom(String name, String description) {
		this.name = name;
		this.description = description;
		this.disease = new ArrayList<Disease>();
	}
	
	public Symptom(int id2, String name2, String description2) {
		this.setID(id2);
		this.setName(name2);
		this.setDescription(description2);
		this.disease = new ArrayList<Disease>();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void addDisease(Disease disease) {
		this.disease.add(disease);
	}

	public void removeDisease(Disease disease) {
		this.disease.remove(disease);
	}

	public List<Disease> getDisease() {
		return disease;
	}

	public void setDisease(List<Disease> disease) {

		this.disease = disease;

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