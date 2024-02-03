package ma.enset.examenjdbcfx2.dao;

import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Entreprise;

import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        DepartementDao departementDao = new DepartementDaoImpl();

        // Test findAll

        Departement newDepartement = new Departement();
        Entreprise entreprise = new Entreprise();

        entreprise.setIdEntreprise(2);

        Departement departement = new Departement();

        departement.setNom("Département XYZ");
        departement.setEntreprise(entreprise);


        //departementDao.save(departement);

        departementDao.delete(3);
        List<Departement> departements = departementDao.findAll();
        departementDao.delete(2);
        System.out.println("Liste des départements :");
        for (Departement t : departements) {
            System.out.println(t);
        }

    }
}
