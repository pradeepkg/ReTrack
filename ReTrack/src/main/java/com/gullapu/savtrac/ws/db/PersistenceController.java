/**
 * 
 */
package com.gullapu.savtrac.ws.db;

import java.io.StringReader;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gullapu.savtrac.handlers.AbstractHandler;
import com.gullapu.savtrac.handlers.HandlerException;
import com.gullapu.savtrac.pojo.Document;
import com.gullapu.savtrac.pojo.User;
import com.gullapu.savtrac.services.db.PersistenceService;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
@Controller
@RequestMapping("/db/ws")
public class PersistenceController {

	private static final Logger logger = Logger.getLogger(PersistenceController.class);

	@Autowired
	private Jaxb2Marshaller jaxbMarshaller;

	@Resource(name = "persistenceService")
	private PersistenceService dbService;

	@Resource(name = "UserHandlers")
	private List<AbstractHandler> userHandlers;

	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable final String key) {
		System.out.println("Controller GET : " + key);
		final User user = dbService.getUser(key);
		return new ModelAndView("userView", BindingResult.MODEL_KEY_PREFIX + "user", user);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteUser(@RequestBody final String requestBody) {
		final Source source = new StreamSource(new StringReader(requestBody));
		logger.info("Received user update request");
		logger.debug("\n" + requestBody);
		final User user = (User) jaxbMarshaller.unmarshal(source);
		dbService.deleteUser(user);
		return new ModelAndView("userView", BindingResult.MODEL_KEY_PREFIX + "user", user);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@RequestBody final String requestBody) {
		final Source source = new StreamSource(new StringReader(requestBody));
		logger.info("Received user update request");
		logger.debug("\n" + requestBody);
		final User user = (User) jaxbMarshaller.unmarshal(source);
		// TODO move to service layer
		processUser(user);
		dbService.saveUser(user);
		return new ModelAndView("userView", BindingResult.MODEL_KEY_PREFIX + "user", user);
	}

	@RequestMapping(value = "/docs/save", method = RequestMethod.POST)
	public ModelAndView saveDocument(@RequestBody final String requestBody) {
		final Source source = new StreamSource(new StringReader(requestBody));
		logger.info("Received save document request");
		logger.debug("\n" + requestBody);
		final Document document = (Document) jaxbMarshaller.unmarshal(source);
		dbService.saveDocument(document);
		return new ModelAndView("userView", BindingResult.MODEL_KEY_PREFIX + "document", document);
	}

	@RequestMapping(value = "/docs/{key}", method = RequestMethod.GET)
	public ModelAndView getDocument(@PathVariable final int key) {
		System.out.println("Controller GET document : " + key);
		final Document document = dbService.getDocument(key);
		return new ModelAndView("userView", BindingResult.MODEL_KEY_PREFIX + "document", document);
	}

	private void processUser(final User user) {
		// Check if the document type is supported
		try {
			for (final AbstractHandler handler : userHandlers) {
				handler.handle(user);
			}
		} catch (final HandlerException e) {
			e.printStackTrace();
		}
	}
}
