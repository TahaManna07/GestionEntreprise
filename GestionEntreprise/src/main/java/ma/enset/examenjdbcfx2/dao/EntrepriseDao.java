package ma.enset.examenjdbcfx2.dao;

import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Entreprise;

import java.util.List;

public interface EntrepriseDao extends DAO<Entreprise> {
    List<Departement> findDepartementsByEntreprise(int idEntreprise);

    List<Entreprise> searchByKeyword(String keyword);

    void printEntrepriseInfoToPDF(int idEntreprise);
}