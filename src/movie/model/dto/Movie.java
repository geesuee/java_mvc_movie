package movie.model.dto;

public class Movie {
	private String movieTitle;
	private String director;
	private String mainActor;
	private String genre;
	private String openingDate;
	private String viewingGrade;
	private int runningTime;
	
	public Movie() {}

	public Movie(String movieTitle, String director, String mainActor, String genre, String openingDate, String viewingGrade,
			int runningTime) {
		super();
		this.movieTitle = movieTitle;
		this.director = director;
		this.mainActor = mainActor;
		this.genre = genre;
		this.openingDate = openingDate;
		this.viewingGrade = viewingGrade;
		this.runningTime = runningTime;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMainActor() {
		return mainActor;
	}

	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	public String getViewingGrade() {
		return viewingGrade;
	}

	public void setViewingGrade(String viewingGrade) {
		this.viewingGrade = viewingGrade;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("제목 : ");
		builder.append(movieTitle);
		builder.append(", 감독 : ");
		builder.append(director);
		builder.append(", 주인공 : ");
		builder.append(mainActor);
		builder.append(", 장르 : ");
		builder.append(genre);
		builder.append(", 개봉일 : ");
		builder.append(openingDate);
		builder.append(", 관람등급: ");
		builder.append(viewingGrade);
		builder.append(", 러닝타임 : ");
		builder.append(runningTime);
		return builder.toString();
	}
}
