public class SavingsAccount extends Account {
    public SavingsAccount(double initialBalance) {
        // call parent constructor with initialBalance
        super(initialBalance);
    }

    @Override
    public String getAccountType() {
        // return "Savings"
        return "Savings";
    }
}