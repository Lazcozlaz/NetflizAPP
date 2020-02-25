package objetos;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parseadores {
	
	static public Busqueda parseadorBusqueda(String resultadosJson) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject Obj = parser.parse(resultadosJson).getAsJsonObject();
		
		int resultados = Obj.get("totalResults").getAsInt();
		
		JsonArray peliculas = Obj.get("Search").getAsJsonArray();
		ArrayList<Pelicula> listaPeliculas = new ArrayList();
		
		for(JsonElement peli : peliculas) {	
			JsonObject peliculaObj = peli.getAsJsonObject();
			String titulo = peliculaObj.get("Title").getAsString();
			int ano = 0;
			try{
				ano = peliculaObj.get("Year").getAsInt();
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			String poster = peliculaObj.get("Poster").getAsString();
			
			String imdbID = peliculaObj.get("imdbID").getAsString();
			
			Pelicula p = new Pelicula(titulo, ano, poster, imdbID);
			listaPeliculas.add(p);
		}
		
		Busqueda b = new Busqueda (resultados, listaPeliculas);
		return b;
}
	
	static public Pelicula parseadorID(String resultadosJson) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject Obj = parser.parse(resultadosJson).getAsJsonObject();
			
		
		String titulo = null;
		int ano = 0;
		String poster = null;;
		String imdbID = null;
		String duracion = null;
		String director = null;
		String generos = null;
		String plot = null;
		
			JsonObject peliculaObj = Obj.getAsJsonObject();
			titulo = peliculaObj.get("Title").getAsString();
		
			try{
				ano = peliculaObj.get("Year").getAsInt();
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			poster = peliculaObj.get("Poster").getAsString();
			

			imdbID = peliculaObj.get("imdbID").getAsString();
			
			duracion = peliculaObj.get("Runtime").getAsString();
			
			director = peliculaObj.get("Director").getAsString();
			
			generos = peliculaObj.get("Genre").getAsString();
			
			plot = peliculaObj.get("Plot").getAsString();
			
		
		Pelicula p = new Pelicula(titulo, ano, poster, imdbID, duracion, director, generos, plot);
		return p;
}
}
