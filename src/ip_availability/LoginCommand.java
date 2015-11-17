package ip_availability;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class LoginCommand extends CommandsHandler{
	
	private ClientHandler client;
	private String name;
	
	public LoginCommand(ClientHandler client, String name){
		this.client = client;
		this.name = name;
	}
	
	public String Login(){
		if (this.client.getUser() ==null) {
			
			this.client.setUser(this.name);
			Server server = this.client.getServer();
			List<String> newUsersToLoginCount = new LinkedList<String>();
			newUsersToLoginCount = server.getUsersToLoginCount();
			newUsersToLoginCount.add(this.name);
			server.setUsersToLoginCount(newUsersToLoginCount); 
			this.client.user.increaseNumberOfLogins();
			this.client.user.setFrom(new Date());
			
			return "ok";
			
		}else if(!client.getUser().getLoggedIn()){
			
			Server server = this.client.getServer();
			this.client.user.setLoggedIn(true);
			List<String> newUsersToLogoutCount = new LinkedList<String>();
			newUsersToLogoutCount = server.getUsersToLoginCount();
			newUsersToLogoutCount.remove(this.name);
			server.setUsersToLogoutCount(newUsersToLogoutCount);
			this.client.user.increaseNumberOfLogins();
			this.client.user.setFrom(new Date());
			
			return "ok";
		}
		
		return "error:alreadyloggedin";
	}
}
