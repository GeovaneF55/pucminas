import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Servidor extends Remote {
    public String conectar( Cliente c ) throws RemoteException;
    public void enviar( String mensagem ) throws RemoteException;
    public void desconectar( Cliente c ) throws RemoteException;
}
