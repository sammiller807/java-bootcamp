import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static void main(String[] args) {
        System.out.println("===== Weak Reference Demonstration =====");

        System.out.println("--- Strong Reference ---");
        Person strongPerson = new Person("Strong User", 40);
        System.out.println("Before GC : " + strongPerson);
        MemoryMonitor.triggerGarbageCollection();
        System.out.println("After GC  : " + strongPerson);
        System.out.println("Object remains because a strong reference still exists.");

        System.out.println();
        System.out.println("--- Weak Reference ---");
        // create Person weakTarget; wrap in WeakReference<Person>
        // null weakTarget; trigger GC; print WeakReference.get() result

        Person weakTarget = new Person("Weak User", 40);
        WeakReference<Person> weakReference = new WeakReference<Person>(weakTarget);

        System.out.println("Before removing strong reference : ...");

        weakTarget = null;
        System.out.println("Strong reference removed");

        MemoryMonitor.triggerGarbageCollection();

        System.out.println("After GC via WeakReference.get() : " + weakReference.get());
        System.out.println("Observation:\n- WeakReference allows GC to collect the object when only weak refs remain");
    }
}
