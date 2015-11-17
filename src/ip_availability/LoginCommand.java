package ip_availability;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LoginCommand extends CommandsHandler{
	
	private ClientHandler client;
	private ClientHandler oldClient;
	private String name;
	
	public LoginCommand(ClientHandler client, String name, ClientHandler oldClient){
		this.client = client;
		this.name = name;
		this.oldClient = oldClient;
	}
	
	public String Login() throws IOException{
		if (this.client.getUser() ==null && this.oldClient == null) {
		
			this.client.setUser(new User(this.name));
			Server server = this.client.getServer();
			List<String> newUsersToLoginCount = server.getUsersToLoginCount(); 
			newUsersToLoginCount.add(this.name);
			this.client.user.increaseNumberOfLogins();
			this.client.user.setFrom(new Date());
			Map<String,ClientHandler> newClientsMap = server.getClientsMap();
			newClientsMap.put(this.name,client);
			
		}else if(this.oldClient == null && !this.client.getUser().getLoggedIn()){
			
			Server server = this.client.getServer();
			this.client.user.setLoggedIn(true);
			List<String> newUsersToLogoutCount = server.getUsersToLoginCount();
			newUsersToLogoutCount.remove(this.name);
			this.client.user.increaseNumberOfLogins();
			this.client.user.setFrom(new Date());
			Map<String,ClientHandler> newClientsMap = server.getClientsMap();
			newClientsMap.remove(this.name);
			newClientsMap.put(this.name,client);
			
		}else if(this.oldClient != null && this.oldClient.getUser().getLoggedIn()){
			
			(new LogoutCommand(this.oldClient)).Logout();
			this.oldClient.stopClient();
			Server server = this.client.getServer();
			this.client.setUser(new User(this.name));
			this.client.user.setLoggedIn(true);
			List<String> newUsersToLogoutCount = server.getUsersToLoginCount();
			newUsersToLogoutCount.remove(this.name);
			this.client.user.increaseNumberOfLogins();
			this.client.user.setFrom(new Date());
			Map<String,ClientHandler> newClientsMap = server.getClientsMap();
			newClientsMap.remove(this.name);
			newClientsMap.put(this.name,client);
			
		}
		
		return "ok";
	}
}
