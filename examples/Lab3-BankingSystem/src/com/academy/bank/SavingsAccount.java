package com.academy.bank;

public class SavingsAccount extends Account implements Printable{
    private double interestRate;

    protected SavingsAccount(String accountNumber, double balance, Customer customer, double interestRate) {
        super(accountNumber, balance, customer);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate / 100;
    }

    @Override
    public void displayAccount() {
        System.out.printf("%s\n", getAccountType());
        System.out.printf("Account Number : %s\n", getAccountNumber());
        System.out.printf("Customer : %s\n", getCustomer().getName());
        System.out.printf("Balance : %.0f\n", getBalance());
        System.out.printf("Interest Rate : %.0f%%\n", interestRate);
        System.out.printf("Interest : %.0f\n", calculateInterest());
    }

    @Override
    public void printDetails() {
        displayAccount();
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
