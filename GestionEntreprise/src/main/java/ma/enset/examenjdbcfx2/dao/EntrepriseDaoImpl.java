package ma.enset.examenjdbcfx2.dao;

import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Entreprise;

import java.util.List;


import java.sql.*;
import java.util.ArrayList;

public class EntrepriseDaoImpl implements EntrepriseDao {

    @Override
    public void save(Entreprise e) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {

            String query = "INSERT INTO entreprise (nom, adresse, telephone, email) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, e.getNom());
            preparedStatement.setString(2, e.getAdresse());
            preparedStatement.setString(3, e.getTelephone());
            preparedStatement.setString(4, e.getEmail());


            preparedStatement.executeUpdate();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }

    @Override
    public List<Entreprise> findAll() {
        List<Entreprise> entreprises = new ArrayList<>();
        Connection connection = DBSingleton.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM entreprise");

            while (resultSet.next()) {
                Entreprise entreprise = new Entreprise();
                entreprise.setIdEntreprise(resultSet.getInt("id_entreprise"));
                entreprise.setNom(resultSet.getString("nom"));
                entreprise.setAdresse(resultSet.getString("adresse"));
                entreprise.setTelephone(resultSet.getString("telephone"));
                entreprise.setEmail(resultSet.getString("email"));
                entreprises.add(entreprise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entreprises;
    }

    @Override
    public Entreprise findById(int id) {
        Entreprise entreprise = null;
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM entreprise WHERE id_entreprise = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entreprise = new Entreprise();
                entreprise.setIdEntreprise(resultSet.getInt("id_entreprise"));
                entreprise.setNom(resultSet.getString("nom"));
                entreprise.setAdresse(resultSet.getString("adresse"));
                entreprise.setTelephone(resultSet.getString("telephone"));
                entreprise.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entreprise;
    }

    @Override
    public void delete(int id) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // D'abord, supprimez les employés liés aux départements de l'entreprise
            String deleteEmployeesQuery = "DELETE e FROM employe e " +
                    "JOIN departement d ON e.id_departement = d.id_departement " +
                    "WHERE d.id_entreprise = ?";
            preparedStatement = connection.prepareStatement(deleteEmployeesQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            // Ensuite, supprimez les départements de l'entreprise
            String deleteDepartmentsQuery = "DELETE FROM departement WHERE id_entreprise = ?";
            preparedStatement = connection.prepareStatement(deleteDepartmentsQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            // Enfin, supprimez l'entreprise
            String deleteEntrepriseQuery = "DELETE FROM entreprise WHERE id_entreprise = ?";
            preparedStatement = connection.prepareStatement(deleteEntrepriseQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Entreprise entreprise) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "UPDATE entreprise SET nom = ?, adresse = ?, telephone = ?, email = ? WHERE id_entreprise = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, entreprise.getNom());
            preparedStatement.setString(2, entreprise.getAdresse());
            preparedStatement.setString(3, entreprise.getTelephone());
            preparedStatement.setString(4, entreprise.getEmail());
            preparedStatement.setInt(5, entreprise.getIdEntreprise());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Departement> findDepartementsByEntreprise(int idEntreprise) {
        List<Departement> departements = new ArrayList<>();
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM departement WHERE id_entreprise = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEntreprise);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Departement departement = new Departement();
                departement.setIdDepartement(resultSet.getInt("id_departement"));
                departement.setNom(resultSet.getString("nom"));

                Entreprise entreprise = new Entreprise();
                entreprise.setIdEntreprise(resultSet.getInt("id_entreprise"));
                //departement.setIdEntreprise(resultSet.getInt("id_entreprise"));

                departement.setEntreprise(entreprise);
                departements.add(departement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departements;
    }

    @Override
    public List<Entreprise> searchByKeyword(String keyword) {
        List<Entreprise> entreprises = new ArrayList<>();
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Remplacez "champ1", "champ2", etc. par les noms réels des champs de votre table Entreprise
            String query = "SELECT * FROM entreprise WHERE id_entreprise LIKE ? OR nom LIKE ? OR adresse LIKE ? OR telephone LIKE ? OR email LIKE ?";
            preparedStatement = connection.prepareStatement(query);

            // Remplacez "%" + keyword + "%" par le format de recherche réel en fonction de vos besoins
            for (int i = 1; i <= 5; i++) {
                preparedStatement.setString(i, "%" + keyword + "%");
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Créez et ajoutez votre objet Entreprise à la liste
                Entreprise entreprise = new Entreprise();
                entreprise.setIdEntreprise(resultSet.getInt("id_entreprise"));
                entreprise.setNom(resultSet.getString("nom"));
                entreprise.setAdresse(resultSet.getString("adresse"));
                entreprise.setTelephone(resultSet.getString("telephone"));
                entreprise.setEmail(resultSet.getString("email"));

                entreprises.add(entreprise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entreprises;
    }

    @Override
    public void printEntrepriseInfoToPDF(int idEntreprise) {
        // Implémentation de la génération de PDF ici
        // Vous pouvez utiliser des bibliothèques comme iText ou Apache PDFBox
        // Exemple simple pour iText : https://www.baeldung.com/java-pdf-creation-itext
    }

    // Méthode utilitaire pour fermer les ressources JDBC

}
