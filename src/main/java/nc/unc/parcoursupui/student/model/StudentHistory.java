package nc.unc.parcoursupui.student.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = StudentHistory.TABLE_NAME)
public class StudentHistory {
    public final static String TABLE_NAME = "STUDENT_HISTORY";
       
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date jobExecutionDate;
    
    @OneToMany
    private List<Student> students = new ArrayList<>();

    
    public StudentHistory() {
	jobExecutionDate = new Date();
    }
    
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }    

    public Date getJobExecutionDate() {
        return jobExecutionDate;
    }

    public void setJobExecutionDate(Date jobExecutionDate) {
        this.jobExecutionDate = jobExecutionDate;
    }

    public List<Student> getStudents() {
	return students;
    }

    public void setStudents(List<Student> students) {
	this.students = students;
    }

}
