/**
 * 
 */
package com.gullapu.savtrac.ws;


/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
public class ConnectionImpl extends Connection {

	@Override
	public <T> T get(final Class<T> returnType, final String path, final Object... params) {
		final String requestURL = getServiceURL() + path;
		System.out.println("URL = " + requestURL);
		return getRestTemplate().getForObject(requestURL, returnType, params);
	}

	@Override
	public <T> void post(final Class<T> returnType, final String path, final Object object,
		final Object... params) {
		final String requestURL = getServiceURL() + path;
		System.out.println("URL = " + requestURL);
		System.out.println("Object = " + object);
		getRestTemplate().postForObject(requestURL, object, returnType);
	}

	@Override
	public void delete(final String path, final Object... params) {
		final String requestURL = getServiceURL() + path;
		System.out.println("URL = " + requestURL);
		getRestTemplate().delete(requestURL, params);
	}
}
