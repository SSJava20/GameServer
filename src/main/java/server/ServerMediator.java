package server;

import java.util.ArrayList;
import java.util.HashMap;

import server.games.Game;

public class ServerMediator {
	private ArrayList<ServerThread> serverThreads;
	private ArrayList<Game> games;
	private HashMap<ServerThread, ServerThread> gameRequests;

	public ServerMediator() {
		serverThreads = new ArrayList<ServerThread>();
		gameRequests = new HashMap<ServerThread, ServerThread>();
	}

	public ArrayList<ServerThread> getServerThreads() {
		return serverThreads;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public HashMap<ServerThread, ServerThread> getGameRequests() {
		return gameRequests;
	}

}
