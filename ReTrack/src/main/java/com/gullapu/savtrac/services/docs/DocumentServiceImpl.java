/**
 * 
 */
package com.gullapu.savtrac.services.docs;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gullapu.savtrac.enums.DocumentOperationStatus;
import com.gullapu.savtrac.handlers.AbstractHandler;
import com.gullapu.savtrac.handlers.HandlerException;
import com.gullapu.savtrac.pojo.Document;
import com.gullapu.savtrac.ws.Connection;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
@Service("docPersistenceService")
public class DocumentServiceImpl implements DocumentService {

	private static final Logger LOGGER = Logger
		.getLogger(DocumentServiceImpl.class);

	private static final String DOC_BASE_URL = "docs/";
	private static final String GET_DOCUMENT = DOC_BASE_URL + "{param}";
	private static final String SAVE_DOCUMENT = DOC_BASE_URL + "save";
	private static final String DELETE_DOCUMENT = DOC_BASE_URL + "delete";

	protected static Logger logger = Logger
		.getLogger(DocumentServiceImpl.class);

	@Resource(name = "webServiceConnection")
	private Connection connection;

	@Resource(name = "documentHandlers")
	private List<AbstractHandler> documentHandlers;

	public Document getDocument(final int documentID) {
		LOGGER.info("Get document >> " + documentID);
		return connection.get(Document.class, GET_DOCUMENT, documentID);
	}

	public void saveDocument(final Document document) {
		LOGGER.info("Save document >> " + document.getFileName());
		document.setOperationStatus(DocumentOperationStatus.FAILED);

		// Check if the document type is supported
		try {
			for (final AbstractHandler handler : documentHandlers) {
				handler.handle(document);
			}
			connection.post(Document.class, SAVE_DOCUMENT, document);
			LOGGER.info("Save document >> Success");
			document.setOperationStatus(DocumentOperationStatus.SUCCESS);
			return;
		} catch (final HandlerException e) {
			e.printStackTrace();
		}
	}

	public void deleteDocument(final int documentID) {
		connection.delete(DELETE_DOCUMENT, documentID);
	}

}
