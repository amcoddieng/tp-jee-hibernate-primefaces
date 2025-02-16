/**package bean;

import database.Personne;

import dao.PersonneHome;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "signupBean")
@SessionScoped
public class SignupBean {

    private String login;
    private String password;
    private String prenom;
    private String nom;
    private String adresse;
    private Integer age;

    private PersonneHome personneHome;

    public SignupBean() {
        personneHome = new PersonneHome();
    }

    // Getters et Setters
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // Méthode d'inscription de l'utilisateur
    public String registerUser() {
        try {
            // Vérification si l'utilisateur existe déjà
            Personne existingPersonne = personneHome.findById(login);
            if (existingPersonne != null) {
                return "Utilisateur déjà existant!";
            }

            // Création d'un nouvel utilisateur
            Personne newPersonne = new Personne();
            newPersonne.setLogin(login);
            newPersonne.setPassword(password);
            newPersonne.setPrenom(prenom);
            newPersonne.setNom(nom);
            newPersonne.setAdresse(adresse);
            newPersonne.setAge(age);

            // Enregistrement de l'utilisateur dans la base de données
            personneHome.persist(newPersonne);

            return "Inscription réussie!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Échec de l'inscription!";
        }
    }
}*/
