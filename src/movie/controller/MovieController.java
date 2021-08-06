package movie.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import movie.exception.DuplicateException;
import movie.exception.NotExistException;
import movie.model.dto.Movie;
import movie.model.dto.Reservation;
import movie.model.dto.Theater;
import movie.service.MovieService;
import movie.view.EndView;
import movie.view.FailView;

public class MovieController {

	private static MovieController instance = new MovieController();
	private MovieService service = MovieService.getInstance();
	
	private MovieController() {}
	
	public static MovieController getInstance() {
		return instance;
	}
	
	
	/**
	 * 모든 Movie 검색
	 */	
	public void getMovieList() {
		ArrayList<Movie> movieList = MovieService.getMovieList();
		
		if(movieList.size() != 0){
			EndView.movieListView(movieList);
		}else {
			EndView.messageView("현재 영화 데이터가 존재하지 않습니다.");
		}
	}
	
	/**
	 * 모든 Theater 검색
	 */	
	public void getTheaterList() {
		ArrayList<Theater> theaterList = MovieService.getTheaterList();
		
		if(theaterList.size() != 0){
			EndView.theaterListView(theaterList);
		}else {
			EndView.messageView("현재 상영관 데이터가 존재하지 않습니다.");
		}
	}
	
	/**
	 * 모든 Reservation 검색
	 */	
	public void getReservationList() {
		ArrayList<Reservation> ReservationList = MovieService.getReservationList();
		
		if(ReservationList.size() != 0){
			EndView.reservationListView(ReservationList);
		}else {
			EndView.messageView("현재 예약 데이터가 존재하지 않습니다");
		}
	}
	
	/**
	 * 해쉬맵으로 매핑된 <Theater, Movie> 검색
	 */	
	public void getMappedData() {
		HashMap<Theater, Movie> mappedData = MovieService.getMovieInfo();
		
		if(!mappedData.isEmpty()) {
			EndView.mappedDataView(mappedData);
		}else {
			EndView.messageView("현재 매핑된 <Theater, Movie> 가 존재하지 않습니다.");
		}
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * 영화 제목으로 특정 Movie 검색
	 * 
	 * @param movieTitle
	 */
	public void getMovie(String movieTitle) {
		Movie movie = service.getMovie(movieTitle);
		if(movie != null) {
			EndView.movieView(movie);
		}else {
			EndView.messageView("검색하신 제목의 영화는 존재하지 않습니다.");
		}
	}
	
	/**
	 * 상영관 이름으로 특정 Theater 검색
	 * 
	 * @param theaterName
	 */
	public void getTheater(String theaterName) {
		Theater theater = service.getTheater(theaterName);
		if(theater != null) {
			EndView.theatertView(theater);
		}else {
			EndView.messageView("검색하신 이름의 상영관은 존재하지 않습니다.");
		}
	}
	
	/**
	 * 예약자 이름으로 특정 Reservation 검색
	 * 
	 * @param name
	 */
	public void getReservation(String name) {
			Reservation reservation = MovieService.getReservation(name);
			if(reservation != null) {
				EndView.reservationView(reservation);
			}else {
				EndView.messageView("검색하신 이름의 예약 내역이 존재하지 않습니다.");
		}
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * 새로운 Reservation 저장
	 * 
	 * @param newReservation
	 */
	public void insertReservation(Reservation newReservation) {
		if(newReservation != null) {
			try {
				service.reservationInsert(newReservation);
				EndView.messageView(newReservation.getName() + "님의 예약이 저장 성공되었습니다.");
			}catch(DuplicateException | NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}
		}else {
			EndView.messageView("새로 저장하고자 하는 예약 정보가 없습니다. 재확인 해 주세요");
		}
	}
	
	/** 
	 * 키보드로 정보를 입력해서 예약 정보 추가 
	 * 
	 */
	public Reservation startReservation() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(in.readLine(),":");;
			
			Reservation newReservation = new Reservation();
			newReservation.setName(st.nextToken());
			newReservation.setMovieTitle(st.nextToken());
			newReservation.setTheaterNo(st.nextToken());
			
			return newReservation;
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * 존재하는 Reservation의 영화 제목 수정 
	 * 예약자 이름으로 검색해서 해당하는 예약의 영화 제목 수정
	 * 
	 * @param name
	 * @param movieTitle
	 */
	public void updateReservation(String name, String theaterNo, String movieTitle) {
		if(movieTitle != null) {
			try {
				service.reservationUpdate(name, theaterNo, movieTitle);
			} catch (NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}	
		}else {
			EndView.messageView("수정하고자 하는 영화 정보가 무효합니다, 재 확인 해 주세요");
		}
	}

//	------------------------------------------------------------------------------------------
	
	/** 
	 * 존재하는 Reservation 삭제 
	 * 예약자 이름으로 검색해서 해당하는 예약 삭제
	 * 
	 * @param name
	 */
	public void deleteReservation(String name) {
		if(name != null) {
			try {
				service.reservationDelete(name);
				EndView.messageView(name + "님의 예약이 삭제되었습니다.");
			}catch(NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}	
		}else {
			EndView.messageView("삭제하고자 하는 예약의 예약자명이 무효합니다. 재 확인 해 주세요");
		}
	}
}