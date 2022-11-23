package doubleshot.teleNextBackend.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
@Getter @Setter
public class Subscription {

    @Id
    @GeneratedValue(generator =  "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true)
    private String id;

    @JoinColumn(name = "user_id")
    private String userId;

    @Column(name = "data_rate")
    private double dateRate;

    @Column(name = "phone_rate")
    private double phoneRate;

    private double data;

    public Subscription(){

    }
}
