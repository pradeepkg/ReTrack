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
import com.gullapu.savtrac.pojo.Product;

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public class Processor_RebateAccess extends Processor {

	public Processor_RebateAccess(String url) {
		super(url);
	}

	@Override
	public Entry parseEntry() {
		Entry entry = new Entry();

		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			CleanerProperties props = cleaner.getProperties();
			props.setAllowHtmlInsideAttributes(true);
			props.setAllowMultiWordAttributes(true);
			props.setRecognizeUnicodeChars(true);
			props.setOmitComments(true);

			// open a connection to the desired URL
			URL url = new URL(getUrl());
			URLConnection conn = url.openConnection();

			TagNode node = cleaner.clean(new InputStreamReader(conn.getInputStream()));
			
			StringBuffer title = (StringBuffer) node.evaluateXPath("//div[@id='mainbody']/p[@class='title']/text()")[0];
			entry.setName(title.toString());
	
			String[] tokens = entry.getName().split("-");
			entry.setEndDate(getDate(tokens[tokens.length - 1]));
			entry.setStartDate(getDate(tokens[tokens.length - 2]));
			
			
			TagNode descriptionNode = (TagNode) node.evaluateXPath("//div[@id='mainbody']/p[2]")[0];
			entry.setDescription(descriptionNode.getText().toString().trim());

			Product product = new Product();
			Object[] productInfo = node.evaluateXPath("//div[@class='productlist']/table/tbody/tr[2]/td");
			
			String name = ((TagNode) productInfo[0]).getText().toString().trim();
			String upc = ((TagNode)productInfo[1]).getText().toString().trim();
			product.setName(name);
			product.setUpc(upc);
			
			String rebateAmount = ((TagNode)productInfo[2]).getText().toString().trim();
			entry.setRebateAmount(Double.parseDouble(rebateAmount.substring(1)));
			entry.setProduct(product);
		} catch (IOException | XPatherException e) {
			e.printStackTrace();
		}
		
		

		return entry;
	}
	
	private Date getDate(String dateString) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static void main(String[] a) {
		Processor_RebateAccess pro = new Processor_RebateAccess("http://newegg.rebateaccess.com/?p=48849");
		Entry entry = pro.parseEntry();

		System.out.println(">>  Name   : " + entry.getName());
		System.out.println(">>  Desc   : " + entry.getDescription());
		System.out.println(">>  Start  : " + entry.getStartDate());
		System.out.println(">>  End    : " + entry.getEndDate());
		System.out.println(">>  Rebate : " + entry.getRebateAmount());
		
		System.out.println(">>  Product Name : " + entry.getProduct().getName());
		System.out.println(">>  Product UPC : " + entry.getProduct().getUpc());
	}

}
