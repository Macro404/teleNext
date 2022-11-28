package doubleshot.teleNextBackend.model;

import java.util.List;

public record OrderDTO(List<String> phoneIds, List<String> planIds) {
}
