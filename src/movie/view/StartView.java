package movie.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import movie.controller.MovieController;
import movie.model.dto.Reservation;

public class StartView {
	
	static Logger logger = Logger.getLogger("movie.view.Log4j");
	
	public static void reservationCheck(String crud) {
		System.out.println("\n---------- 로그 실행 ----------");
		if(crud == "추가") { //예약자 추가
			logger.info("info - 예약자 추가!");
		} else if (crud == "수정") {
			logger.info("info - 예약자 수정!");
		} else if (crud == "삭제") {
			logger.info("info - 예약자 삭제!");
		} else {
			logger.info("info - 데이터 조회");
		}
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MovieController controller = MovieController.getInstance();
		
		try {
			System.out.println("-------- 실행하고 싶은 메뉴를 선택해주세요. --------");
			System.out.println("1) 검색 \t 2) 추가 \t 3) 수정 \t 4) 삭제 \t 5) 한 번에 다 보기");
			int input = Integer.parseInt(br.readLine());
			
			// 검색
			if(input == 1) {
				System.out.println("---------------- 1. 데이터 검색 ----------------");
				System.out.println("1) 전체 리스트 검색 \t 2) 단일 항목 검색 \t 3) 매핑된 <Theater, Movie> 검색");
				int subInput = Integer.parseInt(br.readLine());

				// 전체 리스트 검색
				if(subInput == 1) {
					System.out.println("\n*** 01-1. 모든 Movie 검색 ***");
					controller.getMovieList();	

					System.out.println("\n*** 01-2. 모든 Theater 검색 ***");
					controller.getTheaterList();	

					System.out.println("\n*** 01-3. 모든 Reservation 검색 ***");
					controller.getReservationList();
				}

				// 단일 항목 검색
				else if(subInput == 2) {

					System.out.println("\n*** 01-1. 영화 제목으로 Movie 정보 검색 ***");

					System.out.println("영화 제목을 입력해주세요.");
					String movieTitle = br.readLine();
					controller.getMovie(movieTitle);

					System.out.println("\n*** 01-2. 상영관 번호로 Theater 정보 검색 ***");

					System.out.println("상영관 이름을 입력해주세요.");
					String theaterName = br.readLine();
					controller.getTheater(theaterName);

					System.out.println("\n*** 01-3. 예약자 이름으로 Reservation 정보 검색 ***");

					System.out.println("예약자 이름을 입력해주세요.");
					String name = br.readLine();
					controller.getReservation(name);
				}
				
				// 매핑된 <Theater, Movie> 검색
				else if(subInput == 3) {
					
					System.out.println("\n*** 01. 매핑된 <Theater, Movie> 검색 ***");
					controller.getMappedData();
				}
			}		
		
//			--------------------------------------------------------------------------
			
			// 추가
			else if(input == 2) {
				System.out.println("---------------- 2. 데이터 추가 ----------------");
				
				// 새로운 예약 정보 추가
				System.out.println("\n*** 02. 예약자 정보 추가 ***");
				System.out.println("<< 추가 전 모든 예약자 검색 >>");
				controller.getReservationList();
				
				// 자동으로 예약 번호 중복되지 않게 생성하여 인스턴스 생성
				Reservation newReservation = new Reservation("김혜경", "보스 베이비2", "2관");
				
				controller.insertReservation(newReservation);
				reservationCheck("추가");
				
				System.out.println("\n<< 추가 후 모든 예약자 검색 >>");
				controller.getReservationList();
				
				System.out.println("\n02-1. 해당 상영관에서 상영하지 않는 영화 예약 추가");
				// 수동으로 매핑되어 있지 않은 영화 할당하여 인스턴스 생성
				Reservation newReservation1 = new Reservation("배지수", "랑종", "2관");
				controller.insertReservation(newReservation1);
				
				System.out.println("\n02-2. 예약 번호가 중복되는 예약 추가");
				// 수동으로 중복되는 예약 번호 할당하여 인스턴스 생성
				Reservation newReservation2 = new Reservation(1, "김혜경", "보스 베이비2", "2관");
				controller.insertReservation(newReservation2);
				
				System.out.println("\n02-3. 잔여 좌석이 없는 상영관 예약 추가");
				// 수동으로 잔여 좌석이 없는 상영관 할당하여 인스턴스 생성
				// 2관 보스 보스베이비2는 잔여 좌석 없음
				Reservation newReservation3 = new Reservation("배지수", "모가디슈", "1관");
				controller.insertReservation(newReservation3);
				
				System.out.println("\n<< 추가 후 모든 예약자 검색 >>");
				controller.getReservationList();
				
				//예약자 정보를 입력하여 추가 예약
				System.out.println("\n02-4. 키보드로 예약");
				System.out.println("양식에 따라서 작성해 주세요");
				System.out.println("예약자명:영화명:상영관번호(_관)");
				Reservation newrev = controller.startReservation();
				controller.insertReservation(newrev);
				controller.getReservationList();
				reservationCheck("추가");
				
				System.out.println("\n<< 추가 후 모든 예약자 검색 >>");
				controller.getReservationList();
			}
			
//			--------------------------------------------------------------------------
			
			else if(input == 3) {
				System.out.println("---------------- 3. 데이터 수정 ----------------");
				
				// 예약자 정보 이름으로 검색 후 수정 / 수정 전 검색 / 수정 후 검색
				System.out.println("\n*** 03. 예약자 정보 이름으로 검색 및 수정, 수정 후 예약자 검색 ***");
				System.out.println("<< 수정 전 예약자 검색 >>");
				controller.getReservation("플레이");
				
				controller.updateReservation("플레이", "정글 크루즈", "5관");
				reservationCheck("수정");
				
				System.out.println("\n<< 수정 후 예약자 검색 >>");
				controller.getReservation("플레이");
				
				System.out.println("\n03-1. 매핑되어 있지 않은 상영관, 영화 제목으로 수정");
				controller.updateReservation("플레이", "정글 크루즈", "7관");
				
				System.out.println("\n<< 수정 후 예약자 검색 >>");
				controller.getReservation("플레이");
			}
			
//			--------------------------------------------------------------------------
			
			else if(input == 4) {
				System.out.println("---------------- 4. 데이터 삭제 ----------------");
				
				// 예약자 정보 이름으로 검색 후 삭제 / 삭제 전 검색 / 삭제 후 검색
				System.out.println("\n*** 04. 홍길동 예약자 삭제 후 삭제한 예약자 검색 ***");
				System.out.println("\n<< 삭제 전 예약자 검색 >>");
				controller.getReservation("홍길동");
			
				controller.deleteReservation("홍길동");
				reservationCheck("삭제");
				
				System.out.println("\n<< 삭제 후 예약자 검색 >>");
				controller.getReservation("홍길동");
			}
			
//			--------------------------------------------------------------------------
			
			else if(input == 5) {
				System.out.println("---------------- 한 번에 다 보기 ----------------");
				
				// 전체 검색
				System.out.println("\n*** 01-1. 모든 Movie 검색 ***");
				controller.getMovieList();	

				System.out.println("\n*** 01-2. 모든 Theater 검색 ***");
				controller.getTheaterList();	

				System.out.println("\n*** 01-3. 모든 Reservation 검색 ***");
				controller.getReservationList();
				
				// 단일 항목 검색
				System.out.println("\n*** 01-4. 영화 제목으로 Movie 정보 검색 ***");
				controller.getMovie("블랙 위도우");

				System.out.println("\n*** 01-5. 상영관 이름으로 Theater 정보 검색 ***");
				controller.getTheater("10관");

				System.out.println("\n*** 01-6. 예약자 이름으로 Reservation 정보 검색 ***");
				controller.getReservation("엔코아");
				
				System.out.println("\n*** 01-7. 매핑된 <Theater, Movie> 검색 ***");
				controller.getMappedData();
				
				// -----------------------------------------------------------------------------
				
				// 새로운 예약 정보 추가
				System.out.println("\n*** 02. 예약자 정보 추가 ***");
				System.out.println("<< 추가 전 모든 예약자 검색 >>");
				controller.getReservationList();
				
				// 자동으로 예약 번호 중복되지 않게 생성하여 인스턴스 생성
				Reservation newReservation = new Reservation("김혜경", "보스 베이비2", "2관");
				
				controller.insertReservation(newReservation);
				reservationCheck("추가");
				
				System.out.println("\n<< 추가 후 모든 예약자 검색 >>");
				controller.getReservationList();
				
				System.out.println("\n02-1. 해당 상영관에서 상영하지 않는 영화 예약 추가");
				// 수동으로 매핑되어 있지 않은 영화 할당하여 인스턴스 생성
				Reservation newReservation1 = new Reservation("배지수", "랑종", "2관");
				controller.insertReservation(newReservation1);
				
				System.out.println("\n02-2. 예약 번호가 중복되는 예약 추가");
				// 수동으로 중복되는 예약 번호 할당하여 인스턴스 생성
				Reservation newReservation2 = new Reservation(1, "김혜경", "보스 베이비2", "2관");
				controller.insertReservation(newReservation2);
				
				System.out.println("\n02-3. 잔여 좌석이 없는 상영관 예약 추가");
				// 수동으로 잔여 좌석이 없는 상영관 할당하여 인스턴스 생성
				// 2관 보스 보스베이비2는 잔여 좌석 없음
				Reservation newReservation3 = new Reservation("배지수", "모가디슈", "1관");
				controller.insertReservation(newReservation3);
				
				System.out.println("\n<< 추가 후 모든 예약자 검색 >>");
				controller.getReservationList();
				
				//예약자 정보를 입력하여 추가 예약
				System.out.println("\n02-4. 키보드로 예약");
				System.out.println("양식에 따라서 작성해 주세요");
				System.out.println("예약자명:영화명:상영관번호(_관)");
				Reservation newrev = controller.startReservation();
				controller.insertReservation(newrev);
				controller.getReservationList();
				reservationCheck("추가");
				
				System.out.println("\n<< 추가 후 모든 예약자 검색 >>");
				controller.getReservationList();
				
				// -----------------------------------------------------------------------------
				
				// 예약자 정보 이름으로 검색 후 수정 / 수정 전 검색 / 수정 후 검색
				System.out.println("\n*** 03. 예약자 정보 이름으로 검색 및 수정, 수정 후 예약자 검색 ***");
				System.out.println("<< 수정 전 예약자 검색 >>");
				controller.getReservation("플레이");
				
				controller.updateReservation("플레이", "정글 크루즈", "5관");
				reservationCheck("수정");
				
				System.out.println("\n<< 수정 후 예약자 검색 >>");
				controller.getReservation("플레이");
				
				System.out.println("\n03-1. 매핑되어 있지 않은 상영관, 영화 제목으로 수정");
				controller.updateReservation("플레이", "정글 크루즈", "7관");
				
				System.out.println("\n<< 수정 후 예약자 검색 >>");
				controller.getReservation("플레이");

				// -----------------------------------------------------------------------------
				
				// 예약자 정보 이름으로 검색 후 삭제 / 삭제 전 검색 / 삭제 후 검색
				// 예약자 정보 이름으로 검색 후 삭제 / 삭제 전 검색 / 삭제 후 검색
				System.out.println("\n*** 04. 홍길동 예약자 삭제 후 삭제한 예약자 검색 ***");
				System.out.println("\n<< 삭제 전 예약자 검색 >>");
				controller.getReservation("홍길동");
			
				controller.deleteReservation("홍길동");
				reservationCheck("삭제");
				
				System.out.println("\n<< 삭제 후 예약자 검색 >>");
				controller.getReservation("홍길동");

			} 
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
