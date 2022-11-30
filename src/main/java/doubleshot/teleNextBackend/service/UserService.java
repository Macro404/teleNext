package doubleshot.teleNextBackend.service;

import doubleshot.teleNextBackend.model.*;
import doubleshot.teleNextBackend.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    public void deleteUser(String email) {
        repo.deleteUser(email);
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
        System.out.println(phones.get(0).getModel());
        System.out.println(plans.get(0).getData().toString());
        double total = phones.stream().map(phone -> phone.getPrice()).reduce(0.0, (a, b) -> a + b)
                + plans.stream().map(plan -> plan.getRate()).reduce(0.0, (a, b) -> a + b);
        List<String> modelList = phones.stream().map(phone -> phone.getModel()).distinct().toList();
        List<String> planList = plans.stream().map(plan -> (((Integer) plan.getData().intValue()).toString())).distinct().toList();
        String description = "";
        User user = repo.getUserById(id);
        for (String model : modelList){
            model = model.concat(", ");
            System.out.println("model: " + model);
            description = description.concat(model);
        }
        for (String plan : planList){
            plan = plan.concat("GB, ");
            System.out.println("plan: " + plan);
            description = description.concat(plan);
        }
        for (DataPlan plan : plans) {
            repo.saveSubscription(new Subscription(user, plan.getRate(), plan.getData()));
        }
        System.out.println("description: " + description);
        repo.saveTransaction(new Transaction(description.substring(0, description.length() - 2), new SimpleDateFormat("dd/MM").format(new Date()) + ": ", total, user));
    }
}
