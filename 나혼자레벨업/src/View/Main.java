package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		PlayData data = new PlayData();
		ArrayList<String> dayGraphic = data.getDayGraphic();
		ArrayList<String> goodList = data.getGoodList();
		ArrayList<String> badList = data.getBadList();
		ArrayList<String> title = data.getTitle();
		int score = 0;
		int dday = 20;
		boolean login = false;
		String uid = null;

		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
			String user = "cgi_24IS_cloud3_p1_2";
			String password = "smhrd2";

			conn = DriverManager.getConnection(url, user, password);

			for (int i = 0; i < title.size(); i++) {
				System.out.println(title.get(i));
			}

			System.out.println("*******  게임을 시작합니다  ********  ");

			// 메인메뉴
			while (true) {
				System.out.println("메인메뉴");
				System.out.println("[1] 회원가입 [2] 로그인 [3] 종료");

				int choice = sc.nextInt();
				sc.nextLine(); // 버퍼비우기

				if (choice == 1) {// [1] 회원가입
					System.out.print("ID 입력 : ");
					String id = sc.next();
					System.out.print("PW 입력 : ");
					String pw = sc.next();
					System.out.print("성별 입력 : ");
					String gender = sc.next();
					System.out.print("닉네임 입력 : ");
					String name = sc.next();

					String sql = "INSERT  INTO TB_USER VALUES(?,?)";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, id);
					psmt.setString(2, pw);

					int row1 = psmt.executeUpdate();

					sql = "INSERT INTO TB_INTERN VALUES(?,?,?,?,?)";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, id);
					psmt.setString(2, gender);
					psmt.setInt(3, score);
					psmt.setInt(4, dday);
					psmt.setString(5, name);

					int row2 = psmt.executeUpdate();
					
					if (row1 > 0 && row2 > 0) {
						System.out.println("회원가입 성공");
						
					} else {
						System.out.println("회원가입 실패");
					}
				} else if (choice == 2) {// **로그인
					System.out.print("ID 입력 : ");
					String id = sc.next();
					System.out.print("PW 입력 : ");
					String pw = sc.next();

					String sql = "SELECT * FROM TB_USER WHERE USER_ID=? AND USER_PW=?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, id);
					psmt.setString(2, pw);

					rs = psmt.executeQuery();

					if (rs.next() == true) {
						System.out.println("로그인성공");
						uid = rs.getString("USER_ID");
						System.out.println(uid + " 님 환영합니다~~");
						System.out.println("[1] 출근 [2] 회원정보 [3] 로그아웃");
						login = true;

					} else {
						System.out.println("로그인 실패");
						System.out.println("아이디나 비밀번호를 다시 확인해보세요");

					}

				} else if(choice == 3) { // [3] 종료
					break;

				}

				// 육성메뉴
		 
					while (login) {
						choice = sc.nextInt();
						sc.nextLine();

						if (choice == 1) {
							Random rand = new Random();
							boolean luck = rand.nextBoolean();
							boolean isWorker = true;
							int idx = 0;

							ArrayList<String> quiz = data.getQuiz();
							ArrayList<String> qAnswer = data.getqAnswer();

							String sql = "SELECT IT_SCORE, IT_DDAY FROM TB_INTERN WHERE USER_ID = ?";
							psmt = conn.prepareStatement(sql);
							psmt.setString(1, uid);

							rs = psmt.executeQuery();
							rs.next();
							score = rs.getInt("IT_SCORE");
							dday = rs.getInt("IT_DDAY");

							if (isWorker && dday < 0) {
								System.out.println("인턴사원은 드디어 정규직으로 승진하였습니다. 축하드립니다!");
								break;
								// 어디서 입력받아서 넘겨줄 것인가,,,
							}

							while (isWorker && dday >= 0) {
								System.out.println(dayGraphic.get(dday--));

								for (int i = 0; i < 2; i++) {
									System.out.println((i == 0) ? "오전업무시간입니다." : "오후업무시간입니다.");
									if (luck) {
										System.out.println(goodList.get(rand.nextInt(goodList.size())));
										
										// 퀴즈 DB에서 불러오기
										idx = rand.nextInt(quiz.size());
										System.out.println(quiz.get(idx));
										String answer = sc.nextLine();

										if (answer.equals(qAnswer.get(idx))) {
											score += 10;
											System.out.println("+" + 10 + "점! 인사평가점수가 올랐습니다!\n");
											System.out.println();
										} else {
											score -= 5;
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
										System.out.println(badList.get(rand.nextInt(badList.size())));
										
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

								// DB에서 인턴 정보 불러다가 출력
								System.out.println("===== 정산 =====");
								sql = "UPDATE TB_INTERN SET IT_SCORE=?, IT_DDAY = ? WHERE USER_ID=?";

								psmt = conn.prepareStatement(sql);
								psmt.setInt(1, score);
								psmt.setInt(2, dday);
								psmt.setString(3, uid);
								psmt.executeUpdate();

								sql = "SELECT IT_NAME,USER_ID,IT_GENDER,IT_SCORE,IT_DDAY FROM TB_INTERN WHERE USER_ID = ?";
								psmt = conn.prepareStatement(sql);
								psmt.setString(1, uid);
								rs = psmt.executeQuery();
								rs.next();
								System.out.println("닉네임\t성별\t인사점수\t출근일수");

								System.out.print(rs.getString("IT_NAME") + "\t");
								System.out.print(rs.getString("IT_GENDER") + "\t");
								System.out.print(rs.getString("IT_SCORE") + "\t");
								System.out.println(rs.getString("IT_DDAY") + "\t");
								break;

							}
							
						} else if (choice == 2) {
							System.out.println(uid);

							System.out.println("=== 인턴 정보 ===");
							String sql = "SELECT IT_NAME,USER_ID,IT_GENDER,IT_SCORE,IT_DDAY FROM TB_INTERN WHERE USER_ID = ?";
							psmt = conn.prepareStatement(sql);
							psmt.setString(1, uid);
							rs = psmt.executeQuery();
							rs.next();
							System.out.println("닉네임\t ID\t성별\t인사점수\t출근일수");

							System.out.print(rs.getString("IT_NAME") + "\t");
							System.out.print(rs.getString("USER_ID") + "\t");
							System.out.print(rs.getString("IT_GENDER") + "\t");
							System.out.print(rs.getString("IT_SCORE") + "\t");
							System.out.println(rs.getString("IT_DDAY") + "\t");
							
							System.out.println("[1] 육성메뉴 [2] 탈퇴");
							choice = sc.nextInt();
							sc.nextLine();
							
							if(choice == 2) {
								System.out.println("회원탈퇴를 위한 입력");
								System.out.print("ID 입력 : ");
								String id = sc.next();
								System.out.print("PW 입력 : ");
								String pw = sc.next();

								sql = "DELETE FROM TB_INTERN WHERE USER_ID = ?";
								psmt = conn.prepareStatement(sql);
								psmt.setString(1, id);
								
								int row1 = psmt.executeUpdate();

								sql = "DELETE FROM TB_USER WHERE USER_ID =? AND USER_PW =?";
								psmt = conn.prepareStatement(sql);
								psmt.setString(1, id);
								psmt.setString(2, pw);

								int row2 = psmt.executeUpdate();

								if (row1 > 0 && row2 > 0) {
									login = false;
									System.out.println("회원탈퇴에 성공!");
								} else {
									System.out.println("회원탈퇴에 실패하였습니다..ㅠㅠ");
									System.out.println("아이디나 비밀번호를 다시 확인해주세요ㅠㅠ");
								}
							}
							if(!login) {
								break;
							}
							// 여기는 회원탈퇴가 아니라 로그아웃이에윰
						} else if (choice == 3) {
							System.out.println("로그아웃 되었습니다.");
							login = false;
						}
					}
				} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
