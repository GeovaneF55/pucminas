import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Cliente extends Remote {
    public void Exibir( String mensagem ) throws RemoteException ;
}
