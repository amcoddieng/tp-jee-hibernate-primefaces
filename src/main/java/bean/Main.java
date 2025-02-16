package bean;
import java.util.List;

import dao.PersonneHome;
import database.Personne;

public class Main {
    public static void main(String[] args) {
        PersonneHome personneHome = new PersonneHome();




        // Appeler la méthode ListUsers pour afficher tous les utilisateurs
        List<Personne> users = personneHome.ListUsers();
        System.out.println("Liste des utilisateurs: ");
        for (Personne user : users) {
            System.out.println(user.getPrenom() + " " + user.getNom());
        }

        // Appeler ListUserPrenom pour afficher uniquement les prénoms de tous les utilisateurs
        List<String> prenoms = personneHome.ListUserPrenom();
        System.out.println("Prénoms des utilisateurs: ");
        for (String prenom : prenoms) {
            System.out.println(prenom);
        }

        // Appeler ListUserPrenomWithProjection pour récupérer uniquement les prénoms avec l'API Projection
        List<String> prenomsProjection = personneHome.ListUserPrenomWithProjection();
        System.out.println("Prénoms avec projection: ");
        for (String prenom : prenomsProjection) {
            System.out.println(prenom);
        }



    }
}
