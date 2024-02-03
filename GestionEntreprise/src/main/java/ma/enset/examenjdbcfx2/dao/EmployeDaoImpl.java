package ma.enset.examenjdbcfx2.dao;

import ma.enset.examenjdbcfx2.dao.entities.Departement;
import ma.enset.examenjdbcfx2.dao.entities.Employe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class EmployeDaoImpl implements EmployeDao {
    @Override
    public List<Employe> findAll() {
        Connection connection = DBSingleton.getConnection();
        List<Employe> employes = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM employe";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employe employe = new Employe();
                employe.setIdEmploye(resultSet.getInt("id_employe"));
                employe.setNom(resultSet.getString("nom"));
                employe.setPoste(resultSet.getString("poste"));
                employe.setSalaire(resultSet.getString("salaire"));

                // Création du département avec son ID
                Departement departement = new Departement();
                departement.setIdDepartement(resultSet.getInt("id_departement"));

                employe.setDepartement(departement);

                employes.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }

        return employes;
    }

    @Override
    public Employe findById(int id) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employe employe = null;

        try {
            String query = "SELECT * FROM employe WHERE id_employe = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employe = new Employe();
                employe.setIdEmploye(resultSet.getInt("id_employe"));
                employe.setNom(resultSet.getString("nom"));
                employe.setPoste(resultSet.getString("poste"));
                employe.setSalaire(resultSet.getString("salaire"));

                // Création du département avec son ID
                Departement departement = new Departement();
                departement.setIdDepartement(resultSet.getInt("id_departement"));

                employe.setDepartement(departement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }

        return employe;
    }

    @Override
    public void save(Employe employe) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Assurez-vous que le département associé à l'employé existe déjà dans la base de données
            if (employe.getDepartement() != null && departementExists(employe.getDepartement().getIdDepartement())) {

                String query = "INSERT INTO employe (nom, poste, salaire, id_departement) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, employe.getNom());
                preparedStatement.setString(2, employe.getPoste());
                preparedStatement.setString(3, employe.getSalaire());
                preparedStatement.setInt(4, employe.getDepartement().getIdDepartement());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "DELETE FROM employe WHERE id_employe = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }
    }

    @Override
    public void update(Employe employe) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Assurez-vous que le département associé à l'employé existe déjà dans la base de données
            if (employe.getDepartement() != null && departementExists(employe.getDepartement().getIdDepartement())) {

                String query = "UPDATE employe SET nom = ?,  poste = ?, salaire = ? WHERE id_employe = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, employe.getNom());
                preparedStatement.setString(2, employe.getPoste());
                preparedStatement.setString(3, employe.getSalaire());
                preparedStatement.setInt(4, employe.getIdEmploye());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }
    }

    // Méthode utilitaire pour vérifier si un département existe
    private boolean departementExists(int idDepartement) throws SQLException {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT id_departement FROM departement WHERE id_departement = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idDepartement);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Retourne vrai si le département existe
        } catch (SQLException e) {
            e.printStackTrace();
            return false   ;
        }

    }
}
