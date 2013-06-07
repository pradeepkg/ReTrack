/**
 * 
 */
package com.gullapu.savtrac.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "Entries")
public class Entry implements Serializable {

	@Id
	@Column(name = "entryID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "userName")
	@NotBlank
	@ForeignKey(name = "FK_ENTRY_TO_USER")
	private User user;

	private String name;

	@Column(length = 1024)
	private String description;

	private Date startDate;

	private Date endDate;

	private String productLink;

	private String rebateLink;

	@Column(name = "status")
	private String status = Status.INCOMPLETE;

	private double rebateAmount;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "productID")
	@NotBlank
	@ForeignKey(name = "FK_ENTRY_TO_PRODUCT")
	private Product product;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "processorID")
	@NotBlank
	@ForeignKey(name = "FK_ENTRY_TO_PROCESSOR")
	private Processor processor;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ENTRY_DOCS", joinColumns = { @JoinColumn(name = "entryID") },
		inverseJoinColumns = { @JoinColumn(name = "documentID") })
	private List<Document> documents;

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
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@XmlElement
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
	 * @return the description
	 */
	@XmlElement
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startDate
	 */
	@XmlElement
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	@XmlElement
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the productLink
	 */
	@XmlElement
	public String getProductLink() {
		return productLink;
	}

	/**
	 * @param productLink
	 *            the productLink to set
	 */
	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	/**
	 * @return the rebateLink
	 */
	@XmlElement
	public String getRebateLink() {
		return rebateLink;
	}

	/**
	 * @param rebateLink
	 *            the rebateLink to set
	 */
	public void setRebateLink(String rebateLink) {
		this.rebateLink = rebateLink;
	}

	/**
	 * @return the status
	 */
	@XmlElement
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

	/**
	 * @return the documents
	 */
	@XmlElement
	public List<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents
	 *            the documents to set
	 */
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	/**
	 * @return the user
	 */
	@XmlElement
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the product
	 */
	@XmlElement
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the processor
	 */
	@XmlElement
	public Processor getProcessor() {
		return processor;
	}

	/**
	 * @param processor
	 *            the processor to set
	 */
	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	/**
	 * @return the rebateAmount
	 */
	@XmlElement
	public double getRebateAmount() {
		return rebateAmount;
	}

	/**
	 * @param rebateAmount
	 *            the rebateAmount to set
	 */
	public void setRebateAmount(double rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entry [id=" + id + ", user=" + user + ", name=" + name + ", description=" + description
			+ ", startDate=" + startDate + ", endDate=" + endDate + ", productLink=" + productLink + ", rebateLink="
			+ rebateLink + ", documents=" + documents + "]";
	}

	public void analyzeCompletness() {
		if(Status.VOID.equals(status)){
			return;
		}
		
		status = Status.INCOMPLETE;
		
		if (null == name || name.length() < 1) {
			return;
		} else if (null == description || description.length() < 1) {
			return;
		} else if (null == rebateLink || rebateLink.length() < 1) {
			return;
		} else if (null == startDate || null == endDate) {
			return;
		} else if (null == processor) {
			return;
		} else if (null == product) {
			return;
		}

		processor.analyzeCompletness();
		product.analyzeCompletness();

		if (Status.VALID.equals(processor.getStatus()) && Status.VALID.equals(product.getStatus())) {
			status = Status.VALID;
		}
	}
}
