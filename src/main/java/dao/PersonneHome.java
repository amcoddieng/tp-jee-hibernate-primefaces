package dao;

import database.Personne;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Home object for domain model class Personne.
 */
public class PersonneHome {

    private static final Logger logger = Logger.getLogger(PersonneHome.class.getName());
    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Could not build SessionFactory from hibernate.cfg.xml", e);
            throw new IllegalStateException("Could not build SessionFactory from hibernate.cfg.xml");
        }
    }

    public void persist(Personne transientInstance) {
        logger.log(Level.INFO, "Persisting Personne instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            logger.log(Level.INFO, "Persist successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "Persist failed", re);
            throw re;
        }
    }

	public void attachDirty(Personne instance) {
		logger.log(Level.INFO, "attaching dirty Personne instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Personne instance) {
		logger.log(Level.INFO, "attaching clean Personne instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Personne persistentInstance) {
		logger.log(Level.INFO, "deleting Personne instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Personne merge(Personne detachedInstance) {
		logger.log(Level.INFO, "merging Personne instance");
		try {
			Personne result = (Personne) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Personne findById(java.lang.String id) {
		logger.log(Level.INFO, "getting Personne instance with id: " + id);
		try {
			Personne instance = (Personne) sessionFactory.getCurrentSession().get("database.Personne", id);
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

	public List findByExample(Personne instance) {
		logger.log(Level.INFO, "finding Personne instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("database.Personne")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	
	
	// Liste tous les utilisateurs
    public List<Personne> ListUsers() {
        logger.log(Level.INFO, "Listing all Personne instances");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Personne> result = session.createQuery("from Personne").list();
            session.getTransaction().commit();
            return result;
        } catch (RuntimeException re) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "List all failed", re);
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Liste le prénom de tous les utilisateurs
    public List<String> ListUserPrenom() {
        logger.log(Level.INFO, "Listing first name of all Personne instances");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<String> result = session.createQuery("select p.prenom from Personne p").list();
            session.getTransaction().commit();
            return result;
        } catch (RuntimeException re) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "List first name failed", re);
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Utilisation de l'API Projection pour récupérer seulement le prénom de l'utilisateur
    public List<String> ListUserPrenomWithProjection() {
        logger.log(Level.INFO, "Listing first name of all Personne instances with Projection");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<String> result = session.createQuery("select p.prenom from Personne p").list();
            session.getTransaction().commit();
            return result;
        } catch (RuntimeException re) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "List first name with projection failed", re);
            throw re;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    

    public Personne login(String email, String password) {
        // Enregistrement d'une entrée de log pour indiquer que la tentative de login commence
        logger.log(Level.INFO, "Attempting login for email: " + email);
        
        // Initialisation de la session Hibernate
        Session session = null;
        
        try {
            // Ouverture de la session pour interagir avec la base de données
            session = sessionFactory.openSession();
            // Démarrage de la transaction pour garantir l'intégrité des données
            session.beginTransaction();

            // Création d'un critère pour rechercher dans la classe Personne
            Criteria criteria = session.createCriteria(Personne.class);
            // Ajout d'une restriction sur l'email de la personne
            criteria.add(Restrictions.eq("email", email));
            // Ajout d'une restriction sur le mot de passe de la personne
            criteria.add(Restrictions.eq("password", password));

            // Récupération du premier résultat correspondant aux critères (s'il existe)
            Personne result = (Personne) criteria.uniqueResult();
            
            // Validation de la transaction pour appliquer les changements
            session.getTransaction().commit();
            
            // Si un utilisateur a été trouvé, on logue un message de succès
            if (result != null) {
                logger.log(Level.INFO, "Login successful for email: " + email);
            } else {
                // Si aucun utilisateur n'a été trouvé, on logue un message d'échec
                logger.log(Level.INFO, "Login failed: No user found with the provided credentials");
            }
            
            // Retourne l'utilisateur trouvé, ou null si aucun utilisateur n'est trouvé
            return result;
        } catch (RuntimeException re) {
            // En cas d'erreur, on effectue un rollback de la transaction pour annuler les changements
            if (session != null) {
                session.getTransaction().rollback();
            }
            // Log de l'erreur rencontrée
            logger.log(Level.SEVERE, "Login failed", re);
            // Relance l'exception pour la gestion plus haut dans la pile d'appels
            throw re;
        } finally {
            // Ferme la session Hibernate pour libérer les ressources
            if (session != null) {
                session.close();
            }
        }
    }


}
