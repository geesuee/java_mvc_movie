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
		
		// ��ȭ����, ����, ���ΰ�, �帣, ������, �������, ����Ÿ��
		movieList.add(new Movie("�𰡵�", "���¿�", "������", "�׼�,���", "2021-07-28", "15�� ������" , 121));
		movieList.add(new Movie("���� ���̺�2" , "�� �Ʊ׶�", "�˷� ������", "�ִϸ��̼�,�ڹ̵�,����",  "2021-07-21", "��ü ������" , 107));
		movieList.add(new Movie("���� ������","����Ʈ ��Ʈ����", "��Į�� ���ڽ�", "�׼�, ����, SF", "2021-07-07",  "12�� ������" , 134));
		movieList.add(new Movie("����", "���� �ǻ�ٳ���", "������ ��������", "����, ������, ���", "2021-07-14", "û�ҳ� �����Ұ�", 131));
		movieList.add(new Movie("���� ũ����", "�ڿ� �ݷ� ����", "����� ����", "�׼�, ����", "2021-07-28", "12�� ������", 127));
		movieList.add(new Movie("�츮, ��", "�ʸ��� �޳װ�Ƽ", "�ٹٶ� ���ڹ�", "���,���/�θǽ�", "2021-07-28", "12�� ������", 95));
		movieList.add(new Movie("���̽� �ε�", "������ �����", "���� �Ͻ�", "������", "2021-07-21", "12�� ������", 110));
		movieList.add(new Movie("�ɴٹ� ���� ����� �ߴ�", "���� �������", "�Ƹ����� ī����", "���/�θǽ�", "2021-07-14", "12�� ������", 123));
		movieList.add(new Movie("�Ǵн�",  "ũ����Ƽ�� ����Ʈ", "�ϳ� ȣ��", "���/�θǽ�/���", "2021-07-22", "12�� ������", 98));
		movieList.add(new Movie("���:������","����", "������", "�̽��͸�,������",  "2021-07-28", "15�� ������" , 109));
		
		//�󿵰� ��ȣ, �� �¼� ��, ���� �¼� �� 		
		theaterList.add(new Theater("1��" , 1, 1));
		theaterList.add(new Theater("2��" , 2, 1));
		theaterList.add(new Theater("3��" , 10, 1));
		theaterList.add(new Theater("4��" , 20, 1));
		theaterList.add(new Theater("5��" , 30, 1));
		theaterList.add(new Theater("6��" , 40, 1));
		theaterList.add(new Theater("7��" , 50, 1));
		theaterList.add(new Theater("8��" , 60, 1));
		theaterList.add(new Theater("9��" , 70, 1));
		theaterList.add(new Theater("10��" , 100, 1));
		
		// ������ �̸�, ��ȭ ����, �󿵰� ��ȣ
		reservationList.add(new Reservation("ȫ�浿", "�𰡵�", "1��"));
		reservationList.add(new Reservation("���ھ�", "���� ���̺�2", "2��"));
		reservationList.add(new Reservation("�÷���", "���� ������", "3��"));
		reservationList.add(new Reservation("����", "����", "4��"));
		reservationList.add(new Reservation("�����", "���� ũ����", "5��"));
		reservationList.add(new Reservation("����", "�츮, ��", "6��"));
		reservationList.add(new Reservation("���ϳ�", "���̽� �ε�", "7��"));
		reservationList.add(new Reservation("��س�", "�ɴٹ� ���� ����� �ߴ�", "8��"));
		reservationList.add(new Reservation("������", "�Ǵн�", "9��"));
		reservationList.add(new Reservation("��Ź��", "���:������", "10��"));
		
		// �󿵰��� �� ��ȭ mapping
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
	// movieList, theaterList�� �������� �ʱ� ������, setter�� reservatioList�� ����
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