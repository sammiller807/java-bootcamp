import java.util.Scanner;

public class BillSummary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Product name: ");
        // read name
        String name = scanner.nextLine();

        System.out.print("Quantity: ");
        // read qty (nextLine + Integer.parseInt)
        int qty = Integer.parseInt(scanner.nextLine());

        System.out.print("Unit price: ");
        // read price (nextLine + Double.parseDouble)
        double price = Double.parseDouble(scanner.nextLine());

        // compute total, 10% discount, and final amount
        double total = qty * price;           // qty * price
        double discount = total * 0.1;        // 10% of total
        double finalAmount = total - discount;     // total - discount

        System.out.println("--- Bill Summary ---");
        // print Product, Quantity, Unit price, Total, Discount (10%), Final amount
        // hints: %.2f for money; use 10%% in the format string to print a literal %
        System.out.printf("Product: %s\nQuantity: %d\nUnit Price: %.2f\nTotal: %.2f\nDiscount (10%%): %.2f\nFinal amount: %.2f", name, qty, price, total, discount, finalAmount);

        scanner.close();
    }
}