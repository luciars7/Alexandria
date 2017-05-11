package pojos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "procedure")
public class Procedure implements Serializable {

	private static final long serialVersionUID = -3468306807607790130L;

	@Id
	@GeneratedValue(generator = "procedure")
	@TableGenerator(name = "procedure", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "procedure")
	private int ID;
	private String name;
	private String description;
	@ManyToMany(mappedBy = "procedure")
	private List<Disease> disease;
	@OneToMany(mappedBy = "procedure")
	private List<Paper> paper;
	@OneToMany(mappedBy = "procedure")
	private List<Device> device;

	public void addDevice(Device device) {
		this.device.add(device);
	}

	public void removeDevice(Device device) {
		this.device.remove(device);
	}

	public List<Device> getDevice() {
		return device;
	}

	public void setDevice(List<Device> device) {
		this.device = device;
	}

	public void addDisease(Disease disease) {
		this.disease.add(disease);
	}

	public void removeDisease(Disease disease) {
		this.disease.remove(disease);
	}

	public Procedure(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public void addPaper(Paper paper) {
		this.paper.add(paper);
	}

	public void removePaper(Paper paper) {
		this.paper.remove(paper);
	}

	public List<Paper> getPaper() {
		return paper;
	}

	public void setPaper(List<Paper> paper) {
		this.paper = paper;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Disease> getDisease() {
		return disease;
	}

	public void setDisease(List<Disease> disease) {
		this.disease = disease;
	}

	public Procedure() {

	}

	public Procedure(int id, String name, String description) {
		this.setID(id);
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
		Procedure other = (Procedure) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ID=" + ID + ", name=" + name + ", description=" + description + "]";
	}

}
