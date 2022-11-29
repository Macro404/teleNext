package doubleshot.teleNextBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "transaction_history")
@Getter @Setter
public class Transaction {

    @Id
    @GeneratedValue(generator =  "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String description;

    private String date;

    private double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Transaction(String description, String date, double amount, User user) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.user = user;
    }

    public Transaction() {
    }
}
