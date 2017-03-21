package POJOS;
public class Diseases {
	int ID;
	String name;
	String description;
	BodyParts bodyParts;
	
	public Diseases(int ID, String name, String description, BodyParts bodyParts) {
	    this.ID = ID;
	    this.name = name;
	    this.description = description;
	    this.bodyParts = bodyParts;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BodyParts getBodyParts() {
        return bodyParts;
    }

    public void setBodyPart(BodyParts bodyParts) {
        this.bodyParts = bodyParts;
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
		Diseases other = (Diseases) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}