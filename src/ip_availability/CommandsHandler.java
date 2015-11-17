package ip_availability;

import java.util.Map;
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
	


	public String execute(String command, ClientHandler client) {

		final String[] split = command.split(":");			
		if(!command.contains(":")){
			return "error:unknowncommand";
		}
		if ("login".equals(split[1]) && client.getUser() ==null) {
			user.put(split[0], new User(split[0]));
			usersToLoginCount.add(split[0]); 
			user.get(split[0]).increaseNumberOfLogins();
			user.get(split[0]).setFrom(new Date());
			return "ok";
		}else if("login".equals(split[1]) && !client.getUser().getLoggedIn()){
			user.get(split[0]).setLoggedIn(true);
			usersToLogoutCount.remove(split[0]);
			user.get(split[0]).increaseNumberOfLogins();
			user.get(split[0]).setFrom(new Date());
			return "ok";
		}
		
		if ("logout".equals(split[1])) {
			if (user.get(split[0]) != null && user.get(split[0]).getLoggedIn() == true) {
				user.get(split[0]).setLoggedIn(false);
				usersToLogoutCount.add(split[0]);
				user.get(split[0]).setTo(new Date());
				return "ok";
			} else
				return "error:notlogged";
		}
		
		if ("info".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[2]) !=null
				&& user.get(split[0]).getLoggedIn() == true) {
			
			Integer resultLogins = user.get(split[2]).getNumberOfLogins();
			Boolean resultLoggedIn = user.get(split[2]).getLoggedIn();
			String resultTimeIntervals = user.get(split[2]).getTimeIntervals();
			
			return split[2] + ":" + resultLoggedIn + ":" + resultLogins + resultTimeIntervals;
			
		}else if("info".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[2]) ==null
				&& user.get(split[0]).getLoggedIn() == true){
			
			Integer resultLogins = 0;
			Boolean resultLoggedIn = false;
			
			return split[2] + ":" + resultLoggedIn + ":" + resultLogins;
			
		}else if("info".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[0]).getLoggedIn() == false){
			return "error:notlogged";
		}
		
		if("listavailable".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[0]).getLoggedIn() == true){
			
			String result = new String();
			result = split[0];
			
			for(int i=0; i<usersToLoginCount.size(); i++){
				if(!usersToLoginCount.get(i).equals(split[0])){
					result += ":" + usersToLoginCount.get(i);
				}
			}
			return "ok:" + result;
			
		}else if("listavailable".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[0]).getLoggedIn() == false){
			
			return "error:notlogged";
		}
		
		if("listabsent".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[0]).getLoggedIn()){
			
			String result = new String();
			
			for(String list : usersToLogoutCount){
				result += ":" + list ;
			}
			return "ok" + result;
			
		}else if("listabsent".equals(split[1]) && user.get(split[0]) !=null
				&& !user.get(split[0]).getLoggedIn()){
			
			return "error:notlogged";
		}
		
		if("shutdown".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[0]).getLoggedIn()){
			
			return "shutdown";
			
		}else if("shutdown".equals(split[1]) && user.get(split[0]) !=null
				&& !user.get(split[0]).getLoggedIn()){
			
			return "error:notlogged";
			
		}
		
		return "error:unknowncommand";
	}

}
