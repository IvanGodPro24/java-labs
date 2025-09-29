import java.util.ArrayList;
import java.util.List;
public class Road {
private List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

public void addCarToRoad(Vehicle<? extends Human> car) {
        carsInRoad.add(car);
        }

public int getCountOfHumans() {
        return carsInRoad.stream()
        .mapToInt(v -> v.getPassengers().size())
        .sum();
    }
}
