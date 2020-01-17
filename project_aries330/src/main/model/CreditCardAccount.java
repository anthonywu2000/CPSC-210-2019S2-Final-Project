package model;

import exceptions.ZeroException;
import exceptions.NegativeAmountException;
import exceptions.PaymentPaidOrBalanceNotEnoughForPaymentException;

public class CreditCardAccount extends BalanceAccount {

    private int cardNumber;
    private double paymentNeededSoFar;
    private int creditPoints;
    private boolean payment;


    //EFFECTS: creates a credit card account object
    public CreditCardAccount(String name, int cardNumber, double paymentNeededSoFar, int credits, boolean payment) {
        super(name, cardNumber, paymentNeededSoFar);
        this.cardNumber = cardNumber;
        this.paymentNeededSoFar = paymentNeededSoFar;
        this.creditPoints = credits;
        this.payment = false;
    }

    //EFFECTS: sets the payment to be 0.0 after payment completely
    private void setPaymentNeededSoFar() {
        this.paymentNeededSoFar = 0.0;
    }

    //EFFECTS: sets payment to false (only when payment is not done)
    private void setPaymentToFalse() {
        this.payment = false;
    }

    //EFFECTS: sets the payment to true (only when payment is done)
    private void setPaymentToTrue() {
        this.payment = true;
    }

    //REQUIRES: spent >= 0
    //MODIFIES: this
    //EFFECTS: adds the corresponding credit points with the given amount of money spent
    public int addCreditPoints(double spent) {
        if (spent >= 500.0 && spent < 1000.0) {
            return creditPoints += 160;
        } else if (spent >= 1000.0 && spent < 1500.0) {
            return creditPoints += 180;
        } else if (spent >= 1500.0) {
            return creditPoints += 250;
        } else {
            return creditPoints;
        }
    }

    //REQUIRES: spent > 0.0
    //MODIFIES: this
    //EFFECTS: adds spent to payment and then prints out the total amount needed to be paid, add credit points if
    //         conditions apply
    public void addSpentToPayment(double spent) throws ZeroException, NegativeAmountException {
        if (spent == 0.0) {
            throw new ZeroException();
        } else if (spent < 0.0) {
            throw new NegativeAmountException();
        } else {
            setPaymentToFalse();
            paymentNeededSoFar += spent;
            System.out.println("Your payment is due exactly one month from now.");
            addCreditPoints(spent);
        }
    }

    //REQUIRES: payment must not be made prior to calling this function and balance in the bank account must be
    //          >= paymentNeededSoFar, paymentNeededSoFar should also be >= 0.0
    //MODIFIES: this
    //EFFECTS: takes the balance in the corresponding balance account and makes payments for the paymentNeededSoFar
    //         returns the balance in the balance account after the payment is made, if not, then return the
    //         unchanged balance
    public void paid(CreditCardAccount c, BalanceAccount b) throws PaymentPaidOrBalanceNotEnoughForPaymentException {
        if (c.payment || b.balance < c.paymentNeededSoFar) {
            throw new PaymentPaidOrBalanceNotEnoughForPaymentException();
        } else {
            c.setPaymentToTrue();
            b.balance -= c.paymentNeededSoFar;
            c.setPaymentNeededSoFar();
        }
    }

    //EFFECTS: returns the card number in int
    public int getCardNumber() {
        return cardNumber;
    }

    //EFFECTS: returns the payment in boolean
    public boolean getPayment() {
        return payment;
    }

    //EFFECTS: returns the payment needed in double
    public double getPaymentNeededSoFar() {
        return paymentNeededSoFar;
    }

    //EFFECTS: returns the credit points in int
    public int getcreditPoints() {
        return creditPoints;
    }
}

