/**
 * 
 */
package com.gullapu.savtrac.handlers;

/**
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
public abstract class AbstractHandler {

	private String handlerName;

	private String handlerDescriptionKey;

	/**
	 * Breaks the validation check chain if 'true'.
	 */
	private boolean breakOnFailure;

	/**
	 * A nor argument constructor.
	 */
	public AbstractHandler() {
		super();
	}

	/**
	 * <p>
	 * The rule constructor that builds the rule.
	 * </p>
	 * 
	 * @param handlerName
	 * @param handlerDescriptionKey
	 */
	public AbstractHandler(final String handlerName, final String handlerDescriptionKey) {
		super();
		this.handlerName = handlerName;
		this.handlerDescriptionKey = handlerDescriptionKey;
	}

	/**
	 * @return the handlerName
	 */
	public String getHandlerName() {
		return handlerName;
	}

	/**
	 * @param handlerName the handlerName to set
	 */
	public void setHandlerName(final String handlerName) {
		this.handlerName = handlerName;
	}

	/**
	 * @return the handlerDescriptionKey
	 */
	public String getHandlerDescriptionKey() {
		return handlerDescriptionKey;
	}

	/**
	 * @param handlerDescriptionKey the handlerDescriptionKey to set
	 */
	public void setHandlerDescriptionKey(final String handlerDescriptionKey) {
		this.handlerDescriptionKey = handlerDescriptionKey;
	}

	/**
	 * @return the breakOnFailure
	 */
	public boolean isBreakOnFailure() {
		return breakOnFailure;
	}

	/**
	 * @param breakOnFailure
	 *            the breakOnFailure to set
	 */
	public void setBreakOnFailure(final boolean breakOnFailure) {
		this.breakOnFailure = breakOnFailure;
	}

	public abstract void handle(Object ... params) throws HandlerException;
}
