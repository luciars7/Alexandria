package pojos;
public class Images {
<<<<<<< HEAD
	public int ID;
	public String description;
	public String type;
	public String size;
	public String link;
	public Paper paper;
=======
	private int ID;
	private String description;
	private String type;
	private String size;
	private String link;
	private Paper paper_id;
>>>>>>> branch 'master' of https://github.com/luciars7/Alexandria.git
	
<<<<<<< HEAD
	public Images(int iD, String description, String type, String size, String link, Paper paper) {
=======
	public Images(int iD, String description, String type, String size, String link, Paper paper_id) {
>>>>>>> branch 'master' of https://github.com/luciars7/Alexandria.git
		ID = iD;
		this.description = description;
		this.type = type;
		this.size = size;
		this.link = link;
		this.paper = paper;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Paper getPaper_id() {
<<<<<<< HEAD
		return paper;
=======
		return paper_id;
>>>>>>> branch 'master' of https://github.com/luciars7/Alexandria.git
	}

<<<<<<< HEAD
	public void setPaper_id(Paper paper) {
		this.paper = paper;
=======
	public void setPaper_id(Paper paper_id) {
		this.paper_id = paper_id;
>>>>>>> branch 'master' of https://github.com/luciars7/Alexandria.git
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
		Images other = (Images) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}