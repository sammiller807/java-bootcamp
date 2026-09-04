import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SalaryExtremesDemo {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeData.sample();

        // max by salary
        Optional<Employee> highest = employees.stream()
                // .max(...)
                .max(Comparator.comparingDouble(Employee::salary))
                ;

        // min by salary — new stream
        Optional<Employee> lowest = employees.stream()
                // .min(...)
                .min(Comparator.comparingDouble(Employee::salary))
                ;

        highest.ifPresent(e -> System.out.printf("Highest: %s - %.0f%n", e.name(), e.salary()));
        lowest.ifPresent(e -> System.out.printf("Lowest: %s - %.0f%n", e.name(), e.salary()));
    }
}
