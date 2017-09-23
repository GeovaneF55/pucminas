package com.example.geova.agenda.Models;

/**
 * Created by geovane on 17/09/17.
 */

public class Convidado {
    private String nome;
    private String email;
    private String telefone;

    public Convidado() {
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

    public Convidado clone(){
        Convidado clone = new Convidado();
        clone.setNome(this.getNome());
        clone.setEmail(this.getEmail());
        clone.setTelefone(this.getTelefone());
        return clone;
    }
}
