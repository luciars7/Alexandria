package pojos;
public class BodyPart {
	private int ID;
	private String name;
	private String location;
	
	public BodyPart(int ID, String name, String location){
		this.ID = ID;
		this.name = name;
		this.location = location;
	}
	

	public BodyPart(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}



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
		BodyPart other = (BodyPart) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Body part [ID=" + ID + ", name=" + name + ", location=" + location + "]";
	}
}