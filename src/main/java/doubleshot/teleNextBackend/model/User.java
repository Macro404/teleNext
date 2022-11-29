package doubleshot.teleNextBackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    private String id;

    @OneToMany(
            targetEntity = Subscription.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    private List<Subscription> subscriptions;

    private String name;
    private String email;
    private String address;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Transaction> transactions;



    private String phoneNumber;

    private String personNumber;

    public User(String name, String email, String address, String phoneNumber, String personNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personNumber = personNumber;
    }
    public User() {

    }

}
