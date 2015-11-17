package ip_availability;

public class ShutdownCommand {
	
	private ClientHandler client;
	
	public ShutdownCommand(ClientHandler client){
		this.client = client;
	}

	public String Shutdown(){
		if(this.client.getUser() !=null
		&& this.client.getUser().getLoggedIn()){
	
			return "shutdown";
		}
	
			return "error:notlogged";
	}
}
