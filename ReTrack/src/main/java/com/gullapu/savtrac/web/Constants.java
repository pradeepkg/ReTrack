/**
 * 
 */
package com.gullapu.savtrac.web;

/**
 * <p>
 * The definition interface for constants used in the application.
 * </p>
 * 
 * @author Pradeep Kadambar
 */
public interface Constants {

	public interface Status {
		String VALID = "VALID";
		String VOID = "VOID";
		String INCOMPLETE = "INCOMPLETE";
		String MAILED = "MAILED";
		String PROCESSING = "PROCESSING";
		String APPROVED = "APPROVED";
		String RECEIVED = "RECEIVED";
		String REJECTED = "REJECTED";
	}

	/**
	 * The key for requester/target URL
	 */
	String TARGET_URL = "TARGET_URL";

	/**
	 * The key for storing AA request details.
	 */
	String REQUEST_DETAILS = "REQUEST_DETAILS";

	/**
	 * The session key for user current user.
	 */
	String USER = "user";

	String DOCUMENTS = "documents";

	String ENTRY = "entry";

	String PRODUCT = "product";

	/**
	 * The key for user id in the request header.
	 */
	String USER_ID = "USER_ID";

	/**
	 * The key for user password in the request header.
	 */
	String USER_PASSWORD = "USER_PASSWORD";

	/**
	 * The session key for user current aa user.
	 */
	String AA_USER = "AA_USER_SESSION";

	/**
	 * The session key for current token.
	 */
	String TOKEN = "token";

	/**
	 * The redirect JSP for error handling.
	 */
	String JSP_ERROR = "/WEB-INF/jsps/error.jsp";

	String JSP_CREATE = "/WEB-INF/jsps/createForm.jsp";

	String JSP_CREATE_ENTRY = "/WEB-INF/jsps/createEntry.jsp";

	String JSP_CREATE_PRODUCT = "/WEB-INF/jsps/createProduct.jsp";

	String JSP_CREATE_PROCESSOR = "/WEB-INF/jsps/createProcessor.jsp";

	String JSP_EDIT_ENTRY = "/WEB-INF/jsps/editEntry.jsp";

	String JSP_EDIT_PRODUCT = "/WEB-INF/jsps/editProduct.jsp";

	String JSP_EDIT_PROCESSOR = "/WEB-INF/jsps/editProcessor.jsp";

	String JSP_LIST = "/WEB-INF/jsps/getEntries.jsp";

	String JSP_DOCS_LIST = "/WEB-INF/jsps/getDocs.jsp";
	
	String JSP_ENTRY_MAILED = "/WEB-INF/jsps/entryMailed.jsp";
	
	String JSP_ENTRY_PROCESSING = "/WEB-INF/jsps/entryProcessing.jsp";
	
	String JSP_ENTRY_APPROVED = "/WEB-INF/jsps/entryApproved.jsp";
	
	String JSP_ENTRY_RECEIVED = "/WEB-INF/jsps/entryReceived.jsp";

	/**
	 * The redirect JSP for successful results.
	 */
	String JSP_SUCCESS = "/WEB-INF/jsps/success.jsp";

	/**
	 * The application landing page.
	 */
	String JSP_LANDING = "index.jsp";
}
