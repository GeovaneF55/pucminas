import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class ClienteImpl extends UnicastRemoteObject implements Cliente {
    private static final long serialVersionUID = 1L;
    protected ClienteImpl() throws RemoteException {
        super();
    }
    public void Exibir( String m ) throws RemoteException {
        try {
            System.out.println( m );
        } catch ( Exception e ) {
            System.out.println("Erro " + e );
        }
    }
    public static void main( String[] args ) throws Exception {
        Servidor s = ( Servidor ) Naming.lookup("Servidor");
        Cliente c = new ClienteImpl();
        s.conectar( c );
        s.enviar("Alo Mundo ");
        s.desconectar( c );
    }
}