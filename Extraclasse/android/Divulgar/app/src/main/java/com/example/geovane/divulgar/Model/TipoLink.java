package com.example.geovane.divulgar.Model;

/**
 * Created by geovane on 29/10/17.
 */

public class TipoLink {
    private int id;
    private String tipo;
    private String aliasTipo;

    public TipoLink(String tipo, String aliasTipo) {
        this(1, tipo, aliasTipo);
    }

    public TipoLink(int id, String tipo, String aliasTipo) {
        this.id = id;
        this.tipo = tipo;
        this.aliasTipo = aliasTipo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAliasTipo() {
        return aliasTipo;
    }

    public void setAliasTipo(String aliasTipo) {
        this.aliasTipo = aliasTipo;
    }
}
