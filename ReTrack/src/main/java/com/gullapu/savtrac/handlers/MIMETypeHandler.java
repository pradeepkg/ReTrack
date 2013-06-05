/**
 * 
 */
package com.gullapu.savtrac.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tika.Tika;

import com.gullapu.savtrac.enums.DocumentOperationStatus;
import com.gullapu.savtrac.pojo.Document;

/**
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
public class MIMETypeHandler extends AbstractDocumentHandler {

	private static final Logger LOGGER = Logger.getLogger(MIMETypeHandler.class);

	private final Tika tika = new Tika();

	private List<String> supportedMIMETypes;

	/**
	 * @return the supportedMIMETypes
	 */
	public List<String> getSupportedMIMETypes() {
		return supportedMIMETypes;
	}

	/**
	 * @param supportedMIMETypes
	 *            the supportedMIMETypes to set
	 */
	public void setSupportedMIMETypes(final List<String> supportedMIMETypes) {
		this.supportedMIMETypes = supportedMIMETypes;
	}

	public MIMETypeHandler(final String handlerName, final String handlerDescriptionKey,
		final List<String> supportedMIMETypes) {
		super(handlerName, handlerDescriptionKey);
		this.supportedMIMETypes = supportedMIMETypes;
	}

	@Override
	protected void handleDocument(Document document) throws HandlerException {
		if (!isSupportedDocumentType(document)) {
			document.setOperationStatus(DocumentOperationStatus.MIME_NOT_SUPPORTED);
			LOGGER.info("Save document >> Failed : Document format not supported");
			throw new HandlerException("The document MIME type is not supported.");
		}
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		final MIMETypeHandler utils = new MIMETypeHandler("", "", new ArrayList<String>() {

			private static final long serialVersionUID = 1865587273969388318L;

			{
				add("application/pdf");
				add("image/gif");
				add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				add("text/plain");
			}
		});
		final String DOC_UPLOAD_DIR = "Documents/Download/";
		final File[] dir = new File(DOC_UPLOAD_DIR).listFiles();
		for (final File file : dir) {
			final byte[] fileContent = new byte[(int) file.length()];

			final Document doc = new Document();
			FileInputStream fis = null;

			try {
				fis = new FileInputStream(file);
				doc.setName(file.getName());
				doc.setFileName(file.getName());

				final int size = fis.read(fileContent);
				doc.setSize(size);
				fis.close();

				doc.setData(fileContent);
			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				if (null != fis) {
					try {
						fis.close();
					} catch (final IOException e) {
						// Ignore
					}
				}
			}

			utils.isSupportedDocumentType(doc);
		}
	}

	private boolean isSupportedDocumentType(final Document document) {
		final String mimeType = tika.detect(document.getData());
		final boolean isSupported = supportedMIMETypes.contains(mimeType);
		if (isSupported) {
			System.out.println("MIME = " + mimeType);
		}

		return isSupported;
	}
}
