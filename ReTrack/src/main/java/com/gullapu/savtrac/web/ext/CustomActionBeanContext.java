package com.gullapu.savtrac.web.ext;

import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * <p>
 * The custom Stripes action bean context to provide utilities to store and
 * retrieve objects to and from the session.
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public class CustomActionBeanContext extends ActionBeanContext {

	/**
	 * <p>
	 * The method sets the given object in the session.
	 * </p>
	 * 
	 * @param key
	 *            the session key.
	 * @param value
	 *            the object to be stored in the session.
	 */
	protected void setCurrent(final String key, final Object value) {
		getRequest().getSession().setAttribute(key, value);
	}

	/**
	 * <p>
	 * The method retrieves the object associated with the key from the session.
	 * If the object returned is null, then the value associated with default is
	 * returned.
	 * </p>
	 * 
	 * @param key
	 *            the session key.
	 * @param defaultValue
	 *            the default value.
	 * @return the object corresponding to the key.
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getCurrent(final String key, final T defaultValue) {
		T value = (T) getRequest().getSession().getAttribute(key);
		if (value == null) {
			value = defaultValue;
			setCurrent(key, value);
		}

		return value;
	}

	/**
	 * <p>
	 * The method removes the object associated with the key from the session.
	 * </p>
	 * 
	 * @param key
	 *            the session key.
	 * @param defaultValue
	 *            the default value.
	 * @return the object corresponding to the key.
	 */
	protected void removeCurrent(final String key) {
		getRequest().getSession().removeAttribute(key);
	}
}
