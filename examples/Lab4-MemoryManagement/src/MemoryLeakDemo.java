import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDemo {

    static class Employee {
        private final int id;
        private final String name;
        private final byte[] profileData = new byte[256];

        Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String mode = args[0].toLowerCase();
        switch (mode) {
            case "leak" -> demonstrateLeak();
            case "fix" -> demonstrateFix();
            default -> printUsage();
        }
    }

    private static void demonstrateLeak() {
        System.out.println("===== Memory Leak Demonstration =====");
        System.out.println("Adding employees to a static list that is never cleared...");
        MemoryMonitor.printMemoryReport("Before Allocation");

        List<Employee> employees = LEAK_HOLDER.employees;
        int targetCount = 1_000_000;
        int step = 100_000;

        // add Employee(i, "Employee-" + i) for i=1.targetCount
        // every step objects, print count + MemoryMonitor.printMemoryReport
        for(int i = 1; i < targetCount; i++) {
            if(step == 0) {
                System.out.println("Added 100000 employees");
                MemoryMonitor.printMemoryReport("After 100000 Objects");
                step = 100_000;
            }
            employees.add(new Employee(i,"Employee-" + i));
            step--;
        }
    }

    private static void demonstrateFix() {
        System.out.println("===== Memory Leak Fix Demonstration =====");
        MemoryMonitor.printMemoryReport("Before Allocation");

        // create local ArrayList; add 500_000 employees; print After Allocation
        // clear list, null it, trigger GC, print After GC
        List<Employee> employees = new ArrayList<>();
        for(int i = 0; i < 500_000; i++) {
            employees.add(new Employee(i, "Employee-" + i));
        }
        MemoryMonitor.printMemoryReport("After Allocation");

        System.out.println("Clearing list to remove strong references...");
        employees.clear();
        employees = null;

        System.out.println("Triggering Garbage Collection...");
        MemoryMonitor.triggerGarbageCollection();

        MemoryMonitor.printMemoryReport("After GC");
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java MemoryLeakDemo leak");
        System.out.println("  java MemoryLeakDemo fix");
    }

    private static class LeakHolder {
        private final List<Employee> employees = new ArrayList<>();
    }

    private static final LeakHolder LEAK_HOLDER = new LeakHolder();
}
