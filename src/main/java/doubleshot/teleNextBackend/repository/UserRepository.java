package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.Phone;
import doubleshot.teleNextBackend.model.ProductsDTO;
import doubleshot.teleNextBackend.model.Subscription;
import doubleshot.teleNextBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    SubscriptionJpaRepository subscriptionRepo;
    @Autowired
    PhoneJpaRepository phoneRepo;
    @Autowired
    UserJpaRepository repo;

    public ProductsDTO getAllProducts(){
        List<Phone> phones = new ArrayList<>();
        List<Subscription> subscriptions = new ArrayList<>();
        Iterable<Phone> phoneIterable = phoneRepo.findAll();
        Iterable<Subscription> subscriptionIterable = subscriptionRepo.findAll();
        phoneIterable.forEach(phones::add);
        subscriptionIterable.forEach(subscriptions::add);
        return new ProductsDTO(phones, subscriptions);
    }

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
