package ma.enset.examenjdbcfx2.dao.entities;

public class Departement {
    private int idDepartement;
    private String nom;
    private int idEntreprise; // Association avec Entreprise
    private Entreprise entreprise;

    public Departement() {
    }

    public Departement(String nom, int idEntreprise) {
        this.nom = nom;
        this.idEntreprise = idEntreprise;
    }

    // Setters and Getters
    public int getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Entreprise getEntreprise() {
        return  entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

        // Autres propriétés et méthodes de la classe

        @Override
        public String toString() {
            // Utilisez this.entreprise.getIdEntreprise() sans vérification null
            return "Departement{" +
                    "idDepartement=" + idDepartement +
                    ", nom='" + nom + '\'' +
                    ", entreprise=" +  entreprise.getIdEntreprise()  +
                    '}';
        }

        // Autres constructeurs et méthodes de la classe
    }

    // toString method



    
