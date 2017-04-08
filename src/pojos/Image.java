package pojos;

import java.util.Arrays;

public class Image {

	private int ID;
	private String description;
	private String type;
	private String size;
	private byte[] image ;
	private Paper paper;
	
	public Image(int iD, String description, String type, String size, byte[] image, Paper paper) {
		ID = iD;
		this.description = description;
		this.type = type;
		this.size = size;
		this.image = image;
		this.paper = paper;
	}	

	public Image() {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
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
				+ Arrays.toString(image) + ", paper=" + paper + "]";
	}
	
	
}