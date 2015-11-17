package ip_availability;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class LogoutCommand {
	
	private ClientHandler client;
	
	public LogoutCommand(ClientHandler client){
		this.client = client;
	}

	public String Logout() {
		
		if (this.client.getUser() != null && this.client.getUser().getLoggedIn() == true) {
			
			Server server = this.client.getServer();
			this.client.user.setLoggedIn(false);
			List<String> newUsersToLogoutCount = new LinkedList<String>();
			newUsersToLogoutCount = server.getUsersToLoginCount();
			newUsersToLogoutCount.add(this.client.getUser().getName());
			server.setUsersToLogoutCount(newUsersToLogoutCount);
			this.client.user.setTo(new Date());
			
			return "ok";
		} else
			return "error:notlogged";
	}
}
