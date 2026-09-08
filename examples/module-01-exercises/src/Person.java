public class Person {
    // Provided — do not rewrite boilerplate fields
    String name;
    int age;

    // assign parameters to fields using this.name / this.age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // print "<name> is <age> years old"
    public void display() {
        System.out.printf("%s is %d years old", name, age);
    }

    public static void main(String[] args) {
        // create Person("Aman", 21) and call display()
        Person emp = new Person("Aman", 21);
        emp.display();
    }
}