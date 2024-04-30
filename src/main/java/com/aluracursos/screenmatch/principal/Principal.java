package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ApiKey;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

        for (int i = 0; i < datos.totalTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporadas = listaDeTemporadas.get(i).listaEpisodios();
            for (int j = 0; j < episodiosTemporadas.size(); j++) {
                System.out.println(episodiosTemporadas.get(j).titulo());
            }
        }
        //mismo que lo anterior, pero con una función lambda
        //listaDeTemporadas.forEach(t -> t.listaEpisodios().forEach(e -> System.out.println(e.titulo())));
        System.out.println("Top 5 de episodios");
        //convertir las dos listas a una lista de datos-episodio que incluya temporada
        List<DatosEpisodio> listaDatosEpisodios = listaDeTemporadas.stream().flatMap(t -> t.listaEpisodios()
                .stream()).collect(Collectors.toList());

        //top 5 episodios - filtro los que no tienen evaluacion
        listaDatosEpisodios.stream().filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .limit(5).forEach(System.out::println);

        //convirtiendo datos a lista de tipo episodio
        //con flatMap, cada elemento de la lista de temporadas a un episodio
        //transformándolo a un tipo Episodio
        List<Episodio> listaEpisodios = new ArrayList<>();
        listaEpisodios = listaDeTemporadas.stream().flatMap(t -> t.listaEpisodios()
                .stream().map(d -> new Episodio(t.numeroTemporadas(), d)))
                .collect(Collectors.toList());

        listaEpisodios.forEach(System.out::println);

        //Búsqueda de episodios a partir de un año
        System.out.println("indique año a partir del cual mostrar datos");
        var fecha = scanner.nextLine();
        //falta el try catch por si usuario no ingresa numero

        LocalDate fechaBusqueda = LocalDate.of(Integer.valueOf(fecha), 1, 1); //formato año,mes,dia

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        listaEpisodios.stream()
                .filter(e -> e.getFechaLanzamiento()!= null && e.getFechaLanzamiento().isAfter(fechaBusqueda))
                .forEach(e -> System.out.println("Temporada: " + e.getNumTemporada()
                        + ", Episodio: " + e.getNumEpisodio()
                        + ", Fecha: " + e.getFechaLanzamiento().format(dateTimeFormatter)));

        //buscar episodio por parte del título
        System.out.println("Escriba el titulo del episodio que desea ver");
        var titulo = scanner.nextLine();

        //to lower porque no existe el ignoreCase
        Optional<Episodio> episodioEncontrado = listaEpisodios.stream().filter(e -> e.getTitulo().toLowerCase()
                        .contains(titulo.toLowerCase())).findFirst();

        if (episodioEncontrado.isPresent())
        {
            System.out.println("Se encontró el episodio " + episodioEncontrado.get().getTitulo());
        }
        else
        {
            System.out.println("Episodio no encontrado");
        }
    }
}
