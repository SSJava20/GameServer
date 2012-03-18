package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainServer {
	private static int DEFAULT_PORT = 3224;

	private InetAddress ipAddress;
	private int port;
	private ArrayList<ServerThread> serverThreads;
	private ServerSocket mServerSocket;
	private ServerMediator mediator;

	public MainServer(InetAddress ipAddress, int port) throws IOException {
		this.ipAddress = ipAddress;
		this.port = port;
		serverThreads = new ArrayList<ServerThread>();
		try {
			mServerSocket = new ServerSocket(port, 0, ipAddress);
			System.out.println(Messages.getString("MainServer.0")+ipAddress+Messages.getString("MainServer.1")+port); //$NON-NLS-1$ //$NON-NLS-2$
			while(true)
            {
                Socket nSocket = mServerSocket.accept();
                System.out.println(Messages.getString("MainServer.2")); //$NON-NLS-1$
                //serverThreads.add(new ServerThread(this, nSocket));
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			mServerSocket.close();
		}

	}

	public static void main(String[] args) {
		int port = 3224;
		String ip = Messages.getString("MainServer.3"); //$NON-NLS-1$
		if (args.length>1) {
			port = Integer.parseInt(args[1]);
			ip = args[0];
		}
		try {
			new MainServer(InetAddress.getByName(ip), port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
