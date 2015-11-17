package ip_availability;

import java.io.IOException;

public class CommandsHandler {

	
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
