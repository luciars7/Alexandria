
public class Symptoms {
	int ID;
	String name;
	String description;
	
	public Symptoms(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
