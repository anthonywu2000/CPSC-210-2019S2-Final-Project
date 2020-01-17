package tests;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;
import exceptions.TransactionException;
import exceptions.ZeroException;
import model.SavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestSavingsAccount {

    private SavingsAccount savingsAccount;
    private static final int SAVINGS_NUMBER = 96478825;
    private static final double delta = 0.001;

    @BeforeEach
    public void setUp() {
        savingsAccount = new SavingsAccount("Dennis", SAVINGS_NUMBER, 8950.50, false);
    }

    @Test
    public void testConstructor() {
        assertNotNull(savingsAccount);
    }

    @Test
    public void testAddToThisAccount() {
        try {
            savingsAccount.addToThisAccount(1000.00);
        } catch (NegativeAmountException e) {
            fail("The deposit amount is not negative");
        }
        assertEquals(savingsAccount.getBalance(), 9950.50, delta);
        try {
            savingsAccount.addToThisAccount(-1600.50);
        } catch (NegativeAmountException e) {
            System.out.println("The deposit amount is negative. Exception is expected.");
        }
        assertFalse(savingsAccount.getBalance() == 8350.0);
        assertFalse(savingsAccount.getBalance() == 11551.0);
    }

    @Test
    public void testAddInterestNotSurpassedOneMonth() {
        assertEquals(savingsAccount.addInterest(), 8950.50, delta);
        assertEquals(savingsAccount.getSurpassedOneMonth(), false);
        assertEquals(savingsAccount.getBalance(), 8950.50, delta);
        assertFalse(savingsAccount.getBalance() == 9398.025);
    }

    @Test
    public void testAddInterestSurpassedOneMonth() {
        savingsAccount = new SavingsAccount("Dennis", SAVINGS_NUMBER, 8950.50, true);
        assertEquals(savingsAccount.addInterest(), 9398.025, delta);
        assertEquals(savingsAccount.getSurpassedOneMonth(), true);
        assertEquals(savingsAccount.getBalance(), 9398.025, delta);
        assertFalse(savingsAccount.getBalance() == 8950.50);
    }

    @Test
    public void testWithdrawFromBalanceNegative() {
        try {
            savingsAccount.withdrawFromBalance(-60.00);
        } catch (NegativeAmountException e) {
            System.out.println("The withdraw amount is negative. Exception is expected.");
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw money is below the money in balance");
        } catch (ZeroException e) {
            fail("The amount is not 0.0");
        }
        assertEquals(savingsAccount.getBalance(), 8950.50, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testWithdrawFromBalanceGreaterThanAccount() {
        try {
            savingsAccount.withdrawFromBalance(15000.00);
        } catch (NegativeAmountException e) {
            fail("The withdraw amount is not negative.");
        } catch (NotEnoughMoneyException e) {
            System.out.println("The withdraw amount is greater than current amount in balance. Exception is expected");
        } catch (ZeroException e) {
            fail("The amount is not 0.0");
        }
        assertEquals(savingsAccount.getBalance(), 8950.50, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testWithdrawFromBalanceEnough() {
        try {
            savingsAccount.withdrawFromBalance(950.50);
        } catch (NegativeAmountException e) {
            fail("The withdraw amount is not negative. Exception should not be thrown here.");
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw amount is below the money in balance.");
        } catch (ZeroException e) {
            fail("The amount is not 0.0");
        }
        assertEquals(savingsAccount.getBalance(), 7980.00, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.02, delta);
    }

    @Test
    public void testWithdrawFromBalanceAll() {
        try {
            savingsAccount.withdrawFromBalance(8950.500);
        } catch (NegativeAmountException e) {
            fail("The withdraw amount is not negative. Exception should not be thrown here.");
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw amount is below the money in balance.");
        } catch (ZeroException e) {
            fail("The amount is not 0.0");
        }
        assertEquals(savingsAccount.getBalance(), -20.0, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.02, delta);
    }

    @Test
    public void testWithdrawFromBalanceZero() {
        try {
            savingsAccount.withdrawFromBalance(0.0);
        } catch (NegativeAmountException e) {
            fail("The amount is not negative. exception not expected here.");
        } catch (NotEnoughMoneyException e) {
            fail("The amount in balance is greater than withdrawal amount. Exception not expected.");
        } catch (ZeroException e) {
            //do nothing
            System.out.println("Exception expected here.");
        }
        assertEquals(savingsAccount.getBalance(), 8950.50, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testMakeTransactionZero() {
        try {
            savingsAccount.makeTransactions(0.0);
        } catch (TransactionException e) {
            System.out.println("Transaction amount cannot be 0.0.");
        } catch (NegativeAmountException e) {
            fail("The amount is not negative.");
        } catch (NotEnoughMoneyException e) {
            fail("The amount is not greater than the balance");
        }
        assertEquals(savingsAccount.getBalance(), 8950.50, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testMakeTransactionOverBoundary() {
        try {
            savingsAccount.makeTransactions(9999.99);
        } catch (TransactionException e) {
            System.out.println("Transaction amount cannot be over $1000.00.");
        } catch (NegativeAmountException e) {
            fail("The amount is not negative.");
        } catch (NotEnoughMoneyException e) {
            fail("The amount is not greater than the balance");
        }
        assertEquals(savingsAccount.getBalance(), 8950.50, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testMakeTransactionNegative() {
        try {
            savingsAccount.makeTransactions(-150.00);
        } catch (TransactionException e) {
            fail("Unexpected exception");
        } catch (NegativeAmountException e) {
            System.out.println("The transaction amount is negative. Exception is expected.");
        } catch (NotEnoughMoneyException e) {
            fail("The amount is not greater than balance.");
        }
        assertEquals(savingsAccount.getBalance(), 8950.50, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testMakeTransactionOverBalance() {
        savingsAccount = new SavingsAccount("Dennis", SAVINGS_NUMBER, 500.00, false);
        try {
            savingsAccount.makeTransactions(600.00);
        } catch (TransactionException e) {
            fail("Unexpected exception");
        } catch (NegativeAmountException e) {
            fail("The amount is not negative.");
        } catch (NotEnoughMoneyException e) {
            System.out.println("The transaction amount is greater than the balance.");
        }
        assertEquals(savingsAccount.getBalance(), 500.00, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testMakeTransactionEnough() {
        savingsAccount = new SavingsAccount("Dennis", SAVINGS_NUMBER, 500.00, false);
        try {
            savingsAccount.makeTransactions(350.00);
        } catch (TransactionException e) {
            fail("Unexpected exception");
        } catch (NegativeAmountException e) {
            fail("The amount is not negative.");
        } catch (NotEnoughMoneyException e) {
            fail("The amount is not greater than balance.");
        }
        assertEquals(savingsAccount.getBalance(), 130.00, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.02, delta);
    }

    @Test
    public void testMakeTransactionAtBoundaryButLargerThanBalance() {
        savingsAccount = new SavingsAccount("Dennis", SAVINGS_NUMBER, 500.00, false);
        try {
            savingsAccount.makeTransactions(1000.00);
        } catch (TransactionException e) {
            fail("This is exception is not expected.");
        } catch (NegativeAmountException e) {
            fail("The amount to make transaction is not negative");
        } catch (NotEnoughMoneyException e) {
            System.out.println("The exception is caught! Amount for transaction is greater than balance!");
        }
        assertEquals(savingsAccount.getBalance(), 500.00, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testMakeTransactionAtBoundaryButLessThanBalance() {
        savingsAccount = new SavingsAccount("Dennis", SAVINGS_NUMBER, 8888.88, false);
        try {
            savingsAccount.makeTransactions(1000.00);
        } catch (TransactionException e) {
            fail("This is exception is not expected.");
        } catch (NegativeAmountException e) {
            fail("The amount to make transaction is not negative");
        } catch (NotEnoughMoneyException e) {
            fail("The amount to make transaction is less than the amount in balance");
        }
        assertEquals(savingsAccount.getBalance(), (8888.88-1000.00) - 20, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.02, delta);
    }

    @Test
    public void testMakeTransactionAtBoundary() {
        savingsAccount = new SavingsAccount("Dennis", SAVINGS_NUMBER, 1020.00, false);
        try {
            savingsAccount.makeTransactions(1000.00);
        } catch (TransactionException e) {
            fail("This is exception is not expected.");
        } catch (NegativeAmountException e) {
            fail("The amount to make transaction is not negative");
        } catch (NotEnoughMoneyException e) {
            fail("The amount to make transaction is less than the amount in balance");
        }
        assertEquals(savingsAccount.getBalance(), (1020.00-1000.00) - 20, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.02, delta);
    }

    @Test
    public void testGetName() {
        assertEquals(savingsAccount.getName(), "Dennis");
    }

    @Test
    public void testGetSavingsNumber() {
        assertEquals(savingsAccount.getAccountNumber(), SAVINGS_NUMBER);
    }

    @Test
    public void testGetInterestRate() {
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testGetBalance() {
        assertEquals(savingsAccount.getBalance(), 8950.50, delta);
    }

    @Test
    public void testGetSurpassedOneMonth() {
        assertEquals(savingsAccount.getSurpassedOneMonth(), false);
        assertNotEquals(savingsAccount.getSurpassedOneMonth(), true);
    }
}
