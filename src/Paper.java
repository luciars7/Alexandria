public class Paper {
	public int ID;
	public String title;
	public String source;
	
	public Paper(int iD, String title, String source) {
		ID = iD;
		this.title = title;
		this.source = source;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}