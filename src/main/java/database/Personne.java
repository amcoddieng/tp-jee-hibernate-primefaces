package database;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Personne generated by hbm2java
 */
public class Personne implements Serializable {

    private String login;
    private Integer idpersonne;
    private String prenom;
    private String nom;
    private String password;
    private String adresse;
    private Integer age;
    private Set<Object> enseignants = new HashSet<>(0);
    private Set<Object> etudiants = new HashSet<>(0);

    public Personne() {}

    public Personne(String login) {
        this.login = login;
    }

    public Personne(String login, Integer idpersonne, String prenom, String nom, String password, String adresse,
                    Integer age, Set<Object> enseignants, Set<Object> etudiants) {
        this.login = login;
        this.idpersonne = idpersonne;
        this.prenom = prenom;
        this.nom = nom;
        this.password = password;
        this.adresse = adresse;
        this.age = age;
        this.enseignants = enseignants;
        this.etudiants = etudiants;
    }

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getIdpersonne() {
		return this.idpersonne;
	}

	public void setIdpersonne(Integer idpersonne) {
		this.idpersonne = idpersonne;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set getEnseignants() {
		return this.enseignants;
	}

	public void setEnseignants(Set enseignants) {
		this.enseignants = enseignants;
	}

	public Set getEtudiants() {
		return this.etudiants;
	}

	public void setEtudiants(Set etudiants) {
		this.etudiants = etudiants;
	}

}
