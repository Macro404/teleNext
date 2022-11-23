package doubleshot.teleNextBackend.model;

import java.util.List;

public record ProductsDTO(List<Phone> phones, List<DataPlan> dataPlans) {
}
