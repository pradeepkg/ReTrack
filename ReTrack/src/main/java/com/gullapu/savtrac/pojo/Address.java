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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gullapu.savtrac.web.Constants.Status;

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
@XmlRootElement
@Entity
@Table(name = "Addresses")
public class Address implements Serializable {

	@Id
	@Column(name = "addressID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private int id;

	@XmlElement
	private String line1;

	@XmlElement
	private String line2;

	@XmlElement
	private int poBox;

	@XmlElement
	private String city;

	@XmlElement
	private String state;

	@XmlElement
	private String zipCode;

	private String status = Status.INCOMPLETE;

	/**
	 * @return the line1
	 */
	public String getLine1() {
		return line1;
	}

	/**
	 * @param line1
	 *            the line1 to set
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * @return the line2
	 */
	public String getLine2() {
		return line2;
	}

	/**
	 * @param line2
	 *            the line2 to set
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * @return the poBox
	 */
	public int getPoBox() {
		return poBox;
	}

	/**
	 * @param poBox
	 *            the poBox to set
	 */
	public void setPoBox(int poBox) {
		this.poBox = poBox;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public void analyzeCompletness() {
		if(Status.VOID.equals(status)){
			return;
		}
		
		status = Status.INCOMPLETE;
		
		if (null == line1 || line1.length() < 1) {
			return;
		} else if (null == city || city.length() < 1) {
			return;
		} else if (null == state || state.length() < 1) {
			return;
		} else if (null == zipCode || zipCode.length() < 1) {
			return;
		}

		status = Status.VALID;
	}
}
