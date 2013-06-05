/**
 * 
 */
package com.gullapu.savtrac.ws.docs;

import java.io.StringReader;

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

import com.gullapu.savtrac.pojo.Document;
import com.gullapu.savtrac.services.docs.DocumentService;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
@Controller
@RequestMapping("/docs/ws")
public class DocumentServiceController {

	private static final Logger logger = Logger.getLogger(DocumentServiceController.class);

	@Autowired
	private Jaxb2Marshaller jaxbMarshaller;

	@Autowired
	private DocumentService docService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveDocument(@RequestBody final String requestBody) {
		final Source source = new StreamSource(new StringReader(requestBody));
		logger.info("Received save document request");
		logger.debug("\n" + requestBody);
		final Document document = (Document) jaxbMarshaller.unmarshal(source);
		docService.saveDocument(document);
		return new ModelAndView("documentView", BindingResult.MODEL_KEY_PREFIX + "document", document);
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public ModelAndView getDocument(@PathVariable final int key) {
		System.out.println("Controller GET document : " + key);
		final Document document = docService.getDocument(key);
		return new ModelAndView("documentView", BindingResult.MODEL_KEY_PREFIX + "document", document);
	}
}
