/**
 * 
 */
package com.gullapu.savtrac.ws;

import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kadambar@rsa.com">Pradeep Kadambar</a>
 */
public abstract class Connection {

	private String serviceURL;

	private RestTemplate restTemplate;

	/**
	 * @return the serviceURL
	 */
	public String getServiceURL() {
		return serviceURL;
	}

	/**
	 * @param serviceURL
	 *            the serviceURL to set
	 */
	public void setServiceURL(final String serviceURL) {
		this.serviceURL = serviceURL;
	}

	/**
	 * @return the restTemplate
	 */
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	/**
	 * @param restTemplate
	 *            the restTemplate to set
	 */
	public void setRestTemplate(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public abstract <T> T get(Class<T> returnType, final String path, Object... params);

	public abstract <T> void post(Class<T> returnType, final String path, final Object object, Object... params);

	public abstract void delete(String path, Object... params);
}
