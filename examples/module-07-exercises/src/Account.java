public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            // throw InsufficientFundsException
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
    }
}
