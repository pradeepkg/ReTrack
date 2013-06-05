/**
 * 
 */
package com.gullapu.savtrac.web;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import com.gullapu.savtrac.pojo.Entry;
import com.gullapu.savtrac.pojo.User;
import com.gullapu.savtrac.web.ext.AbstractActionBean;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kadambar@rsa.com">Pradeep Kadambar</a>
 */
public class GetEntriesActionBean extends AbstractActionBean {

	private static final Logger LOGGER = Logger.getLogger(GetEntriesActionBean.class);
	
	private List<Entry> userEntries = new ArrayList<Entry>();

	/**
	 * @return the userEntries
	 */
	public List<Entry> getUserEntries() {
		return userEntries;
	}

	/**
	 * @param userEntries the userEntries to set
	 */
	public void setUserEntries(List<Entry> userEntries) {
		this.userEntries = userEntries;
	}

	@DefaultHandler
	public Resolution takeToLanding(){
		return new ForwardResolution(Constants.JSP_LANDING);
	}
	
	public Resolution getEntries() {
		User user = persistenceService.getUser("pgkadam");
		if ("NOT_FOUND".equals(user.getUserStatus())) {
			user.setUserID("pgkadam");
			user.setfName("Pradeep");
			user.setlName("Kadambar");
			user.setEnabled(1);
			user.setUserStatus("VALID");
			persistenceService.saveUser(user);
		}
		
		setUser(user);
		
		////////////////
		user = getUser();
		LOGGER.debug(MessageFormat.format("Retrieving entries for user {0}", user.getUserID()));
		userEntries = persistenceService.getEntries(user.getUserID());

		return new ForwardResolution(Constants.JSP_LIST);
	}
}
