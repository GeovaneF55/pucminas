package com.example.geovane.divulgar.Model;

import java.util.ArrayList;

/**
 * Created by geovane on 15/10/17.
 */

public class Curso {
    private String nomeCurso;
    private ArrayList<Periodo> periodos;

    public Curso(String nomeCurso) {
        this.periodos = new ArrayList<Periodo>();
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

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
