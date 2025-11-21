package br.com.codexcb.application.model;

import java.time.LocalDate;

public class Livro {
    private Integer id;
    private String titulo;
    private String autor;
    private String isbnCodigo;
    private String idioma;
    private String editora;
    private LocalDate dataPublicacao;
    private int copia;
    private int idLocalizacao;
    private String genero;

    //construtor
    public Livro(Integer id, String titulo, String autor, String isbnCodigo, String idioma, String editora, LocalDate dataPublicacao, int copia, int idLocalizacao, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbnCodigo = isbnCodigo;
        this.idioma = idioma;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
        this.copia = copia;
        this.idLocalizacao = idLocalizacao;
        this.genero = genero;
    }

    //getters and setters
    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbnCodigo() {
        return isbnCodigo;
    }

    public void setIsbnCodigo(String isbnCodigo) {
        this.isbnCodigo = isbnCodigo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public int getCopia() {
        return copia;
    }

    public void setCopia(int copia) {
        this.copia = copia;
    }

    public int getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(int idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
