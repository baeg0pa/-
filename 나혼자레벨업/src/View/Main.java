package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;
		Scanner input = new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
			String user = "cgi_24IS_cloud3_p1_2";
			String password = "smhrd2";

			conn = DriverManager.getConnection(url, user, password);

			if (conn != null) {
				System.out.println("나 혼자 레벨업!");
			} else {
				System.out.println("서버와의 연결에 실패하였습니다.\n관리자에게 문의해주세요");
			}

//			System.out.println("[1]회원가입 [2]로그인 [3]종료");
//			System.out.print(">> ");

//			System.out.println(" __        __         __       ________     __\n"
//							 + "|  |      |  |    ___|  |___  |_____   |   |  |\n"
//							 + "|  |      |  |_  |__________|      /   /   |  |_\n"
//							 + "|  |      |   _|   |  ___  |      /    \\   |   _|\n"
//							 + "|  |____  |  |     |_______|     /  /\\  \\  |  |\n"
//							 + "|_______| |  |     ___| |___    /__/  \\__\\ |  | \n"
//							 + "          |__|    |_________|              |__|\n"
//							 + "                    | |____\n"
//							 + "                    |______|\n"
//							 + "\n"
//							 + "            ________   __   "
//							 + "           |______  | |  |   \n"
//							 + "            _____|  |_|  |    \n"
//							 + "           |  _____|__   | \n"
//							 + "           |________| |  |\n"
//							 +  "");

			System.out.println("☆☆■■■■■☆☆■■☆☆■■■■■■☆☆■■☆☆☆☆☆■■■■☆☆☆☆☆☆☆☆☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆■■☆■☆☆☆■☆☆☆■☆■■☆■☆☆☆■■■■■☆☆■■");
			System.out.println("☆■■■■■■☆☆■■☆☆■■☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆■■■■■■☆☆■■☆☆■■■■■☆■■☆■☆☆☆■☆☆☆■☆■■☆■☆☆■■■■■■☆☆■■");
			System.out.println("☆■■☆☆☆■■☆■■☆☆■■☆☆☆☆☆☆■■☆☆■■■■■■■■■■☆☆☆☆☆☆☆■■☆☆■■☆☆☆☆☆■■☆■■☆■☆☆☆■■■■■☆■■☆■☆☆■■☆☆☆■■☆■■");
			System.out.println("☆■■☆☆☆■■☆■■☆☆■■■■■■■■■■☆☆☆■■■■■■■■☆☆☆☆☆☆☆■■■☆☆■■☆☆☆☆☆■■☆■■☆■☆☆☆■☆☆☆■■■■☆■☆☆■■☆☆☆■■■■■");
			System.out.println("☆■■☆☆☆■■☆■■☆☆■■☆■☆☆☆☆■■☆☆☆■■■■■■■■☆☆☆☆☆☆☆■■☆☆☆■■☆☆■■■■■☆■■☆■☆☆☆■☆☆■■☆■■☆■☆☆■■■■■■■☆■■");
			System.out.println("☆■■■■■■☆☆■■☆☆■■☆☆☆☆☆☆■■☆☆☆☆■■■■■■■☆☆☆☆☆☆■■■☆☆☆■■■■■☆☆☆■■■■☆■☆☆☆■■■■■☆■■☆■☆☆☆■■■■■☆☆■■");
			System.out.println("☆☆■■■■■☆☆■■☆☆■■■■■■■☆■■☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆■■■■■☆☆■■☆■■☆☆☆☆☆■■☆■☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
			System.out.println("☆☆☆☆☆☆☆☆☆■■☆☆☆☆■☆☆☆☆☆■■☆■■■■■■■■■■■■☆■■■■☆■■■☆■■☆■■☆☆☆☆☆■■☆■☆☆☆☆☆■■■■■■■■☆☆☆☆■☆☆☆☆☆■■");
			System.out.println("☆☆☆■■☆☆☆☆■■☆☆☆☆■■☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆■■■☆☆☆■■☆■■☆☆■■■■■■■■☆■☆☆☆☆☆☆☆☆☆☆☆■■☆☆☆☆■☆☆☆☆☆■■");
			System.out.println("☆☆☆■■☆☆☆☆☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆■■☆☆☆☆■■☆☆■■☆■☆☆☆☆☆■■■■■■■■☆☆☆☆■■■■■■■■");
			System.out.println("☆☆☆■■☆☆☆☆☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆■■☆■☆☆☆☆☆■☆☆☆☆☆☆☆☆☆☆☆■☆☆☆☆☆■■");
			System.out.println("☆☆☆■■■■■■■■☆☆☆☆■■■■■■■■☆☆☆■■■■■■■■■☆☆☆☆☆☆☆☆☆☆☆■■☆☆☆☆☆☆☆☆■■☆■☆☆☆☆☆■■■■■■■■■☆☆☆■■■■■■■■");

//			Scanner sc = new Scanner(System.in);
//			System.out.println("==== 캐릭터 정보창 ====");
//			System.out.println("[1] 출근 [2] 캐릭터 정보보기 [3] 탈퇴하기 >> ");
//			while (true) {
//				int choice = sc.nextInt();
//				if (choice == 1) {
//					System.out.println("==== 출근 ====");
//					
//					
//					
//				} else if (choice == 2) {
//					System.out.println("==== 캐릭터 정보보기 ====");
//					System.out.println("ID\t닉네임\t성별\t인사점수\t진행날짜");
//				} else if (choice == 3) {
//					// 탈퇴 창
//					System.out.println("==== 회원 삭제 ====");
//					System.out.print("ID 입력 : ");
//					String id = sc.next();
//					System.out.print("PW 입력 : ");
//					String pw = sc.next();
//					int row = 0;
//					if (row > 0) {
//						System.out.println("회원탈퇴에 성공하였습니다!");
//					} else {
//						System.out.println("회원탈퇴에 실패하였습니다..ㅠㅠ");
//						System.out.println("아이디나 비밀번호를 다시 확인해주세요ㅠㅠ");
//					}
//				}
//
//			}

			System.out.println();
			System.out.println();
			System.out.println();

			// id, pw, name, gender, score, dday

			int score = 0;

			ArrayList<String> goodList = new ArrayList<>();
			goodList.add("럭키! 상사가 길에서 5만원을 주웠습니다.");
			goodList.add("팀원과 함께 산 로또에 상사만 당첨! 10만원을 획득하였습니다.");
			goodList.add("상사가 미팅에 성공해 기분이 좋습니다.");
			goodList.add("건물 전기공사로 정전이 되었으나 파일을 날리지 않았습니다.");
			goodList.add("귀여운 강아지와 산책으로 힐링!");
			goodList.add("인센티브를 받아 팀원들과 함께 단합의 시간을 보냈습니다!");
			goodList.add("출근길에 귀여운 고양이를 만나서 기분이 좋습니다!");

			ArrayList<String> badList = new ArrayList<>();
			badList.add("화창한 아침이지만, 상사의 기분이 좋지 않습니다.");
			badList.add("갑자기 내린 소나기로 인해 전기가 나가 작업하던 파일을 날렸습니다.");
			badList.add("상사가 교통사고로 차가 고장이 나서 현재 기분이 나쁩니다.");
			badList.add("지하철 공사로 길이 막혀 지각을 한 상사가 기분이 좋지 않습니다.");
			badList.add("지진으로 인한 피해로 주식이 떨어진 상사가 기분이 안 좋습니다.");
			badList.add("진행되던 프로젝트에 보고서가 누락되었습니다!");
			badList.add("팀원과의 불화로 사무실 분위기가 싸늘해졌습니다.");

			Random rand = new Random();
			boolean luck = rand.nextBoolean();

			for (int i = 0; i < 2; i++) {
				if (luck) {
					System.out.println(goodList.get(rand.nextInt(goodList.size())));
					// 퀴즈 DB에서 불러오기
					System.out.println("곰이 물구나무를 서면?");
					String answer = input.next();
					if (answer.equals("문")) {
						score += 10;
						System.out.println(score + "로 인사평가점수가 올랐습니다!");
						System.out.println();
					} else {
						score -= 5;
						if(score < 0)
							System.out.println("인사평가점수가 0점 이하입니다!");
						System.out.println(score + "로 인사평가점수가 떨어졌습니다..");
						System.out.println();
					}
				} else {
					System.out.println(badList.get(rand.nextInt(badList.size())));
					// 퀴즈 DB에서 불러오기
					System.out.println("할아버지가 때리면?");
					String answer = input.next();
					if (answer.equals("친할아버지")) {
						score += 5;
						System.out.println(score + "로 인사평가점수가 올랐습니다!");
						System.out.println();
					} else {
						score -= 10;
						System.out.println(score + "로 인사평가점수가 떨어졌습니다..");
						System.out.println();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
}
