import java.rmi.*;

public interface SprinklerRemote extends Remote {
    public int getLimit() throws RemoteException;
    public String getLocation() throws RemoteException;
    public String getState() throws RemoteException;
}
