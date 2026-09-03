package com.academy.bank;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BankService {
    private static final int MAX_CUSTOMERS = 50;
    private static final int MAX_ACCOUNTS = 100;
    private static final int MAX_TRANSACTIONS = 500;

    private final Customer[] customers = new Customer[MAX_CUSTOMERS];
    private final Account[] accounts = new Account[MAX_ACCOUNTS];
    private final Transaction[] transactions = new Transaction[MAX_TRANSACTIONS];

    private int customerCount = 0;
    private int accountCount = 0;
    private int transactionCount = 0;
    private int nextAccountNumber = 10001;
    private int nextTransactionNumber = 1;

    private final Scanner scanner;

    public BankService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createCustomer() {
        try {
            System.out.print("Customer ID : ");
            String id = scanner.nextLine().trim();

            if(findCustomer(id) != null) {
                System.out.println("ID already exists. Please try again.");
            }

            System.out.print("Name : ");
            String name = scanner.nextLine().trim();
            if(name.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again");
                return;
            }

            System.out.print("Email : ");
            String email = scanner.nextLine().trim();
            if(email.isEmpty()) {
                System.out.println("Email cannot be empty. Please try again");
                return;
            }

            System.out.print("Phone : ");
            String phone = scanner.nextLine().trim();
            if(phone.isEmpty()) {
                System.out.println("Phone number cannot be empty. Please try again");
                return;
            }

            customers[customerCount++] = new Customer(id, name, email, phone);
            System.out.println("Customer Created Successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }
    public void createSavingsAccount() {
        try {
            Customer customer = readExistingCustomer();

            double balance = readPositiveAmount("Initial Balance : ");
            double interestRate = readPositiveAmount("Interest Rate : ");

            accounts[accountCount++] = new SavingsAccount(String.valueOf(nextAccountNumber++), balance, customer, interestRate);
            System.out.println("Savings Account Created.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public void createCurrentAccount() {
        try {
            Customer customer = readExistingCustomer();
            double balance = readPositiveAmount("Initial Balance : ");
            double transactionFee = readPositiveAmount("Transaction Fee : ");

            accounts[accountCount++] = new CurrentAccount(String.valueOf(nextAccountNumber++), balance, customer, transactionFee);
            System.out.println("Savings Account Created.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public void deposit() {
        // read existing account + amount; account.deposit; recordTransaction DEPOSIT
        // print updated balance
        Account account = readExistingAccount();

        if(account == null) {
            System.out.println("Account does not exist. Please try again.");
            return;
        }

        double amount = readPositiveAmount("Amount : ");
        account.deposit(amount);

        recordTransaction(account.getAccountNumber(), amount, account.getAccountType());
        System.out.println("Balance Updated : " + account.getBalance());

    }

    public void withdraw() {
        // read existing account + amount; account.withdraw; record on success
        // for CurrentAccount, print fee + total deducted; print updated balance
        Account account = readExistingAccount();
        if(account == null) {
            System.out.println("Account does not exist. Please try again.");
            return;
        }

        double amount = readPositiveAmount("Amount : ");
        if(account.withdraw(amount)) {
            recordTransaction(account.getAccountNumber(), amount, account.getAccountType());
        } else {
            System.out.println("Withdraw Failed. Please try again.");
            return;
        }

        if(account.getAccountType().equals("Current")) {
            System.out.println("Fee : " + account.calculateCharges());
        }

        System.out.println("Balance Updated : " + account.getBalance());

    }

    public void displayAccounts() {
        // if empty print message; else loop displayAccount() for each
        if (accountCount == 0) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("----------------------------------");
        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccount();
            System.out.println("----------------------------------");
        }
    }

    public void displayCustomers() {
        if (customerCount == 0) {
            System.out.println("No customers available.");
            return;
        }

        System.out.println("----------------------------------");
        for (int i = 0; i < customerCount; i++) {
            customers[i].display();
            System.out.println("----------------------------------");
        }
    }

    public void transferMoney() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayTransactionHistory() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayAccountsSortedByBalance() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayHighestBalanceCustomer() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void generateAccountSummaryReport() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    private Customer findCustomer(String customerId) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equalsIgnoreCase(customerId)) {
                return customers[i];
            }
        }
        return null;
    }

    private Account findAccount(String accountNumber) {
        for(int i = 0; i < accountCount; i++) {
            if(accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }

    private Account readExistingAccount() {
        if (accountCount == 0) {
            System.out.println("No accounts available.");
            return null;
        }

        System.out.print("Account Number : ");
        String accountNumber = scanner.nextLine().trim();
        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
        }

        return account;
    }

    private Customer readExistingCustomer() {
        if (customerCount == 0) {
            System.out.println("Create a customer first.");
            return null;
        }

        System.out.print("Customer ID : ");
        String customerId = scanner.nextLine().trim();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
        }

        return customer;
    }

    private void recordTransaction(String accountNumber, double amount, String type) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            return;
        }

        String transactionId = "T" + nextTransactionNumber++;
        String date = LocalDate.now().toString();
        transactions[transactionCount++] = new Transaction(transactionId, amount, type, date, accountNumber);
    }

    private double readPositiveAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Amount must not be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid amount. Please try again.");
            }
        }
    }
}
