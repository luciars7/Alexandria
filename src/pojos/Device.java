package pojos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "device")
public class Device implements Serializable {

	private static final long serialVersionUID = -4117700635988378197L;

	@Id
	@GeneratedValue(generator = "device")
	@TableGenerator(name = "device", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "device")
	private int ID;
	private String name;
	private String type;
	private float price;
	private String brand;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "procedure_id")
	private Procedure procedure;
	@OneToMany(mappedBy = "device")
	private List<Paper> paper;

	public Device() {

	}

	public Device(int iD, String name, String type, float price, String brand) {
		ID = iD;
		this.name = name;
		this.type = type;
		this.price = price;
		this.brand = brand;
	}
	
	public Device(String name, String type, float price, String brand) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.brand = brand;
	}

	public Device(String name, String type, float price, String brand, Procedure procedure, List<Paper> paper) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.brand = brand;
		this.procedure = procedure;
		this.paper = paper;
	}

	public int getID() {
		return ID;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public List<Paper> getPaper() {
		return paper;
	}

	public void setPaper(List<Paper> paper) {
		this.paper = paper;
	}

	public void addPaper(Paper paper) {
		this.paper.add(paper);
	}

	public void removePaper(Paper paper) {
		this.paper.remove(paper);
	}

	public void setPrice(float price) {
		this.price = price;
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