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
	 * ��� Movie �˻�
	 */	
	public void getMovieList() {
		ArrayList<Movie> movieList = MovieService.getMovieList();
		
		if(movieList.size() != 0){
			EndView.movieListView(movieList);
		}else {
			EndView.messageView("���� ��ȭ �����Ͱ� �������� �ʽ��ϴ�.");
		}
	}
	
	/**
	 * ��� Theater �˻�
	 */	
	public void getTheaterList() {
		ArrayList<Theater> theaterList = MovieService.getTheaterList();
		
		if(theaterList.size() != 0){
			EndView.theaterListView(theaterList);
		}else {
			EndView.messageView("���� �󿵰� �����Ͱ� �������� �ʽ��ϴ�.");
		}
	}
	
	/**
	 * ��� Reservation �˻�
	 */	
	public void getReservationList() {
		ArrayList<Reservation> ReservationList = MovieService.getReservationList();
		
		if(ReservationList.size() != 0){
			EndView.reservationListView(ReservationList);
		}else {
			EndView.messageView("���� ���� �����Ͱ� �������� �ʽ��ϴ�");
		}
	}
	
	/**
	 * �ؽ������� ���ε� <Theater, Movie> �˻�
	 */	
	public void getMappedData() {
		HashMap<Theater, Movie> mappedData = MovieService.getMovieInfo();
		
		if(!mappedData.isEmpty()) {
			EndView.mappedDataView(mappedData);
		}else {
			EndView.messageView("���� ���ε� <Theater, Movie> �� �������� �ʽ��ϴ�.");
		}
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * ��ȭ �������� Ư�� Movie �˻�
	 * 
	 * @param movieTitle
	 */
	public void getMovie(String movieTitle) {
		Movie movie = service.getMovie(movieTitle);
		if(movie != null) {
			EndView.movieView(movie);
		}else {
			EndView.messageView("�˻��Ͻ� ������ ��ȭ�� �������� �ʽ��ϴ�.");
		}
	}
	
	/**
	 * �󿵰� �̸����� Ư�� Theater �˻�
	 * 
	 * @param theaterName
	 */
	public void getTheater(String theaterName) {
		Theater theater = service.getTheater(theaterName);
		if(theater != null) {
			EndView.theatertView(theater);
		}else {
			EndView.messageView("�˻��Ͻ� �̸��� �󿵰��� �������� �ʽ��ϴ�.");
		}
	}
	
	/**
	 * ������ �̸����� Ư�� Reservation �˻�
	 * 
	 * @param name
	 */
	public void getReservation(String name) {
			Reservation reservation = MovieService.getReservation(name);
			if(reservation != null) {
				EndView.reservationView(reservation);
			}else {
				EndView.messageView("�˻��Ͻ� �̸��� ���� ������ �������� �ʽ��ϴ�.");
		}
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * ���ο� Reservation ����
	 * 
	 * @param newReservation
	 */
	public void insertReservation(Reservation newReservation) {
		if(newReservation != null) {
			try {
				service.reservationInsert(newReservation);
				EndView.messageView(newReservation.getName() + "���� ������ ���� �����Ǿ����ϴ�.");
			}catch(DuplicateException | NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}
		}else {
			EndView.messageView("���� �����ϰ��� �ϴ� ���� ������ �����ϴ�. ��Ȯ�� �� �ּ���");
		}
	}
	
	/** 
	 * Ű����� ������ �Է��ؼ� ���� ���� �߰� 
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
	 * �����ϴ� Reservation�� ��ȭ ���� ���� 
	 * ������ �̸����� �˻��ؼ� �ش��ϴ� ������ ��ȭ ���� ����
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
			EndView.messageView("�����ϰ��� �ϴ� ��ȭ ������ ��ȿ�մϴ�, �� Ȯ�� �� �ּ���");
		}
	}

//	------------------------------------------------------------------------------------------
	
	/** 
	 * �����ϴ� Reservation ���� 
	 * ������ �̸����� �˻��ؼ� �ش��ϴ� ���� ����
	 * 
	 * @param name
	 */
	public void deleteReservation(String name) {
		if(name != null) {
			try {
				service.reservationDelete(name);
				EndView.messageView(name + "���� ������ �����Ǿ����ϴ�.");
			}catch(NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}	
		}else {
			EndView.messageView("�����ϰ��� �ϴ� ������ �����ڸ��� ��ȿ�մϴ�. �� Ȯ�� �� �ּ���");
		}
	}
}