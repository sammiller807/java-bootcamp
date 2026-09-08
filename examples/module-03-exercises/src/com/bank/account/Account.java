package com.bank.account;
public class Account {
    private String accountNumber; // private
    double balance; // default
    protected double interestRate; // protected
    public String bankName; // public

    // Constructor
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = 4.5;
        this.bankName = "Trusted Bank";
    }
}