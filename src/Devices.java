
public class Devices {
	int ID;
	String name;
	String type;
	int price;
	String brand;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Devices(int iD, String name, String type, int price, String brand) {
		super();
		ID = iD;
		this.name = name;
		this.type = type;
		this.price = price;
		this.brand = brand;
	}
	
}