package afpa.mesdep.Model;

/**
 * Class Stagiaire
 * Permet de générer un stagiaire.
 * 
 * @author Caumartin François-Régis
 * @author Boyer Clément
 * @author Sipiere Germain
 */

public class Stagiaire 
{
    private String id;
    private String nom;
    private String prenom;
    private String numero;
    private String adresse;
    private String code_postal;
    private String ville;
    private String pol_eligible;
    private String pol_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numéro) {
        this.numero = numéro;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEligible() {
        return pol_eligible;
    }

    public void setEligible(String eligible) {
        this.pol_eligible = eligible;
    }

    public String getDateEligible() {
        return pol_date;
    }
    public void setDateEligible(String dateEligible) {
        this.pol_date = dateEligible;
    }
}
