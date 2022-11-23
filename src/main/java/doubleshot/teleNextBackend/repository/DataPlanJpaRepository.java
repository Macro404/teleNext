package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.DataPlan;
import org.springframework.data.repository.CrudRepository;

public interface DataPlanJpaRepository extends CrudRepository<DataPlan, String> {
}
