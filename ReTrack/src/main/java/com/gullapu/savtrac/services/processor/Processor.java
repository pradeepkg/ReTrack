/**
 * 
 */
package com.gullapu.savtrac.services.processor;

import com.gullapu.savtrac.pojo.Entry;

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public abstract class Processor {
	
	protected String host;
	
	private String name;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	public abstract Entry parseEntry(String url);

	/**
	 * @param url
	 */
	public Processor(String name, String host) {
		this.name = name;
		this.host = host;
	}
}
