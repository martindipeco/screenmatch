package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.service.ApiKey;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    //para declarar una constante
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = ApiKey.getApiKey();
    private ConvierteDatos conversor = new ConvierteDatos();
    public void muestraMenu()
    {
        System.out.println("Escriba el nombre de la serie a buscar: ");
        //podemos declararlo como String. En este caso var puede inferir el tipo
        var nombreSerie = scanner.nextLine();
        String direccion = URL_BASE + nombreSerie.replace(" ", "+") + "&apikey=" + API_KEY;
        var json = consumoAPI.obtenerDatos(direccion);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);

        List<DatosTemporada> listaDeTemporadas = new ArrayList<>();
        //hago un for i con inicio en temporada 1 - x q no hay temporada 0
        //datos.totalTemporadas sería lo mismo que listaDeTemporada.size?
        for (int i = 1; i <= datos.totalTemporadas() ; i++) {
            String direccionTemporada = "https://www.omdbapi.com/?t=" + nombreSerie.replace(" ", "+") + "&Season=" + i + "&apikey=" + API_KEY;
            var jsonTemporadas = consumoAPI.obtenerDatos(direccionTemporada);
            var datosListaTemporadas = conversor.obtenerDatos(jsonTemporadas, DatosTemporada.class);
            listaDeTemporadas.add(datosListaTemporadas);
        }
        listaDeTemporadas.forEach(System.out::println);
    }
}
