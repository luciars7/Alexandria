package pojos;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;

@Entity

@Table(name = "disease")

public class Disease implements Serializable {

	private static final long serialVersionUID = -4645657267981261074L;

	@Id
	@GeneratedValue(generator = "disease")
	@TableGenerator(name = "disease", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "disease")
	private int ID;
	private String name;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bodypart")
	private BodyPart bodypart;
	@ManyToMany
	@JoinTable(name = "symptomdisease", joinColumns = {
			@JoinColumn(name = "symptom", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "disease", referencedColumnName = "ID") })
	private List<Symptom> symptom;
	@ManyToMany
	@JoinTable(name = "paperdisease", joinColumns = {
			@JoinColumn(name = "paper", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "disease", referencedColumnName = "ID") })
	private List<Paper> paper;
	@ManyToMany
	@JoinTable(name = "imagedisease", joinColumns = {
			@JoinColumn(name = "image", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "disease", referencedColumnName = "ID") })
	private List<Image> image;
	@ManyToMany
	@JoinTable(name = "proceduredisease", joinColumns = {
			@JoinColumn(name = "procedure", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "disease", referencedColumnName = "ID") })
	private List<Procedure> procedure;

	public Disease() {
		this.image = new ArrayList<Image>();
		this.symptom = new ArrayList<Symptom>();
		this.paper = new ArrayList<Paper>();
		this.procedure = new ArrayList<Procedure>();
	}

	public Disease(int id, String name, String description, BodyPart bodypart) {
		this.setID(id);
		this.setName(name);
		this.setDescription(description);
		this.setBodyPart(bodypart);
		this.image = new ArrayList<Image>();
		this.symptom = new ArrayList<Symptom>();
		this.paper = new ArrayList<Paper>();
		this.procedure = new ArrayList<Procedure>();
	}

	public Disease(String name, String description, BodyPart bodyPart) {
		this.setName(name);
		this.setDescription(description);
		this.setBodyPart(bodyPart);
		this.image = new ArrayList<Image>();
		this.symptom = new ArrayList<Symptom>();
		this.paper = new ArrayList<Paper>();
		this.procedure = new ArrayList<Procedure>();
	}

	public Disease(String name2, String description2) {
		this.setName(name2);
		this.setDescription(description2);
		this.image = new ArrayList<Image>();
		this.symptom = new ArrayList<Symptom>();
		this.paper = new ArrayList<Paper>();
		this.procedure = new ArrayList<Procedure>();
	}

	public Disease(int id2, String name2, String description2) {
		this.setID(id2);
		this.setName(name2);
		this.setDescription(description2);
		this.image = new ArrayList<Image>();
		this.symptom = new ArrayList<Symptom>();
		this.paper = new ArrayList<Paper>();
		this.procedure = new ArrayList<Procedure>();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BodyPart getBodypart() {
		return bodypart;
	}

	public void setBodypart(BodyPart bodypart) {
		this.bodypart = bodypart;
	}

	public BodyPart getBodyPart() {
		return bodypart;
	}

	public void setBodyPart(BodyPart bodyPart) {
		this.bodypart = bodyPart;
	}

	public void addPaper(Paper paper) {
		this.paper.add(paper);
	}

	public void removePaper(Paper paper) {
		this.paper.remove(paper);
	}

	public List<Paper> getPaper() {
		return paper;
	}

	public void setPaper(List<Paper> paper) {
		this.paper = paper;
	}

	public void addProcedure(Procedure procedure) {
		this.procedure.add(procedure);
	}

	public void removeProcedure(Procedure procedure) {
		this.procedure.remove(procedure);
	}

	public List<Procedure> getProcedure() {
		return procedure;
	}

	public void setProcedure(List<Procedure> procedure) {
		this.procedure = procedure;
	}

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

	public void addImage(Image image) {
		this.image.add(image);
	}

	public void removeImage(Image image) {
		this.image.remove(image);
	}

	public List<Symptom> getSymptom() {
		return symptom;
	}

	public void setSymptom(List<Symptom> symptom) {
		this.symptom = symptom;
	}

	public void addSymptom(Symptom symptom) {
		this.symptom.add(symptom);
	}

	public void removeSymptom(Symptom symptom) {
		this.symptom.remove(symptom);
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
		return "[ID = " + ID + ", name = " + name + ", description = " + description + "]";
	}
}