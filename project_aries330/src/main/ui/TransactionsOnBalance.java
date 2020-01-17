package ui;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionsOnBalance {

    private JFrame balanceFrame;
    private JPanel panel;


    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static LocalDateTime now = LocalDateTime.now();

    public TransactionsOnBalance() {

        balanceFrame = new JFrame("BalanceTransaction");
        panel = new JPanel();

        String enterName = JOptionPane.showInputDialog("Please enter the name "
                + "of the receiver (organization or person) or account: ");
        String enterAmount = JOptionPane.showInputDialog("Please enter the amount that you want to wire: ");

        double amount = Double.parseDouble(enterAmount);
        double newBalance = Operations.balance.getBalance() - amount;

        if (amount > 0.0 && amount <= Operations.balance.getBalance()) {
            JOptionPane.showMessageDialog(null, "The amount you have wired is " + amount
                    + " and is now transferred successfully to " + enterName + ". Your transaction was processed on "
                    + dtf.format(now));
            JOptionPane.showMessageDialog(null, "Your balance in your balance account is "
                    + newBalance);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount!");
        }
    }

    public static void main(String[]args) {
        new TransactionsOnBalance();
    }
}
