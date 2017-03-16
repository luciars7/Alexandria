public class Diseases {
int ID;
String name;
String description;
BodyParts bodyParts;

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

    public BodyParts getBodyPart() {
        return bodyParts;
    }

    public void setBodyPart(int bodyPart) {
        this.bodyParts = bodyParts;
    }

    public Diseases(int ID, String name, String description, int bodyParts) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.bodyParts = bodyParts;
    }

}