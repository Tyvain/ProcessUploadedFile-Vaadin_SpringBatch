package nc.unc.importparcoursup.batch.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.opencsv.bean.CsvToBeanBuilder;

import nc.unc.importparcoursup.dao.parcoursupDAO.Student;
import nc.unc.importparcoursup.dao.parcoursupDAO.StudentHistory;

public class StudentReader implements ItemReader<Student> {
    private Iterator<Student> iteratorStudent;
    private StudentHistory studentHistory;

    public StudentReader(File inputFile, StudentHistory studentHistory) throws IOException {
	this.studentHistory = studentHistory;
	Reader reader = new BufferedReader(new FileReader(inputFile));
	iteratorStudent = new CsvToBeanBuilder<Student>(reader).withType(Student.class)
		.withIgnoreLeadingWhiteSpace(true)
		.withSeparator('	')
		.build()
		.iterator();
    }

    @Override
    public Student read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
	if (iteratorStudent.hasNext()) {
	    Student stu = iteratorStudent.next();
	    stu.setStudentHistory(studentHistory);
	    return stu;
	} else {
	    return null;
	}
    }

}
