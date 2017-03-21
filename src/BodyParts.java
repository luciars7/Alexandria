public class BodyParts {
	int ID;
	String name;
	String location;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BodyParts(int ID, String name, String location) {
		this.ID = ID;
		this.name = name;
		// qwe
		this.location = location;
	}
}