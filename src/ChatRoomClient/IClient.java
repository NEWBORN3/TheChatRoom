package ChatRoomClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
	public void ActiveUserList(String[] currentUsers) throws RemoteException;
}
