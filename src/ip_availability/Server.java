package ip_availability;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Server {
	private final int port;
	private boolean running;
	private final List<ClientHandler> clients = Collections.synchronizedList(new LinkedList<ClientHandler>());
	private ServerSocket serverSocket;
	private Map<String,ClientHandler> clientsMap = (Map<String, ClientHandler>) Collections.synchronizedMap(new HashMap<String,ClientHandler>());
	private List<String> usersToLoginCount = Collections.synchronizedList(new LinkedList<String>());
	private List<String> usersToLogoutCount = Collections.synchronizedList(new LinkedList<String>());

	public Server(int port) {
		this.port = port;
	}

	public void startServer() throws IOException {
		final ServerSocket localServerSocket = createServerSocket();

		while(isRunning()) {
			final Socket socket;
			try {
				socket = localServerSocket.accept();
			} catch (SocketException e) {
				if (!localServerSocket.isClosed()) {
					throw e;
				}
				break;
			}
			final ClientHandler client =
				new ClientHandler(this, socket);
			clients.add(client);
			new Thread(client).start();
		}
	}

	private synchronized ServerSocket createServerSocket() throws IOException {
		if (running) {
			throw new IllegalStateException("Already running");
		}

		running = true;
		serverSocket = new ServerSocket(port);
		return serverSocket;
	}
	
	public synchronized boolean isRunning() {
		return running;
	}

	public synchronized void stopServer() throws IOException {
		if (!running) {
			throw new IllegalStateException("Not running");
		}

		running = false;
		serverSocket.close();
		serverSocket = null;
		
		for (ClientHandler next : clients) {
			if(next != null){
				next.stopClient();
			}
		}
	}

	public void onClientStopped(ClientHandler clientHandler) {
		clients.remove(clientHandler);
	}

	public Map<String,ClientHandler> getClientsMap() {
		return clientsMap;
	}

	public void setClientsMap(Map<String,ClientHandler> clientsMap) {
		this.clientsMap = clientsMap;
	}

	public List<String> getUsersToLoginCount() {
		return usersToLoginCount;
	}

	public void setUsersToLoginCount(List<String> usersToLoginCount) {
		this.usersToLoginCount = usersToLoginCount;
	}

	public List<String> getUsersToLogoutCount() {
		return usersToLogoutCount;
	}

	public void setUsersToLogoutCount(List<String> usersToLogoutCount) {
		this.usersToLogoutCount = usersToLogoutCount;
	}
}
