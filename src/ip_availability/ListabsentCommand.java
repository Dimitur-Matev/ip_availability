package ip_availability;

public class ListabsentCommand {
	
	private ClientHandler client;
	
	public ListabsentCommand(ClientHandler client){
		this.client = client;
	}

	public String Listabsent(){
		
		if(this.client.getUser() !=null
				&& this.client.getUser().getLoggedIn()){
			
			String result = new String();
			Server server = this.client.getServer();
			for(String list : server.getUsersToLogoutCount()){
				result += ":" + list ;
			}
			return "ok" + result;
			
		}else if(this.client.getUser() !=null
				&& !this.client.getUser().getLoggedIn()){
			
			return "error:notlogged";
		}
		return "error:notlogged";
	}
}
