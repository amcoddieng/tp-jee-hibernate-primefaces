package bean;

import database.Personne;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
public class LoginBean {

    private String login;
    private String password;
    private Personne personne;

    // Getters et setters pour login et password

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Méthode pour vérifier les identifiants et connecter l'utilisateur
    public String loginAction() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        try {
            // Création de la requête HQL pour vérifier les identifiants
            String hql = "FROM Personne WHERE login = :login AND password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("login", login);
            query.setParameter("password", password);
            personne = (Personne) query.uniqueResult();

            if (personne != null) {
                // Connexion réussie
                return "accueil.xhtml?faces-redirect=true";
            } else {
                // Si les informations de connexion sont incorrectes
                return "login.xhtml?faces-redirect=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "login.xhtml?faces-redirect=true"; // En cas d'erreur
        } finally {
            session.close();
            factory.close();
        }
    }
    // Méthode pour la déconnexion
    public String logoutAction() {
        this.personne = null;  // Effacer les données de session
        return "login.xhtml?faces-redirect=true";  // Redirection vers la page de connexion
    }
}
