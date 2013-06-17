/**
 * 
 */
package com.gullapu.savtrac.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.tag.BeanFirstPopulationStrategy;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.gullapu.savtrac.pojo.Document;
import com.gullapu.savtrac.pojo.Entry;
import com.gullapu.savtrac.pojo.Processor;
import com.gullapu.savtrac.pojo.Product;
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
public class CreateActionBean extends AbstractActionBean {

	private static final Logger LOGGER = Logger.getLogger(CreateActionBean.class);

//	@ValidateNestedProperties({ @Validate(field = "name", required = true, minlength = 5, maxlength = 50),
//		@Validate(field = "description", required = true, minlength = 5, maxlength = 1024),
//		@Validate(field = "rebateAmount", required = true) })
	private Entry entry;

	private FileBean[] attachments = new FileBean[5];

	private int entryID;

	private FileBean rebateForm;

	List<Processor> processors;

	private int processorID;

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
	 * @return the rebateForm
	 */
	public FileBean getRebateForm() {
		return rebateForm;
	}

	/**
	 * @param rebateForm
	 *            the rebateForm to set
	 */
	public void setRebateForm(FileBean rebateForm) {
		this.rebateForm = rebateForm;
	}

	/**
	 * @return the processors
	 */
	public List<Processor> getProcessors() {
		return processors;
	}

	/**
	 * @param processors
	 *            the processors to set
	 */
	public void setProcessors(List<Processor> processors) {
		this.processors = processors;
	}

	/**
	 * @return the processorID
	 */
	public int getProcessorID() {
		return processorID;
	}

	/**
	 * @param processorID
	 *            the processorID to set
	 */
	public void setProcessorID(int processorID) {
		this.processorID = processorID;
	}

	@DefaultHandler
	@DontValidate
	public Resolution showForm() {
		LOGGER.debug("Forward request to showForm_1 page - Exiting");
		User user = persistenceService.getUser("pgkadam");
		if ("NOT_FOUND".equals(user.getUserStatus())) {
			user.setUserID("pgkadam");
			user.setfName("Pradeep");
			user.setlName("Kadambar");
			user.setEnabled(1);
			user.setUserStatus("VALID");
			persistenceService.saveUser(user);
		}

		entry = new Entry();

		saveInSession(Constants.USER, user);

		return new ForwardResolution(Constants.JSP_CREATE);
	}

	public Resolution processForm() {
		entry = processorService.parseURL(entry.getRebateLink());
		saveInSession(Constants.PRODUCT, entry.getProduct());
		return new ForwardResolution(Constants.JSP_CREATE_ENTRY);
	}

	@HandlesEvent("createEntry")
	public Resolution createEntry() {
		try {
			LOGGER.debug("Entry name = " + entry.getName());
			LOGGER.debug("Entry description = " + entry.getDescription());
			LOGGER.debug("Entry start date = " + entry.getStartDate());
			LOGGER.debug("Entry end date = " + entry.getEndDate());
			List<Document> documents = new ArrayList<Document>();

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

			entry.setDocuments(documents);

			User user = getUser();
			entry.setUser(user);
			entry.changeStatus(Status.VALID);

			entry = persistenceService.saveEntry(entry);
			entry.setProduct((Product) getFromSession(Constants.PRODUCT));
			LOGGER.debug("Entry saved - Exiting - " + entry);
			return new ForwardResolution(Constants.JSP_CREATE_PRODUCT).addParameter("entryID", entry.getId());
		} catch (IOException e) {
			LOGGER.error("Failed to save entry", e);
		}

		return new ForwardResolution(Constants.JSP_ERROR);
	}

	@HandlesEvent("createProduct")
	public Resolution createProduct() {
		LOGGER.debug("Forward request to showForm_2 page - Exiting");
		Entry dbEntry = persistenceService.getEntry(entryID);

		Product product = entry.getProduct();
		entry = dbEntry;
		entry.setProduct(product);
		entry.changeStatus(Status.VALID);

		Entry response = persistenceService.saveEntry(entry);
		LOGGER.debug("Entry saved - Exiting - " + response);

		processors = persistenceService.getProcessors();

		return new ForwardResolution(Constants.JSP_CREATE_PROCESSOR).addParameter("entryID", entry.getId());
	}

	@HandlesEvent("createProcessor")
	public Resolution createProcessor() {
		LOGGER.debug("Forward request to showForm_3 page - Exiting");
		Entry dbEntry = persistenceService.getEntry(entryID);

		Processor processor = null;

		if (processorID > 0) {
			processor = persistenceService.getProcessor(processorID);
		} else {
			processor = entry.getProcessor();
		}

		entry = dbEntry;
		entry.setProcessor(processor);
		entry.changeStatus(Status.VALID);

		Entry response = persistenceService.saveEntry(entry);
		LOGGER.debug("Entry saved - Exiting - " + response);

		return new ForwardResolution(Constants.JSP_SUCCESS);
	}
}
