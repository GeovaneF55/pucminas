package bootup_v2;

import java.io.IOException;

public interface Registro extends Comparable<Object>, Cloneable {
	public void setCodigo(int codigo);
    public int getCodigo();
    public String getString();

    public byte[] getByteArray() throws IOException;
    public void setByteArray(byte[] buffer) throws IOException;

    public int compareTo(Object objeto);
    public Object clone() throws CloneNotSupportedException;
}
