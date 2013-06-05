/**
 * 
 */
package com.gullapu.savtrac.handlers;

import com.gullapu.savtrac.pojo.Document;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kadambar@rsa.com">Pradeep Kadambar</a>
 */
public abstract class AbstractDocumentHandler extends AbstractHandler {

	public AbstractDocumentHandler(String handlerName, String handlerDescriptionKey) {
		super(handlerName, handlerDescriptionKey);
	}

	/**
	 * @see com.gullapu.savtrac.handlers.AbstractHandler#handle(java.lang.Object[])
	 */
	@Override
	public void handle(Object... params) throws HandlerException {
		if (params == null | params.length < 1 || params[0] == null || !(params[0] instanceof Document)) {
			throw new HandlerException("The handler requires a valid Document input.");
		}
		
		final Document document = (Document) params[0];
		handleDocument(document);
	}

	protected abstract void handleDocument(final Document document) throws HandlerException;
}
