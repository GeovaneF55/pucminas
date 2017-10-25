package com.example.geovane.divulgar.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by geovane on 15/10/17.
 */

public class Materia implements Serializable {
    private int id;
    private String nomeMateria;
    private int id_periodo;
    private ArrayList<String> videos;
    private ArrayList<String> pdfs;
    private ArrayList<String> links;

    public Materia(String nomeMateria) {
        this(1, nomeMateria, 1);
    }

    public Materia(int id, String nomeMateria, int id_periodo) {
        this.id = id;
        this.nomeMateria = nomeMateria;
        this.id_periodo = id_periodo;
        this.videos = new ArrayList<String>();
        this.pdfs = new ArrayList<String>();
        this.links = new ArrayList<String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public ArrayList<String> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<String> videos) {
        this.videos = videos;
    }

    public void addVideos(String video){
        this.videos.add(video);
    }

    public String removeVideo(String video) {
        return this.videos.remove(this.videos.indexOf(video));
    }

    public ArrayList<String> getPdfs() {
        return pdfs;
    }

    public void setPdfs(ArrayList<String> pdfs) {
        this.pdfs = pdfs;
    }

    public void addPDFs(String pdf){
        this.pdfs.add(pdf);
    }

    public String removePDFs(String pdf) {
        return this.pdfs.remove(this.pdfs.indexOf(pdf));
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public void addLinks(String link){
        this.pdfs.add(link);
    }

    public String removeLinks(String link) {
        return this.links.remove(this.links.indexOf(link));
    }
}
