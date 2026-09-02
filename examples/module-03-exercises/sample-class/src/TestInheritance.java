public class TestInheritance {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", 5);
        myCar.start(); myCar.stop(); // inherited
        myCar.numDoors = 4; // own
        myCar.playMusic();
        myCar.brand = "Toyota"; // inherited
        myCar.maxSpeed = 180;
        System.out.println("Brand: " + myCar.brand);
        System.out.println("Max Speed: " + myCar.maxSpeed);
    }
}