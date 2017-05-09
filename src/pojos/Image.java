package pojos;

import java.io.Serializable;
import java.util.Arrays;

public class Image implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2122694598463647223L;
	private int ID;
	private String description;
	private String type;
	private String size;
	private byte[] image;
	private Paper paper;
	private Disease disease;
	
	public Image(int iD, String description, String type, String size, byte[] image, Paper paper) {
		ID = iD;
		this.description = description;
		this.type = type;
		this.size = size;
		this.image = image;
		this.paper = paper;
	}	
	
	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Image() {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
	}

	public Image(String description, String type, String size, byte[] image, Paper paper, Disease disease) {
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