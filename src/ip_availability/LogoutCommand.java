package ip_availability;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class LogoutCommand {
	
	private ClientHandler client;
	
	public LogoutCommand(ClientHandler client){
		this.client = client;
	}

	public String Logout() {
		
		if (this.client.getUser() != null && this.client.getUser().getLoggedIn() == true) {
			
			Server server = this.client.getServer();
			this.client.user.setLoggedIn(false);
			List<String> newUsersToLogoutCount = server.getUsersToLoginCount();
			newUsersToLogoutCount.add(this.client.getUser().getName());
			this.client.user.setTo(new Date());
			Map<String,ClientHandler> newClientsMap = server.getClientsMap();
			newClientsMap.remove(this.client.getUser().getName());
			newClientsMap.put(this.client.getUser().getName(),client);
			
			return "ok";
		} else
			return "error:notlogged";
	}
}
