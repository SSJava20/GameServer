package server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import server.games.Game;

public class ServerThread implements Runnable {

	private String name;
	private Socket clientSocket;
	private MainServer server;
	private Game myGame;
	private Thread playerThread;
	char Mark;
	ServerMediator mediator;

	public ServerThread(ServerMediator mediator) {
		this.mediator = mediator;
		this.clientSocket = clientSocket;
		playerThread = new Thread(this);
		playerThread.start();
	}

	public char getMark() {
		return Mark;
	}

	public void setMark(char mark) {
		Mark = mark;
	}

	public void run() {
		Scanner in = null;
		try {
			in = new Scanner(new InputStreamReader(
					clientSocket.getInputStream()));
		} catch (IOException e1) {
			mediator.getServerThreads().remove(this);
			e1.printStackTrace();
		}
		while (true) {
			String getStringCommand = "";
			try {
				getStringCommand = in.nextLine();
				System.out.println("<INFO> "+getStringCommand);
				operateCommand(getStringCommand);
			} catch (Exception e) {
				try {
					clientSocket.close();
					mediator.getServerThreads().remove(this);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
		Thread.currentThread().interrupt();
	}
	
	public void sendCommand() {
		
	}

	public void sendGameState(GameState toSend) {

	}

	private void operateCommand(String getcommand) {

	}

}
