package ui;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;
import exceptions.NotGreaterThanOrEqualAmountException;
import model.BalanceAccount;
import model.SmartBanking;

import java.util.Scanner;

public class InputHandlerForBalanceAccount extends InputHandler {

    public InputHandlerForBalanceAccount() {

    }

    //REQUIRES: assume that the user has been prompted to enter the correct name and account number that matches the
    //          system
    //EFFECTS: prompts the user to enter the account
    public void getInput(BalanceAccount a) {
        Scanner name = new Scanner(System.in);
        printEnterName();
        String x = name.nextLine();
        Scanner accountN = new Scanner(System.in);
        printEnterAccountNumber();
        int y = accountN.nextInt();
        showCurrent(y, a, x);
    }

    //REQUIRES: acctNumber > 0
    //EFFECTS: shows the current values of balance and credits inside the user's account
    public void showCurrent(int acctNumber, BalanceAccount a, String name) {
        if (acctNumber != a.getAccountNumber() || !name.equals(a.getName())) {
            printIncorrectNameAndNumberStatement();
        } else {
            printCurrentBalance(a);
            manipulateAccount(a);
        }
    }

    //REQUIRES: assumes that the user is prompted to enter either 'deposit' and 'withdraw' only and nothing else
    //EFFECTS: take user input and determines whether the user would like to deposit or withdraw their account
    private void manipulateAccount(BalanceAccount a) {
        Scanner yn = new Scanner(System.in);
        printDepositOrWithdraw();
        String z = yn.nextLine();
        decision(z, a);
    }

    //REQUIRES: assumes the the user is prompted to enter either 'deposit' or 'withdraw' and nothing else
    //EFFECTS: determines whether the user wants to deposit or withdraw a certain amount from their balance
    private void decision(String depositOrWithdraw, BalanceAccount a) {
        if (depositOrWithdraw.equals("deposit")) {
            Scanner addedAmount = new Scanner(System.in);
            printEnterAmountToDeposit();
            double add = addedAmount.nextDouble();
            try {
                determineDepositAmount(add, a);
            } catch (NotGreaterThanOrEqualAmountException e) {
                System.out.println("The deposit has to be greater or equal to $100.0");
            }
        }
        if (depositOrWithdraw.equals("withdraw")) {
            continueDecision(a);
        }
    }

    @Override
    public void continueDecision(SmartBanking s) {
        super.continueDecision(s);
    }

    //REQUIRES: amt has to be >= 100.0
    //MODIFIES: this
    //EFFECTS: takes an amt that needs to be deposited into the current balance of the bank account
    public void determineDepositAmount(double amt, BalanceAccount a) throws NotGreaterThanOrEqualAmountException {
        if (amt < 100.0) {
            throw new NotGreaterThanOrEqualAmountException();
        }
        if (amt >= 100.0) {
            try {
                a.addToThisAccount(amt);
            } catch (NegativeAmountException e) {
                printNegativeStatementWarning();
            }
            printUpdatedBalance(a);
        } else {
            System.out.println("Please deposit an amount that is greater than $100.");
        }
    }

    @Override
    public void determineWithdrawAmount(double withdrawAmt, SmartBanking s) throws
            NotGreaterThanOrEqualAmountException, NotEnoughMoneyException {
        super.determineWithdrawAmount(withdrawAmt, s);
    }
}

