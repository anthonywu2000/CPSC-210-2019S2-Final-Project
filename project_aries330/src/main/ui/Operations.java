package ui;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;
import exceptions.PaymentPaidOrBalanceNotEnoughForPaymentException;
import exceptions.TransactionException;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Operations {

    public static Holder helloWorld = new Holder("HelloWorld", 25,
            "January 1, 1994", "0000 Golden Prados Dr, Diamond Bar, CA 91765",
            754321986);

    public static CreditCardAccount c = new CreditCardAccount("HelloWorld", 12345,
            1600.00, 250, true);
    public static BalanceAccount balance = new BalanceAccount("HelloWorld", 13579, 16050.90);


    public static SavingsAccount s = new SavingsAccount("HelloWorld", 99999, 18000.00,
            false);

    public static InputHandlerForSavingsAccount is = new InputHandlerForSavingsAccount();

    public static InputHandlerForBalanceAccount ib = new InputHandlerForBalanceAccount();

    public Operations() {
    }

    public static void enterAccount() {
        Scanner username = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String u = username.nextLine();
        Scanner password = new Scanner(System.in);
        System.out.print("Please enter your password: ");
        String p = password.nextLine();

        if (!u.equals("helloworld") || !p.equals("helloworld1234")) {
            System.out.println("Incorrect username or password!");;
        } else {
            printScreen();
        }
    }


    public static String printScreen() {
        return "Hello " + helloWorld.getFullName() + " ! Welcome to your personalized SmartBanking!";
    }

    //user input 0
    public static String skipService() {
        return "Please contact us or book and appointment "
                + "if you need any banking services other than provided in your personalized SmartBanking.";
    }

    //EFFECTS: prompts the user to make transactions from their account to the receiver of their choice
    public static void setUpTransaction(SmartBanking s) {
        Scanner transaction = new Scanner(System.in);
        System.out.print("Please enter the name of the receiver (organization or person) or account: ");
        String transPerson = transaction.nextLine();
        Scanner transAmount = new Scanner(System.in);
        System.out.print("Please enter the amount that you want to wire: ");
        double amount = transAmount.nextDouble();
        try {
            s.makeTransactions(amount);
        } catch (TransactionException e) {
            System.out.println("Amount cannot be 0.0 or over the maximum amount of transaction.");
        } catch (NegativeAmountException e) {
            System.out.println("Transaction amount cannot be negative!");
        } catch (NotEnoughMoneyException e) {
            System.out.println("Amount in balance is not enough for the amount of transaction.");
        }
    }

    //user input 1
    public static void transactionService() {
        Scanner balanceOrSavingsTrans = new Scanner(System.in);
        System.out.print("You have selected to make transactions. "
                + "Please select to make transaction from balance or savings account: ");
        String bot = balanceOrSavingsTrans.nextLine();

        if (bot.equals("balance")) {
            setUpTransaction(balance);
        } else if (bot.equals("savings")) {
            setUpTransaction(s);
        }
    }

    //user input 2
    public static void depositOrWithdrawService() {
        Scanner balanceOrSavingsDepositOrWithdraw = new Scanner(System.in);
        System.out.print("You have selected to deposit/withdraw money. "
                + "Please select to deposit/withdraw from balance or savings account: ");
        String bod = balanceOrSavingsDepositOrWithdraw.nextLine();

        if (bod.equals("balance")) {
            ib.getInput(balance);
        } else if (bod.equals("savings")) {
            is.getInput(s);
        }
    }

    //user input 3
    public static void payService() {
        try {
            c.paid(c, balance);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            System.out.println("You have already made your payment or there is not enough money in your"
                    + " balance account.");
        }
        System.out.println("Your payment has been processed successfully. "
                + "The remaining money in your balance is " + balance.getBalance());
    }


    //a static helper method that reduces coupling
    public static void printAccountNumberAndBalance(SmartBanking s) {
        System.out.println("Account number: " + s.getAccountNumber());
        System.out.println("Current balance: " + s.getBalance());
    }

    //user input 5
    public static void viewingAccountDetailService() {
        Scanner viewAccount = new Scanner(System.in);
        System.out.print("Please select the account that you want to view: ");
        String va = viewAccount.nextLine();

        if (va.equals("balance")) {
            printAccountNumberAndBalance(balance);
        }

        if (va.equals("savings")) {
            printAccountNumberAndBalance(s);
            System.out.println("Interest rate per month: " + s.getInterestRate());
        }

        if (va.equals("credit card")) {
            System.out.println("Card number: " + c.getCardNumber());
            System.out.println("Your payment needed: " + c.getPaymentNeededSoFar());
            System.out.println("Your credits points: " + c.getcreditPoints());
            needPayment();
        }
    }

    //EFFECTS: prints out statements which serves as a reminder to the user whether they need to make payment or not
    //         at this time
    public static void needPayment() {
        if (!c.getPayment()) {
            System.out.println("Your payment is due next month. Please pay by your balance account.");
        } else {
            System.out.println("You do not have any payments at this time.");
        }
    }

    //user input 6
    public static void contactUs() {
        System.out.println("Bank name: LA West Coast Bank");
        List<String> branches = new ArrayList<>();
        branches.add("0000 Madrona Ave, Torrance, CA 92786    Phone: 111-111-1111");
        branches.add("710 Maple Ave, LA, CA 54637  Phone: 222-222-2222");
        branches.add("1000 Platt Blvd, Claremont, CA 89874   Phone: 333-333-3333");
        branches.add("300 N E St, San Bernardino, CA 65432    Phone: 444-444-4444");
        branches.add("4113 Pereira Dr, Irvine, CA 12345   Phone: 555-555-5555");
        branches.add("308 Westwood Plaza, LA, CA 90095   Phone: 666-666-6666");
        branches.add("3131 S Hoover St #1910, LA, CA 90089   Phone: 777-777-7777");
        branches.add("635 S Broadway, LA, CA 90014   Phone: 888-888-8888 ");
        branches.add("5301 Whittier Blvd, East LA, CA 90022   Phone: 999-999-9999");
        System.out.println("Your current address is: " + helloWorld.getAddress());
        System.out.println("Below are the list of branches (a local bank available only in LA, California)");
        for (int i = 0; i < branches.size(); i++) {
            System.out.println(branches.get(i));
        }
        System.out.println("All branches have the same opening and closing times from 8AM to 6:30PM (Monday "
                + "to Saturday) respectively. Phone service is only available until 5PM.");
    }

    //user input 7
    public static void callCurrencyService() {
        CurrencyExchangeRates.main(null);
    }

    //user input 8
    public static void exit() {
        System.out.println("Thank you for using your personalized SmartBanking!");
    }
}
