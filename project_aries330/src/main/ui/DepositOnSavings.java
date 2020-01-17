package ui;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ui.DepositOnBalance.setBalance;
import static ui.Operations.s;

public class DepositOnSavings {

    private JFrame balanceFrame;
    private JPanel panel;

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static LocalDateTime now = LocalDateTime.now();

    public DepositOnSavings() {

        balanceFrame = new JFrame("SavingsDeposit");
        panel = new JPanel();

        String enterAccNumber = JOptionPane.showInputDialog("Please enter your savings account number: ");
        int accountNumber = Integer.parseInt(enterAccNumber);
        if (accountNumber != s.getAccountNumber()) {
            JOptionPane.showMessageDialog(null, "You have entered the wrong account number");
        } else {
            String depositAmount = JOptionPane.showInputDialog("Please enter the amount that you want to deposit: ");
            double deposit = Double.parseDouble(depositAmount);
            if (deposit > 0.0) {
                JOptionPane.showMessageDialog(null, "You have successfully deposited an amount of "
                        + deposit + " in your balance account, number " + s.getAccountNumber() + ". The deposit "
                        + "was processed on " + dtf.format(now));
                JOptionPane.showMessageDialog(null, "Your balance is " + deposit(deposit));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount!");
            }
        }
    }

    public static void main(String[] args) {
        new DepositOnSavings();
    }

    private static double deposit(double deposit) {
        if (deposit > 0.0) {
            double balance = s.getBalance();
            balance += deposit;
            setBalance(balance);
            return balance;
        } else {
            return s.getBalance();
        }
    }
}
