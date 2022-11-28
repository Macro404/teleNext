package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhoneJpaRepository extends CrudRepository<Phone, String> {
}
