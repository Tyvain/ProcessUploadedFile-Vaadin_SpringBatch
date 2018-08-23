package nc.unc.parcoursupui.student.repositories;

import org.springframework.data.repository.CrudRepository;

import nc.unc.parcoursupui.student.model.StudentHistory;

public interface StudentHistoryRepository extends CrudRepository<StudentHistory, Long> {
    public StudentHistory findFirstByOrderByJobExecutionDateDesc();
}
