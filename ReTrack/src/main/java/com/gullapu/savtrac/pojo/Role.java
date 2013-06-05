/**
 * 
 */
package com.gullapu.savtrac.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kadambar@rsa.com">Pradeep Kadambar</a>
 */
@Entity
@Table(name = "Roles")
public class Role {

	@Column(name = "roleID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String authority;

	//@ManyToMany(fetch = FetchType.EAGER)
	//@JoinColumn(name = "userName", referencedColumnName = "userName")
	//private User user;

	public Role(String authority) {
		this.authority = authority;
	}

	/**
	 * 
	 */
	public Role() {
		super();
	}

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
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority
	 *            the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
