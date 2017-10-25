package com.example.geovane.divulgar.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by geovane on 15/10/17.
 */

public class Curso implements Serializable {
    private int id;
    private String nomeCurso;
    private ArrayList<Periodo> periodos;

    public Curso(String nomeCurso) {
        this(1, nomeCurso);
    }

    public Curso(int id, String nomeCurso) {
        this.id = id;
        this.nomeCurso = nomeCurso;
        this.periodos = new ArrayList<Periodo>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public ArrayList<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(ArrayList<Periodo> periodos) {
        this.periodos = periodos;
    }

    public void addPeriodo(Periodo periodo){
        this.periodos.add(periodo);
    }

    public Periodo removePeriodo(Periodo periodo) {
        return this.periodos.remove(this.periodos.indexOf(periodo));
    }
}
