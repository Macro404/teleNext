package doubleshot.teleNextBackend.service;

import doubleshot.teleNextBackend.model.User;
import doubleshot.teleNextBackend.model.UserDTO;
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
        return new User(userDTO.name(), userDTO.address(), userDTO.phoneNumber(), userDTO.personNumber());
    }

    public UserDTO  userToUserDto(User user){
        return new UserDTO(user.getName(), user.getAddress(), user.getPhoneNumber(), user.getPersonNumber());
    }
}
