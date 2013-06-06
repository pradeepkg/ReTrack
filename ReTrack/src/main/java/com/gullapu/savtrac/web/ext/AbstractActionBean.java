/**
 * 
 */
package com.gullapu.savtrac.web.ext;

import java.text.MessageFormat;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.log4j.Logger;

import com.gullapu.savtrac.pojo.User;
import com.gullapu.savtrac.services.db.PersistenceService;
import com.gullapu.savtrac.services.processor.ProcessorService;
import com.gullapu.savtrac.web.Constants;

/**
 * <p>
 * The base action bean class to set and retrieve the current action bean
 * context.
 * </p>
 * 
 * @author <a href="mailto:pradeep.kadambar@rsa.com">Pradeep Kadambar</a>
 * @author <a href="mailto:aaron.stromas@rsa.com">Aaron Stromas</a>
 */
public abstract class AbstractActionBean implements ActionBean {

	private static final Logger LOGGER = Logger.getLogger(AbstractActionBean.class);
	
	@SpringBean
	protected PersistenceService persistenceService;
	
	@SpringBean
	protected ProcessorService processorService;

	/**
	 * The handler for action bean context.
	 */
	private CustomActionBeanContext context;

	/**
	 * @see net.sourceforge.stripes.action.ActionBean#getContext()
	 */
	@Override
	public CustomActionBeanContext getContext() {
		return context;
	}

	/**
	 * @see net.sourceforge.stripes.action.ActionBean#setContext(net.sourceforge.stripes.action.ActionBeanContext)
	 */
	@Override
	public void setContext(final ActionBeanContext context) {
		if (null == context) {
			throw new IllegalArgumentException(MessageFormat.format("Expected context {0}. Received [{1}]",
				CustomActionBeanContext.class, null));
		} else {
			if (CustomActionBeanContext.class.equals(context.getClass())) {
				this.context = (CustomActionBeanContext) context;
			} else {
				throw new IllegalArgumentException(MessageFormat.format("Expected context {0}. Received [{1}]",
					CustomActionBeanContext.class, context));
			}
		}
	}

	protected void setUser(User user) {
		saveInSession(Constants.USER, user);
	}

	protected User getUser() {
		return getFromSession(Constants.USER);
	}
	
	protected <T> void saveInSession(String key, T object) {
		getContext().getRequest().getSession().setAttribute(key, object);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getFromSession(String key){
		return (T) getContext().getRequest().getSession().getAttribute(key);
	}
}
