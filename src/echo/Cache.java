package echo;

import java.util.HashMap;

public class Cache extends HashMap<String, String> {
	private Thread thread;
	
	public String search(String request) {
		String result = null;
		try {
			if (this.containsKey(request)) {
				thread = Thread.currentThread();
				result = this.get(request);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public void update(String request, String response) {
		this.put(request, response);

	}
}
