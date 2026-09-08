//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class WoraProbe {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        System.out.println(osName);

        System.out.println("Bytecode runs on: " + osName);
    }
}