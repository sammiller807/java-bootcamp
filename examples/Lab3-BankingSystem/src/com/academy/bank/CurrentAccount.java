package com.academy.bank;

public class CurrentAccount extends Account implements Printable {
    private double transactionFee;

    protected CurrentAccount(String accountNumber, double balance, Customer customer, double transactionFee) {
        super(accountNumber, balance, customer);
        this.transactionFee = transactionFee;
    }

    @Override
    public double calculateCharges() {
        return transactionFee;
    }

    @Override
    public void displayAccount() {
        System.out.printf("%s\n", getAccountType());
        System.out.printf("Account Number : %s\n", getAccountNumber());
        System.out.printf("Customer : %s\n", getCustomer().getName());
        System.out.printf("Balance : %.0f\n", getBalance());
        System.out.printf("Transaction Fee : %.0f\n", transactionFee);
    }

    @Override
    public void printDetails() {
        displayAccount();
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
