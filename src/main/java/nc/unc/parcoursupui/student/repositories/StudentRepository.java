package nc.unc.parcoursupui.student.repositories;

import org.springframework.data.repository.CrudRepository;

import nc.unc.parcoursupui.student.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
