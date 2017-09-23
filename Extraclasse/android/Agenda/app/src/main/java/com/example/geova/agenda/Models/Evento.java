package com.example.geova.agenda.Models;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by geovane on 17/09/17.
 */

public class Evento {

    private String nome;
    private DateFormat dataInicial;
    private DateFormat dataFinal;

    private Organizador organizador;
    private ArrayList<Convidado> convidados;

    public Evento() {
        setNome("");
        setDataInicial(null);
        setDataFinal(null);
        setOrganizador(null);
        this.convidados = new ArrayList<Convidado>();
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDataInicial(DateFormat dataInicial){
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(DateFormat dataFinal){
        this.dataFinal = dataFinal;
    }

    public void setOrganizador(Organizador organizador){
        this.organizador = organizador.clone();
    }

    public void pushConvidados(Convidado convidado){
        this.convidados.add(convidado);
    }

    public String getNome(){
        return this.nome;
    }

    public DateFormat getDataInicial(){
        return this.dataInicial;
    }

    public DateFormat getDataFinal(){
        return this.dataFinal;
    }

    public Organizador getOrganizador(){
        return this.organizador;
    }

    public ArrayList<Convidado> getConvidados(){
        return this.convidados;
    }
}