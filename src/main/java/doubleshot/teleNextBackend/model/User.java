package doubleshot.teleNextBackend.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
            mappedBy = "user_id"
    )
    private List<Subscription> subscriptions;

    private String name;

    private String address;

    private String phoneNumber;

    private String personNumber;

    public User(String name, String address, String phoneNumber, String personNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personNumber = personNumber;
    }
    public User() {

    }

}
