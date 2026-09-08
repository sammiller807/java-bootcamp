public class PropagationDemo {
    static void accountLayer()
           throws InsufficientFundsException { // throws InsufficientFundsException
        // Deepest layer creates the domain failure.
        // throw new InsufficientFundsException(100.00, 150.00)
        throw new InsufficientFundsException(100.00, 150.00);
    }

    static void serviceLayer()
           throws InsufficientFundsException { // throws InsufficientFundsException
        // No recovery here, so declare and let it propagate.
        accountLayer();
    }

    static void menuLayer()
            throws InsufficientFundsException { // throws InsufficientFundsException
        // Still no recovery action; keep the contract.
        serviceLayer();
    }

    public static void main(String[] args) {
        try {
            menuLayer();
        } catch (InsufficientFundsException ex) { // catch InsufficientFundsException
            // print "Caught at main: " + ex.getMessage()
            // ex.printStackTrace(System.out)
            System.out.println("Caught at main: " + ex.getLocalizedMessage());
            ex.printStackTrace(System.out);
        }
    }
}
