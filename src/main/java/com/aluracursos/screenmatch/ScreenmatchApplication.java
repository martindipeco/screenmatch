package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.principal.Principal;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ApiKey;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraMenu();
//		System.out.println("Hola desde CommandLineRunner");
//
//		String clave = ApiKey.getApiKey();
//
//		//Reemplazar por título ingresado por usuario
//		String busqueda = "game+of+thrones";
//
//		String direccion = "https://www.omdbapi.com/?t=" + busqueda + "&apikey=" + clave;
//		String direccionEpisodio = "https://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=" + clave;
//
//		//ConsumoAPI consumoAPI = new ConsumoAPI();
//		var consumoAPI = new ConsumoAPI();
//		var json = consumoAPI.obtenerDatos(direccion);
//		var jsonEpisodio = consumoAPI.obtenerDatos(direccionEpisodio);
//
//
//		ConvierteDatos conversor = new ConvierteDatos();
//		var datos = conversor.obtenerDatos(json, DatosSerie.class);
//		var datosEpisodio = conversor.obtenerDatos(jsonEpisodio, DatosEpisodio.class);
//
//		List<DatosTemporada> listaDeTemporadas = new ArrayList<>();
//		//hago un for i con inicio en temporada 1 - x q no hay temporada 0
//		//datos.totalTemporadas sería lo mismo que listaDeTemporada.size?
//		for (int i = 1; i <= datos.totalTemporadas() ; i++) {
//			String direccionTemporada = "https://www.omdbapi.com/?t=game+of+thrones&Season="+i+"&apikey=" + clave;
//			var jsonTemporadas = consumoAPI.obtenerDatos(direccionTemporada);
//			var datosListaTemporadas = conversor.obtenerDatos(jsonTemporadas, DatosTemporada.class);
//			listaDeTemporadas.add(datosListaTemporadas);
//		}
//
//		System.out.println(datos);
//		System.out.println(datosEpisodio);
//
//		listaDeTemporadas.forEach(System.out::println);
	}
}
