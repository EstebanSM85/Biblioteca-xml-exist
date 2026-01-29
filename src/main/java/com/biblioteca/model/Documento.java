package com.biblioteca.model;

public class Documento {

    private int id;
    private String titulo;
    private String autor;
    private String editorial;
    private String fechaPublicacion;
    private String genero;
    private String descripcion;
    private String contenidoXML;

    public Documento(int id, String titulo, String autor, String editorial,
                     String fechaPublicacion, String genero, String descripcion,
                     String contenidoXML) {

        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.genero = genero;
        this.descripcion = descripcion;
        this.contenidoXML = contenidoXML;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getEditorial() { return editorial; }
    public String getFechaPublicacion() { return fechaPublicacion; }
    public String getGenero() { return genero; }
    public String getDescripcion() { return descripcion; }
    public String getContenidoXML() { return contenidoXML; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setEditorial(String editorial) { this.editorial = editorial; }
    public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setContenidoXML(String contenidoXML) { this.contenidoXML = contenidoXML; }
}