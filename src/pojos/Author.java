package pojos;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"ID","name","origin","association","paper"})
@Entity
@Table(name = "author")

public class Author implements Serializable {
	private static final long serialVersionUID = 5523276157826073516L;

	@Id
	@GeneratedValue(generator = "author")
	@TableGenerator(name = "author", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "author")
	@XmlAttribute
	private int ID;
	@XmlAttribute
	private String name;
	@XmlTransient
	private String origin;
	@XmlTransient
	private String association;
	@ManyToMany
	@JoinTable(name = "paperauthor", joinColumns = {
			@JoinColumn(name = "paper", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "author", referencedColumnName = "ID") })
	@XmlTransient
	private List<Paper> paper;
	
	public Author() {
		this.paper = new ArrayList<Paper>();
	}

	public Author(int iD, String name, String origin, String association) {
		ID = iD;
		this.name = name;
		this.origin = origin;
		this.association = association;
		this.paper = new ArrayList<Paper>();
	}

	public Author(String name2, String origin2, String association2) {
		this.name = name2;
		this.origin = origin2;
		this.association = association2;
		this.paper = new ArrayList<Paper>();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
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
		Author other = (Author) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ID = " + ID + ", name = " + name + ", origin = " + origin + ", association = " + association + "]";
	}
}