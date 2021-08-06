package movie.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import movie.model.dto.Movie;
import movie.model.dto.Reservation;
import movie.model.dto.Theater;

public class EndView {

	// �����ϴ� ��� ��ȭ ������ ���
	public static void movieListView(ArrayList<Movie> allMovieData) {
		for (Movie movie : allMovieData) {
			System.out.println(movie);
		}
	}
	
	// �����ϴ� ��� �󿵰� ������ ���
	public static void theaterListView(ArrayList<Theater> allTheaterData) {
		for (Theater theater : allTheaterData) {
			System.out.println(theater);
		}
	}

	// �����ϴ� ��� ���� ������ ���
	public static void reservationListView(ArrayList<Reservation> allReservatioinData) {
		for (Reservation reservation : allReservatioinData) {
			System.out.println(reservation);
		}
	}

//	----------------------------------------------------------------------------------
	
	// ���ε� <Theater, Movie> ������ ���
	public static void mappedDataView(HashMap<Theater, Movie> mappedData) {
		Iterator<Theater> keys = mappedData.keySet().iterator();
		Theater key = null;
		
		while(keys.hasNext()) {
			key = keys.next();
			System.out.println("<< " + key.getTheaterNo() +" >> " + key);
			System.out.println("<< " + key.getTheaterNo() + " �� ��ȭ>> " + mappedData.get(key) + "\n");
		}
	}
	
//	----------------------------------------------------------------------------------
	
	// �����ϴ� ��ȭ ������ (����)���
	public static void movieView(Movie movie) {
		if (movie != null) {
			System.out.println(movie);
		} else {
			System.out.println("�ش� ��ȭ �����Ͱ� �������� �ʽ��ϴ�.");
		}
	}

	// �����ϴ� �󿵰� ������ (����)���
	public static void theatertView(Theater theater) {
		if (theater != null) {
			System.out.println(theater);
		} else {
			System.out.println("�ش� �󿵰� �����Ͱ� �������� �ʽ��ϴ�.");
		}
	}

	// �����ϴ� ���� ������ (����)���
	public static void reservationView(Reservation reservation) {
		if (reservation != null) {
			System.out.println(reservation);
		} else {
			System.out.println("�ش� ���� �����Ͱ� �������� �ʽ��ϴ�.");
		}
	}

//	----------------------------------------------------------------------------------
	
	// ���ܰ� �ƴ� �ܼ� �޼��� ���
	public static void messageView(String message) {
		System.out.println(message);
	}

	
}