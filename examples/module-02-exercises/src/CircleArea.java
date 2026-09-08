import java.util.Scanner;

public class CircleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Radius: ");
        // read radius as a double (nextLine + Double.parseDouble)
        double r = Double.parseDouble(scanner.nextLine());

        // area = π × r²  (use Math.PI)
        double area = Math.PI * (r * r);

        // print area with two decimal places (printf %.2f)
        System.out.printf("Area: %.2f", area);

        scanner.close();
    }
}