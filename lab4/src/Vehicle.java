import java.util.ArrayList;
import java.util.List;

public class Vehicle<T extends Human> {
    private int capacity;
    private List<T> passengers;

    public Vehicle(int capacity) {
        this.capacity = capacity;
        this.passengers = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void addPassenger(T passenger) throws Exception {
        if (passengers.size() >= capacity) {
            throw new Exception("Усі місця зайняті!");
        }
        passengers.add(passenger);
    }

    public void removePassenger(T passenger) throws Exception {
        if (!passengers.remove(passenger)) {
            throw new Exception("Цього пасажира немає у транспорті!");
        }
    }

    public List<T> getPassengers() {
        return passengers;
    }
}

class Bus extends Vehicle<Human> {
    public Bus(int capacity) {
        super(capacity);
    }
}

class Taxi extends Vehicle<Human> {
    public Taxi(int capacity) {
        super(capacity);
    }
}

class FireTruck extends Vehicle<Firefighter> {
    public FireTruck(int capacity) {
        super(capacity);
    }
}

class PoliceCar extends Vehicle<Policeman> {
    public PoliceCar(int capacity) {
        super(capacity);
    }
}
