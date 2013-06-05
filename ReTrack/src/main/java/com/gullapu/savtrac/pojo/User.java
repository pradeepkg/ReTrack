package com.gullapu.savtrac.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

/*
 @TypeDef(
 name = "encryptedString", typeClass = EncryptedStringType.class, 
 parameters = { 
 @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") 
 }
 )
 */

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
@XmlRootElement
@Entity
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = 1779898630683043605L;

	@Id
	@Column(name = "userName")
	@NotBlank
	@Length(min = 3, max = 12)
	private String userID;

	// @Type(type="encryptedString")
	private String password;

	@Column(name = "firstName")
	@NotBlank
	@Length(min = 3, max = 12)
	private String fName;

	@Column(name = "lastName")
	private String lName;

	private String userStatus;

	private int enabled;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "userName") }, inverseJoinColumns = { @JoinColumn(name = "roleID") })
	private Set<Role> roles = new HashSet<Role>();

	/**
	 * @return the userID
	 */
	@XmlElement
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(final String userID) {
		this.userID = userID;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public String getfName() {
		return fName;
	}

	public void setfName(final String fName) {
		this.fName = fName;
	}

	@XmlElement
	public String getlName() {
		return lName;
	}

	public void setlName(final String lName) {
		this.lName = lName;
	}

	@XmlElement
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(final String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the enabled
	 */
	@XmlElement
	public int getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the roles
	 */
	@XmlElement
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
