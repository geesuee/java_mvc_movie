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

	// ReservationNo는 수정하지 않기 때문에 getter만 생성
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
		builder.append("[예약 번호]");
		builder.append(reservationNo);
		builder.append(", [예약자 이름]");
		builder.append(name);
		builder.append(", [영화 제목]");
		builder.append(movieTitle);
		builder.append(", [상영관 번호]");
		builder.append(theaterNo);
		return builder.toString();
	}
}


