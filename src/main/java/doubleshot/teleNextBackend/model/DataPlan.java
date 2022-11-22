package doubleshot.teleNextBackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_plans")
@Getter @Setter
public class DataPlan {

    @Id
    private String id;

    private double rate;

    private double data;

    public DataPlan() {
    }
}
