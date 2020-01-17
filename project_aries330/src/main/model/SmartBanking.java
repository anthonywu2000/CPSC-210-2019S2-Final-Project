package model;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;
import exceptions.TransactionException;
import exceptions.ZeroException;

import java.util.ArrayList;
import java.util.List;

public abstract class SmartBanking {

    protected double balance;
    protected String name;
    protected int accountNumber;
    protected List<Holder> holder;

    public SmartBanking(double balance, String name, int accountNumber) {
        this.name = name;
        if (balance > 0.0) {
            this.balance = balance;
        }
        this.accountNumber = accountNumber;
        holder = new ArrayList<>();
    }

    //REQUIRES: balance should be > 0.0 and deposit should be > 0.0
    //MODIFIES: this
    //EFFECTS: adds the given deposit to the current balance, if the balance and deposit does not satisfy this
    //         requirement prints the statement that it is invalid
    public void addToThisAccount(double deposit) throws NegativeAmountException {
        if (deposit <= 0.0) {
            throw new NegativeAmountException();
        } else {
            balance += deposit;
            System.out.println("The added deposit amount is " + deposit + ". The total amount is " + balance);
        }
    }

    //EFFECTS: returns name in String
    public String getName() {
        return name;
    }

    //EFFECTS: returns account Number in int
    public int getAccountNumber() {
        return accountNumber;
    }

    //EFFECTS: returns balance Number in double
    public double getBalance() {
        return balance;
    }

    public abstract void withdrawFromBalance(double withdraw) throws NegativeAmountException,
            NotEnoughMoneyException, ZeroException;

    public abstract void makeTransactions(double amt) throws TransactionException, NegativeAmountException,
            NotEnoughMoneyException;
}
