package doubleshot.teleNextBackend.repository;

import doubleshot.teleNextBackend.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class Repository {

    @Autowired
    SubscriptionJpaRepository subscriptionRepo;
    @Autowired
    PhoneJpaRepository phoneRepo;
    @Autowired
    UserJpaRepository userRepo;
    @Autowired
    DataPlanJpaRepository dataRepo;
    @Autowired
    TransactionJpaRepository transactionRepo;
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
        return userRepo.findUserById(id);
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public void deleteUser(String email) {
        userRepo.deleteUsersByEmail(email);
    }

    public Phone savePhone(Phone phone) {return phoneRepo.save(phone);}

    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepo.save(subscription);
    }

    public DataPlan saveDataPlan(DataPlan plan) {
        return dataRepo.save(plan);
    }

    public List<Subscription> getSubscriptionByEmail(String email){
        User user = userRepo.findUsersByEmail(email).iterator().next();
        List<Subscription> subscriptions = new ArrayList<>();
        Iterable<Subscription> subscriptionIterable = subscriptionRepo.findSubscriptionByUserId(user.getId());
        for(Subscription sub : subscriptionIterable){
            subscriptions.add(sub);
        }
        return subscriptions;
    }

    public void deleteSubscription(String id) {
        subscriptionRepo.deleteById(id);
    }

    public User findUserByEmail(String email) {
        return userRepo.findUsersByEmail(email).iterator().next();
    }

    public List<DataPlan> findDataPlansById(List<String> ids) {
        List<DataPlan> plans = ids.stream().map(id -> dataRepo.findById(id).get()).toList();
        return plans;
    }

    public List<Phone> findPhonesById(List<String> ids){
        List<Phone> phones = ids.stream().map(id -> phoneRepo.findById(id).get()).toList();
        return phones;
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
    }
}
