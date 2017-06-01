package pojos;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@Entity

@Table(name = "procedure")

public class Procedure implements Serializable {

	private static final long serialVersionUID = -3468306807607790130L;

	@Id

	@GeneratedValue(generator = "procedure")

	@TableGenerator(name = "procedure", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "procedure")
	@XmlAttribute
	private int ID;
	@XmlAttribute
	private String name;
	@XmlTransient
	private String description;
	@ManyToMany(mappedBy = "procedure")
	private List<Disease> disease;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paper")
	@XmlTransient
	private Paper paper;
	@OneToMany(mappedBy = "procedure")
	@XmlTransient
	private List<Device> device;
	
	public Procedure() {
		this.disease = new ArrayList<Disease>();
		this.device = new ArrayList<Device>();
	}

	public Procedure(int id, String name, String description) {
		this.setID(id);
		this.name = name;
		this.description = description;
		this.disease = new ArrayList<Disease>();
		this.device = new ArrayList<Device>();
	}

	public Procedure(String name, String description) {
		this.name = name;
		this.description = description;
		this.disease = new ArrayList<Disease>();
		this.device = new ArrayList<Device>();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
	
	public List<Disease> getDisease() {
		return disease;
	}

	public void setDisease(List<Disease> disease) {
		this.disease = disease;
	}


	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
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
		return "[ID = " + ID + ", name = " + name + ", description = " + description + "]";
	}
}