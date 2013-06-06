/**
 * 
 */
package com.gullapu.savtrac.services.processor;

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public class ProcessorService {
	
	public Processor getProcessor(String url){
		return new Processor_RebateAccess(url);
	}

}
