package server;

import java.util.ArrayList;

public class ServerMediator {
	private ArrayList<ServerThread> serverThreads;

	public ServerMediator() {
		serverThreads = new ArrayList<ServerThread>();
	}

	public ArrayList<ServerThread> getServerThreads() {
		return serverThreads;
	}
	
	
}
