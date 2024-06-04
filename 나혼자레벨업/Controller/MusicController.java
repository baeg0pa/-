package Controller;

import java.util.ArrayList;
import java.util.Random;

import Model.Music;
import javazoom.jl.player.MP3Player;

public class MusicController {
	// Controller ->기능들을 가지고 있는 도구

	// 1) 필드
	Random rd = new Random();
	private MP3Player mp3 = new MP3Player();

	private ArrayList<Music> musicList = new ArrayList<Music>();
	private int i = 0;
	// 현재 순서(몇번째 곡인지 기억하고 있는 변수)

	// 2 메소드

	// 1.기본생성자
	public MusicController() {
		String comPath = "C:\\javastudy\\EX09_ArrayList\\player\\"; // 중복되는 주소
		musicList.add(new Music("밤양갱", "비비", comPath + "밤양갱.mp3"));
		musicList.add(new Music("BubbleGum", "뉴진스", comPath + "Bubble Gum.mp3"));
		musicList.add(new Music("고민중독", "QWER", comPath + "고민중독.mp3"));
		musicList.add(new Music("첫만남은계획대로되지않아", "TWS", comPath + "첫만남은계획대로되지않아.mp3"));
		musicList.add(new Music("한페이지가될수있게", "데이식스", comPath + "한페이지가될수있게.mp3"));
		musicList.add(new Music("ETA", "뉴진스", comPath + "ETA.mp3"));
		musicList.add(new Music("빨간맛", "레드벨벳", comPath + "레드벨벳 - 빨간 맛.mp3"));
		musicList.add(new Music("링딩동", "샤이니", comPath + "샤이니 - 링딩동.mp3"));
		musicList.add(new Music("MAESTRO", "세븐틴", comPath + "세븐틴 - MAESTRO.mp3"));
		musicList.add(new Music("손오공", "세븐틴", comPath + "세븐틴 - 손오공.mp3"));
		musicList.add(new Music("LOVEDIVE", "아이브", comPath + "아이브 - LOVE-DIVE.mp3"));
		musicList.add(new Music("해야", "아이브", comPath + "아이브 - 해야.mp3"));
		musicList.add(new Music("후라이의꿈", "악뮤", comPath + "악뮤 - 후라이의-꿈.mp3"));
		musicList.add(new Music("던던댄스", "오마이걸", comPath + "오마이걸 - 던던댄스.mp3"));
		musicList.add(new Music("신호등", "이무진", comPath + "이무진 -신호등.mp3"));
		musicList.add(new Music("INVU", "태연", comPath + "태연 -INVU.mp3"));
		
	}

	// 2.재생하는 메소드
	public Music play() {

		i = rd.nextInt(musicList.size());
		if (mp3.isPlaying()) {
			mp3.stop();
		}
		mp3.play(musicList.get(i).getPath());
		return musicList.get(i);
	}

	// 3. 정지하는 메소드
	public boolean stop() {
		boolean result = false;
		if (mp3.isPlaying()) {
			mp3.stop();
			result = true;
		}
		return result;
	}

}
