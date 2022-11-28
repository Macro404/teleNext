package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionJpaRepository extends CrudRepository <Transaction, String> {
}
