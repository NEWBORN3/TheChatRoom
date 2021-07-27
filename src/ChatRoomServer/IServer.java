package ChatRoomServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ChatRoomClient.ChatUI;
import ChatRoomClient.IClient;

public interface IServer extends Remote {
	public void registerClient(String name,IClient cli) throws RemoteException;
	public boolean isUserUnique(String name) throws RemoteException;
	public void leaveUser(String name) throws RemoteException;
	public void chat(String sender, String message ) throws RemoteException;
	public void chatPrivate(String sender,int receiver, String message) throws RemoteException;
}
