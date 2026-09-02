package com.bank.savings;
import com.bank.account.Account;

public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public void showInfo() {
        System.out.println(interestRate); // OK
        System.out.println(bankName); // OK
    }
}