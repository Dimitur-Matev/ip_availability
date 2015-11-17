package ip_availability;

public class InfoCommand {
	
	private ClientHandler client;
	private ClientHandler client2;
	private String name;
	
	public InfoCommand(ClientHandler client,ClientHandler client2, String name){
		this.client = client;
		this.client2 = client2;
		this.name = name;
	}

	public String Info() {

		if (this.client != null && this.client2 != null
				&& this.client.getUser().getLoggedIn() == true) {

			Integer resultLogins = this.client2.getUser().getNumberOfLogins();
			Boolean resultLoggedIn = this.client2.getUser().getLoggedIn();
			String resultTimeIntervals = this.client2.getUser().getTimeIntervals();

			return this.name + ":" + resultLoggedIn + ":" + resultLogins + resultTimeIntervals;

		} else if (this.client != null && this.client2 == null
				&& this.client.getUser().getLoggedIn() == true) {

			Integer resultLogins = 0;
			Boolean resultLoggedIn = false;

			return this.name + ":" + resultLoggedIn + ":" + resultLogins;

		} else if (this.client.getUser() != null && this.client.getUser().getLoggedIn() == false) {
			return "error:notlogged";
		}
		return "error:notlogged";

	}
}
