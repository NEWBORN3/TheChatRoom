package ChatRoomServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server implements IServer {
	public Server() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//start rmi registry server
		RMIRegistryStart();
		
		
		try {
			//Instantiating the implemented class
			IServer ser = new Server();
		
			Registry reg = LocateRegistry.getRegistry();
			
			//Export the object of the implemented class
			IServer stub = (IServer) UnicastRemoteObject.exportObject(ser,0);
			
			//binding the remote object(stub) in the registry
			reg.bind("The Chat room", stub);
			System.out.println("server is ready");
			
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
	public void registerClient() {
		// TODO Auto-generated method stub
		System.out.println("well");
	}
	
}

