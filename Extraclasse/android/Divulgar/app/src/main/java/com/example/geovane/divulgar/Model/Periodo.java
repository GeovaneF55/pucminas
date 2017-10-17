package com.example.geovane.divulgar.Model;

import java.util.ArrayList;

/**
 * Created by geovane on 15/10/17.
 */

public class Periodo {
    private int periodo;
    private ArrayList<Materia> materias;

    public Periodo(int periodo) {
        this.materias = new ArrayList<Materia>();
        this.periodo = periodo;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public void addMateria(Materia materia){
        this.materias.add(materia);
    }

    public Materia removeMateria(Materia materia) {
        return this.materias.remove(this.materias.indexOf(materia));
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
}
