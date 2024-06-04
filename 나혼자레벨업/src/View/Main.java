package View;
// TODO Auto-generated method stub

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.MemberDAO;
import Controller.MusicController;
import Controller.PlayDataDAO;
import Model.MemberDTO;
import Model.Music;
import Model.PlayDataDTO;

public class Main {

	public static void main(String[] args) {
		PlayDataDAO pDao = new PlayDataDAO();
		ArrayList<String> text = new ArrayList<>();

		boolean login = false;
		String uid = null;

		Scanner sc = new Scanner(System.in);

		text = pDao.getTitle();
		for (int i = 0; i < text.size(); i++) {
			System.out.println(text.get(i));
		}

		System.out.println("*******  게임을 시작합니다  ********  ");

		// 메인메뉴
		while (true) {
			int score = 0;
			int dday = 20;
			MemberDAO dao = new MemberDAO();

			System.out.println("===== 메인메뉴 =====");
			System.out.println("[1] 회원가입 [2] 로그인 [3] 종료");

			int choice = sc.nextInt();
			sc.nextLine();

			if (choice == 1) {// **회원가입
				System.out.print("ID 입력 : ");
				String id = sc.nextLine();
				System.out.print("PW 입력 : ");
				String pw = sc.nextLine();
				System.out.print("성별 입력 : ");
				String gender = sc.nextLine();
				System.out.print("닉네임 입력 : ");
				String name = sc.nextLine();

				MemberDAO joinDAO = new MemberDAO();
				MemberDTO dto = new MemberDTO(id, pw);
				int row1 = dao.joinUser(dto);

				dto = new MemberDTO(id, gender, score, dday, name);
				int row2 = dao.joinIntern(dto);

				if (row1 + row2 > 1) {
					System.out.println("회원가입 성공");
				} else {
					System.out.println("회원가입 실패");
				}

			} else if (choice == 2) {// **로그인
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 : ");
				String pw = sc.next();

				MemberDTO dto = new MemberDTO(id, pw);
				uid = dao.login(dto);

				if (uid != null) {
					login = true;
					System.out.println("로그인 성공");
					System.out.println(uid + "님 환영합니다~~");
				} else {
					System.out.println("로그인실패");
				}

			} else if (choice == 3) {
				break;

			}

			// 육성메뉴
			while (login) {
				System.out.println("[1] 출근 [2] 회원정보 [3] 로그아웃");
				choice = sc.nextInt();
				sc.nextLine();

				if (choice == 1) {
					Random rand = new Random();
					boolean luck = rand.nextBoolean();
					boolean isWorker = true;
					int idx = 0;

					ArrayList<String> quiz = pDao.getQuiz();
					ArrayList<String> qAnswer = pDao.getqAnswer();

					MemberDTO dto = dao.select();

					score = dto.getScore();
					dday = dto.getDday();

					if (isWorker && dday < 0) {
						System.out.println("인턴사원은 드디어 정규직으로 승진하였습니다. 축하드립니다!");
						System.out.println("[1]새로하기 \t[2]이어하기");
						// 어디서 입력받아서 넘겨줄 것인가,,,
					}

					while (isWorker && dday >= 0) {
						System.out.println(pDao.getDayGraphic().get(dday--));

						for (int i = 0; i < 2; i++) {
							System.out.println((i == 0) ? "오전업무시간입니다." : "오후업무시간입니다.");
							if (luck) {
								System.out.println(pDao.getGoodList().get(rand.nextInt(pDao.getGoodList().size())));
								// 퀴즈 DB에서 불러오기

								MusicController con = new MusicController();

								System.out.println(" 업무 : 노래맞추기~~~");
								System.out.println("===== 재생 =====");
								Music m = con.play();

								System.out.println("정답을 입력하세요");
								String answer = sc.nextLine();

								if (answer.equals(m.getTitle())) {
									score += 10;
									System.out.println("+" + 10 + "점! 인사평가점수가 올랐습니다!\n");
									System.out.println();
									con.stop();

								} else {
									score -= 5;
									con.stop();
									if (score < 0) {
										System.out.println("인사평가점수가 0점 이하입니다!");
										System.out.println("안타깝게도 인턴은 해고되었습니다...\n");
										isWorker = false;
										break;
									} else {
										System.out.println("-" + 5 + "점.. 인사평가점수가 떨어졌습니다..\n");
									}
								}
							} else {
								System.out.println(pDao.getBadList().get(rand.nextInt(pDao.getBadList().size())));
								// 퀴즈 DB에서 불러오기
								idx = rand.nextInt(quiz.size());
								System.out.println(quiz.get(idx));
								String answer = sc.nextLine();

								if (answer.equals(qAnswer.get(idx))) {
									score += 5;
									System.out.println("+" + 5 + "점! 인사평가점수가 올랐습니다!\n");
									System.out.println();
								} else {
									score -= 10;
									if (score < 0) {
										System.out.println("인사평가점수가 0점 이하입니다!");
										System.out.println("안타깝게도 인턴은 해고되었습니다...\n");
										isWorker = false;
										break;
									} else {
										System.out.println("-" + 10 + "점.. 인사평가점수가 떨어졌습니다..\n");
									}
								}
							}
						}

						// String id, String gender,int score,int dday,String name
						// DB에서 인턴 정보 불러다가 출력
						System.out.println("===== 정산 =====");
						dao.update(new MemberDTO(score, dday));
						dto = dao.select();
						System.out.println("닉네임\t성별\t인사점수\t출근일수");

						System.out.print(dto.getName() + "\t");
						System.out.print(dto.getGender() + "\t");
						System.out.print(dto.getScore() + "\t");
						System.out.println(dto.getDday() + "\t");

						break;
					}

				} else if (choice == 2) {
					MemberDTO dto = dao.select();

					System.out.println("=== 인턴 정보 ===");
					System.out.println("닉네임\tID\t성별\t인사점수\t출근일수");

					System.out.print(dto.getName() + "\t");
					System.out.print(dto.getId() + "\t");
					System.out.print(dto.getGender() + "\t");
					System.out.print(dto.getScore() + "\t");
					System.out.print(dto.getDday() + "\t");
					System.out.println();

					System.out.println("[1] 육성메뉴 [2] 탈퇴");
					choice = sc.nextInt();
					sc.nextLine();
					if (choice == 2) {

						System.out.println("회원탈퇴를 위한 입력");
						System.out.print("ID 입력 : ");
						String id = sc.next();
						System.out.print("PW 입력 : ");
						String pw = sc.next();

						dao = new MemberDAO();
						dto = new MemberDTO(id, pw);

						int row = dao.delete(dto);
						if (row > 0) {
							System.out.println("회원탈퇴에 성공!");
						} else {
							System.out.println("회원탈퇴에 실패하였습니다..ㅠㅠ");
							System.out.println("아이디나 비밀번호를 다시 확인해주세요ㅠㅠ");
						}

						login = false;

					}

				} else if (choice == 3) {

					System.out.println("로그아웃");
					login = false;
				}
			} // login 시 while문

		} // while 젤 큰거

	}

}
