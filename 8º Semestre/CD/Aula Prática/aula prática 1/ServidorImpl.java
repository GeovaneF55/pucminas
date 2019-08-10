import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ServidorImpl extends UnicastRemoteObject implements Servidor {
    private static final long serialVersionUID = 1L;
    Vector<Cliente> conectados;
    
    protected ServidorImpl() throws RemoteException {
        super();
        conectados = new Vector<Cliente>();
    }

    public String conectar( Cliente c ) throws RemoteException {
        conectados.addElement( c );
        return (" Usuario Conectado ");
    }

    public void desconectar( Cliente c ) throws RemoteException {
        conectados.remove( c );
    }

    public void enviar( String mensagem ) throws RemoteException {
        int i = 0;
        do {
            Cliente c = ( Cliente ) conectados.elementAt( i );
            c.Exibir( mensagem );
        } while ( i < conectados.size() );
    }

    public static void main( String[] args ) {
        try {
            Servidor s = new ServidorImpl();
            Naming.rebind("Servidor", s );
        } catch ( Exception e ) {
            System.out.println("Erro no servidor: " + e.getMessage() );
        }
    }
}
