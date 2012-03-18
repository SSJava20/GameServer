package server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import server.games.Game;

public class ServerThread implements Runnable {

	private Thread playerThread;
	private Socket clientSocket;
	private ServerMediator mediator;
	private Game currentGame;
	
	public ServerThread(ServerMediator mediator) {
		this.clientSocket = clientSocket;
		this.mediator = mediator;
		playerThread = new Thread(this);
		playerThread.start();
	}
	
	private void operateCommand(String command) {
		
	}

	public void run() {
		Scanner in = null;
		try {
			in = new Scanner(new InputStreamReader(
					clientSocket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			String getStringCommand = "";
			try {
				getStringCommand = in.nextLine();
				System.out.println(getStringCommand);
				operateCommand(getStringCommand);
			} catch (Exception e) {
				try {
					clientSocket.close();
					// server.serverThreads.remove(this);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			}
			System.out.println(getStringCommand);
		}
		Thread.currentThread().interrupt();
	}

}
