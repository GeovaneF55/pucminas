package bootup;
import java.io.*;

public class Investidor extends Usuario {
    protected String cpf;
    protected String cnpj;
    // protected DataURL site;

    public Investidor() {
        super();
        cpf = "";
        cnpj = "";
    }

    public Investidor(int codigo, String nome, String email, String senha,
                        String telefone, String cpf, String cnpj) {
        super(codigo, nome, email, senha, telefone);
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(registro);

        out.write(super.getByteArray());
        out.writeUTF(cpf);
        out.writeUTF(cnpj);
        // out.write(site);

        return registro.toByteArray();
    }

    public void setByteArray(byte[] buffer) throws IOException {
        ByteArrayInputStream registro = new ByteArrayInputStream(buffer);
        DataInputStream in = new DataInputStream(registro);

        super.setByteArray(buffer, in);
        cpf = in.readUTF();
        cnpj = in.readUTF();
        // site = in.read();
    }

    public int compareTo(Object objeto) {
        return codigo - ((Investidor) objeto).codigo;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
