package ChatRoomServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ChatRoomClient.IClient;

import java.util.ArrayList;
import java.util.List;



public class Server extends UnicastRemoteObject implements IServer {
	private List<UserModel> activeUser = new ArrayList<UserModel>();;
	public Server() throws RemoteException {
		// TODO Auto-generated constructor stub
	}
	private DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
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
			System.out.println(" ");
			System.out.println("-----------Server is Running-------------");
			
			
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
			activeUser.add(new UserModel(name,cli));
			System.out.println(cli);
			System.out.println(name+ "Joined at : "+ myFormatObj.format(LocalDateTime.now()));
			updateActiveUser();
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	private void updateActiveUser() {
		String[] currentUsers = getUserList();
		System.out.println("---------------");
		for(UserModel c : activeUser){
			try {
				c.getClient().ActiveUserList(currentUsers);	
			} 
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}	
	}

	private String[] getUserList(){
		// generate an array of current users
		String[] allUsers = new String[activeUser.size()];
		System.out.println("**Chat Members**");
		for(int i = 0; i< allUsers.length; i++){
			allUsers[i] = activeUser.get(i).getUsername();
			System.out.println(" : "+ allUsers[i]);
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

	@Override
	public void leaveUser(String name) throws RemoteException {
		// TODO Auto-generated method stub
		for(UserModel us: activeUser )
		{
			if(us.getUsername().equals(name))
			{
				System.out.println("-- "+ us.getUsername() + " Left The Chat Room at " + myFormatObj.format(LocalDateTime.now()));
				activeUser.remove(us);
				sendLeaveNote(us.getUsername());
				updateActiveUser();
				break;
			}
		}
		
	}
	
	public void sendLeaveNote(String nameOf) throws RemoteException {
		for(UserModel us: activeUser )
		{
			us.getClient().LeaveNote("[ChatRoom Server] " + nameOf + " Left the Chat room \n");
		}
	}
	
	@Override
	public void chat(String sender, String message) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("ChatLog++ : " + sender.toUpperCase() + " :  " + message );
		for(UserModel us : activeUser)
		{
			us.getClient().ReceivedMessage(sender.toUpperCase() + " :  " + message + "\n");
		}
		
	}
	
}

