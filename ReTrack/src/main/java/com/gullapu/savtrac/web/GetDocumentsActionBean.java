/**
 * 
 */
package com.gullapu.savtrac.web;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

import org.apache.log4j.Logger;

import com.gullapu.savtrac.pojo.Document;
import com.gullapu.savtrac.pojo.Entry;
import com.gullapu.savtrac.web.ext.AbstractActionBean;

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public class GetDocumentsActionBean extends AbstractActionBean {

	private static final Logger LOGGER = Logger.getLogger(GetDocumentsActionBean.class);

	private int entryID;
	
	private int documentID;

	private List<Document> documents;

	/**
	 * @return the entryID
	 */
	public int getEntryID() {
		return entryID;
	}

	/**
	 * @param entryID
	 *            the entryID to set
	 */
	public void setEntryID(int entryID) {
		this.entryID = entryID;
	}

	/**
	 * @return the documentID
	 */
	public int getDocumentID() {
		return documentID;
	}

	/**
	 * @param documentID the documentID to set
	 */
	public void setDocumentID(int documentID) {
		this.documentID = documentID;
	}

	/**
	 * @return the documents
	 */
	public List<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents
	 *            the documents to set
	 */
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@DefaultHandler
	public Resolution takeToLanding() {
		return new ForwardResolution(Constants.JSP_LANDING);
	}

	public Resolution getDocumentsForEntry() {
		LOGGER.debug(MessageFormat.format("Get documents for entity {0}", entryID));

		Entry entry = persistenceService.getEntry(entryID);
		documents = entry.getDocuments();
		return new ForwardResolution(Constants.JSP_DOCS_LIST);
	}

	public StreamingResolution getDocument() {
		Document document = persistenceService.getDocument(documentID);
		String mimeType = document.getMimeType();

		final byte[] file = document.getData();
		return new StreamingResolution(mimeType) {
			@Override
			protected void stream(HttpServletResponse response) throws Exception {
				response.getOutputStream().write(file);
			}
		};
	}
}
