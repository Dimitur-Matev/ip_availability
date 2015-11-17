package ip_availability;

public class ListavailableCommand {
	
private ClientHandler client;
	
	public ListavailableCommand(ClientHandler client){
		this.client = client;
	}

	public String Listavailable(){
		
		if (this.client.getUser() != null
				&& this.client.getUser().getLoggedIn() == true) {

			String result = new String();
			result = this.client.getUser().getName();
			String name = this.client.getUser().getName();
			Server server = this.client.getServer();
			for (int i = 0; i < server.getUsersToLoginCount().size(); i++) {
				if (!server.getUsersToLoginCount().get(i).equals(name)) {
					result += ":" + server.getUsersToLoginCount().get(i);
				}
			}
			return "ok:" + result;

		} else if (this.client.getUser() != null
				&& this.client.getUser().getLoggedIn() == false) {

			return "error:notlogged";
		}
		return "error:notlogged";
	}
}
