package ip_availability;

import java.util.Map;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class CommandsHandler {


	//final static Map<String, User> user = new HashMap<String, User>();
	
	
	public boolean Authenticate(){
		// implementation code
		return false;
	}
	

	public String execute(String command, ClientHandler client) throws IOException {

		final String[] split = command.split(":");			
		Server server = client.getServer();
		
		if ("login".equals(split[0]) && server.getClientsMap().containsKey(split[1]))
			return (new LoginCommand(client, split[1], server.getClientsMap().get(split[1])).Login());
		else if ("login".equals(split[0]) && !server.getClientsMap().containsKey(split[1])) 
				return (new LoginCommand(client, split[1], null)).Login();
		else if ("logout".equals(split[0])) return (new LogoutCommand(client)).Logout();
		else if ("shutdown".equals(split[0])) return (new ShutdownCommand(client)).Shutdown();
		else if ("info".equals(split[0])) return (new InfoCommand(client, server.getClientsMap().get(split[1]),split[1])).Info();
		else if ("listavailable".equals(split[0])) return (new ListavailableCommand(client)).Listavailable();
		else if ("listabsent".equals(split[0])) return  (new ListabsentCommand(client)).Listabsent();
		
		return "error:unknowncommand";	
	}

}
