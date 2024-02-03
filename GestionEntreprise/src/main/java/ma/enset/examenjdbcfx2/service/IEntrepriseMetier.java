package ma.enset.examenjdbcfx2.service;

import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Entreprise;
import ma.enset.examenjdbcfx2.dao.entities.Employe;

import java.util.List;

public interface IEntrepriseMetier {
    // Entreprise methods
    void saveEntreprise(Entreprise entreprise);
    List<Entreprise> findAllEntreprises();
    Entreprise findEntrepriseById(int id);
    void deleteEntreprise(int id);
    void updateEntreprise(Entreprise entreprise);
    List<Departement> findDepartementsByEntreprise(int idEntreprise);
    List<Entreprise> searchEntreprisesByKeyword(String keyword);
    void printEntrepriseInfoToPDF(int idEntreprise);

    // Departement methods

    void saveDepartement(Departement departement);
    void deleteDepartement(int id);
    void updateDepartement(Departement departement);

    // Employe methods

    void saveEmploye(Employe employe);
    void deleteEmploye(int id);
    void updateEmploye(Employe employe);
}
