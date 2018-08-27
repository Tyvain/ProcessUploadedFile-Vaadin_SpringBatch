package nc.unc.importparcoursup.dao2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = Student2History.TABLE_NAME)
public class Student2History {
    public final static String TABLE_NAME = "STUDENT_HISTORY";
       
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Date jobExecutionDate;
    
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "studentHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student2> students = new ArrayList<>();

    
    public Student2History() {
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

    public List<Student2> getStudents() {
	return students;
    }

    public void setStudents(List<Student2> students) {
	this.students = students;
    }

}
