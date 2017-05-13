package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image implements Serializable {

	private static final long serialVersionUID = -2122694598463647223L;

	@Id
	@GeneratedValue(generator = "image")
	@TableGenerator(name = "image", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "image")
	private int ID;
	private String description;
	private String type;
	private String size;
	private byte[] image;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paper")
	private Paper paper;
	@ManyToMany(mappedBy = "image")
	private List<Disease> disease;

	public Image() {
		this.disease = new ArrayList<Disease>();
	}

	
	public Image(int iD, String description, String type, String size, byte[] image, Paper paper) {
		ID = iD;
		this.description = description;
		this.type = type;
		this.size = size;
		this.image = image;
		this.paper = paper;
		this.disease = new ArrayList<Disease>();
	}

	public Image(String description, String type, String size, byte[] image) {
		this.description = description;
		this.type = type;
		this.size = size;
		this.image = image;
		this.disease = new ArrayList<Disease>();
	}
	
	public List<Disease> getDisease() {
		return disease;
	}

	public void setDisease(List<Disease> disease) {
		this.disease = disease;
	}

	public void addDisease(Disease disease) {
		this.disease.add(disease);
	}

	public void removeDisease(Disease disease) {
		this.disease.remove(disease);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getID() {
		return ID;
	}

	public Image(String description, String type, String size, byte[] image, Paper paper, List<Disease> disease) {
		this.description = description;
		this.type = type;
		this.size = size;
		this.image = image;
		this.paper = paper;
		this.disease = disease;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Paper getPaper() {

		return paper;

	}

	public void setPaper(Paper paper) {
		this.paper = paper;
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
		Image other = (Image) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ID=" + ID + ", description=" + description + ", type=" + type + ", size=" + size + ", image="
				+ Arrays.toString(image) + ", paper=" + paper.getTitle() + "]";
	}

}