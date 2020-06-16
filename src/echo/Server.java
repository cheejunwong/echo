package echo;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

	protected ServerSocket mySocket;
	protected int myPort;
	public static boolean DEBUG = true;
	protected Class<?> handlerType;

	public Server(int port, String handlerType) {
		try {
			myPort = port;
			mySocket = new ServerSocket(myPort);
			this.handlerType = (Class.forName(handlerType));
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} // catch
	}


	public void listen() {
		while(true) {
			try {
			// accept a connection
				Socket socket = mySocket.accept();
			
			// make handler
				RequestHandler handler = makeHandler(socket);
				
			// start handler in its own thread
				Thread thread = new Thread(handler);
				thread.start();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		} // while 
	}

	public RequestHandler makeHandler(Socket s) {
		
		// handler = a new instance of handlerType
		RequestHandler handler = null;
		try {
		//	    use: try { handlerType.getDeclaredConstructor().newInstance() } catch ...
			handler = (RequestHandler) handlerType.getDeclaredConstructor().newInstance();
		
		// set handler's socket to s
			handler.setSocket(s);
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		// return handler
			return handler;
		
	}



	public static void main(String[] args) {
		int port = 5555;
		String service = "echo.RequestHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}