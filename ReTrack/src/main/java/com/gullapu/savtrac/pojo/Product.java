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

/**
 * <p>
 * </p>
 * 
 * @author Pradeep Kadambar
 */
@XmlRootElement
@Entity
@Table(name = "Products")
public class Product implements Serializable {

	@Id
	@Column(name = "productID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private int id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String upc;
	
	@XmlElement
	private double price;
	
	private boolean isComplete;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the upc
	 */
	public String getUpc() {
		return upc;
	}

	/**
	 * @param upc the upc to set
	 */
	public void setUpc(String upc) {
		this.upc = upc;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
	
	public void analyzeCompletness(){
		if(null != name && name.length() > 0){
			isComplete = true;
		}
	}
}
