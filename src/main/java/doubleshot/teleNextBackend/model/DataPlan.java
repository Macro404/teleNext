package doubleshot.teleNextBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_plan")
public class DataPlan {

    @Id
    private String id;

    private double rate;

    private double data;
}
