// Abstract class (Abstraction)
abstract class ATM {
    abstract void checkBalance();
    abstract void withdraw(double amount);
    abstract void deposit(double amount);
}

// Encapsulation: Account class with private fields and public methods
class Account {
    private double balance;
    private int pin;

    // Constructor
    public Account(double balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Getter for PIN
    public int getPin() {
        return pin;
    }
}

// Inheritance and Polymorphism: ATMImplementation class inherits and implements methods
class ATMImplementation extends ATM {
    private Account account;

    // Constructor to initialize the account
    public ATMImplementation(Account account) {
        this.account = account;
    }

    // Method to check balance
    @Override
    void checkBalance() {
        System.out.println("Your current balance is: $" + account.getBalance());
    }

    // Method to withdraw money
    @Override
    void withdraw(double amount) {
        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds! Unable to withdraw.");
        } else {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawal successful! You withdrew $" + amount);
            checkBalance();
        }
    }

    // Method to deposit money
    @Override
    void deposit(double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful! You deposited $" + amount);
        checkBalance();
    }
}

// Main class
public class ATMProgram {
    public static void main(String[] args) {
        // Initialize an account with $1000 balance and PIN 1234
        Account account = new Account(1000, 1234);
        ATM atm = new ATMImplementation(account);

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("Welcome to the ATM Machine!");
        System.out.print("Please enter your PIN: ");
        int enteredPin = scanner.nextInt();

        // Verify PIN
        if (enteredPin == account.getPin()) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        } else {
            System.out.println("Incorrect PIN! Access denied.");
        }

        scanner.close();
    }
}
