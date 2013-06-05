/**
 * 
 */
package com.gullapu.savtrac.handlers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import com.gullapu.savtrac.pojo.Document;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
public class HashingHandler extends AbstractDocumentHandler {

	private static final String DEFAULT_ALGORITHM = "MD5";

	private String hashingAlgorithm;

	/**
	 * @param hashingAlgorithm the hashingAlgorithm to set
	 */
	public void setHashingAlgorithm(String hashingAlgorithm) {
		this.hashingAlgorithm = hashingAlgorithm;
	}

	/**
	 * @return the hashingAlgorithm
	 */
	public String getHashingAlgorithm() {
		return hashingAlgorithm;
	}

	public String getHash(byte[] content) throws HandlerException {
		try {
			MessageDigest digest = MessageDigest.getInstance(getHashingAlgorithm());
			return new String(digest.digest(content));
		} catch (NoSuchAlgorithmException e) {
			throw new HandlerException(MessageFormat.format("Failed to initialize with hashing algorithm {0}",
				getHashingAlgorithm()), e);
		}
	}

	public HashingHandler(String handlerName, String handlerDescriptionKey, String hashingAlgorithm)
		throws HandlerException {
		super(handlerName, handlerDescriptionKey);
		if (null == hashingAlgorithm | hashingAlgorithm.length() < 3) {
			hashingAlgorithm = DEFAULT_ALGORITHM;
		}
		this.hashingAlgorithm = hashingAlgorithm;
	}

	/**
	 * @see com.gullapu.savtrac.handlers.AbstractDocumentHandler#handleDocument(com.gullapu.savtrac.pojo.Document)
	 */
	@Override
	protected void handleDocument(Document document) throws HandlerException {
		document.setHash(getHash(document.getData()));
		document.setHash(getHashingAlgorithm());
	}
}
