package movie.model.dto;

public class Theater {

	private String theaterNo;
	private int totalSeats;
	private int bookedSeats;
	private int remainSeats = totalSeats - bookedSeats;
	
	public Theater() {}

	public Theater(String theaterNo, int totalSeats, int bookedSeats) {
			super();
		this.theaterNo = theaterNo;
		this.totalSeats = totalSeats;
		this.bookedSeats = bookedSeats;
		this.remainSeats = totalSeats - bookedSeats;
	}
	
	public String getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(String theaterNo) {
		this.theaterNo = theaterNo;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public int getRemainSeats() {
		return remainSeats;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[상영관 번호]");
		builder.append(theaterNo);
		builder.append(", [좌석 예매 현황]");
		builder.append(remainSeats + "/" + totalSeats);

		return builder.toString();
	}
}

