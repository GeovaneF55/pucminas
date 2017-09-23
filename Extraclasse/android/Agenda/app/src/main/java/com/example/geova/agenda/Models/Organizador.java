package com.example.geova.agenda.Models;

import java.text.DateFormat;

/**
 * Created by geovane on 17/09/17.
 */

public class Organizador {
    private String nome;
    private String email;
    private String telefone;

    public Organizador() {
        setNome("");
        setEmail("");
        setTelefone("");
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public Organizador clone(){
        Organizador clone = new Organizador();
        clone.setNome(this.getNome());
        clone.setEmail(this.getEmail());
        clone.setTelefone(this.getTelefone());
        return clone;
    }
}
