/**
 * 
 */
package com.gullapu.savtrac.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.gullapu.savtrac.services.docs.PDFDocument;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kadambar@rsa.com">Pradeep Kadambar</a>
 */
public class DocumentUtils {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// DocumentUtils.getPDF();
		// Entry entry = getPDFAsHTML();
		// System.out.println(entry);

		File path = new File("C:/Users/kadamp/Google Drive/Rebates/234__TD-9782 (US).pdf");
		URL url = new URL("http://static.highspeedbackbone.net/rebates/TDI-10218%20%28US%29.pdf");
		PDFDocument pdf = new PDFDocument(path);

	}
}
