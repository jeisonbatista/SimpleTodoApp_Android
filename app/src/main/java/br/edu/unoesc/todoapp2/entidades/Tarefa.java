package br.edu.unoesc.todoapp2.entidades;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private Long id;
    private String titulo;
    private String descricao;
    private boolean urgente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    @Override
    public String toString() {
        return id + " - " + titulo;
    }
}
