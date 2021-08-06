package movie.service;

import java.util.ArrayList;
import java.util.HashMap;

import movie.model.MovieModel;
import movie.model.dto.Movie;
import movie.model.dto.Reservation;
import movie.model.dto.Theater;

import movie.exception.DuplicateException;
import movie.exception.NotExistException;
import movie.exception.overBookedException;

public class MovieService {
	
	private static MovieService instance = new MovieService();
	
	private static MovieModel movieModel = MovieModel.getInstance();
	
	private MovieService() {}
	
	public static MovieService getInstance() {
		return instance;
	}
	
	
	
	/**
	 * 모든 Movie 검색
	 */
	public static ArrayList<Movie> getMovieList() {
		return MovieModel.getMovieList();
	}
	
	/**
	 * 모든 Theater 검색
	 */
	public static ArrayList<Theater> getTheaterList() {
		return MovieModel.getTheaterList();
	}

	/**
	 * 모든 Reservation 검색
	 */
	public static ArrayList<Reservation> getReservationList() {
		return MovieModel.getReservationList();
	}
	
	/**
	 * 매핑된 <상영관, 영화> 검색
	 */
	public static HashMap<Theater, Movie> getMovieInfo() {
		return MovieModel.movieAll();
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * 영화 제목으로 특정 Movie 검색 - movie 반환
	 * 
	 * @param movieTitle
	 * @return Movie 검색된 영화, 검색하고자 하는 영화가 없을 경우 null 반환
	 */
	public Movie getMovie(String movieTitle) {
		ArrayList<Movie> movieList = getMovieList();
		
		for(Movie movie : movieList) {
			if (movie.getMovieTitle().equals(movieTitle)) {
				return movie;
			}
		}
		return null;
	}
	
	/**
	 * 상영관 이름으로 특정 Theater 검색 - theater 반환
	 * 
	 * @param theaterName
	 * @return Theater 검색된 상영관, 검색하고자 하는 상영관이 없을 경우 null 반환
	 */
	public Theater getTheater(String theaterNo) {
		ArrayList<Theater> theaterList = getTheaterList();
		
		for(Theater theater : theaterList) {
			if (theater.getTheaterNo().equals(theaterNo)) {
				return theater;
			}
		}
		return null;
	}
	
	/**
	 * 예약자 이름으로 특정 Reservation 검색 - reservation 반환
	 * 
	 * @param name
	 * @return Reservation 검색된 예약, 검색하고자 하는 예약 내역이 없을 경우 null 반환
	 */
	public static Reservation getReservation(String name) {
		ArrayList<Reservation> reservationList = getReservationList();
		
		for(Reservation reservation : reservationList) {
			if (reservation.getName().equals(name)) {
				return reservation;
			} 
		}
		return null;
	}
	
	/**
	 * 예약 번호로 특정 Reservation 검색 - reservation 반환
	 * >> 아래 새로운 Reservation 추가 시 중복 예외 발생을 처리하기 위해 생성
	 * 
	 * @param reservationNo
	 * @return Reservation 검색된 예약, 검색하고자 하는 예약 내역이 없을 경우 null 반환
	 */
	public static Reservation getReservationNo(int reservationNo) {
		ArrayList<Reservation> reservationList = getReservationList();
		
		for(Reservation reservation : reservationList) {
			if (reservation.getReservationNo() == reservationNo) {
				return reservation;
			} 
		}
		return null;
	}
		
//	------------------------------------------------------------------------------------------
	
	/**
	 * 새로운 Reservation 추가 
	 * 1. newReservation에 할당된 상영관 번호와 영화 제목이 매핑되어 있지 않을 경우, NotExistException 예외 발생
	 * 2. newReservation에 할당된 예약번호가 중복될 경우, DuplicateException 예외 발생
	 * 3. newReservation에 할당된 상영관에 잔여 좌석이 없을 경우, overBookedException 예외 발생
	 * 
	 * 4. 예약 성공 시 해당 Reservation 상영관의 예약 좌석 수+1;
	 * 
	 * @param newReservation      저장하고자 하는 새로운 예약
	 * @throws DuplicateException
	 * @throws NotExistException 
	 * @throws overBookedException 
	 */
	public void reservationInsert(Reservation newReservation) throws DuplicateException, NotExistException, overBookedException {
		Theater newTheater = getTheater(newReservation.getTheaterNo());	
		String mappedMovieTitle = getMovieInfo().get(newTheater).getMovieTitle();
		
		Reservation reservation = getReservationNo(newReservation.getReservationNo());
		Theater theater = getTheater(newReservation.getTheaterNo());
		
		if(!mappedMovieTitle.equals(newReservation.getMovieTitle())) {
			throw new NotExistException("입력하신 영화는 해당 상영관에서 상영하지 않습니다.");
		}
		if (reservation != null) {
			throw new DuplicateException("해당 예약 번호와 동일한 번호의 예약 내역이 존재합니다.");
		}
		if(theater.getRemainSeats() == 0) {
			throw new overBookedException("해당 상영관에 잔여 좌석이 없습니다.");
		}
		movieModel.insertReservation(newReservation);
		theater.setBookedSeats(theater.getBookedSeats()+1);
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * Reservation의 영화 <상영관 번호, 영화 제목> 수정 - 예약자 이름으로 검색해서 해당 예약의 <상영관 번호, 영화 제목> 수정
	 * 1. 수정하고자 하는 예약이 존재하지 않을 경우, NotExistException 발생
	 * 2. 상영관 번호, 영화 제목이 movieInfo에 매핑되어 있지 않을 경우,(=해당 상영관에서 입력한 영화를 상영하지 않을 경우) NotExistException 발생
	 * 3. 해당 상영관에 잔여 좌석이 없을 경우, overBookedException 예외 발생
	 * 
	 * 4. 수정 성공 시
	 * 		- 이전 상영관의 예약 좌석 수-1;
	 * 		- 새로운 Reservation 상영관의 예약 좌석 수+1;
	 * 
	 * @param name 예약자 이름
	 * @param theaterNo 상영관 번호
	 * @param movieTitle 영화 제목
	 * @throws NotExistException
	 * @throws overBookedException 
	 */
	public void reservationUpdate(String name, String movieTitle, String theaterNo) throws NotExistException, overBookedException {
		Theater newTheater = getTheater(theaterNo);	
		String mappedMovieTitle = getMovieInfo().get(newTheater).getMovieTitle();
		
		Reservation reservation = getReservation(name);
		
		if(reservation == null){
			throw new NotExistException("수정하고자 하는 예약 내역이 존재하지 않는 에러가 발생했습니다.");
		}
		if(!mappedMovieTitle.equals(movieTitle)) {
			throw new NotExistException("입력하신 영화는 해당 상영관에서 상영하지 않습니다.");
		}
		if(newTheater.getRemainSeats() == 0) {
			throw new overBookedException("해당 상영관에 잔여 좌석이 없습니다.");
		}
		
		// 이전 상영관의 예약 좌석 수-1;
		String oldTheaterNo = reservation.getTheaterNo();
		for(Theater theater : getTheaterList()) {
			if(theater.getTheaterNo().equals(oldTheaterNo)) {
				theater.setBookedSeats(theater.getBookedSeats()-1);
			}
		}
		
		// 새로운 상영관의 예약 좌석 수+1;
		reservation.setMovieTitle(movieTitle);
		reservation.setTheaterNo(theaterNo);
		newTheater.setBookedSeats(newTheater.getBookedSeats()+1);
	}

//	------------------------------------------------------------------------------------------
	
	/**
	 * Reservation 삭제 - 예약자 이름으로 해당 예약 삭제
	 * 
	 * 삭제 성공 시 해당 Reservation 상영관의 예약 좌석 수--;
	 * 
	 * @param name 예약자 이름
	 * @throws NotExistException
	 */
	public void reservationDelete(String name) throws NotExistException{
		Reservation reservation = getReservation(name);
		Theater theater = getTheater(reservation.getTheaterNo());
		
		if(reservation == null | theater == null) {
			throw new NotExistException("삭제하고자 하는 예약 내역이 존재하지 않는 에러가 발생했습니다.");
		}
		getReservationList().remove(reservation);
		theater.setBookedSeats(theater.getBookedSeats()+1);
	}
	
}