package movie.model.dto;

public class Reservation {
	private static int serialNum = 1;
	private int reservationNo;
	private String name;
	private String movieTitle;
	private String theaterNo;
	
	public Reservation() {
		super();
		this.reservationNo = serialNum;
		serialNum++;
	}

	public Reservation(String name, String movieTitle, String theaterNo) {
		super();
		this.reservationNo = serialNum;
		serialNum++;
		this.name = name;
		this.movieTitle = movieTitle;
		this.theaterNo = theaterNo;
	}
	
	public Reservation(int reservationNo, String name, String movieTitle, String theaterNo) {
		super();
		this.reservationNo = reservationNo;
		this.name = name;
		this.movieTitle = movieTitle;
		this.theaterNo = theaterNo;
	}

	// ReservationNo�� �������� �ʱ� ������ getter�� ����
	public int getReservationNo() {
		return reservationNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getTheaterNo() {
		return theaterNo;
	}

	public void setTheaterNo(String theaterNo) {
		this.theaterNo = theaterNo;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[���� ��ȣ]");
		builder.append(reservationNo);
		builder.append(", [������ �̸�]");
		builder.append(name);
		builder.append(", [��ȭ ����]");
		builder.append(movieTitle);
		builder.append(", [�󿵰� ��ȣ]");
		builder.append(theaterNo);
		return builder.toString();
	}
}

