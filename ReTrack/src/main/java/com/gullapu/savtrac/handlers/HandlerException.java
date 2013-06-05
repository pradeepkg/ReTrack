/**
 * 
 */
package com.gullapu.savtrac.handlers;

import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
public class HandlerException extends Exception {

	private static final long serialVersionUID = -7212465223966821574L;

	public HandlerException(final String string) {
		super(string);
	}

	public HandlerException(NoSuchAlgorithmException e) {
		super(e);
	}

	public HandlerException(String msg, NoSuchAlgorithmException e) {
		super(msg, e);
	}

}
