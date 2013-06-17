/**
 * 
 */
package com.gullapu.savtrac.web;

import java.text.MessageFormat;
import java.util.Date;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.tag.BeanFirstPopulationStrategy;

import org.apache.log4j.Logger;

import com.gullapu.savtrac.pojo.Entry;
import com.gullapu.savtrac.web.Constants.Status;
import com.gullapu.savtrac.web.ext.AbstractActionBean;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
@CustomPopulationStrategy(BeanFirstPopulationStrategy.class)
public class ProcessActionBean extends AbstractActionBean {

	private static final Logger LOGGER = Logger.getLogger(EditActionBean.class);
	
	private int entryID;
	
	private Date date = new Date();

	/**
	 * @return the entryID
	 */
	public int getEntryID() {
		return entryID;
	}

	/**
	 * @param entryID the entryID to set
	 */
	public void setEntryID(int entryID) {
		this.entryID = entryID;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	@DefaultHandler
	@DontValidate
	public Resolution defaultAction() {
		return new ForwardResolution(Constants.JSP_LANDING);
	}
	
	public Resolution showMailForm() {
		LOGGER.debug(MessageFormat.format("MAILED - entry ID {0}", entryID));
		return new ForwardResolution(Constants.JSP_ENTRY_MAILED).addParameter("entryID", entryID);
	}

	public Resolution entryMailed() {
		LOGGER.debug(MessageFormat.format("MAILED - entry ID {0}", entryID));
		Entry entry = persistenceService.getEntry(entryID);
		entry.setMailedDate(date);
		entry.changeStatus(Status.MAILED);
		persistenceService.saveEntry(entry);
		return new ForwardResolution(GetEntriesActionBean.class, "getEntries");
	}
	
	public Resolution showProcessingForm() {
		LOGGER.debug(MessageFormat.format("PROCESSING - entry ID {0}", entryID));
		return new ForwardResolution(Constants.JSP_ENTRY_PROCESSING).addParameter("entryID", entryID);
	}
	
	public Resolution entryProcessing() {
		LOGGER.debug(MessageFormat.format("PROCESSING - entry ID {0}", entryID));
		Entry entry = persistenceService.getEntry(entryID);
		entry.setProcessingDate(date);
		entry.changeStatus(Status.PROCESSING);
		persistenceService.saveEntry(entry);
		return new ForwardResolution(GetEntriesActionBean.class, "getEntries");
	}
	
	public Resolution showApprovedForm() {
		LOGGER.debug(MessageFormat.format("APPROVED - entry ID {0}", entryID));
		return new ForwardResolution(Constants.JSP_ENTRY_APPROVED).addParameter("entryID", entryID);
	}
	
	public Resolution entryApproved() {
		LOGGER.debug(MessageFormat.format("APPROVED - entry ID {0}", entryID));
		Entry entry = persistenceService.getEntry(entryID);
		entry.setApprovedDate(date);
		entry.changeStatus(Status.APPROVED);
		persistenceService.saveEntry(entry);
		return new ForwardResolution(GetEntriesActionBean.class, "getEntries");
	}
	
	public Resolution showReceivedForm() {
		LOGGER.debug(MessageFormat.format("RECEIVED - entry ID {0}", entryID));
		return new ForwardResolution(Constants.JSP_ENTRY_RECEIVED).addParameter("entryID", entryID);
	}
	
	public Resolution entryReceived() {
		LOGGER.debug(MessageFormat.format("RECEIVED - entry ID {0}", entryID));
		Entry entry = persistenceService.getEntry(entryID);
		entry.setReceiveDate(date);
		entry.changeStatus(Status.RECEIVED);
		persistenceService.saveEntry(entry);
		return new ForwardResolution(GetEntriesActionBean.class, "getEntries");
	}
	
	public Resolution entryVoid() {
		LOGGER.debug(MessageFormat.format("VOID - entry ID {0}", entryID));
		Entry entry = persistenceService.getEntry(entryID);
		entry.setReceiveDate(date);
		entry.changeStatus(Status.RECEIVED);
		persistenceService.saveEntry(entry);
		return new ForwardResolution(GetEntriesActionBean.class, "getEntries");
	}
	
	public Resolution entryRejected() {
		LOGGER.debug(MessageFormat.format("REJECTED - entry ID {0}", entryID));
		Entry entry = persistenceService.getEntry(entryID);
		entry.changeStatus(Status.REJECTED);
		persistenceService.saveEntry(entry);
		return new ForwardResolution(GetEntriesActionBean.class, "getEntries");
	}
}
