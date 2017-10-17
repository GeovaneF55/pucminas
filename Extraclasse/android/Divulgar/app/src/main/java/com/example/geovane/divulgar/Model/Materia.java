package com.example.geovane.divulgar.Model;

import java.util.ArrayList;

/**
 * Created by geovane on 15/10/17.
 */

public class Materia {
    private String nomeMateria;
    private ArrayList<String> videos;
    private ArrayList<String> pdfs;
    private ArrayList<String> links;

    public Materia(String nomeMateria) {
        this.videos = new ArrayList<String>();
        this.pdfs = new ArrayList<String>();
        this.links = new ArrayList<String>();
        this.nomeMateria = nomeMateria;
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

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }
}
