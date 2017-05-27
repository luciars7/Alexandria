package pojos;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;

@Entity

@Table(name = "paper")

public class Paper implements Serializable {
	private static final long serialVersionUID = 1001927883398001730L;
	@Id
	@GeneratedValue(generator = "paper")
	@TableGenerator(name = "paper", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "paper")
	private int ID;
	private String title;
	private String source;
	@ManyToMany(mappedBy = "paper")
	private List<Author> author;
	@ManyToMany(mappedBy = "paper")
	private List<Disease> disease;
	@OneToMany(mappedBy = "paper")
	private List<Device> device;
	@OneToMany(mappedBy = "paper")
	private List<Procedure> procedure;
	@OneToMany(mappedBy = "paper")
	private List<Image> image;
	public Paper() {
		this.disease = new ArrayList<Disease>();
		this.author = new ArrayList<Author>();
		this.image = new ArrayList<Image>();
		this.procedure = new ArrayList<Procedure>();
	}

	public Paper(String title, String source) {

		this.title = title;

		this.source = source;

		this.disease = new ArrayList<Disease>();

		this.author = new ArrayList<Author>();

		this.image = new ArrayList<Image>();
		this.procedure = new ArrayList<Procedure>();

	}

	public Paper(int iD, String title, String source) {

		ID = iD;

		this.title = title;

		this.source = source;

		this.disease = new ArrayList<Disease>();

		this.author = new ArrayList<Author>();

		this.image = new ArrayList<Image>();
		this.procedure = new ArrayList<Procedure>();
		
	}

	public Paper(String title, String source, List<Author> author, List<Disease> disease) {

		super();

		this.title = title;

		this.source = source;

		this.author = author;

		this.disease = disease;

		this.image = new ArrayList<Image>();
		this.procedure = new ArrayList<Procedure>();

	}

	public List<Device> getDevice() {

		return device;

	}

	public void setDevice(List<Device> device) {

		this.device = device;

	}

	public List<Procedure> getProcedure() {

		return procedure;

	}

	public void setProcedure(List<Procedure> procedure) {

		this.procedure = procedure;

	}
	
	public void addProcedure(Procedure p) {

		this.procedure.add(p);

	}

	public void removeProcedure(Procedure p) {

		this.procedure.remove(p);

	}


	public static long getSerialversionuid() {

		return serialVersionUID;

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

	public List<Author> getAuthor() {

		return author;

	}

	public void setAuthor(List<Author> author) {

		this.author = author;

	}

	public void addAuthor(Author author) {

		this.author.add(author);

	}

	public void removeAuthor(Author author) {

		this.author.remove(author);

	}

	public int getID() {

		return ID;

	}

	public void setID(int iD) {

		ID = iD;

	}

	public String getTitle() {

		return title;

	}

	public void setTitle(String title) {

		this.title = title;

	}

	public String getSource() {

		return source;

	}

	public void setSource(String source) {

		this.source = source;

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

		Paper other = (Paper) obj;

		if (ID != other.ID)

			return false;

		return true;

	}

	@Override

	public String toString() {

		return "[ID=" + ID + ", title=" + title + ", source=" + source + "]";

	}

}