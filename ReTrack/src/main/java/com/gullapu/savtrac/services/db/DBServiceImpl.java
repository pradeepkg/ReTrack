/**
 * 
 */
package com.gullapu.savtrac.services.db;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gullapu.savtrac.pojo.Document;
import com.gullapu.savtrac.pojo.Entry;
import com.gullapu.savtrac.pojo.Processor;
import com.gullapu.savtrac.pojo.Product;
import com.gullapu.savtrac.pojo.User;

/**
 * <p>
 * </p>
 * 
 * @author <a href="mailto:pradeep.kg.rao@gmail.com">Pradeep Kadambar</a>
 */
@Service("dbPersistenceService")
@Transactional
public class DBServiceImpl implements PersistenceService {

	protected static Logger logger = Logger.getLogger(DBServiceImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public User deleteUser(final User user) {
		System.out.println("User = " + user.getUserID());
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
		return user;
	}

	public User getUser(final String key) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, key);
		session.getTransaction().commit();
		session.close();
		if (null == user) {
			user = new User();
			user.setUserID(key);
			user.setUserStatus("NOT_FOUND");
		}
		return user;
	}

	public User saveUser(final User user) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.merge(user);
		session.getTransaction().commit();
		session.close();
		return user;
	}

	public Document getDocument(final int documentID) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		Document document = (Document) session.get(Document.class, documentID);
		session.getTransaction().commit();
		session.close();
		if (null == document) {
			document = new Document();
			document.setName("UNKNOWN");
		}
		return document;
	}

	public Document saveDocument(final Document document) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(document);
		session.getTransaction().commit();
		session.close();
		return document;
	}

	@Override
	public Entry saveEntry(Entry entry) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		entry = (Entry) session.merge(entry);
		session.getTransaction().commit();
		session.close();
		return entry;
	}

	@Override
	public Product saveProduct(Product product) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		product = (Product) session.merge(product);
		session.getTransaction().commit();
		session.close();
		return product;
	}

	@Override
	public Processor saveProcessor(Processor processor) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		processor = (Processor) session.merge(processor);
		session.getTransaction().commit();
		session.close();
		return processor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entry> getEntries(String userID) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM Entry E WHERE E.user.userID=:userID";
		
		Query query = session.createQuery(hql);
		query.setParameter("userID", userID);
		List<Entry> results = query.list();
		
		session.getTransaction().commit();
		session.close();
		return results;
	}

	@Override
	public Entry getEntry(int entryID) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM Entry E WHERE E.entryID=:entryID";
		
		Query query = session.createQuery(hql);
		query.setParameter("entryID", entryID);
		Entry entry = (Entry) query.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		return entry;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Processor> getProcessors() {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM Processor ";
		
		Query query = session.createQuery(hql);
		List<Processor> results = query.list();
		
		session.getTransaction().commit();
		session.close();
		return results;
	}

	@Override
	public Processor getProcessor(int processorID) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM Processor P WHERE P.processorID=:processorID";
		
		Query query = session.createQuery(hql);
		query.setParameter("processorID", processorID);
		Processor processor = (Processor) query.uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		return processor;
	}

	/*
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		try {
			User user = getUser(userName);
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			return new org.springframework.security.core.userdetails.User(user.getUserID(), user.getPassword(),
				enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				getGrantedAuthorities(user.getRoles()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<GrantedAuthority> getGrantedAuthorities(Set<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		return authorities;
	}
	*/
}
