package ChatRoomClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
	public void ActiveUserList(String[] currentUsers) throws RemoteException;
	public void ReceivedMessage(String received) throws RemoteException;
	public void LeaveNote(String msg) throws RemoteException;
}
