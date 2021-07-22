package ChatRoomServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import ChatRoomClient.IClient;

import java.util.ArrayList;
import java.util.List;



public class Server extends UnicastRemoteObject implements IServer {
	private List<UserModel> activeUser = new ArrayList<UserModel>();;
	public Server() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// start rmi registry server
		RMIRegistryStart();
		
		
		try {
			//Instantiating the implemented class
			IServer ser = new Server();
		
			Registry reg = LocateRegistry.getRegistry();
			
			//Export the object of the implemented class
//			IServer stub = (IServer) UnicastRemoteObject.exportObject(ser,0);
			
			//binding the remote object(stub) in the registry
			reg.bind("The Chat room", ser);
			System.out.println("-----------server is ready-------------");
			
			
		}catch(Exception e)
		{
			System.err.println("server error");
			e.printStackTrace();
		}

	}
	
	public static void RMIRegistryStart()
	{
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("RMI Registry Started ");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerClient(String name,IClient cli) throws RemoteException {
		// TODO Auto-generated method stub
		try 
		{
			System.out.println(name+"n then");
			activeUser.add(new UserModel(name,cli));
			for(UserModel usi : activeUser)
			{
				System.out.println(usi.getUsername()+"oo");
			}
			System.out.println("well");
			System.out.println("-----hfa=----");
			updateActiveUser();
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	private void updateActiveUser() {
		String[] currentUsers = getUserList();
		System.out.println("-----hfa=----09");
		for(UserModel c : activeUser){
			try {
				c.getClient().ActiveUserList(currentUsers);
				System.out.println(c.getUsername()+"o00000o");
				
			} 
			catch (RemoteException e) {
				System.out.println("Samuer");
				e.printStackTrace();
			}
		}	
	}

	private String[] getUserList(){
		// generate an array of current users
		String[] allUsers = new String[activeUser.size()];
		for(int i = 0; i< allUsers.length; i++){
			allUsers[i] = activeUser.get(i).getUsername();
			System.out.println(allUsers[i]+"moi");
		}
		return allUsers;
	}

	@Override
	public boolean isUserUnique(String name) throws RemoteException {
		// TODO Auto-generated method stub
		boolean isUnique = true;
		for(UserModel us : activeUser )
		{
			String uName = name.toLowerCase();
			if(us.getUsername().toLowerCase().equals(uName))
			{
				isUnique = false;
				break;
			}
		}
		return isUnique;
	}
	
}

