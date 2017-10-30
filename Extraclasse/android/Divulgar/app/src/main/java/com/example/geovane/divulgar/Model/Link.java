package com.example.geovane.divulgar.Model;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by geovane on 29/10/17.
 */

public class Link {
    private int id;
    private String nome;
    private URI url;
    private int id_Materia;
    private int id_TipLink;

    public Link(String nome, String url) {
        this(1, nome, url, 1, 1);
    }

    public Link(int id, String nome, String url, int id_Materia, int id_TipLink) {
        this.id = id;
        this.nome = nome;
        try {
            this.url = new java.net.URI(url);
        } catch (URISyntaxException e) {
            this.url =null;
        }
        this.id_Materia = id_Materia;
        this.id_TipLink = id_TipLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(String url) {
        try {
            this.url = new java.net.URI(url);
        } catch (URISyntaxException e) {
            this.url =null;
        }
    }

    public int getId_Materia() {
        return id_Materia;
    }

    public void setId_Materia(int id_Materia) {
        this.id_Materia = id_Materia;
    }

    public int getId_TipLink() {
        return id_TipLink;
    }

    public void setId_TipLink(int id_TipLink) {
        this.id_TipLink = id_TipLink;
    }
}
