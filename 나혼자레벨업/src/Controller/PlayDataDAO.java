package Controller;

import java.util.ArrayList;

import Model.PlayDataDTO;

public class PlayDataDAO {
	// PlayDataDTO에 들어있는 데이터,,,, DTO를 이렇게 사용해도 되는지는 잘 모르겠지만,,
	// DTO를 이용해서 DAO에 method 만들어보겠음
	private PlayDataDTO dto;
	
	public PlayDataDAO() {
		dto = new PlayDataDTO();
	}
	

	public ArrayList<String> getTitle() {
		return dto.getTitle();
	}
	
	public ArrayList<String> getGoodList() {
		return dto.getGoodList();
	}

	public ArrayList<String> getBadList() {
		return dto.getBadList();
	}
	
	
	public ArrayList<String> getDayGraphic() {
		return dto.getDayGraphic();
	}
	
	public ArrayList<String> getQuiz() {
		return dto.getQuiz();
	}
	
	public ArrayList<String> getqAnswer() {
		return dto.getqAnswer();
	}
	
}
