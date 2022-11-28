package doubleshot.teleNextBackend.service;

import doubleshot.teleNextBackend.model.*;
import doubleshot.teleNextBackend.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    Repository repo;

    public UserDTO getUserById(String id){
        return userToDTO(repo.getUserById(id));
    }

    public User createUser(CreateUserDTO newUser) {

        System.out.println(newUser.name() + " "  + newUser.email());
        return repo.saveUser(DTOToUser(newUser));
    }

    public void deleteUser(String id) {
        repo.deleteUser(id);
    }

    public Phone addPhone(PhoneDTO dto) {
        return repo.savePhone(new Phone(dto.model(), dto.price(), dto.camera(), dto.cpu(), dto.battery(), dto.screen(), dto.images()));
    }

    public ProductsDTO getAllProducts(){
        return repo.getAllProducts();
    }

    public Subscription addSubscription(Subscription subscription) {
        return repo.saveSubscription(subscription);
    }

    public DataPlan addDataPlan(DataPlan plan) {
        return repo.saveDataPlan(plan);
    }

    public List<Subscription> getSubscriptionByEmail(String email){
        return repo.getSubscriptionByEmail(email);
    }

    public void deleteSubscription(String id) {
        repo.deleteSubscription(id);
    }

    public UserDTO getUserByEmail(String email) {
        return userToDTO(repo.findUserByEmail(email));
    }

    public UserDTO userToDTO(User user){
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAddress(), user.getPhoneNumber(), user.getTransactions(), user.getSubscriptions());
    }

    public User DTOToUser(CreateUserDTO createUserDTO){
        return new User(createUserDTO.name(), createUserDTO.email(), createUserDTO.address(), createUserDTO.phoneNumber(), createUserDTO.personNumber());
    }

    public void addOrder(OrderDTO orderDTO, String id) {
        List<Phone> phones = repo.findPhonesById(orderDTO.phoneIds());
        List<DataPlan> plans = repo.findDataPlansById(orderDTO.planIds());
        double total = phones.stream().map(phone -> phone.getPrice()).reduce(0.0, (a, b) -> a + b)
                + plans.stream().map(plan -> plan.getRate()).reduce(0.0, (a, b) -> a + b);
        List<String> descriptionList = phones.stream().map(phone -> phone.getModel()).distinct().toList();
        plans.stream().map(plan -> (plan.getData().toString())).distinct().forEach(descriptionList::add);
        String description = "";
        User user = repo.getUserById(id);
        for (String name : descriptionList){
            description.concat(name + ",");
        }
        for (DataPlan plan : plans) {
            repo.saveSubscription(new Subscription(user, plan.getRate(), plan.getData()));
        }
        repo.saveTransaction(new Transaction(description, new Date().toString(), total, user));
    }
}
