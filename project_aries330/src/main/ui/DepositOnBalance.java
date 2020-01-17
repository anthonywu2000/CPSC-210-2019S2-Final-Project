package ui;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DepositOnBalance {

    private JFrame balanceFrame;
    private JPanel panel;

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static LocalDateTime now = LocalDateTime.now();

    public DepositOnBalance() {

        balanceFrame = new JFrame("BalanceDeposit");
        panel = new JPanel();

        String enterAccNumber = JOptionPane.showInputDialog("Please enter your balance account number: ");
        int accountNumber = Integer.parseInt(enterAccNumber);
        if (accountNumber != Operations.balance.getAccountNumber()) {
            JOptionPane.showMessageDialog(null, "You have entered the wrong account number");
        } else {
            String depositAmount = JOptionPane.showInputDialog("Please enter the amount that you want to deposit: ");
            double deposit = Double.parseDouble(depositAmount);
            if (deposit > 0.0) {
                JOptionPane.showMessageDialog(null, "You have successfully deposited an amount of "
                        + deposit + " in your balance account, number " + Operations.balance.getAccountNumber()
                        + ". Your deposit was processed on " + dtf.format(now));
                JOptionPane.showMessageDialog(null, "Your balance is " + deposit(deposit));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount!");
            }
        }
    }

    public static void main(String[] args) {
        new DepositOnBalance();
    }

    private static double deposit(double deposit) {
        if (deposit > 0) {
            double b = Operations.balance.getBalance();
            b += deposit;
            return setBalance(b);
        } else {
            return Operations.balance.getBalance();
        }
    }

    public static double setBalance(double balance) {
        double set = Operations.balance.getBalance();
        set = balance;
        return set;
    }
}
