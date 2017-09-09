package bootup;
import java.io.*;

abstract class Projeto implements Registro{
    protected int idProjeto;
    protected int idUsuario;
    protected byte estado;
    protected String descricao;

    // Métodos implementados

    public void setCodigo(int codigo){
        this.idProjeto = codigo;
    }

    public int getCodigo(){
        return idProjeto;
    }

    public abstract String getString();

    public abstract byte[] getByteArray() throws IOException;
    public abstract void setByteArray(byte[] buffer) throws IOException;

    public abstract int compareTo(Object objeto);
    public abstract Object clone() throws CloneNotSupportedException;
}