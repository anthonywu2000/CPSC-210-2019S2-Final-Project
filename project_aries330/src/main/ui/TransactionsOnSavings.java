package ui;

import model.SavingsAccount;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionsOnSavings {

    private JFrame savingsFrame;
    private JPanel panel;
    private final double transferFee = 20.00;

    public static SavingsAccount s = new SavingsAccount("HelloWorld", 99999, 18000.00,
            false);

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static LocalDateTime now = LocalDateTime.now();

    public TransactionsOnSavings() {

        savingsFrame = new JFrame("SavingsTransaction");
        panel = new JPanel();

        String enterName = JOptionPane.showInputDialog("Please enter the name "
                + "of the receiver (organization or person) or account: ");
        String enterAmount = JOptionPane.showInputDialog("Please enter the amount that you want to wire: ");

        double amount = (Double.parseDouble(enterAmount)) + transferFee;
        double actualAmount = amount - transferFee;
        double changedInterestRate = 0.02;
        double newSavingsBalance = s.getBalance() - amount;

        if (amount > 0.0 && amount <= Operations.balance.getBalance()) {
            JOptionPane.showMessageDialog(null, "The amount you have wired (an additional "
                    + "$20.0 transfer fee is charged from your savings account) is " + amount
                    + " and the actual amount " + actualAmount + " is now transferred successfully to " + enterName
                    + ". Your transaction was processed on " + dtf.format(now));
            JOptionPane.showMessageDialog(null, "Your interest rate is "
                    + "now changed to " + changedInterestRate + " and your savings balance is " + newSavingsBalance);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount!");
        }
    }

    public static void main(String[]args) {
        new TransactionsOnSavings();
    }
}
