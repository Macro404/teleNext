package doubleshot.teleNextBackend.model;

import java.util.List;

public record UserDTO(String id, String name, String email, String address, String phoneNumber, List<Transaction> transactionList, List<Subscription> subscriptionList) {
}
