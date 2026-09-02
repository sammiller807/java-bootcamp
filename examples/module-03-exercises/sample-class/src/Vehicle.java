class Vehicle {
    String brand;
    int maxSpeed;

    Vehicle(String brand) {
        this.brand = brand;
    }

    void start() {
        System.out.println("Vehicle started");
    }
    void stop() {
        System.out.println("Vehicle stopped");
    }
}
class Car extends Vehicle {
    int numDoors;

    void playMusic() {
        System.out.println("Playing music");
    }

    Car(String brand, int numDoors) {
        super(brand);
        this.numDoors = numDoors;
    }

    @Override
    void start() {
        System.out.println("Car started with key");
    }


}