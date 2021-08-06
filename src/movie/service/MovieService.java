package movie.service;

import java.util.ArrayList;
import java.util.HashMap;

import movie.model.MovieModel;
import movie.model.dto.Movie;
import movie.model.dto.Reservation;
import movie.model.dto.Theater;

import movie.exception.DuplicateException;
import movie.exception.NotExistException;

public class MovieService {
	
	private static MovieService instance = new MovieService();
	
	private static MovieModel movieModel = MovieModel.getInstance();
	
	private MovieService() {}
	
	public static MovieService getInstance() {
		return instance;
	}
	
	
	
	/**
	 * ��� Movie �˻�
	 */
	public static ArrayList<Movie> getMovieList() {
		return MovieModel.getMovieList();
	}
	
	/**
	 * ��� Theater �˻�
	 */
	public static ArrayList<Theater> getTheaterList() {
		return MovieModel.getTheaterList();
	}

	/**
	 * ��� Reservation �˻�
	 */
	public static ArrayList<Reservation> getReservationList() {
		return MovieModel.getReservationList();
	}
	
	/**
	 * ���ε� <�󿵰�, ��ȭ> �˻�
	 */
	public static HashMap<Theater, Movie> getMovieInfo() {
		return MovieModel.movieAll();
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * ��ȭ �������� Ư�� Movie �˻� - movie ��ȯ
	 * 
	 * @param movieTitle
	 * @return Movie �˻��� ��ȭ, �˻��ϰ��� �ϴ� ��ȭ�� ���� ��� null ��ȯ
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
	 * �󿵰� �̸����� Ư�� Theater �˻� - theater ��ȯ
	 * 
	 * @param theaterName
	 * @return Theater �˻��� �󿵰�, �˻��ϰ��� �ϴ� �󿵰��� ���� ��� null ��ȯ
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
	 * ������ �̸����� Ư�� Reservation �˻� - reservation ��ȯ
	 * 
	 * @param name
	 * @return Reservation �˻��� ����, �˻��ϰ��� �ϴ� ���� ������ ���� ��� null ��ȯ
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
	 * ���� ��ȣ�� Ư�� Reservation �˻� - reservation ��ȯ
	 * >> �Ʒ� ���ο� Reservation �߰� �� �ߺ� ���� �߻��� ó���ϱ� ���� ����
	 * 
	 * @param reservationNo
	 * @return Reservation �˻��� ����, �˻��ϰ��� �ϴ� ���� ������ ���� ��� null ��ȯ
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
	 * ���ο� Reservation �߰� 
	 * 1. newReservation�� �Ҵ�� �󿵰� ��ȣ�� ��ȭ ������ ���εǾ� ���� ���� ���, NotExistException ���� �߻�
	 * 2. newReservation�� �Ҵ�� �����ȣ�� �ߺ��� ���, DuplicateException ���� �߻�
	 * 3. newReservation�� �Ҵ�� �󿵰��� �ܿ� �¼��� ���� ���, NotExistException ���� �߻�
	 * 
	 * 4. ���� ���� �� �ش� Reservation �󿵰��� ���� �¼� ��+1;
	 * 
	 * @param newReservation      �����ϰ��� �ϴ� ���ο� ����
	 * @throws DuplicateException
	 * @throws NotExistException 
	 */
	public void reservationInsert(Reservation newReservation) throws DuplicateException, NotExistException {
		Theater newTheater = getTheater(newReservation.getTheaterNo());	
		String mappedMovieTitle = getMovieInfo().get(newTheater).getMovieTitle();
		
		Reservation reservation = getReservationNo(newReservation.getReservationNo());
		Theater theater = getTheater(newReservation.getTheaterNo());
		
		if(!mappedMovieTitle.equals(newReservation.getMovieTitle())) {
			throw new NotExistException("�Է��Ͻ� ��ȭ�� �ش� �󿵰����� ������ �ʽ��ϴ�.");
		}
		else if (reservation != null) {
			throw new DuplicateException("�ش� ���� ��ȣ�� ������ ��ȣ�� ���� ������ �����մϴ�.");
		}
		else if(theater.getRemainSeats() == 0) {
			throw new NotExistException("�ش� �󿵰��� �ܿ� �¼��� �����ϴ�.");
		}
		movieModel.insertReservation(newReservation);
		theater.setBookedSeats(theater.getBookedSeats()+1);
	}
	
//	------------------------------------------------------------------------------------------
	
	/**
	 * Reservation�� ��ȭ <�󿵰� ��ȣ, ��ȭ ����> ���� - ������ �̸����� �˻��ؼ� �ش� ������ <�󿵰� ��ȣ, ��ȭ ����> ����
	 * 1. �󿵰� ��ȣ, ��ȭ ������ movieInfo�� ���εǾ� ���� ���� ���(=�ش� �󿵰����� �Է��� ��ȭ�� ������ ���� ���) NotExistException �߻�
	 * 2. �����ϰ��� �ϴ� ������ �������� ���� ��� NotExistException �߻�
	 * 
	 * 3. ���� ���� ��
	 * 		- ���� �󿵰��� ���� �¼� ��-1;
	 * 		- ���ο� Reservation �󿵰��� ���� �¼� ��+1;
	 * 
	 * @param name ������ �̸�
	 * @param theaterNo �󿵰� ��ȣ
	 * @param movieTitle ��ȭ ����
	 * @throws NotExistException
	 */
	public void reservationUpdate(String name, String movieTitle, String theaterNo) throws NotExistException {
		Theater newTheater = getTheater(theaterNo);	
		String mappedMovieTitle = getMovieInfo().get(newTheater).getMovieTitle();
		
		Reservation reservation = getReservation(name);
		
		if(!mappedMovieTitle.equals(movieTitle)) {
			throw new NotExistException("�Է��Ͻ� ��ȭ�� �ش� �󿵰����� ������ �ʽ��ϴ�.");
		}
		if(reservation == null){
			throw new NotExistException("�����ϰ��� �ϴ� ���� ������ �������� �ʴ� ������ �߻��߽��ϴ�.");
		}
		
		// ���� �󿵰��� ���� �¼� ��-1;
		String oldTheaterNo = reservation.getTheaterNo();
		for(Theater theater : getTheaterList()) {
			if(theater.getTheaterNo().equals(oldTheaterNo)) {
				theater.setBookedSeats(theater.getBookedSeats()-1);
			}
		}
		
		// ���ο� �󿵰��� ���� �¼� ��+1;
		reservation.setMovieTitle(movieTitle);
		reservation.setTheaterNo(theaterNo);
		newTheater.setBookedSeats(newTheater.getBookedSeats()+1);
	}

//	------------------------------------------------------------------------------------------
	
	/**
	 * Reservation ���� - ������ �̸����� �ش� ���� ����
	 * 
	 * ���� ���� �� �ش� Reservation �󿵰��� ���� �¼� ��--;
	 * 
	 * @param name ������ �̸�
	 * @throws NotExistException
	 */
	public void reservationDelete(String name) throws NotExistException{
		Reservation reservation = getReservation(name);
		Theater theater = getTheater(reservation.getTheaterNo());
		
		if(reservation == null | theater == null) {
			throw new NotExistException("�����ϰ��� �ϴ� ���� ������ �������� �ʴ� ������ �߻��߽��ϴ�.");
		}
		getReservationList().remove(reservation);
		theater.setBookedSeats(theater.getBookedSeats()+1);
	}
	
}