package ip_availability;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	private static final String COMMAND_STOP_SERVER = "shutdown";

	private final Socket socket;
	private final Server server;
	public User user;

	public ClientHandler(Server server, Socket socket) {
		this.socket = socket;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			CommandsHandler command = new CommandsHandler();
			final PrintStream out = new PrintStream(socket.getOutputStream());
			final Scanner scanner = new Scanner(socket.getInputStream());
			String result = new String();

			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				result = command.execute(line,this);
				if (COMMAND_STOP_SERVER.equals(result)) {
					server.stopServer();
					break;
				}
				out.println(result);
			}

			scanner.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			server.onClientStopped(this);
 		}
	}

	public void stopClient() throws IOException {
		socket.close();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Server getServer(){
		return this.server;
	}
}