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
//		if(!command.contains(":")){
//			return "error:unknowncommand";
//		}
		
		String result;
		Server server = client.getServer();
		
		
		if ("login".equals(split[0]) && server.getClientsMap().containsKey(split[1]))
			return result = (new LoginCommand(client, split[1], server.getClientsMap().get(split[1])).Login());
		else if ("login".equals(split[0]) && !server.getClientsMap().containsKey(split[1])) 
				return result = (new LoginCommand(client, split[1], null)).Login();
		else if ("logout".equals(split[0])) return result = (new LogoutCommand(client)).Logout();
		else if ("shutdown".equals(split[0])) return result = (new ShutdownCommand(client)).Shutdown();
		else if ("info".equals(split[0])) return result = (new InfoCommand(client, server.getClientsMap().get(split[1]),split[1])).Info();
//		else if ("listavailable".equals(split[0])) result = ListAvailable(line);
//		else if ("listabsent".equals(split[0])) result = ListAbsent(line);
		else result = "error:unknowncommand";
		return result;
		
		

	
		
//		if ("info".equals(split[1]) && user.get(split[0]) !=null
//				&& user.get(split[2]) !=null
//				&& user.get(split[0]).getLoggedIn() == true) {
//			
//			Integer resultLogins = user.get(split[2]).getNumberOfLogins();
//			Boolean resultLoggedIn = user.get(split[2]).getLoggedIn();
//			String resultTimeIntervals = user.get(split[2]).getTimeIntervals();
//			
//			return split[2] + ":" + resultLoggedIn + ":" + resultLogins + resultTimeIntervals;
//			
//		}else if("info".equals(split[1]) && user.get(split[0]) !=null
//				&& user.get(split[2]) ==null
//				&& user.get(split[0]).getLoggedIn() == true){
//			
//			Integer resultLogins = 0;
//			Boolean resultLoggedIn = false;
//			
//			return split[2] + ":" + resultLoggedIn + ":" + resultLogins;
//			
//		}else if("info".equals(split[1]) && user.get(split[0]) !=null
//				&& user.get(split[0]).getLoggedIn() == false){
//			return "error:notlogged";
//		}
		
//		if("listavailable".equals(split[1]) && user.get(split[0]) !=null
//				&& user.get(split[0]).getLoggedIn() == true){
//			
//			String result = new String();
//			result = split[0];
//			
//			for(int i=0; i<usersToLoginCount.size(); i++){
//				if(!usersToLoginCount.get(i).equals(split[0])){
//					result += ":" + usersToLoginCount.get(i);
//				}
//			}
//			return "ok:" + result;
//			
//		}else if("listavailable".equals(split[1]) && user.get(split[0]) !=null
//				&& user.get(split[0]).getLoggedIn() == false){
//			
//			return "error:notlogged";
//		}
//		
//		if("listabsent".equals(split[1]) && user.get(split[0]) !=null
//				&& user.get(split[0]).getLoggedIn()){
//			
//			String result = new String();
//			
//			for(String list : usersToLogoutCount){
//				result += ":" + list ;
//			}
//			return "ok" + result;
//			
//		}else if("listabsent".equals(split[1]) && user.get(split[0]) !=null
//				&& !user.get(split[0]).getLoggedIn()){
//			
//			return "error:notlogged";
//		}
//		
//		if("shutdown".equals(split[1]) && user.get(split[0]) !=null
//				&& user.get(split[0]).getLoggedIn()){
//			
//			return "shutdown";
//			
//		}else if("shutdown".equals(split[1]) && user.get(split[0]) !=null
//				&& !user.get(split[0]).getLoggedIn()){
//			
//			return "error:notlogged";
//			
//		}
//		
	}

}
