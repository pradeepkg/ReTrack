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
@Table(name = "Entries")
public class Entry implements Serializable {

	@Transient
	private static final long serialVersionUID = 1224279787048322786L;

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

	private Date createDate;

	private Date mailedDate;

	private Date processingDate;

	private Date approvedDate;

	private Date receiveDate;

	private String productLink;

	private String rebateLink;

	@XmlElement
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the mailedDate
	 */
	public Date getMailedDate() {
		return mailedDate;
	}

	/**
	 * @param mailedDate
	 *            the mailedDate to set
	 */
	public void setMailedDate(Date mailedDate) {
		this.mailedDate = mailedDate;
	}

	/**
	 * @return the processingDate
	 */
	public Date getProcessingDate() {
		return processingDate;
	}

	/**
	 * @param processingDate
	 *            the processingDate to set
	 */
	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	/**
	 * @return the approvedDate
	 */
	public Date getApprovedDate() {
		return approvedDate;
	}

	/**
	 * @param approvedDate
	 *            the approvedDate to set
	 */
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	/**
	 * @return the receiveDate
	 */
	public Date getReceiveDate() {
		return receiveDate;
	}

	/**
	 * @param receiveDate
	 *            the receiveDate to set
	 */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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
			+ rebateLink + ", status=" + status + ", rebateAmount=" + rebateAmount + ", product=" + product
			+ ", processor=" + processor + ", documents=" + documents + "]";
	}

	public void changeStatus(String nextStatus) {
		switch (status) {
		case Status.VOID:
			status = nextStatus;
			return;
		case Status.INCOMPLETE:
			if (null == name || name.length() < 1) {
				return;
			} else if (null == description || description.length() < 1) {
				return;
			} else if (null == startDate || null == endDate) {
				return;
			}

			if (null != processor) {
				processor.analyzeCompletness();
			}

			if (null != product) {
				product.analyzeCompletness();
			}

			if (null != processor && null != product && Status.VALID.equals(processor.getStatus())
				&& Status.VALID.equals(product.getStatus())) {
				status = Status.VALID;
			}
			break;
		case Status.VALID:
			if (nextStatus.equals(Status.MAILED) || nextStatus.equals(Status.VOID)) {
				status = nextStatus;
			}
			break;
		case Status.MAILED:
			if (nextStatus.equals(Status.PROCESSING) || nextStatus.equals(Status.VOID)) {
				status = nextStatus;
			}
			break;
		case Status.PROCESSING:
			if (nextStatus.equals(Status.APPROVED) || nextStatus.equals(Status.VOID)) {
				status = nextStatus;
			}
			break;
		case Status.APPROVED:
			if (nextStatus.equals(Status.RECEIVED)) {
				status = nextStatus;
			}
			break;
		case Status.RECEIVED:
			status = nextStatus;
			break;
		default:
			break;
		}
	}
}
