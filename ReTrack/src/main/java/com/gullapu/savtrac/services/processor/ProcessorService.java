/**
 * 
 */
package com.gullapu.savtrac.services.processor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.gullapu.savtrac.pojo.Entry;

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public class ProcessorService {

	private List<Processor> processors;

	/**
	 * @param processors
	 */
	public ProcessorService(List<Processor> processors) {
		this.processors = processors;
	}

	private Processor getProcessor(String url) {

		try {
			String host = new URL(url).getHost();
			for (Processor processor : processors) {
				if (host.indexOf(processor.getHost()) > -1) {
					return processor;
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public Entry parseURL(String url) {
		Processor processor = getProcessor(url);
		return processor.parseEntry(url);
	}

	public static void main(String[] a) throws MalformedURLException {
		String urlString = "http://newegg.rebateaccess.com/?p=48849";
		List<Processor> processors = new ArrayList<Processor>();

		ProcessorService service = new ProcessorService(processors);
		Processor_RebateAccess pro = new Processor_RebateAccess("RebateAccess", "newegg.rebateaccess.com");
		processors.add(pro);

		Entry entry = service.parseURL(urlString);

		System.out.println(">>  Name   : " + entry.getName());
		System.out.println(">>  Desc   : " + entry.getDescription());
		System.out.println(">>  Start  : " + entry.getStartDate());
		System.out.println(">>  End    : " + entry.getEndDate());
		System.out.println(">>  Rebate : " + entry.getRebateAmount());

		System.out.println(">>  Product Name : " + entry.getProduct().getName());
		System.out.println(">>  Product UPC : " + entry.getProduct().getUpc());
	}
}
