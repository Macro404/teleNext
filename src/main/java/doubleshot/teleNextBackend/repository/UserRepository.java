package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.*;
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
    @Autowired
    DataPlanJpaRepository dataRepo;

    public ProductsDTO getAllProducts(){
        List<Phone> phones = new ArrayList<>();
        List<DataPlan> dataPlans = new ArrayList<>();
        Iterable<Phone> phoneIterable = phoneRepo.findAll();
        Iterable<DataPlan> dataPlanIterable = dataRepo.findAll();
        for(Phone phone : phoneIterable){
            phones.add(phone);
        }
        for(DataPlan dataPlan: dataPlanIterable){
            dataPlans.add(dataPlan);
        }
        return new ProductsDTO(phones, dataPlans);
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

    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepo.save(subscription);
    }

    public DataPlan saveDataPlan(DataPlan plan) {
        return dataRepo.save(plan);
    }

    public List<Subscription> getSubscriptionByEmail(String email){
        User user = repo.findUsersByEmail(email).iterator().next();
        System.out.println(user.getId());
        List<Subscription> subscriptions = new ArrayList<>();
        Iterable<Subscription> subscriptionIterable = subscriptionRepo.findSubscriptionByUserId(user.getId());
        for(Subscription sub : subscriptionIterable){
            subscriptions.add(sub);
            System.out.println(sub.getPhoneRate());
        }
        return subscriptions;
    }
}
