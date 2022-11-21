package doubleshot.teleNextBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
public class Phone {

    @Id
    String id;
    String model;
    double price;
    String camera;
    String cpu;
    String battery;
    String screen;

    public Phone(String model, double price, String camera, String cpu, String battery, String screen) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.camera = camera;
        this.cpu = cpu;
        this.battery = battery;
        this.screen = screen;
    }

    public Phone() {

    }
}
