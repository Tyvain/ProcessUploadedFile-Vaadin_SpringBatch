package nc.unc.importparcoursup.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import nc.unc.importparcoursup.commons.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
