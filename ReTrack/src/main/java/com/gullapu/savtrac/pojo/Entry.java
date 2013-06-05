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

	private String description;

	private Date startDate;

	private Date endDate;

	private String productLink;

	private String rebateLink;
	
	private boolean isComplete;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productID")
	@NotBlank
	@ForeignKey(name = "FK_ENTRY_TO_PRODUCT")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "processorID")
	@NotBlank
	@ForeignKey(name = "FK_ENTRY_TO_PROCESSOR")
	private Processor processor;

	@OneToMany(cascade = CascadeType.ALL)
	// @LazyCollection(LazyCollectionOption.EXTRA)
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
	 * @return the isComplete
	 */
	public boolean isComplete() {
		return isComplete;
	}

	/**
	 * @param isComplete the isComplete to set
	 */
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
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
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the processor
	 */
	public Processor getProcessor() {
		return processor;
	}

	/**
	 * @param processor the processor to set
	 */
	public void setProcessor(Processor processor) {
		this.processor = processor;
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
}
