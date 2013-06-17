/**
 * 
 */
package com.gullapu.savtrac.web;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;

import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.tag.BeanFirstPopulationStrategy;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.gullapu.savtrac.pojo.Document;
import com.gullapu.savtrac.pojo.Entry;
import com.gullapu.savtrac.pojo.User;
import com.gullapu.savtrac.web.Constants.Status;
import com.gullapu.savtrac.web.ext.AbstractActionBean;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
@CustomPopulationStrategy(BeanFirstPopulationStrategy.class)
public class EditActionBean extends AbstractActionBean {

	private static final Logger LOGGER = Logger.getLogger(EditActionBean.class);

	private Entry entry;

	private FileBean[] attachments = new FileBean[5];

	private int entryID;

	private int documentID;

	/**
	 * @return the entry
	 */
	public Entry getEntry() {
		return entry;
	}

	/**
	 * @param entry
	 *            the entry to set
	 */
	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	/**
	 * @return the attachment
	 */
	public FileBean[] getAttachments() {
		return attachments;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachments(FileBean[] attachments) {
		this.attachments = attachments;
	}

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
	 * @param documentID
	 *            the documentID to set
	 */
	public void setDocumentID(int documentID) {
		this.documentID = documentID;
	}

	public Resolution showEditForm() {
		LOGGER.debug(MessageFormat.format("Retrieving entry for entry ID {0}", entryID));
		entry = persistenceService.getEntry(entryID);
		saveInSession(Constants.DOCUMENTS, entry.getDocuments());
		return new ForwardResolution(Constants.JSP_EDIT_ENTRY);
	}

	public Resolution editEntry() {
		try {
			LOGGER.debug("Entry ID = " + entry.getId());
			LOGGER.debug("Entry name = " + entry.getName());
			LOGGER.debug("Entry description = " + entry.getDescription());
			LOGGER.debug("Entry start date = " + entry.getStartDate());
			LOGGER.debug("Entry end date = " + entry.getEndDate());

			List<Document> documents = getFromSession(Constants.DOCUMENTS);

			for (FileBean attachment : attachments) {
				if (null != attachment) {
					LOGGER.debug("Uploaded data size = " + attachment.getSize());
					Document doc = new Document();
					LOGGER.debug("Uploaded data type = " + attachment.getContentType());
					LOGGER.debug("Uploaded file name = " + attachment.getFileName());
					doc.setMimeType(attachment.getContentType());
					doc.setFileName(attachment.getFileName());
					InputStream is = attachment.getInputStream();
					byte[] bytes = IOUtils.toByteArray(is);
					doc.setData(bytes);
					documents.add(doc);
				}
			}

			Entry dbEntry = persistenceService.getEntry(entry.getId());
			dbEntry.setName(entry.getName());
			dbEntry.setDescription(entry.getDescription());
			dbEntry.setStartDate(entry.getStartDate());
			dbEntry.setEndDate(entry.getEndDate());
			dbEntry.setDocuments(documents);

			User user = getUser();
			dbEntry.setUser(user);
			dbEntry.changeStatus(Status.VALID);

			Entry response = persistenceService.saveEntry(dbEntry);
			LOGGER.debug("Entry updated - Exiting - " + response);
			return new ForwardResolution(Constants.JSP_SUCCESS);
		} catch (IOException e) {
			LOGGER.error("Failed to update entry", e);
		}

		return new ForwardResolution(Constants.JSP_ERROR);
	}

	public Resolution deleteAttachment() {
		LOGGER.debug(MessageFormat.format("Retrieving entry for entry ID {0}", entryID));
		List<Document> documents = getFromSession(Constants.DOCUMENTS);
		Document document = new Document();
		document.setId(documentID);
		documents.remove(document);
		entry.setDocuments(documents);
		User user = getFromSession(Constants.USER);
		entry.setUser(user);
		saveInSession(Constants.DOCUMENTS, entry.getDocuments());
		return new ForwardResolution(Constants.JSP_EDIT_ENTRY);
	}

	public Resolution completeEntry() {
		LOGGER.debug(MessageFormat.format("Completing entry for entry ID {0}", entryID));
		entry = persistenceService.getEntry(entryID);

		if (null == entry.getProduct() || entry.getProduct().getStatus().equals(Status.INCOMPLETE)) {
			return new ForwardResolution(Constants.JSP_EDIT_PRODUCT).addParameter("entryID", entry.getId());
		} else if (null == entry.getProcessor() || (entry.getProduct().getStatus().equals(Status.INCOMPLETE))) {
			ForwardResolution resolution = new ForwardResolution(Constants.JSP_EDIT_PROCESSOR);
			resolution.addParameter("entryID", entry.getId());
			if (null != entry.getProcessor()) {
				resolution.addParameter("processorID", entry.getProcessor().getId());
			}

			return resolution;
		}

		return new ForwardResolution(GetEntriesActionBean.class, "getEntries");
	}

	public Resolution editProduct() {
		LOGGER.debug("Product ID = " + entry.getProduct().getId());
		LOGGER.debug("Product Name = " + entry.getProduct().getName());
		LOGGER.debug("Product Price = " + entry.getProduct().getPrice());
		LOGGER.debug("Product UPC = " + entry.getProduct().getUpc());
		Entry dbEntry = persistenceService.getEntry(entryID);
		dbEntry.setProduct(entry.getProduct());
		entry = dbEntry;
		entry.changeStatus(Status.VALID);
		entry = persistenceService.saveEntry(entry);
		LOGGER.debug("Product updated - Exiting - " + entry);
		return new ForwardResolution(EditActionBean.class, "completeEntry");
	}

	public Resolution editProcessor() {
		LOGGER.debug("Processor ID = " + entry.getProcessor().getId());
		LOGGER.debug("Processor Name = " + entry.getProcessor().getName());
		LOGGER.debug("Processor Email = " + entry.getProcessor().getEmail());
		LOGGER.debug("Processor Homepage = " + entry.getProcessor().getHomePage());
		Entry dbEntry = persistenceService.getEntry(entryID);
		dbEntry.setProcessor(entry.getProcessor());
		entry = dbEntry;
		entry.changeStatus(Status.VALID);
		entry = persistenceService.saveEntry(entry);
		LOGGER.debug("Processor updated - Exiting - " + entry);
		return new ForwardResolution(EditActionBean.class, "completeEntry");
	}
}
