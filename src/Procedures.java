
public class Procedures {
	int ID;
	String name;
	String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Procedures(int iD, String name, String description) {
		super();
		ID = iD;
		this.name = name;
		this.description = description;
	}
	
	
	
}
