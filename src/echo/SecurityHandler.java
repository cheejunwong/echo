package echo;

import java.net.Socket;

public class SecurityHandler extends ProxyHandler {
	private static UserTable users = new UserTable();
	private Boolean loggedIn = false;

	// constructor that calls super
	public SecurityHandler() {
		super();
	}

	public SecurityHandler(Socket s) {
		super(s);
	}

	protected String response(String request) throws Exception {
		
		String[] requestArray = request.split(" ");
		// 1. if request = "new user password" call users.newUser and terminate session
		// (Thread.stop?)
		if (requestArray[0].equalsIgnoreCase("new")) {
			String result = users.newUser(requestArray[1], requestArray[2]);
			active = false;
			shutDown();
			return result + " Type \"quit\" for a new session to login";
			
		}

		// 2. if request = "login user password" if legit loggedIn = true else stop
		if (requestArray[0].equalsIgnoreCase("login")) {
			
			String password = users.getPassword(requestArray[1]);
			
			if ((password != null) && requestArray[2].equals(password)) {
				loggedIn = true;
				return "login successful";
				
			} else {
				return "unrecognized user/password try again";
			}
		}

		// 3. else if loggedIn return super.response(request)
		if (loggedIn && active) {
			return super.response(request);
		}
		
		return "Invalid command";
	}

}
