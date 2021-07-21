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
	
	private IServer ser;
	ChatUI gui;
	private String _userName;
	
	public Client(ChatUI gui, String userName) throws RemoteException{
		// TODO Auto-generated constructor stub
		super();
		this.gui = gui;
		this._userName = userName;
	}
	
	public void clientStart() throws RemoteException {
		System.out.println("Client is r*****");
		try {
			Registry reg = LocateRegistry.getRegistry(null);
			ser = (IServer) reg.lookup("The Chat room");	
			System.out.println("Client is running nuss...\n*****");
			registerToServer();
		} 
		catch (Exception  e) {
			System.out.println(e.toString());
		}
		
	}
	
	public void registerToServer() throws RemoteException {
		try {
			System.out.println("Clien now here:  " + _userName);
			ser.registerClient(_userName,this);
		} catch(Exception e) {
			
		}
	}

	@Override
	public void ActiveUserList(String[] currentUsers) throws RemoteException {
		// TODO Auto-generated method stub
		//gui.setClientPanel(currentUsers);
		gui.updateList(currentUsers);
		gui.ActiveUserPanel.repaint();
		gui.ActiveUserPanel.revalidate();
	}
}
