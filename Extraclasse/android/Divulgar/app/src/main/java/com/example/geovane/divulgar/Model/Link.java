package com.example.geovane.divulgar.Model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by geovane on 29/10/17.
 */

public class Link {
    private long id;
    private String nome;
    private String url;
    private long id_Materia;
    private long id_TipLink;

    public Link(String nome, String url) {
        this(1, nome, url, 1, 1);
    }

    public Link(long id, String nome, String url, long id_Materia, long id_TipLink) {
        this.id = id;
        this.nome = nome;
        try {
            this.url = getVideoId(new java.net.URI(url).toString());
        } catch (URISyntaxException e) {
            this.url = null;
        }
        this.id_Materia = id_Materia;
        this.id_TipLink = id_TipLink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url.toString();
    }

    public void setUrl(String url) {
        try {
            this.url = getVideoId(new java.net.URI(url).toString());
        } catch (URISyntaxException e) {
            this.url =null;
        }
    }

    public long getId_Materia() {
        return id_Materia;
    }

    public void setId_Materia(long id_Materia) {
        this.id_Materia = id_Materia;
    }

    public long getId_TipLink() {
        return id_TipLink;
    }

    public void setId_TipLink(long id_TipLink) {
        this.id_TipLink = id_TipLink;
    }

    public static String getVideoId(String url){
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if(matcher.find()){
            return matcher.group();
        }
        return "";
    }
}
