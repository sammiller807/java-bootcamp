import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        // create HashSet<String> categories
        Set<String> categories = new HashSet<>();

        // add "Java" and print whether the set changed (add returns boolean)
        System.out.println(
                "Added Java first time: "
                + categories.add("Java"));

        // add "Testing" and "Databases"
        categories.add("Testing");
        categories.add("Databases");

        // try adding "Java" again — print whether the set changed
        System.out.println(
                "Added Java second time: "
                + categories.add("Java"));

        System.out.println(
                "Unique count: " + categories.size());
        System.out.println(
                "Contains Testing: "
                + categories.contains("Testing"));

        // print sorted view using new TreeSet<>(categories)
        System.out.println(
                "Sorted view: "
                + new TreeSet<>(categories));
    }
}
