public class Author {
	public int ID;
	public String name;
	public String origin;
	public String association;

	public Author(int iD, String name, String origin, String association) {
		ID = iD;
		this.name = name;
		this.origin = origin;
		this.association = association;
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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}
	
	
}