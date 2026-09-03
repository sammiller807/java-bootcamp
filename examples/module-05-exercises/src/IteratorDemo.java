import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        // wrap List.of(...) in new ArrayList<>(...) so removal is allowed
        List<String> titles = new ArrayList<>();

        titles.add("Java 21");
        titles.add("Clean Code");
        titles.add("Deprecated");

        // obtain an Iterator<String> from titles
        Iterator<String> iterator = titles.iterator();

        // loop while iterator.hasNext()
        while (iterator.hasNext()) {
            String title = iterator.next();

            if (title.startsWith("Deprecated")) {
                // remove through the iterator (not titles.remove)
                iterator.remove();
            }
        }

        System.out.println("Remaining: " + titles);
    }
}
