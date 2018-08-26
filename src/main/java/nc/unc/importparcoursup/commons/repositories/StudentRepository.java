package nc.unc.importparcoursup.commons.repositories;

import org.springframework.data.repository.CrudRepository;

import nc.unc.importparcoursup.commons.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
