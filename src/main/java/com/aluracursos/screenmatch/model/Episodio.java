package com.aluracursos.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer numTemporada;
    private String titulo;
    private Integer numEpisodio;
    private Double evaluacion;
    private LocalDate fechaLanzamiento;

    public Episodio(Integer numeroTemporada, DatosEpisodio d) {
        this.numTemporada = numeroTemporada;
        this.titulo = d.titulo();
        this.numEpisodio = d.numeroEpisodio();
        try //algunos vienen como N/A
        {
            this.evaluacion = Double.valueOf(d.evaluacion()); //debo convertir porque viene como String
        }
        catch (NumberFormatException e)
        {
            this.evaluacion = 0.0;
        }
        try //algunos vienen como N/A
        {
            this.fechaLanzamiento = LocalDate.parse(d.fechaLanzamiento()); //debo convertir porque viene como String
        }
        catch (DateTimeParseException e)
        {
            this.fechaLanzamiento = null;
        }

    }

    public Integer getNumTemporada() {
        return numTemporada;
    }

    public void setNumTemporada(Integer numTemporada) {
        this.numTemporada = numTemporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumEpisodio() {
        return numEpisodio;
    }

    public void setNumEpisodio(Integer numEpisodio) {
        this.numEpisodio = numEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "Temporada: " + numTemporada +
                ", Titulo: '" + titulo + '\'' +
                ", Episodio: " + numEpisodio +
                ", Evaluacion: " + evaluacion +
                ", Fecha de Lanzamiento: " + fechaLanzamiento +
                '}';
    }
}
