package echo;

import java.net.Socket;

public class CacheHandler extends ProxyHandler {
	private static Cache cache = new Cache();

	// add constructor that calls super
	public CacheHandler() {
		super();
	}

	public CacheHandler(Socket s) {
		super(s);
	}

	public String response(String request) throws Exception {
		String result;
		// 1. cache.search(request)
		if (cache.search(request) != null) {
			result = cache.search(request);

		}
		// 2. else result = super.response(request)
		else {
			result = super.response(request);
			// 3. cache.update(request, result)
			cache.update(request, result);

		}
		// 4. return result
		return result;
	}
}
