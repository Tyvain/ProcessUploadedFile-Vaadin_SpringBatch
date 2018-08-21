package nc.unc.parcoursupui;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = Student.TABLE_NAME)
public class Student {
    public final static String TABLE_NAME = "STUDENT";

    @Id
    private Long id;

    
    private String groupe;

    
    private String classement;

    private String codeAmenagementOuiSi;

    
    private String numéro;

    private String codeFormation;

    
    private String formation;

    
    private String filière;

    public Student() {
	// default constructor
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getGroupe() {
	return groupe;
    }

    public void setGroupe(String groupe) {
	this.groupe = groupe;
    }

    public String getClassement() {
	return classement;
    }

    public void setClassement(String classement) {
	this.classement = classement;
    }

    public String getCodeAmenagementOuiSi() {
	return codeAmenagementOuiSi;
    }

    public void setCodeAmenagementOuiSi(String codeAmenagementOuiSi) {
	this.codeAmenagementOuiSi = codeAmenagementOuiSi;
    }

    public String getNuméro() {
	return numéro;
    }

    public void setNuméro(String numéro) {
	this.numéro = numéro;
    }

    public String getCodeFormation() {
	return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
	this.codeFormation = codeFormation;
    }

    public String getFormation() {
	return formation;
    }

    public void setFormation(String formation) {
	this.formation = formation;
    }

    public String getFilière() {
	return filière;
    }

    public void setFilière(String filière) {
	this.filière = filière;
    }

    public static String getTableName() {
	return TABLE_NAME;
    }

}
