package com.example.geovane.divulgar.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by geovane on 15/10/17.
 */

public class Periodo implements Serializable {
    private int id;
    private int periodo;
    private int id_curso;
    private ArrayList<Materia> materias;

    public Periodo(int periodo) {
        this(1, periodo, 1);
    }

    public Periodo(int id, int periodo, int id_curso) {
        this.id = id;
        this.periodo = periodo;
        this.id_curso = id_curso;
        this.materias = new ArrayList<Materia>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
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
}
