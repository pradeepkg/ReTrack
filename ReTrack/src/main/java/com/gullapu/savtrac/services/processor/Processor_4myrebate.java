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
public class Processor_4myrebate implements Processor {

	@Override
	public Entry processDocument(Document document) {
		String text  = document.getXML();
		Entry entry = new Entry();
		String[] lines = text.split(System.getProperty("line.separator"));
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			
		}
		return entry;
	}

	

}
