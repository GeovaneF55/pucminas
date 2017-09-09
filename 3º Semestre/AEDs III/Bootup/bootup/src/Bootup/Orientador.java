package bootup;
import java.io.*;

public class Orientador extends Usuario {
    protected String[] areaAtuacao;
    // protected DataURL lattes;

    public Orientador() {
        super();
        areaAtuacao = new String[2];
        for (int i = 0; i < areaAtuacao.length; i++) {
            areaAtuacao[i] = "";
        }
    }

    public Orientador(int codigo, String nome, String email, String senha,
                        String telefone, String[] areaAtuacao) {
        super(codigo, nome, email, senha, telefone);
        this.areaAtuacao = areaAtuacao;
    }

    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(registro);

        out.write(super.getByteArray());
        out.writeShort(areaAtuacao.length);
        for (String area : areaAtuacao) {
            out.writeUTF(area);
        }
        // out.write(lattes);

        return registro.toByteArray();
    }

    public void setByteArray(byte[] buffer) throws IOException {
        ByteArrayInputStream registro = new ByteArrayInputStream(buffer);
        DataInputStream in = new DataInputStream(registro);

        super.setByteArray(buffer, in);
        int numAreas = in.readShort();
        for (int i = 0; i < numAreas; i++) {
            areaAtuacao[i] = in.readUTF();
        }
        // lattes = in.read();
    }

    public int compareTo(Object objeto) {
        return codigo - ((Orientador) objeto).codigo;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
