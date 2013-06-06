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

	private String url;
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param url
	 */
	public Processor(String url) {
		super();
		this.url = url;
	}

	public abstract Entry parseEntry();
}
