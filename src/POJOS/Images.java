package POJOS;
public class Images {
	public int ID;
	public String description;
	public String type;
	public String size;
	public String link;
	public Paper paper_id;

	public Images(int iD, String description, String type, String size, String link, Paper paper_id) {
		ID = iD;
		this.description = description;
		this.type = type;
		this.size = size;
		this.link = link;
		this.paper_id = paper_id;
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
		return paper_id;
	}

	public void setPaper_id(Paper paper_id) {
		this.paper_id = paper_id;
	}

}