package doubleshot.teleNextBackend.model;

import java.util.List;

public record CreateUserDTO(String name, String email, String address, String phoneNumber, String personNumber) {
}
