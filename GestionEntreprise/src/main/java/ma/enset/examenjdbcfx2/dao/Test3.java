package ma.enset.examenjdbcfx2.dao;

import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Employe;

import java.util.List;

public class Test3 {

    public static void main(String[] args) {
        EmployeDao employeDao = new EmployeDaoImpl();

        // Test de la méthode findAll
        List<Employe> employes = employeDao.findAll();
        System.out.println("Liste des employés :");
        for (Employe employe : employes) {
            System.out.println(employe);
        }
        int employeId = 1; // Remplacez par l'ID réel de l'employé à rechercher
        Employe employeById = employeDao.findById(employeId);
        System.out.println("Employé avec l'ID " + employeId + " :");
        System.out.println(employeById);

        Employe nouvelEmploye = new Employe();
        nouvelEmploye.setNom("Nouveau Nom");
        nouvelEmploye.setPoste("Nouveau Poste");
        nouvelEmploye.setSalaire("Nouveau Salaire");
        Departement d = new Departement();
        d.setIdDepartement(13);

        nouvelEmploye.setDepartement(d);

        employeDao.save(nouvelEmploye);
        employes = employeDao.findAll();
        System.out.println("Liste des employés :");
        for (Employe employe : employes) {
            System.out.println(employe);
        }

    }
}
