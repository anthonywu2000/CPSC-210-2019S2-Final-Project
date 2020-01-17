package ui;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;
import exceptions.NotGreaterThanOrEqualAmountException;
import exceptions.ZeroException;
import model.SmartBanking;

import java.util.Scanner;

public abstract class InputHandler {

    public void printUpdatedBalance(SmartBanking s) {
        System.out.println("Your updated balance is: " + s.getBalance());
    }

    public void printEnterName() {
        System.out.println("Please enter your name: ");
    }

    public void printEnterAccountNumber() {
        System.out.println("Please enter your account number: ");
    }

    public void printCurrentBalance(SmartBanking s) {
        System.out.println("Your current balance is: " + s.getBalance());
    }

    public void printIncorrectNameAndNumberStatement() {
        System.out.println("Your name or account number is incorrect.");
    }

    public void printDepositOrWithdraw() {
        System.out.println("Do you want to deposit or withdraw: ");
    }

    public void printEnterAmountToDeposit() {
        System.out.println("Please enter the amount that you want to deposit: ");
    }

    public void printEnterAmountToWithdraw() {
        System.out.println("Please enter the amount that you want to withdraw: ");
    }

    public void printGreater50Statement() {
        System.out.println("The withdrawn amount must be greater or equal to $50.0");
    }

    public void printBalanceNotEnough(SmartBanking s) {
        System.out.println("Balance not enough to retrieve withdrawn amount. Your balance is only " + s.getBalance());
    }

    public void printNegativeStatementWarning() {
        System.out.println("Negative amount not accepted as a deposit!");
    }

    //EFFECTS: deals with the effects when user enters to 'withdraw'
    public void continueDecision(SmartBanking s) {
        Scanner withdrawnAmount = new Scanner(System.in);
        printEnterAmountToWithdraw();
        double withdraw = withdrawnAmount.nextDouble();
        try {
            determineWithdrawAmount(withdraw, s);
        } catch (NotGreaterThanOrEqualAmountException e) {
            printGreater50Statement();
        } catch (NotEnoughMoneyException e) {
            printBalanceNotEnough(s);
        }
    }

    //REQUIRES: withdrawAmt has to be >= 50.0 and <= the current balance within the account
    //MODIFIES: this
    //EFFECTS: takes a withdrawAmt that needs to be withdrawn from th current balance of the bank account
    public void determineWithdrawAmount(double withdrawAmt, SmartBanking s)
            throws NotGreaterThanOrEqualAmountException, NotEnoughMoneyException {
        if (withdrawAmt < 50.0) {
            throw new NotGreaterThanOrEqualAmountException();
        } else if (withdrawAmt > s.getBalance()) {
            throw new NotEnoughMoneyException();
        } else if (withdrawAmt >= 50.0 && withdrawAmt <= s.getBalance()) {
            try {
                s.withdrawFromBalance(withdrawAmt);
            } catch (NotEnoughMoneyException e) {
                System.out.println("The amount to withdraw is larger than current amount in balance.");
            } catch (ZeroException e) {
                System.out.println("The amount to withdraw cannot be $0.0.");
            } catch (NegativeAmountException e) {
                System.out.println("No negative withdraw amount!");
            }
            printUpdatedBalance(s);
        } else {
            System.out.println("Please withdraw an amount that is greater than $0.");
        }
    }
}
