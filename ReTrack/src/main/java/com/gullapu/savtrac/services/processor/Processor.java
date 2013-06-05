/**
 * 
 */
package com.gullapu.savtrac.services.processor;

import com.gullapu.savtrac.pojo.Entry;
import com.gullapu.savtrac.services.docs.Document;

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public interface Processor {

	Entry processDocument(Document document);
}
