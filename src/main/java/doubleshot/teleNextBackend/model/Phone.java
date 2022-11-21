package doubleshot.teleNextBackend.model;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
public class Phone {

    @Id
    String id;
    String model;
    double price;
    String camera;
    String cpu;
    String battery;
    String screen;
    @Column(length = 1500)
    String images;

    public Phone(String model, double price, String camera, String cpu, String battery, String screen, String images) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.price = price;
        this.camera = camera;
        this.cpu = cpu;
        this.battery = battery;
        this.screen = screen;
        this.images = images;
    }

    public Phone() {
        this.id = UUID.randomUUID().toString();
    }
}
