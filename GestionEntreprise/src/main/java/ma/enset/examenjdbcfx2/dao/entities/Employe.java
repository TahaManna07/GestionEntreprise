package ma.enset.examenjdbcfx2.dao.entities;

public class Employe {
    private int idEmploye;
    private String nom;
    private String poste;
    private String salaire;
    private Departement departement;

    public Employe(int idEmploye, String nom, String poste, String salaire, Departement departement) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
        this.departement = departement;
    }

    public Employe() {}



    // Setters and Getters
    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }



    // toString method
    @Override
    public String toString() {
        return "Employe{" +
                "idEmploye=" + idEmploye +
                ", nom='" + nom + '\'' +
                ", poste='" + poste + '\'' +
                ", salaire='" + salaire + '\'' +
                ", idDepartement "+ departement.getIdDepartement() +
                '}';
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
