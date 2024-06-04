package Model;

public class Music {
	private String title;
	private String singer;
	private String path;

	public Music() {

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Music(String title, String singer, String path) {
		this.title = title;
		this.singer = singer;
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public String getPath() {
		return path;
	}

}
