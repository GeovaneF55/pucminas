package com.example.geovane.divulgar.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by geovane on 15/10/17.
 */

public class Materia implements Serializable {
    private long id;
    private String nomeMateria;
    private long id_periodo;
    private ArrayList<Link> links;

    public Materia(String nomeMateria) {
        this(1, nomeMateria, 1);
    }

    public Materia(long id, String nomeMateria, int id_periodo) {
        this.id = id;
        this.nomeMateria = nomeMateria;
        this.id_periodo = id_periodo;
        this.links = new ArrayList<Link>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public long getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(long id_periodo) {
        this.id_periodo = id_periodo;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public void addLinks(Link link){
        this.links.add(link);
    }

    public Link removeLinks(Link link) {
        return this.links.remove(this.links.indexOf(link));
    }
}
