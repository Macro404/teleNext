package doubleshot.teleNextBackend.service;

import doubleshot.teleNextBackend.model.User;
import doubleshot.teleNextBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class Service {

    @Autowired
    UserRepository userRepo;

    public User getUserById(String id){
        return userRepo.getUserById(id);
    }
}
