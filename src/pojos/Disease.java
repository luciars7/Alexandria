package pojos;

import java.io.Serializable;

public class Disease implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4645657267981261074L;
	private int ID;
	private String name;
	private String description;
	private BodyPart bodyPart;
	private Symptom symptom;
	private Paper paper;
	private Image image;
	private Procedure procedure;

	public Disease(int id, String name, String description, BodyPart bodypart) {
		this.setID(id);
		this.setName(name);
		this.setDescription(description);
		this.setBodyPart(bodypart);
	}
	
	public Disease(String name, String description, BodyPart bodyPart) {
		this.setName(name);
		this.setDescription(description);
		this.setBodyPart(bodyPart);
	}

	public Disease(String name2, String description2) {
		this.setName(name2);
		this.setName(description2);
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public BodyPart getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;
	}

	public Disease() {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
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
		Disease other = (Disease) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ID=" + ID + ", name=" + name + ", description=" + description + "]";
	}

}