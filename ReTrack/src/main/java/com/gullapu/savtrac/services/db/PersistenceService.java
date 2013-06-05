/**
 * 
 */
package com.gullapu.savtrac.services.db;

import java.util.List;

import com.gullapu.savtrac.pojo.Document;
import com.gullapu.savtrac.pojo.Entry;
import com.gullapu.savtrac.pojo.Processor;
import com.gullapu.savtrac.pojo.Product;
import com.gullapu.savtrac.pojo.User;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
public interface PersistenceService {

	User getUser(final String userID);

	User deleteUser(final User user);

	User saveUser(final User user);

	Document getDocument(final int documentID);

	Document saveDocument(final Document document);
	
	Entry saveEntry(final Entry entry);
	
	Product saveProduct(final Product product);
	
	Processor saveProcessor(final Processor processor);
	
	List<Entry> getEntries(final String userID);
	
	Entry getEntry(final int entryID);
	
	List<Processor> getProcessors();
	
	Processor getProcessor(final int processorID);
}
