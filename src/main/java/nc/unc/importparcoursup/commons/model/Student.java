package nc.unc.importparcoursup.commons.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;

@Entity
@Table(name = Student.TABLE_NAME)
public class Student {
    public final static String TABLE_NAME = "STUDENT";
    public final static String STUDENT_HISTORY_ID = "STUDENT_HISTORY_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = STUDENT_HISTORY_ID)
    private StudentHistory studentHistory;

    @CsvBindByName
    private String groupe;

    @CsvBindByName
    private String classement;

    @CsvBindByName(column = "Code aménagement (oui - si)")
    private String codeAmenagementOuiSi;

    @CsvBindByName
    private String numéro;

    @CsvBindByName(column = "Code formation", required = true)
    private String codeFormation;

    @CsvBindByName
    private String formation;

    @CsvBindByName
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

    public StudentHistory getStudentHistory() {
	return studentHistory;
    }

    public void setStudentHistory(StudentHistory studentHistory) {
	this.studentHistory = studentHistory;
    }

}
