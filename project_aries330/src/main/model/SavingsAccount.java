package model;

import exceptions.TransactionException;
import exceptions.ZeroException;
import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;

public class SavingsAccount extends SmartBanking {

    private double interestRate = 0.05;
    private boolean surpassedOneMonth;
    private static final double MAX_AMOUNT_FOR_TRANSACTIONS = 1000.00;
    private static final double TRANSFER_FEE = 20.00;

    //REQUIRES: balance and interest Rate should be both > 0.0
    //EFFECTS: the constructor initializes the fields
    public SavingsAccount(String name, int accountNumber, double balance, boolean surpassedOneMonth) {
        super(balance, name, accountNumber);
        this.accountNumber = accountNumber;
        this.surpassedOneMonth = surpassedOneMonth;
    }

    //MODIFIES: this
    //EFFECTS: sets the interest rate to a different value
    private void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    //MODIFIES: this
    //EFFECTS: increase the balance by its five percent interest rate per month if the savings account
    //         surpassed one month of its creation (surpassedOneMonth == true)
    public double addInterest() {
        if (surpassedOneMonth) {
            return balance =  balance + (balance * interestRate);
        } else {
            return balance;
        }
    }

    @Override
    //REQUIRES: withdraw > 0.0 and <= balance
    //MODIFIES: this
    //EFFECTS: withdraw the amount (withdraw) from the savings account with additional transfer fee and changes the
    //         monthly interest rate
    public void withdrawFromBalance(double withdraw) throws NegativeAmountException,
            NotEnoughMoneyException, ZeroException {
        if (withdraw < 0.0) {
            throw new NegativeAmountException();
        } else if (withdraw == 0.0) {
            throw new ZeroException();
        } else if (withdraw > balance) {
            throw new NotEnoughMoneyException();
        } else {
            System.out.println("Note: transactions on a savings account will require "
                    + "an additional transfer fee of $20.0");
            balance -= withdraw;
            balance -= TRANSFER_FEE;
            setInterestRate(0.02);
            System.out.println("The amount removed from account is " + withdraw + ". The total amount is " + balance);
        }
    }


    @Override
    //REQUIRES: amt has to be > 0.0, <= MAX_AMOUNT_FOR_TRANSACTIONS, and <= balance
    //MODIFIES: this
    //EFFECTS: makes transactions through saving account with additional changed transfer fee and changes in the
    //         monthly interest rate
    public void makeTransactions(double amt) throws TransactionException,
            NegativeAmountException, NotEnoughMoneyException {
        if (amt == 0.0 || amt > MAX_AMOUNT_FOR_TRANSACTIONS) {
            throw new TransactionException();
        } else if (amt < 0.0) {
            throw new NegativeAmountException();
        } else if (amt > balance) {
            throw new NotEnoughMoneyException();
        } else {
            System.out.println("Note: transactions on a savings account will require "
                    + "an additional transfer fee of $20.0");
            balance -= amt;
            balance -= TRANSFER_FEE;
            setInterestRate(0.02);
            System.out.println("The transaction has been made successfully. You have transferred an amount of $"
                    + amt + " and the remaining money in your savings account is $" + balance);
        }
    }

    //EFFECTS: returns the interest rate in double
    public double getInterestRate() {
        return interestRate;
    }

    //EFFECTS: returns the surpassedOneMonth in boolean
    public boolean getSurpassedOneMonth() {
        return surpassedOneMonth;
    }
}
