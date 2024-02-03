package ma.enset.examenjdbcfx2.dao;

import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Entreprise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementDaoImpl implements DepartementDao {

    @Override
    public List<Departement> findAll() {
        Connection connection = DBSingleton.getConnection();
        List<Departement> departements = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT d.id_departement, d.nom AS nom_departement, e.id_entreprise, e.nom AS nom_entreprise " +
                    "FROM departement d " +
                    "JOIN entreprise e ON d.id_entreprise = e.id_entreprise";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Departement departement = new Departement();
                departement.setIdDepartement(resultSet.getInt("id_departement"));
                departement.setNom(resultSet.getString("nom_departement"));

                Entreprise entreprise = new Entreprise();
                entreprise.setIdEntreprise(resultSet.getInt("id_entreprise"));
                entreprise.setNom(resultSet.getString("nom_entreprise"));

                departement.setEntreprise(entreprise);
                departements.add(departement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }

        return departements;
    }

    @Override
    public Departement findById(int id) {
        Departement departement = null;
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM departement WHERE id_departement = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                // Ajoutez d'autres colonnes selon votre modèle de données

                departement = new Departement();
                departement.setIdDepartement(id);
                departement.setNom(nom);
                // Définissez d'autres propriétés selon votre modèle de données
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departement;
    }

    @Override
    public void save(Departement departement) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Assurez-vous que l'entreprise associée au département existe avant d'ajouter le département
            if (departement.getEntreprise() != null && entrepriseExists(departement.getEntreprise().getIdEntreprise())) {
                String query = "INSERT INTO departement (nom, id_entreprise) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, departement.getNom());
                preparedStatement.setInt(2, departement.getEntreprise().getIdEntreprise());

                preparedStatement.executeUpdate();

                System.out.println("Le département a été ajouté avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean entrepriseExists(int idEntreprise) throws SQLException {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT id_entreprise FROM entreprise WHERE id_entreprise = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEntreprise);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Si next() est vrai, l'entreprise existe
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void delete(int id) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Supprimez d'abord les employés associés
            String deleteEmployesQuery = "DELETE FROM employe WHERE id_departement = ?";
            preparedStatement = connection.prepareStatement(deleteEmployesQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            // Ensuite, supprimez le département
            String deleteDepartementQuery = "DELETE FROM departement WHERE id_departement = ?";
            preparedStatement = connection.prepareStatement(deleteDepartementQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Departement departement) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "UPDATE departement SET nom = ? WHERE id_departement = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, departement.getNom());
            // Ajoutez d'autres colonnes selon votre modèle de données
            preparedStatement.setInt(2, departement.getIdDepartement());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
