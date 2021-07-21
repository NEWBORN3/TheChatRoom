package ChatRoomServer;

import ChatRoomClient.IClient;

public class UserModel {
	
	private String _userName;
	private IClient _cli;
	public UserModel(String userName, IClient cli) {
		// TODO Auto-generated constructor stub
		this._userName = userName;
		this._cli = cli;
	}
	
	public String getUsername() {
		return _userName;
	}
	public IClient getClient() {
		return _cli;
	}

}
