/**
 * 
 */
package com.gullapu.savtrac.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gullapu.savtrac.enums.DocumentOperationStatus;

/**
 * @author kadamp
 * 
 */
@XmlRootElement
@Entity
@Table(name = "Documents")
public class Document implements Serializable {

	private static final long serialVersionUID = -1282525113412620114L;

	@Column(name = "documentID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String fileName;

	private int size;

	private String mimeType;

	private String hash;

	private String hashingAlorithm;

	@Lob
	private byte[] data;

	@Transient
	private DocumentOperationStatus operationStatus;

	/**
	 * @return the id
	 */
	@XmlElement
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@XmlElement
	public int getSize() {
		return size;
	}

	/**
	 * @return the mimeType
	 */
	@XmlElement
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType
	 *            the mimeType to set
	 */
	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @return the fileName
	 */
	@XmlElement
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public void setSize(final int size) {
		this.size = size;
	}

	@XmlElement
	public String getHash() {
		return hash;
	}

	public void setHash(final String hash) {
		this.hash = hash;
	}

	@XmlElement
	public String getHashingAlorithm() {
		return hashingAlorithm;
	}

	public void setHashingAlorithm(final String hashingAlorithm) {
		this.hashingAlorithm = hashingAlorithm;
	}

	/**
	 * @return the data
	 */
	@XmlElement
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}

	/**
	 * @return the operationStatus
	 */
	@XmlElement
	public DocumentOperationStatus getOperationStatus() {
		return operationStatus;
	}

	/**
	 * @param operationStatus
	 *            the operationStatus to set
	 */
	public void setOperationStatus(final DocumentOperationStatus operationStatus) {
		this.operationStatus = operationStatus;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Document [id=" + id + ", name=" + name + ", fileName=" + fileName + ", size=" + size + "]";
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Document)) {
			return false;
		}
		Document other = (Document) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
}
