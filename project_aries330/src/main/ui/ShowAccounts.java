package ui;

import model.BalanceAccount;
import model.CreditCardAccount;
import model.SavingsAccount;

import javax.swing.*;

public class ShowAccounts {

    public static CreditCardAccount c = new CreditCardAccount("HelloWorld", 12345,
            0.0, 250, true);
    public static BalanceAccount balance = new BalanceAccount("HelloWorld", 13579, 16050.90);


    public static SavingsAccount s = new SavingsAccount("HelloWorld", 99999, 18100.00,
            false);

    private static JFrame showingFrame;
    private static JPanel panel;

    public ShowAccounts() {

        showingFrame = new JFrame("Showing Available Accounts");
        panel = new JPanel();

        String enterAccountType = JOptionPane.showInputDialog("Enter the type of the account "
                + "that you would like to view (balance, savings, credit card: all lowercase letters): ");

        if (enterAccountType.equals("balance")) {
            JOptionPane.showMessageDialog(null, "Account number: " + balance.getAccountNumber()
                    + "\nBalance: " + balance.getBalance());
        } else if (enterAccountType.equals("savings")) {
            JOptionPane.showMessageDialog(null, "Account number: " + s.getAccountNumber()
                    + "\nBalance: " + s.getBalance() + "\nInterest Rate: " + s.getInterestRate());
        } else if (enterAccountType.equals("credit card")) {
            JOptionPane.showMessageDialog(null, "Card number: " + c.getCardNumber()
                    + "\nPayment needed: " + c.getPaymentNeededSoFar() + "\nCredit Points: "
                    + c.getcreditPoints() + "\nPayment Status (true if credit "
                    + "payment is processed): " + determinePaymentStatus());
        }

    }

    public static void main(String[] args) {
        new ShowAccounts();
    }

    private static boolean determinePaymentStatus() {
        if (c.getPaymentNeededSoFar() == 0.0) {
            return !c.getPayment();
        } else {
            return c.getPayment();
        }
    }

}
