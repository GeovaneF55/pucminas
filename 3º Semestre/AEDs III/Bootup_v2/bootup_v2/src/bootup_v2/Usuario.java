package bootup_v2;
import java.io.*;

abstract class Usuario implements Registro {
    protected int codigo;
    protected String nome;
    // protected DataURI foto;
    protected String email;
    protected String senha;
    protected String telefone;

    public Usuario() {
        codigo = 0;
        nome = "";
        // foto = null;
        email = "";
        senha = "";
        telefone = "";
    }

    public Usuario(int codigo, String nome, String email, String senha, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        // this.foto = foto;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getString() {
        return nome;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                "\nNome: " + nome +
                "\nE-mail: " + email +
                "\nSenha: " + senha +
                "\nTelefone: " + telefone;
    }
    
    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(registro);

        out.writeInt(codigo);
        out.writeUTF(nome);
        // out.write(foto);
        out.writeUTF(email);
        out.writeUTF(senha);
        out.writeUTF(telefone);

        return registro.toByteArray();
    }

    public void setByteArray(byte[] buffer, DataInputStream in) throws IOException {
        codigo = in.readInt();
        nome = in.readUTF();
        // foto = in.read();
        email = in.readUTF();
        senha = in.readUTF();
        telefone = in.readUTF();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
