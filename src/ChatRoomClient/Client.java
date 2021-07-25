package ChatRoomClient;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;



import ChatRoomServer.IServer;



public class Client extends UnicastRemoteObject implements IClient {
	
	public  IServer ser;
	public  ChatUI gui;
	private String _userName;
	
	public Client(ChatUI gui) throws RemoteException{
		// TODO Auto-generated constructor stub
		super();
		this.gui = gui;
	}
	
	public Boolean clientStart(String sName) throws RemoteException {
		try {
			Registry reg = LocateRegistry.getRegistry(null);
			ser = (IServer) reg.lookup(sName);	//localhost
			return true;
		} 
		catch (Exception  e) {
			System.out.println("Could'nt Connect to server: ");
			System.out.println(e.toString());
			return false;
		}	
	}
	
	public void registerToServer(String userName) throws RemoteException {
		try {
			this._userName = userName;
			ser.registerClient(_userName,this);
		} catch(Exception e) {
			System.out.println("Wonder io---");
		}
	}

	@Override
	public void ActiveUserList(String[] currentUsers) throws RemoteException {
		// TODO Auto-generated method stub
		gui.ChatPanel.remove(gui.listScrollPane);
		gui.ChatPanel.remove(gui._userList);
		gui.updateList(currentUsers);
		gui.ChatPanel.repaint();
		gui.ChatPanel.revalidate();
	}

	@Override
	public void ReceivedMessage(String received) throws RemoteException {
		// TODO Auto-generated method stub
		gui.ChatArea.append(received);
	}

	@Override
	public void LeaveNote(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		gui.ChatArea.append(msg);
		System.out.print("wegooot"+ msg);
	}
	
	
}
