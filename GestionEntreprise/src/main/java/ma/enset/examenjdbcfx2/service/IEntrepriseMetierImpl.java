package ma.enset.examenjdbcfx2.service;

import ma.enset.examenjdbcfx2.dao.DepartementDao;
import ma.enset.examenjdbcfx2.dao.EmployeDao;
import ma.enset.examenjdbcfx2.dao.EntrepriseDao;
import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Employe;
import ma.enset.examenjdbcfx2.dao.entities.Entreprise;

import java.util.List;

public class IEntrepriseMetierImpl implements IEntrepriseMetier{

    DepartementDao departementDao;
    EntrepriseDao entrepriseDao;
    EmployeDao employeDao;
    @Override
    public void saveEntreprise(Entreprise entreprise) {
        entrepriseDao.save(entreprise);
    }

    @Override
    public List<Entreprise> findAllEntreprises() {
        return entrepriseDao.findAll();
    }

    @Override
    public Entreprise findEntrepriseById(int id) {
        return entrepriseDao.findById(id);
    }

    @Override
    public void deleteEntreprise(int id) {
        entrepriseDao.delete(id);

    }

    @Override
    public void updateEntreprise(Entreprise entreprise) {
        entrepriseDao.update(entreprise);
    }

    @Override
    public List<Departement> findDepartementsByEntreprise(int idEntreprise) {
        return entrepriseDao.findDepartementsByEntreprise(idEntreprise);
    }

    @Override
    public List<Entreprise> searchEntreprisesByKeyword(String keyword) {
        return entrepriseDao.searchByKeyword(keyword);
    }

    @Override
    public void printEntrepriseInfoToPDF(int idEntreprise) {
        entrepriseDao.printEntrepriseInfoToPDF(idEntreprise);

    }

    @Override
    public void saveDepartement(Departement departement) {
        departementDao.save(departement);
    }

    @Override
    public void deleteDepartement(int id) {
        departementDao.delete(id);

    }

    @Override
    public void updateDepartement(Departement departement) {
        departementDao.update(departement);
    }

    @Override
    public void saveEmploye(Employe employe) {
        employeDao.save(employe);
    }

    @Override
    public void deleteEmploye(int id) {
        employeDao.delete(id);
    }

    @Override
    public void updateEmploye(Employe employe) {
        employeDao.update(employe);
    }
}
