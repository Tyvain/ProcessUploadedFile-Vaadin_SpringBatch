package nc.unc.importparcoursup.dao.parcoursupDAO;

import org.springframework.data.repository.CrudRepository;

public interface StudentHistoryRepository extends CrudRepository<StudentHistory, Long> {
    public StudentHistory findFirstByOrderByJobExecutionDateDesc();
}
