package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionJpaRepository extends CrudRepository<Subscription, String> {
}
