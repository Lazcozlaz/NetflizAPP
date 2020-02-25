package objetos;

public class Pelicula {
	
	String titulo;
	int ano;
	String poster;
	String imdbID;
	String duracion;
	String director;
	String generos;
	String plot;
	
	
	public Pelicula(String titulo, int ano, String poster, String imdbID) {
		super();
		this.titulo = titulo;
		this.ano = ano;
		this.poster = poster;
		this.imdbID = imdbID;
	}
	public Pelicula(String titulo, int ano, String poster, String imdbID, String duracion, String director,
			String generos, String plot) {
		super();
		this.titulo = titulo;
		this.ano = ano;
		this.poster = poster;
		this.imdbID = imdbID;
		this.duracion = duracion;
		this.director = director;
		this.generos = generos;
		this.plot = plot;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getGeneros() {
		return generos;
	}
	public void setGeneros(String generos) {
		this.generos = generos;
	}
		public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", ano=" + ano + ", poster=" + poster + ", imdbID=" + imdbID
				+ ", duracion=" + duracion + ", director=" + director + ", generos=" + generos + "]";
	}	
}
