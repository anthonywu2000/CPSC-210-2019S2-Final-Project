package ui;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;
import exceptions.NotGreaterThanOrEqualAmountException;
import model.SavingsAccount;
import model.SmartBanking;

import java.util.Scanner;

public class InputHandlerForSavingsAccount extends InputHandler {

    public InputHandlerForSavingsAccount() {

    }

    //REQUIRES: assume that the user has been prompted to enter the correct name and account number that matches the
    //          system
    //EFFECTS: prompts the user to enter the account
    public void getInput(SavingsAccount s) {
        Scanner name = new Scanner(System.in);
        printEnterName();
        String x = name.nextLine();
        Scanner accountN = new Scanner(System.in);
        printEnterAccountNumber();
        int y = accountN.nextInt();
        showCurrent(y, s, x);
    }

    //REQUIRES: acctNumber > 0
    //EFFECTS: shows the current values of balance and credits inside the user's account
    public void showCurrent(int acctNumber, SavingsAccount s, String name) {
        if (acctNumber != s.getAccountNumber() || !name.equals(s.getName())) {
            printIncorrectNameAndNumberStatement();
        } else {
            printCurrentBalance(s);
            manipulateAccount(s);
        }
    }

    //REQUIRES: assumes that the user is prompted to enter either 'deposit' and 'withdraw' only and nothing else
    //EFFECTS: take user input and determines whether the user would like to deposit or withdraw their account
    //         if deposit, the credit points do not change. If withdraw, the credit points will increase accordingly
    private void manipulateAccount(SavingsAccount s) {
        Scanner yn = new Scanner(System.in);
        printDepositOrWithdraw();
        String z = yn.nextLine();
        decision(z, s);
    }

    //REQUIRES: assumes the the user is prompted to enter either 'deposit' or 'withdraw' and nothing else
    //EFFECTS: determines whether the user wants to deposit or withdraw a certain amount from their balance
    private void decision(String depositOrWithdraw, SavingsAccount s) {
        if (depositOrWithdraw.equals("deposit")) {
            Scanner addedAmount = new Scanner(System.in);
            printEnterAmountToDeposit();
            double add = addedAmount.nextDouble();
            try {
                determineDepositAmount(add, s);
            } catch (NotGreaterThanOrEqualAmountException e) {
                System.out.println("The deposit has to be greater than $0.0.");
            }
        }
        if (depositOrWithdraw.equals("withdraw")) {
            continueDecision(s);
        }
    }

    @Override
    public void continueDecision(SmartBanking s) {
        super.continueDecision(s);
    }

    //REQUIRES: amt has to be >= 100.0
    //MODIFIES: this
    //EFFECTS: takes an amt that needs to be deposited into the current balance of the bank account
    public void determineDepositAmount(double amt, SavingsAccount s) throws NotGreaterThanOrEqualAmountException {
        if (amt <= 0.0) {
            throw new NotGreaterThanOrEqualAmountException();
        } else if (amt > 0.0) {
            try {
                s.addToThisAccount(amt);
            } catch (NegativeAmountException e) {
                printNegativeStatementWarning();
            }
            printUpdatedBalance(s);
        }
    }

    @Override
    public void determineWithdrawAmount(double withdrawAmt, SmartBanking s)
            throws NotGreaterThanOrEqualAmountException, NotEnoughMoneyException {
        super.determineWithdrawAmount(withdrawAmt, s);
    }
}

