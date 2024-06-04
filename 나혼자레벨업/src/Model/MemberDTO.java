package Model;
//미니


public class MemberDTO {

	private String id;
	private String pw;
	private String name;
	private int score ;
	private int dday ;
	private String gender;

	public MemberDTO(String id, String pw, String name, String gender) {

		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
	}

	
	
	public MemberDTO(String id, String gender,int score,int dday,String name) {
		
		this.id = id;
		this.name = name;
		this.score = score;
		this.dday = dday;
		this.gender = gender;
	}



	public MemberDTO(String id, String pw) {
		
		this.id = id;
		this.pw = pw;
	}

	public MemberDTO(int score, int dday) {
		this.score = score;
		this.dday = dday;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDday() {
		return dday;
	}

	public void setDday(int dday) {
		this.dday = dday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
