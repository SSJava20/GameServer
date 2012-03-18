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
	
	public ArrayList<ServerThread> getFreeserverThreads() {
		ArrayList<FreePlayer> freeList = new ArrayList<FreePlayer>();
        for(int i = 0; i<serverThreads.size(); i++)
        {
            if((serverThreads.get(i).getMyGame() == null) && (serverThreads.get(i) != formTo))
                freeList.add(new FreePlayer(i, serverThreads.get(i).getName()));
        }
        return freeList;
	}
	
	
}
