package doubleshot.teleNextBackend.service;

import doubleshot.teleNextBackend.model.*;
import doubleshot.teleNextBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    public UserDTO getUserById(String id){
        return userToUserDto(userRepo.getUserById(id));
    }

    public User createUser(UserDTO userDTO) {
        return userRepo.saveUser(userDtoToUser(userDTO));
    }

    public User userDtoToUser(UserDTO userDTO){
        return new User(userDTO.id(), userDTO.name(), userDTO.email(), userDTO.address(), userDTO.phoneNumber(), userDTO.personNumber());
    }

    public UserDTO  userToUserDto(User user){
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAddress(), user.getPhoneNumber(), user.getPersonNumber());
    }

    public void deleteUser(String id) {
        userRepo.deleteUser(id);
    }

    public Phone addPhone(PhoneDTO dto) {
        return userRepo.savePhone(new Phone(dto.model(), dto.price(), dto.camera(), dto.cpu(), dto.battery(), dto.screen(), dto.images()));
    }

    public ProductsDTO getAllProducts(){
        return userRepo.getAllProducts();
    }

    public Subscription addSubscription(Subscription subscription) {
        return userRepo.saveSubscription(subscription);
    }

    public DataPlan addDataPlan(DataPlan plan) {
        return userRepo.saveDataPlan(plan);
    }
}
