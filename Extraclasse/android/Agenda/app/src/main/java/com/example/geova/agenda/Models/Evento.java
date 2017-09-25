package com.example.geova.agenda.Models;

import java.io.Serializable;

import java.util.ArrayList;


/**
 * Created by geovane on 17/09/17.
 */

public class Evento implements Serializable{

    private String nome;
    private String dataInicial;
    private String dataFinal;
    private String horaInicial;
    private String horaFinal;

    private Organizador organizador;
    private ArrayList<Convidado> convidados;

    public Evento() {
        setNome("");
        setDataInicial(null);
        setDataFinal(null);
        setHoraInicial(null);
        setHoraFinal(null);
        setOrganizador(null);
        this.convidados = new ArrayList<Convidado>();
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDataInicial(String dataInicial){
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(String dataFinal){
        this.dataFinal = dataFinal;
    }

    public void setHoraInicial(String horaInicial){
        this.horaInicial = horaInicial;
    }

    public void setHoraFinal(String horaFinal){
        this.horaFinal = horaFinal;
    }

    public void setOrganizador(Organizador organizador){
        if(organizador != null){
            this.organizador = organizador.clone();
        } else{
            this.organizador = null;
        }
    }

    public void pushConvidados(Convidado convidado){
        this.convidados.add(convidado);
    }

    public String getNome(){
        return this.nome;
    }

    public String getDataInicial(){
        return this.dataInicial;
    }

    public String getDataFinal(){
        return this.dataFinal;
    }

    public String getHoraInicial(){
        return this.horaInicial;
    }

    public String getHoraFinal(){
        return this.horaFinal;
    }

    public Organizador getOrganizador(){
        return this.organizador;
    }

    public ArrayList<Convidado> getConvidados(){
        return this.convidados;
    }
}