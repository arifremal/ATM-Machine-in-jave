import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Account class with encapsulation
class Account {
    private double balance;
    private int pin;

    public Account(double balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }
}

// GUI class for ATM
public class ATMGUI {
    private Account account;
    private JFrame frame;
    private JTextField pinField, amountField;
    private JLabel messageLabel;
    private JButton checkBalanceButton, withdrawButton, depositButton;

    public ATMGUI() {
        // Initialize an account with $1000 balance and PIN 1234
        account = new Account(1000, 1234);
        createUI();
    }

    // Create the GUI
    private void createUI() {
        frame = new JFrame("ATM Machine");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        // PIN entry panel
        JPanel pinPanel = new JPanel(new FlowLayout());
        pinPanel.add(new JLabel("Enter PIN:"));
        pinField = new JTextField(10);
        pinPanel.add(pinField);
        JButton pinSubmitButton = new JButton("Submit");
        pinSubmitButton.addActionListener(new PinSubmitListener());
        pinPanel.add(pinSubmitButton);

        // Message label
        messageLabel = new JLabel("Welcome to the ATM!", JLabel.CENTER);
        messageLabel.setForeground(Color.BLUE);

        // Amount entry panel
        JPanel amountPanel = new JPanel(new FlowLayout());
        amountPanel.add(new JLabel("Enter Amount:"));
        amountField = new JTextField(10);
        amountField.setEnabled(false); // Disable until PIN is verified
        amountPanel.add(amountField);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        checkBalanceButton = new JButton("Check Balance");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        JButton exitButton = new JButton("Exit");

        // Disable buttons until PIN is verified
        checkBalanceButton.setEnabled(false);
        withdrawButton.setEnabled(false);
        depositButton.setEnabled(false);

        // Add action listeners for buttons
        checkBalanceButton.addActionListener(e -> checkBalance());
        withdrawButton.addActionListener(e -> withdraw());
        depositButton.addActionListener(e -> deposit());
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the panel
        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(exitButton);

        // Add components to the frame
        frame.add(pinPanel);
        frame.add(messageLabel);
        frame.add(amountPanel);
        frame.add(buttonPanel);

        frame.setVisible(true);
    }

    // Listener to enable buttons after successful PIN entry
    private class PinSubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int enteredPin = Integer.parseInt(pinField.getText());
                if (enteredPin == account.getPin()) {
                    messageLabel.setText("PIN Verified! You can now use the ATM.");
                    messageLabel.setForeground(Color.GREEN);
                    amountField.setEnabled(true);
                    checkBalanceButton.setEnabled(true);
                    withdrawButton.setEnabled(true);
                    depositButton.setEnabled(true);
                } else {
                    messageLabel.setText("Incorrect PIN! Try again.");
                    messageLabel.setForeground(Color.RED);
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid PIN format! Enter digits only.");
                messageLabel.setForeground(Color.RED);
            }
        }
    }

    // Check balance method
    private void checkBalance() {
        messageLabel.setText("Current Balance: $" + account.getBalance());
        messageLabel.setForeground(Color.BLUE);
    }

    // Withdraw method
    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > account.getBalance()) {
                messageLabel.setText("Insufficient funds! Cannot withdraw.");
                messageLabel.setForeground(Color.RED);
            } else if (amount <= 0) {
                messageLabel.setText("Enter a valid amount to withdraw!");
                messageLabel.setForeground(Color.RED);
            } else {
                account.setBalance(account.getBalance() - amount);
                messageLabel.setText("Withdrawn: $" + amount + ". New Balance: $" + account.getBalance());
                messageLabel.setForeground(Color.BLUE);
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount! Enter digits only.");
            messageLabel.setForeground(Color.RED);
        }
    }

    // Deposit method
    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                messageLabel.setText("Enter a valid amount to deposit!");
                messageLabel.setForeground(Color.RED);
            } else {
                account.setBalance(account.getBalance() + amount);
                messageLabel.setText("Deposited: $" + amount + ". New Balance: $" + account.getBalance());
                messageLabel.setForeground(Color.BLUE);
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount! Enter digits only.");
            messageLabel.setForeground(Color.RED);
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMGUI::new);
    }
}
