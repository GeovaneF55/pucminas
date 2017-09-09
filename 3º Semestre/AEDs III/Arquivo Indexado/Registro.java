package aed3;
import java.io.*;

public interface Registro extends Comparable, Cloneable {
    
    public void setCodigo(int codigo);
    public int getCodigo();
    public String getString();   // retorna um campo string qualquer (nome, título, descricao, etc.)
    
    public byte[] getByteArray() throws IOException;
    public void setByteArray(byte[] b) throws IOException;
    
    public int compareTo(Object b );
    public Object clone() throws CloneNotSupportedException;
    
}
