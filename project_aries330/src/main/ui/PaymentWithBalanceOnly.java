package ui;

import model.BalanceAccount;
import model.CreditCardAccount;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentWithBalanceOnly {

    private JFrame paymentFrame;
    private JPanel panel;

    public static CreditCardAccount c = new CreditCardAccount("HelloWorld", 12345,
            1600.00, 250, false);
    public static BalanceAccount b = new BalanceAccount("HelloWorld", 13579, 16050.90);

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static LocalDateTime now = LocalDateTime.now();

    public PaymentWithBalanceOnly() {

        paymentFrame = new JFrame("CreditCardPayment");
        panel = new JPanel();

        String enterCreditCardNumber = JOptionPane.showInputDialog("Please enter the credit card number: ");
        int creditCardNumber = Integer.parseInt(enterCreditCardNumber);
        if (creditCardNumber != c.getCardNumber()) {
            JOptionPane.showMessageDialog(null, "The credit card number is invalid.");
        } else {
            String enterBalanceAccountNumber = JOptionPane.showInputDialog("Please enter balance account number: ");
            int balanceAccountNumber = Integer.parseInt(enterBalanceAccountNumber);

            if (balanceAccountNumber != b.getAccountNumber()) {
                JOptionPane.showMessageDialog(null, "You have entered the wrong account number");
            } else {
                int dialogButton = JOptionPane.showConfirmDialog(null, "Your credit payment is "
                                + c.getPaymentNeededSoFar() + " and your credit points in the account is "
                                + c.getcreditPoints()  + ". Please select yes to process your payment.",
                        "CONFIRMATION",JOptionPane.YES_NO_OPTION);

                if (dialogButton == JOptionPane.YES_OPTION) {
                    double processPayment = b.getBalance() - c.getPaymentNeededSoFar();
                    double paymentNeeded = c.getPaymentNeededSoFar() - c.getPaymentNeededSoFar();
                    boolean swapPayment = c.getPayment();
                    JOptionPane.showMessageDialog(null, "Your payment is complete! "
                            + "Your current balance is " + processPayment + " and your payment needed in credit card "
                            + "is " + paymentNeeded + ". Your payment was processed on " + dtf.format(now));
                } else {
                    new SecondFrame();
                }
            }
        }
    }

    public static void main(String[] args) {
        new PaymentWithBalanceOnly();
    }
}
