import java.util.HashMap;
import java.util.Scanner;

class Banky {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Banky(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void transfer(Banky toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred: $" + amount + " to account " + toAccount.getAccountNumber());
        } else {
            System.out.println("Invalid transfer amount or insufficient funds.");
        }
    }
}

public class BankingSystem {
    private HashMap<String, Banky> accounts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // consume the newline

        Banky account = new Banky(accountNumber, accountHolder, initialBalance);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully.");
    }

    public void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Banky account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume the newline
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Banky account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume the newline
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer() {
        System.out.print("Enter source account number: ");
        String fromAccountNumber = scanner.nextLine();
        Banky fromAccount = accounts.get(fromAccountNumber);
        if (fromAccount != null) {
            System.out.print("Enter destination account number: ");
            String toAccountNumber = scanner.nextLine();
            Banky toAccount = accounts.get(toAccountNumber);
            if (toAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // consume the newline
                fromAccount.transfer(toAccount, amount);
            } else {
                System.out.println("Destination account not found.");
            }
        } else {
            System.out.println("Source account not found.");
        }
    }

    public void showBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Banky account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Show Balance");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    showBalance();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        BankingSystem system = new BankingSystem();
        system.start();
    }
}
