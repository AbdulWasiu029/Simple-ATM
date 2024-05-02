import javax.swing.*;
import java.awt.*;

public class ATMGUI extends JFrame {
    private double balance;
    private final String adminUsername = "admin";
    private final String adminPassword = "123";

    public ATMGUI() {
        setTitle("ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Login dialog
        loginDialog();
    }

    private void loginDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField userField = new JTextField(10);
        JPasswordField passField = new JPasswordField(10);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (username.equals(adminUsername) && password.equals(adminPassword)) {
                // Login successful, show options
                showOptions();
            } else {
                // Invalid login, show error message and exit
                JOptionPane.showMessageDialog(null, "Invalid username or password. Exiting...");
                System.exit(0);
            }
        } else {
            // User cancelled, exit the application
            System.exit(0);
        }
    }

    private void showOptions() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(3, 1));
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        checkBalanceButton.addActionListener(e -> checkBalance());
        withdrawButton.addActionListener(e -> withdraw());
        depositButton.addActionListener(e -> deposit());
        optionsPanel.add(checkBalanceButton);
        optionsPanel.add(withdrawButton);
        optionsPanel.add(depositButton);
        add(optionsPanel, BorderLayout.CENTER);
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(null, "Your balance is: $" + balance);
    }

    private void withdraw() {
        String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    JOptionPane.showMessageDialog(null, "Withdrawal Successful");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Withdrawal Amount");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Input");
            }
        }
    }

    private void deposit() {
        String input = JOptionPane.showInputDialog("Enter amount to deposit:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    balance += amount;
                    JOptionPane.showMessageDialog(null, "Deposit Successful");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Deposit Amount");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Input");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ATMGUI().setVisible(true);
        });
    }
}
