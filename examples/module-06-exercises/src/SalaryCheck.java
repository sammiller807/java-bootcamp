@FunctionalInterface
public interface SalaryCheck {
    // declare one abstract method that takes an Employee and returns boolean
    boolean test(Employee employee);
}