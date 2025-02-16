package bean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import database.Personne;

public class Main {
    public static void main(String[] args) {
        // Chargement de la configuration Hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Création de la première personne
            Personne personne1 = new Personne();
            personne1.setNom("dieng");
            personne1.setPrenom("amadou");
            personne1.setAge(25);
            personne1.setAdresse("kaolack");
            personne1.setLogin("amadoudieng101");
            personne1.setPassword("diengcode");
            // Enregistrement de la personne dans la base
            session.save(personne1);

            // Validation de la transaction
            transaction.commit();
            System.out.println("Données enregistrées avec succès !");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}

