package objetos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Busqueda {
	
	int numeroResultados;
	ArrayList<Pelicula> peliculas;
	
	public Busqueda(int numeroResultados, ArrayList<Pelicula> peliculas) {
		super();
		this.numeroResultados = numeroResultados;
		this.peliculas = peliculas;
	}
	
	public Busqueda(ArrayList<Pelicula> peliculas) {
		super();
		this.peliculas = peliculas;
	}

	public int getNumeroResultados() {
		return numeroResultados;
	}

	public void setNumeroResultados(int numeroResultados) {
		this.numeroResultados = numeroResultados;
	}

	public ArrayList<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	@Override
	public String toString() {
		return "Busqueda [numeroResultados=" + numeroResultados + ", peliculas=" + peliculas + "]";
	}
	
	
	
}
