package bootup;
import java.io.*;

public class Aluno extends Usuario {
    protected String matricula;
    protected String curso;
    // private DataURL lattes;

    public Aluno() {
        super();
        matricula = "";
        curso = "";
    }

    public Aluno(int codigo, String nome, String email, String senha,
                    String telefone, String matricula, String curso) {
        super(codigo, nome, email, senha, telefone);
        this.matricula = matricula;
        this.curso = curso;
        // this.lattes = lattes;
    }

    @Override
    public String toString() {
        return super.toString() + "\nMatrícula: " + matricula +
                                    "\nCurso: " + curso;
    }
    
    public byte[] getByteArray() throws IOException {
        ByteArrayOutputStream registro = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(registro);

        out.write(super.getByteArray());
        out.writeUTF(matricula);
        out.writeUTF(curso);
        // out.write(lattes);

        return registro.toByteArray();
    }

    public void setByteArray(byte[] buffer) throws IOException {
        ByteArrayInputStream registro = new ByteArrayInputStream(buffer);
        DataInputStream in = new DataInputStream(registro);

        super.setByteArray(buffer, in);
        matricula = in.readUTF();
        curso = in.readUTF();
        // lattes = in.read();
    }

    public int compareTo(Object objeto) {
        return codigo - ((Aluno) objeto).codigo;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
