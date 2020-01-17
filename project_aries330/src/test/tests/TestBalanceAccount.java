package tests;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughMoneyException;
import exceptions.TransactionException;
import exceptions.ZeroException;
import model.BalanceAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestBalanceAccount {

    private BalanceAccount balanceAccount;
    private final int accountNumber = 698734;
    private static final double delta = 0.001;

    @BeforeEach
    public void setUp() {
        balanceAccount = new BalanceAccount("Dennis", accountNumber, 773.00);
    }

    @Test
    public void testConstructor() {
        assertNotNull(balanceAccount);
    }

    @Test
    public void testAddToThisAccount() {
        try {
            balanceAccount.addToThisAccount(50.50);
        } catch (NegativeAmountException e) {
            fail("The deposit satisfies and does not throws an exception.");
        }
        assertEquals(773.00 + 50.50, balanceAccount.getBalance(), delta);
        try {
            balanceAccount.addToThisAccount(-19.00);
        } catch (NegativeAmountException e) {
            System.out.println("Got Negative Amount Exception!!");
        }
        assertFalse(balanceAccount.getBalance() == 31.5);
    }

    @Test
    public void testWithdrawFromBalanceNotEnough() {
        try {
            balanceAccount.withdrawFromBalance(800.00);
        } catch (NotEnoughMoneyException e) {
            System.out.println("Withdrawal fail. Exception thrown as balance is not enough.");
        } catch (ZeroException e) {
            fail("The withdraw amount is not 0.0.");
        } catch (NegativeAmountException e) {
            fail("The withdraw amount is not negative. Exception should not be expected.");
        }
        assertEquals(773.00, balanceAccount.getBalance(), delta);
    }

    @Test
    public void testWithdrawFromBalanceEnough() {
        try {
            balanceAccount.withdrawFromBalance(100.00);
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw amount is less than the balanceAccount amount. Exception should not be expected.");
        } catch (ZeroException e) {
            fail("The withdraw amount is not 0.0.");
        } catch (NegativeAmountException e) {
            fail("The withdraw amount is not negative. Exception should not be expected.");
        }
        assertEquals(773.00 - 100.00, balanceAccount.getBalance(), delta);
    }

    @Test
    public void testWithdrawFromBalanceNegative() {
        try {
            balanceAccount.withdrawFromBalance(-100.00);
        } catch (NotEnoughMoneyException e) {
            fail("Exception not expected here.");
        } catch (ZeroException e) {
            fail("The withdraw amount is not 0.0.");
        } catch (NegativeAmountException e) {
            System.out.println("NegativeAmountException caught!");
        }
        assertFalse(balanceAccount.getBalance() == 873.00);
    }

    @Test
    public void testWithdrawFromBalanceZero() {
        try {
            balanceAccount.withdrawFromBalance(0.0);
        } catch (NotEnoughMoneyException e) {
            fail("0.0 is greater than current amount in balanceAccount.");
        } catch (ZeroException e) {
            //do nothing, as expected
        } catch (NegativeAmountException e) {
            fail("The amount is not negative. Exception should not be expected.");
        }
        assertEquals(balanceAccount.getBalance(), 773.00, delta);
    }

    @Test
    public void testMakeTransactionZero() {
        try {
            balanceAccount.makeTransactions(0.0);
        } catch (NegativeAmountException e) {
            fail("This exception is not expected in this case.");
        } catch (NotEnoughMoneyException e) {
            fail("This exception is not expected in this case.");
        } catch (TransactionException e) {
            System.out.println("The transaction amount is 0.0, so TransactionException is expected.");
        }
        assertEquals(balanceAccount.getBalance(), 773.00, delta);
    }

    @Test
    public void testMakeTransactionNegative() {
        try {
            balanceAccount.makeTransactions(-700.50);
        } catch (TransactionException e) {
            fail("This exception is not expected in this case.");
        } catch (NegativeAmountException e) {
            System.out.println("The transaction amount is -700.50, so NegativeAmountException is expected.");
        } catch (NotEnoughMoneyException e) {
            fail("This exception is not expected in this case.");
        }
        assertEquals(balanceAccount.getBalance(), 773.00, delta);
        assertNotEquals(balanceAccount.getBalance(), 1473.50);
    }

    @Test
    public void testMakeTransactionLargerThanBalance() {
        try {
            balanceAccount.makeTransactions(999.99);
        } catch (NegativeAmountException e) {
            fail("This exception is not expected in this case.");
        } catch (NotEnoughMoneyException e) {
            System.out.println("The transaction amount is 999.99, which is greater than the balanceAccount amount, so "
                    + "NotEnoughMoneyException is expected.");
        } catch (TransactionException e) {
            fail("This exception is not expected in this case.");
        }
        assertEquals(balanceAccount.getBalance(), 773.00, delta);
        assertNotEquals(balanceAccount.getBalance(), -226.99);
        assertNotEquals(balanceAccount.getBalance(), 773.00 + 999.99);
    }

    @Test
    public void testMakeTransactionBoundaryButLargerThanBalance() {
        try {
            balanceAccount.makeTransactions(1000.00);
        } catch (NegativeAmountException e) {
            fail("This exception is not expected in this case.");
        } catch (NotEnoughMoneyException e) {
            System.out.println("The transaction amount is 1000.00, which is greater than the balanceAccount amount, so "
                    + "NotEnoughMoneyException is expected.");
        } catch (TransactionException e) {
            fail("This exception is not expected in this case.");
        }
        assertEquals(balanceAccount.getBalance(), 773.00, delta);
    }


    @Test
    public void testMakeTransactionEnough() {
        try {
            balanceAccount.makeTransactions(650.38);
        } catch (NegativeAmountException e) {
            fail("This exception is not expected in this case.");
        } catch (NotEnoughMoneyException e) {
            fail("This exception is not expected in this case.");
        } catch (TransactionException e) {
            fail("This exception is not expected in this case.");
        }
        assertEquals(balanceAccount.getBalance(), 773.00 - 650.38, delta);
    }


    @Test
    public void testMakeTransactionGreaterThanBoundaryAndEnough() {
        BalanceAccount balanceAccount = new BalanceAccount("Sharon", 12345, 2900.50);
        try {
            balanceAccount.makeTransactions(1500.00);
        } catch (NegativeAmountException e) {
            fail("This exception is not expected in this case.");
        } catch (NotEnoughMoneyException e) {
            fail("This exception is not expected in this case.");
        } catch (TransactionException e) {
            System.out.println("The transaction amount is over 1000.00, so TransactionException is expected.");
        }
        assertEquals(balanceAccount.getBalance(), 2900.50, delta);
    }

    @Test
    public void testMakeTransactionAtBoundaryAndEnough() {
        BalanceAccount balanceAccount = new BalanceAccount("John", accountNumber,5000.00);
        try {
            balanceAccount.makeTransactions(1000.00);
        } catch (NegativeAmountException e) {
            fail("This exception is not expected");
        } catch (NotEnoughMoneyException e) {
            fail("This exception is not expected to be here.");
        } catch (TransactionException e) {
            fail("This exception is not thrown");
        }
        assertEquals(balanceAccount.getBalance(), 4000.00, delta);
    }

    @Test
    public void testGetName() {
        assertEquals(balanceAccount.getName(), "Dennis");
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals(balanceAccount.getAccountNumber(), accountNumber);
    }

    @Test
    public void testGetBalance() {
        assertEquals(balanceAccount.getBalance(), 773.0, delta);
    }
}