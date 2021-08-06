package movie.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import movie.model.dto.Movie;
import movie.model.dto.Reservation;
import movie.model.dto.Theater;

public class EndView {

	// 존재하는 모든 영화 데이터 출력
	public static void movieListView(ArrayList<Movie> allMovieData) {
		for (Movie movie : allMovieData) {
			System.out.println(movie);
		}
	}
	
	// 존재하는 모든 상영관 데이터 출력
	public static void theaterListView(ArrayList<Theater> allTheaterData) {
		for (Theater theater : allTheaterData) {
			System.out.println(theater);
		}
	}

	// 존재하는 모든 예약 데이터 출력
	public static void reservationListView(ArrayList<Reservation> allReservatioinData) {
		for (Reservation reservation : allReservatioinData) {
			System.out.println(reservation);
		}
	}

//	----------------------------------------------------------------------------------
	
	// 매핑된 <Theater, Movie> 데이터 출력
	public static void mappedDataView(HashMap<Theater, Movie> mappedData) {
		Iterator<Theater> keys = mappedData.keySet().iterator();
		Theater key = null;
		
		while(keys.hasNext()) {
			key = keys.next();
			System.out.println("<< " + key.getTheaterNo() +" >> " + key);
			System.out.println("<< " + key.getTheaterNo() + " 상영 영화>> " + mappedData.get(key) + "\n");
		}
	}
	
//	----------------------------------------------------------------------------------
	
	// 존재하는 영화 데이터 (단일)출력
	public static void movieView(Movie movie) {
		if (movie != null) {
			System.out.println(movie);
		} else {
			System.out.println("해당 영화 데이터가 존재하지 않습니다.");
		}
	}

	// 존재하는 상영관 데이터 (단일)출력
	public static void theatertView(Theater theater) {
		if (theater != null) {
			System.out.println(theater);
		} else {
			System.out.println("해당 상영관 데이터가 존재하지 않습니다.");
		}
	}

	// 존재하는 예약 데이터 (단일)출력
	public static void reservationView(Reservation reservation) {
		if (reservation != null) {
			System.out.println(reservation);
		} else {
			System.out.println("해당 예약 데이터가 존재하지 않습니다.");
		}
	}

//	----------------------------------------------------------------------------------
	
	// 예외가 아닌 단순 메세지 출력
	public static void messageView(String message) {
		System.out.println(message);
	}

	
}