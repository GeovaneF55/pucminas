package aed3;
import java.io.*;

public class Livro implements Registro {
    protected int codigo;
    protected String titulo;
    protected String autor;
    protected float preco;
    
    public Livro(int c, String t, String a, float p) {
        codigo = c;
        titulo = t;
        autor = a;
        preco = p;
    }
    public Livro() {
        codigo = 0;
        titulo = "";
        autor = "";
        preco = 0;
    }
    
    public void setCodigo(int c) {
        codigo = c;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getString() {
        return titulo;
    }
    
    public String toString() {
        return "\nCódigo..:" + codigo +
                "\nTítulo..:" + titulo +
                "\nAutor...:" + autor +
                "\nPreço...:" + preco;
    }
    
    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream( registro );
        saida.writeInt(codigo);
        saida.writeUTF(titulo);
        saida.writeUTF(autor);
        saida.writeFloat(preco);
        return registro.toByteArray();        
    }
    
    public void setByteArray(byte[] b) throws IOException {
        ByteArrayInputStream registro = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(registro);
        codigo = entrada.readInt();
        titulo = entrada.readUTF();
        autor = entrada.readUTF();
        preco = entrada.readFloat();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public int compareTo( Object b ) {
        return codigo - ((Livro)b).codigo;
    }

    /*
    public int compareTo( Object b ) {
        return titulo.compareTo(((Livro)b).titulo);
    }
    */
    
}
