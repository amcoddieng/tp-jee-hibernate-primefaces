package dao;
// Generated 13 f�vr. 2025, 17:53:57 by Hibernate Tools 5.4.33.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import database.Enseignant;
import database.EnseignantId;

/**
 * Home object for domain model class Enseignant.
 * @see database.Enseignant
 * @author Hibernate Tools
 */
public class EnseignantHome {

	private static final Logger logger = Logger.getLogger(EnseignantHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Enseignant transientInstance) {
		logger.log(Level.INFO, "persisting Enseignant instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Enseignant instance) {
		logger.log(Level.INFO, "attaching dirty Enseignant instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Enseignant instance) {
		logger.log(Level.INFO, "attaching clean Enseignant instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Enseignant persistentInstance) {
		logger.log(Level.INFO, "deleting Enseignant instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Enseignant merge(Enseignant detachedInstance) {
		logger.log(Level.INFO, "merging Enseignant instance");
		try {
			Enseignant result = (Enseignant) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Enseignant findById(database.EnseignantId id) {
		logger.log(Level.INFO, "getting Enseignant instance with id: " + id);
		try {
			Enseignant instance = (Enseignant) sessionFactory.getCurrentSession().get("database.Enseignant", id);
			if (instance == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
	}

	public List findByExample(Enseignant instance) {
		logger.log(Level.INFO, "finding Enseignant instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("database.Enseignant")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
}
