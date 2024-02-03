package ma.enset.examenjdbcfx2.dao;

import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Entreprise;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        EntrepriseDao entrepriseDao = new EntrepriseDaoImpl();
        entrepriseDao.delete(10);

        Entreprise newEntreprise = new Entreprise();
        newEntreprise.setNom("Nouvelle Entreprise");
        newEntreprise.setAdresse("Nouvelle Adresse");
        newEntreprise.setTelephone("Nouveau Téléphone");
        newEntreprise.setEmail("nouvelle@entreprise.com");
        //entrepriseDao.save(newEntreprise);
        System.out.println("Liste des entreprises : ");
        List<Entreprise> entreprises = entrepriseDao.findAll();
        for (Entreprise entreprise : entreprises) {
            System.out.println(entreprise);
        }
        int idEntrepriseRecherchee = 1;
        System.out.println("\nEntreprise avec l'ID " + idEntrepriseRecherchee + " : ");
        Entreprise entrepriseTrouvee = entrepriseDao.findById(idEntrepriseRecherchee);
        System.out.println(entrepriseTrouvee);

        int idEntrepriseASupprimer = 3; // Remplacez par un ID existant dans votre base de données
       // entrepriseDao.delete(idEntrepriseASupprimer);
        int idEntrepriseAMettreAJour = 2; // Remplacez par un ID existant dans votre base de données
        Entreprise entrepriseAMettreAJour = entrepriseDao.findById(idEntrepriseAMettreAJour);
        entrepriseAMettreAJour.setNom("EdsdsJour");
        entrepriseAMettreAJour.setAdresse("Nosdsdsdsse");

        //entrepriseDao.update(entrepriseAMettreAJour);
        int idEntreprise = 1;

        // Test de la méthode findDepartementsByEntreprise
        List<Departement> departements = entrepriseDao.findDepartementsByEntreprise(idEntreprise);

        System.out.println("Liste des départements de l'entreprise avec l'ID " + idEntreprise + " :");
        for (Departement departement : departements) {
            System.out.println(departement);
        }
        System.out.println("**********");
        String keyword = "entreprise";

        // Test de la méthode searchByKeyword
         entreprises = entrepriseDao.searchByKeyword(keyword);

        System.out.println("Résultats de la recherche pour le mot-clé \"" + keyword + "\" :");
        for (Entreprise entreprise : entreprises) {
            System.out.println(entreprise);
        }




    }

}
