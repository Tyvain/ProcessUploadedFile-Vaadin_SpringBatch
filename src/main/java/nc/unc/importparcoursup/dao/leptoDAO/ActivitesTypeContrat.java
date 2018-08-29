package nc.unc.importparcoursup.dao.leptoDAO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITES_TYPE_CONTRAT")
public class ActivitesTypeContrat {
    
    @Id
    private int atco_ordre;
    
    private String atco_libelle;

    public String getAtco_libelle() {
        return atco_libelle;
    }

    public void setAtco_libelle(String atco_libelle) {
        this.atco_libelle = atco_libelle;
    }

    public int getAtco_ordre() {
        return atco_ordre;
    }

    public void setAtco_ordre(int atco_ordre) {
        this.atco_ordre = atco_ordre;
    }
    
    
}
