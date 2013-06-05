package com.gullapu.savtrac.services.docs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class PDFDocument implements Document {

	public static final Logger LOGGER = LoggerFactory.getLogger(PDFDocument.class);

	InputStream inputStream;

	public PDFDocument(URL url) throws IOException {
		if (null == url) {
			throw new IllegalArgumentException("URL is a required parameter");
		}

		inputStream = TikaInputStream.get(url);
	}

	public PDFDocument(File file) throws IOException {
		if (null == file) {
			throw new IllegalArgumentException("File is a required parameter");
		}

		inputStream = TikaInputStream.get(file);
	}

	public PDFDocument(InputStream is) throws IOException {
		if (null == is) {
			throw new IllegalArgumentException("InputStream is a required parameter");
		}

		inputStream = TikaInputStream.get(is);
	}

	public String getXML() {
		try {

			// AutoDetect is normally best, unless you know the best parser for
			// the type
			Parser parser = new AutoDetectParser();

			// Handler for indented XHTML
			StringWriter sw = new StringWriter();
			SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
			TransformerHandler handler = factory.newTransformerHandler();
			handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "xml");
			handler.getTransformer().setOutputProperty(OutputKeys.INDENT, "yes");
			handler.setResult(new StreamResult(sw));

			Metadata metadata = new Metadata();
			parser.parse(inputStream, handler, metadata, new ParseContext());
			return sw.toString();
		} catch (TransformerConfigurationException | IOException | SAXException | TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
