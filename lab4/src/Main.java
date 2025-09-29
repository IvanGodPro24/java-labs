public class Main {
    public static void main(String[] args) {
        try {
            Road road = new Road();

            Bus bus = new Bus(3);
            bus.addPassenger(new Passenger());
            bus.addPassenger(new Policeman());
            bus.addPassenger(new Firefighter());

            Policeman policeman = new Policeman();


            Taxi taxi = new Taxi(2);
            taxi.addPassenger(new Passenger());

            FireTruck fireTruck = new FireTruck(2);
            fireTruck.addPassenger(new Firefighter());

            PoliceCar policeCar = new PoliceCar(2);
            policeCar.addPassenger(policeman);

            road.addCarToRoad(bus);
            road.addCarToRoad(taxi);
            road.addCarToRoad(fireTruck);
            road.addCarToRoad(policeCar);

            System.out.println("Загальна кількість людей на дорозі: " + road.getCountOfHumans());
            System.out.println("Кількість місць у поліції: " + policeCar.getCapacity());

            System.out.println("Кількість зайнятих місць у поліції: " + policeCar.getOccupiedSeats());

            policeCar.removePassenger(policeman);

            System.out.println("Кількість зайнятих місць у поліції: " + policeCar.getOccupiedSeats());


        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
