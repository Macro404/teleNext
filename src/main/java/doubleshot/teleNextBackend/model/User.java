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
            mappedBy = "userId"
    )
    private List<Subscription> subscriptions;

    private String name;
    private String email;
    private String address;



    private String phoneNumber;

    private String personNumber;

    public User(String id, String name, String email, String address, String phoneNumber, String personNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personNumber = personNumber;
    }
    public User() {

    }

}
