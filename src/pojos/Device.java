package pojos;
public class Device {
	private int ID;
	private String name;
	private String type;
	private float price;
	private String brand;
	
	public Device(int iD, String name, String type, float price, String brand) {
		ID = iD;
		this.name = name;
		this.type = type;
		this.price = price;
		this.brand = brand;
	}
	
	public Device() {
		// TODO Auto-generated constructor stub
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public float getPrice() {
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
		Device other = (Device) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ID=" + ID + ", name=" + name + ", type=" + type + ", price($) =" + price + ", brand=" + brand + "]";
	}	
	
	
}