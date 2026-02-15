package Group4.tracer.repository;

import Group4.tracer.model.ChangeLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//allows us to use autowired in our controller to use this tool
@Repository
public interface ChangeLogRepository extends CrudRepository<ChangeLog, String> {
}
