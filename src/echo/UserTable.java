package echo;

import java.util.HashMap;

public class UserTable extends HashMap<String, String> {

	private Thread thread;

	public String getPassword(String user) {
		String result = null;
		try {
			if (this.containsKey(user)) {
				thread = Thread.currentThread();
				result = this.get(user);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String newUser(String user, String password) {
		try {
			if (!this.containsKey(user)) {
				this.put(user, password);
				Thread.sleep(100);
				return "ok, session ended";
			} else {
				return "user existed";
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "Invalid";
	}
}
