package com.example.geova.agenda.Models;

import java.io.Serializable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by geovane on 17/09/17.
 */

public class Evento implements Serializable{

    private String nome;
    private Date dataInicial;
    private Date dataFinal;
    private Time horaInicial;
    private Time horaFinal;

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

    public void setDataInicial(Date dataInicial){
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(Date dataFinal){
        this.dataFinal = dataFinal;
    }

    public void setHoraInicial(Time horaInicial){
        this.horaInicial = horaInicial;
    }

    public void setHoraFinal(Time horaFinal){
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

    public Date getDataInicial(){
        return this.dataInicial;
    }

    public Date getDataFinal(){
        return this.dataFinal;
    }

    public Time getHoraInicial(){
        return this.horaInicial;
    }

    public Time getHoraFinal(){
        return this.horaFinal;
    }

    public Organizador getOrganizador(){
        return this.organizador;
    }

    public ArrayList<Convidado> getConvidados(){
        return this.convidados;
    }
}