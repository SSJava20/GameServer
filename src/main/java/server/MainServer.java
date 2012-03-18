package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainServer {

	private InetAddress ipAddress;
	private int port;
	private ArrayList<ServerThread> serverThreads;
	private ServerSocket mServerSocket;

	public MainServer(InetAddress ipAddress, int port) throws IOException {
		this.ipAddress = ipAddress;
		this.port = port;
		serverThreads = new ArrayList<ServerThread>();
		try {
			mServerSocket = new ServerSocket(port, 0, ipAddress);
			System.out.println("<INFO> Server started at "+ipAddress+":"+port);
			while(true)
            {
                Socket nSocket = mServerSocket.accept();
                System.out.println("<INFO> New client connected");
                //serverThreads.add(new ServerThread(this, nSocket));
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			mServerSocket.close();
		}

	}

	public static void main(String[] args) {
		try {
			new MainServer(InetAddress.getByName("127.0.0.1"), 3224);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
