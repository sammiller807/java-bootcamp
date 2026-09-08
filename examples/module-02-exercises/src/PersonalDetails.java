import java.util.Scanner;   // Scanner lives in java.util — must import it

public class PersonalDetails {
    public static void main(String[] args) {
        // create a Scanner reading from System.in
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        // read the whole line as a String
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        // read an int with nextInt()
        int age = scanner.nextInt();
        // consume the leftover newline after nextInt() (critical!)
        scanner.nextLine();

        System.out.print("Enter your city: ");
        // read the city with nextLine()
        String city = scanner.nextLine();

        // print a greeting with printf — %s for strings, %d for age, %n for newline
        System.out.printf("Hello, %s! You are %d years old and live in %s.%n", name, age, city);

        scanner.close();
    }
}