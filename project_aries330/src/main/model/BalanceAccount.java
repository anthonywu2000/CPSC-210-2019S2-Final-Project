package model;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;
import exceptions.TransactionException;
import exceptions.ZeroException;

public class BalanceAccount extends SmartBanking {

    private final double maxtransactions = 1000.00;

    //REQUIRES: balance should be > 0.0
    //EFFECTS: the constructor initializes the balance account fields
    public BalanceAccount(String name, int accountNumber, double balance) {
        super(balance, name, accountNumber);
    }

    @Override
    //REQUIRES: withdraw > 0.0 and must be >= withdraw
    //MODIFIES: this
    //EFFECTS: removes the amount withdraw from the balance, if the balance and withdraw does not satisfy this
    //         requirement prints the statement that it is invalid
    public void withdrawFromBalance(double withdraw) throws NotEnoughMoneyException, NegativeAmountException,
            ZeroException {
        if (withdraw < 0.0) {
            throw new NegativeAmountException();
        } else if (withdraw == 0.0) {
            throw new ZeroException();
        } else if (balance < withdraw) {
            throw new NotEnoughMoneyException();
        } else {
            balance -= withdraw;
            System.out.println("The amount removed from account is " + withdraw + ". The total amount is " + balance);
        }
    }

    @Override
    //REQUIRES: amount must be > 0.0, <= maxtransactions, and <= balance
    //MODIFIES: this
    //EFFECTS: removes the amt from balance account in order to make transactions, prints out corresponding statement
    //         if amt does not satisfy the if conditions
    public void makeTransactions(double amt) throws TransactionException, NegativeAmountException,
            NotEnoughMoneyException {
        if (amt == 0.0 || amt > maxtransactions) {
            throw new TransactionException();
        } else if (amt < 0.0) {
            throw new NegativeAmountException();
        } else if (amt > balance) {
            throw new NotEnoughMoneyException();
        } else {
            balance -= amt;
            System.out.println("The transaction has been made successfully. You have transferred an amount of "
                    + amt + " and the remaining money in your balance account is " + balance);
        }
    }
}
