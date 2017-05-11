package pojos;

import java.io.Serializable;

public class Paper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1001927883398001730L;
	private int ID;
	private String title;
	private String source;
	private Author author;
	private Disease disease;
	
	public Paper(int iD, String title, String source) {
		ID = iD;
		this.title = title;
		this.source = source;
	}
	
	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}


	public Paper(String title, String source, Author author, Disease disease) {
		super();
		this.title = title;
		this.source = source;
		this.author = author;
		this.disease = disease;
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