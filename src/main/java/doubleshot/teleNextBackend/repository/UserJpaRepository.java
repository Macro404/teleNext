package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserJpaRepository extends CrudRepository<User, String> {
    User findUserById(String id);
    Iterable<User> findUsersByEmail(String email);

    @Transactional
    void deleteUsersByEmail(String email);

}
