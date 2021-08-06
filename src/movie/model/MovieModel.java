package movie.model;

import java.util.ArrayList;
import java.util.HashMap;

import movie.model.dto.Movie;
import movie.model.dto.Reservation;
import movie.model.dto.Theater;


public class MovieModel {
	private static MovieModel instance = new MovieModel();
	
	private static ArrayList<Movie> movieList = new ArrayList<Movie>();
	private static ArrayList<Theater> theaterList = new ArrayList<Theater>();
	private static ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	
	private static HashMap<Theater, Movie>movieInfo = new HashMap<>();
	
	static{
		
		// 영화제목, 감독, 주인공, 장르, 개봉일, 관람등급, 러닝타임
		movieList.add(new Movie("모가디슈", "류승완", "김윤석", "액션,드라마", "2021-07-28", "15세 관람가" , 121));
		movieList.add(new Movie("보스 베이비2" , "톰 맥그라스", "알렉 볼드윈", "애니메이션,코미디,모험",  "2021-07-21", "전체 관람가" , 107));
		movieList.add(new Movie("블랙 위도우","케이트 쇼트랜드", "스칼릿 조핸슨", "액션, 모험, SF", "2021-07-07",  "12세 관람가" , 134));
		movieList.add(new Movie("랑종", "반종 피산다나쿤", "나릴야 군몽콘켓", "공포, 스릴러, 드라마", "2021-07-14", "청소년 관람불가", 131));
		movieList.add(new Movie("정글 크루즈", "자움 콜렛 세라", "드웨인 존슨", "액션, 모험", "2021-07-28", "12세 관람가", 127));
		movieList.add(new Movie("우리, 둘", "필리포 메네게티", "바바라 수코바", "드라마,멜로/로맨스", "2021-07-28", "12세 관람가", 95));
		movieList.add(new Movie("아이스 로드", "조나단 헨슬레이", "리암 니슨", "스릴러", "2021-07-21", "12세 관람가", 110));
		movieList.add(new Movie("꽃다발 같은 사랑을 했다", "도이 노부히로", "아리무라 카스미", "멜로/로맨스", "2021-07-14", "12세 관람가", 123));
		movieList.add(new Movie("피닉스",  "크리스티안 펫졸트", "니나 호스", "드라마/로맨스/멜로", "2021-07-22", "12세 관람가", 98));
		movieList.add(new Movie("방법:재차의","김용완", "엄지완", "미스터리,스릴러",  "2021-07-28", "15세 관람가" , 109));
		
		//상영관 번호, 총 좌석 수, 예약 좌석 수 		
		theaterList.add(new Theater("1관" , 1, 1));
		theaterList.add(new Theater("2관" , 2, 1));
		theaterList.add(new Theater("3관" , 10, 1));
		theaterList.add(new Theater("4관" , 20, 1));
		theaterList.add(new Theater("5관" , 30, 1));
		theaterList.add(new Theater("6관" , 40, 1));
		theaterList.add(new Theater("7관" , 50, 1));
		theaterList.add(new Theater("8관" , 60, 1));
		theaterList.add(new Theater("9관" , 70, 1));
		theaterList.add(new Theater("10관" , 100, 1));
		
		// 예약자 이름, 영화 제목, 상영관 번호
		reservationList.add(new Reservation("홍길동", "모가디슈", "1관"));
		reservationList.add(new Reservation("엔코아", "보스 베이비2", "2관"));
		reservationList.add(new Reservation("플레이", "블랙 위도우", "3관"));
		reservationList.add(new Reservation("행인", "랑종", "4관"));
		reservationList.add(new Reservation("눈사람", "정글 크루즈", "5관"));
		reservationList.add(new Reservation("김사랑", "우리, 둘", "6관"));
		reservationList.add(new Reservation("김하나", "아이스 로드", "7관"));
		reservationList.add(new Reservation("김앤나", "꽃다발 같은 사랑을 했다", "8관"));
		reservationList.add(new Reservation("제빵왕", "피닉스", "9관"));
		reservationList.add(new Reservation("김탁구", "방법:재차의", "10관"));
		
		// 상영관별 상영 영화 mapping
		movieInfo.put(theaterList.get(0), movieList.get(0));
		movieInfo.put(theaterList.get(1), movieList.get(1));
		movieInfo.put(theaterList.get(2), movieList.get(2));
		movieInfo.put(theaterList.get(3), movieList.get(3));
		movieInfo.put(theaterList.get(4), movieList.get(4));
		movieInfo.put(theaterList.get(5), movieList.get(5));
		movieInfo.put(theaterList.get(6), movieList.get(6));
		movieInfo.put(theaterList.get(7), movieList.get(7));
		movieInfo.put(theaterList.get(8), movieList.get(8));
		movieInfo.put(theaterList.get(9), movieList.get(9));

	}
	
	public static MovieModel getInstance() {
		return instance;
	}
	
	public void insertReservation(Reservation newReservation) {
		reservationList.add(newReservation);
	}

	public static HashMap<Theater, Movie> movieAll() {
		return movieInfo;
	}
	
	// movieList, theaterList, reservationList getter
	// movieList, theaterList는 수정하지 않기 때문에, setter는 reservatioList만 생성
	public static ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public static ArrayList<Theater> getTheaterList() {
		return theaterList;
	}

	public static ArrayList<Reservation> getReservationList() {
		return reservationList;
	}

	public static void setRevervationList(ArrayList<Reservation> revervationList) {
		MovieModel.reservationList = revervationList;
	}

}
