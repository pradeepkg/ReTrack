/**
 * 
 */
package com.gullapu.savtrac.services.docs;

import com.gullapu.savtrac.pojo.Document;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
public interface DocumentService {

	Document getDocument(final int documentID);

	void saveDocument(final Document document);

	void deleteDocument(final int documentID);
}
