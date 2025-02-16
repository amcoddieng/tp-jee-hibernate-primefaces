package bean;

import database.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "personneBean")
@SessionScoped
public class PersonneBean {

    private String nom;
    private String prenom;
    private int age;
    private String adresse;
    private String login;
    private String password;

    // Getters and setters pour chaque propriété

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

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

    // Méthode pour inscrire une personne
    public String inscrire() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Création de la personne à partir des données du formulaire
            Personne personne = new Personne();
            personne.setNom(nom);
            personne.setPrenom(prenom);
            personne.setAge(age);
            personne.setAdresse(adresse);
            personne.setLogin(login);
            personne.setPassword(password);

            // Enregistrement de la personne dans la base
            session.save(personne);

            // Validation de la transaction
            transaction.commit();
            System.out.println("Données enregistrées avec succès !");

            // Rediriger vers la page d'accueil après l'inscription réussie
            return "accueil.xhtml?faces-redirect=true";

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "signup.xhtml"; // Revenir à la page d'inscription en cas d'erreur
        } finally {
            session.close();
            factory.close();
        }
    }
    
    
}
