package dao;
// Generated 13 f�vr. 2025, 17:53:57 by Hibernate Tools 5.4.33.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import database.Etudiant;
import database.EtudiantId;

/**
 * Home object for domain model class Etudiant.
 * @see database.Etudiant
 * @author Hibernate Tools
 */
public class EtudiantHome {

	private static final Logger logger = Logger.getLogger(EtudiantHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Etudiant transientInstance) {
		logger.log(Level.INFO, "persisting Etudiant instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Etudiant instance) {
		logger.log(Level.INFO, "attaching dirty Etudiant instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Etudiant instance) {
		logger.log(Level.INFO, "attaching clean Etudiant instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Etudiant persistentInstance) {
		logger.log(Level.INFO, "deleting Etudiant instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Etudiant merge(Etudiant detachedInstance) {
		logger.log(Level.INFO, "merging Etudiant instance");
		try {
			Etudiant result = (Etudiant) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Etudiant findById(database.EtudiantId id) {
		logger.log(Level.INFO, "getting Etudiant instance with id: " + id);
		try {
			Etudiant instance = (Etudiant) sessionFactory.getCurrentSession().get("database.Etudiant", id);
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

	public List findByExample(Etudiant instance) {
		logger.log(Level.INFO, "finding Etudiant instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("database.Etudiant")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
}
