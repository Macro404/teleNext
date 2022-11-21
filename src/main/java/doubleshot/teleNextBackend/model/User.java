package doubleshot.teleNextBackend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

public class User {

    @Id
    @GeneratedValue(generator =  "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true)
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
}
