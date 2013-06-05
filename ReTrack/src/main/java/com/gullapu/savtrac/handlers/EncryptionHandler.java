/**
 * 
 */
package com.gullapu.savtrac.handlers;

import com.gullapu.savtrac.handlers.AbstractDocumentHandler;
import com.gullapu.savtrac.handlers.HandlerException;
import com.gullapu.savtrac.pojo.Document;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
public class EncryptionHandler extends AbstractDocumentHandler {

	public EncryptionHandler(String handlerName, String handlerDescriptionKey) {
		super(handlerName, handlerDescriptionKey);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see com.gullapu.savtrac.handlers.AbstractDocumentHandler#handleDocument(com.gullapu.savtrac.pojo.Document)
	 */
	@Override
	protected void handleDocument(Document document) throws HandlerException {
		// TODO Auto-generated method stub

	}

}
