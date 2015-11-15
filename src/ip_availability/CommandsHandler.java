package ip_availability;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class CommandsHandler {

	final static Map<String, User> user = new HashMap<String, User>();
	final static List<String> usersToLoginCount = new LinkedList<String>();
	final static List<String> usersToLogoutCount = new LinkedList<String>();

	public String execute(String command) {
		final String[] split = command.split(":");			
		if(!command.contains(":")){
			return "error:unknowncommand";
		}
		
		if ("login".equals(split[1]) && user.get(split[0]) ==null) {
			user.put(split[0], new User(split[0]));
			usersToLoginCount.add(split[0]); 
			user.get(split[0]).increaseNumberOfLogins();
			return "ok";
		}else if("login".equals(split[1]) && !user.get(split[0]).getLoggedIn()){
			user.get(split[0]).setLoggedIn(true);
			usersToLogoutCount.remove(split[0]);
			user.get(split[0]).increaseNumberOfLogins();
			return "ok";
		}
		
		if ("logout".equals(split[1])) {
			if (user.get(split[0]) != null && user.get(split[0]).getLoggedIn() == true) {
				user.get(split[0]).setLoggedIn(false);
				usersToLogoutCount.add(split[0]);
				return "ok";
			} else
				return "error:notlogged";
		}
		
		if ("info".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[2]) !=null
				&& user.get(split[0]).getLoggedIn() == true) {
			
			Integer resultLogins = user.get(split[2]).getNumberOfLogins();
			Boolean resultLoggedIn = user.get(split[2]).getLoggedIn();
			
			return split[2] + ":" + resultLoggedIn + ":" + resultLogins;
			
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
		
		if("stopServer".equals(split[1]) && user.get(split[0]) !=null
				&& user.get(split[0]).getLoggedIn()){
			
			return "stopServer";
			
		}else if("stopServer".equals(split[1]) && user.get(split[0]) !=null
				&& !user.get(split[0]).getLoggedIn()){
			
			return "error:notlogged";
			
		}
		
		return "error:unknowncommand";
	}

}
