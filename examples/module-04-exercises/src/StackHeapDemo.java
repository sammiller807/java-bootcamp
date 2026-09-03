public class StackHeapDemo {
    static class Person {
        String name;
        Person(String name) {
            // this.name = name;
            this.name = name;
        }
    }

    static void printPerson(Person person) {
        // compute nameLength; print name + length
        int nameLength = person.name.length();
        System.out.printf("%s has %d letters.\n", person.name, nameLength);
    }

    public static void main(String[] args) {
        // create Person on heap; call printPerson; print a local count
        int count = 1;
        Person person = new Person("Sam");
        printPerson(person);
        System.out.println("Count: " + count);
    }
}
