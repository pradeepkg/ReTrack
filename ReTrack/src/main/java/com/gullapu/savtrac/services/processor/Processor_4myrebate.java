/**
 * 
 */
package com.gullapu.savtrac.services.processor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.gullapu.savtrac.pojo.Entry;

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public class Processor_4myrebate extends Processor {

	public Processor_4myrebate(String name, String urlPattern) {
		super(name, urlPattern);
	}

	@Override
	public Entry parseEntry(String urlString) {
		Entry entry = new Entry();

		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			CleanerProperties props = cleaner.getProperties();
			props.setAllowHtmlInsideAttributes(true);
			props.setAllowMultiWordAttributes(true);
			props.setRecognizeUnicodeChars(true);
			props.setOmitComments(true);

			// open a connection to the desired URL
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			TagNode node = cleaner.clean(new InputStreamReader(conn.getInputStream()));
			
			Object[] infoNodes = node.evaluateXPath("//table[@id='rebateDetails']/tbody");
			TagNode info_node = (TagNode) infoNodes[0];
			Object[] infos = info_node.evaluateXPath("//tr");
			for (Object object2 : infos) {
				TagNode infoNode = (TagNode) object2;
				Object[] detailNodes = infoNode.evaluateXPath("//td");

				if (detailNodes.length == 2) {
					String tdTitle = ((TagNode) detailNodes[0]).getText().toString().trim();
					String tdText = ((TagNode) detailNodes[1]).getText().toString().trim();
					System.out.println('[' + tdTitle + " = " + tdText + ']');

					if (tdTitle.indexOf("Details") > -1) {
						entry.setDescription(tdText);
					} else if (tdTitle.indexOf("Starts") > -1) {
						entry.setStartDate(getDate(tdText));
					} else if (tdTitle.indexOf("Expires") > -1) {
						entry.setEndDate(getDate(tdText));
					} else if (tdTitle.indexOf("Amount") > -1) {
						entry.setRebateAmount(Double.parseDouble(tdText.substring(1)));
					}
					System.out.println("--------------------------------");
				}
			}
		} catch (IOException | XPatherException e) {
			e.printStackTrace();
		}
		
		

		return entry;
	}

	private Date getDate(String dateString) {
		try {
			return new SimpleDateFormat("MM / dd / yyyy", Locale.ENGLISH).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static void main(String[] a) {
		Processor_4myrebate pro = new Processor_4myrebate("", "");
		Entry entry = pro.parseEntry("http://www.4myrebate.com/?oc=TD-9827");

		System.out.println(">>  Desc   : " + entry.getDescription());
		System.out.println(">>  Start  : " + entry.getStartDate());
		System.out.println(">>  End    : " + entry.getEndDate());
		System.out.println(">>  Rebate : " + entry.getRebateAmount());
	}

}
