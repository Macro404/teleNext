package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.Phone;
import doubleshot.teleNextBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    PhoneJpaRepository phoneRepo;

    @Autowired
    UserJpaRepository repo;

    public User getUserById(String id){
        return repo.findUserById(id);
    }

    public User saveUser(User user){
        return repo.save(user);
    }

    public void deleteUser(String id) {
        repo.deleteById(id);
    }

    public Phone savePhone(Phone phone) {return phoneRepo.save(phone);}

}
