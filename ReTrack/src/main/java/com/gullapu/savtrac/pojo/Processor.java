/**
 * 
 */
package com.gullapu.savtrac.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import com.gullapu.savtrac.web.Constants.Status;
/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
@XmlRootElement
@Entity
@Table(name = "Processors")
public class Processor implements Serializable {

	@Transient
	private static final long serialVersionUID = -2225558458015988106L;

	@Id
	@Column(name = "processorID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private int id;

	@XmlElement
	private String name;

	@XmlElement
	private String homePage;

	@XmlElement
	private String email;

	@XmlElement
	private String phone;

	@XmlElement
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressID")
	@NotBlank
	@ForeignKey(name = "FK_PROCESSOR_TO_ADDRESS")
	private Address address;

	@XmlElement
	private String status = Status.INCOMPLETE;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the homePage
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * @param homePage
	 *            the homePage to set
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
		
		if (null == name || name.length() < 1) {
			return;
		} else if (null == address) {
			return;
		}

		address.analyzeCompletness();
		status = address.getStatus();
	}
}
