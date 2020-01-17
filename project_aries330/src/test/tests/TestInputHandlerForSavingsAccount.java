package tests;

import exceptions.NotEnoughMoneyException;
import exceptions.NotGreaterThanOrEqualAmountException;
import model.SavingsAccount;
import ui.InputHandlerForSavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestInputHandlerForSavingsAccount {

    private SavingsAccount savingsAccount;
    private InputHandlerForSavingsAccount ih;
    private static final double delta = 0.001;

    @BeforeEach
    public void setUp() {
        savingsAccount = new SavingsAccount("Yu-chi", 456789,
                65880.50, false);
        ih = new InputHandlerForSavingsAccount();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ih);
    }

    @Test
    public void testDetermineDepositAmountZero() {
        try {
            ih.determineDepositAmount(0.0, savingsAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            System.out.println("NotGreaterOrEqualToHundredException caught!");
        }
        assertTrue(savingsAccount.getBalance() == 65880.50);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testDetermineDepositAmountNegative() {
        try {
            ih.determineDepositAmount(-10.0, savingsAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            //do nothing
        }
        assertTrue(savingsAccount.getBalance() == 65880.50);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testDetermineDepositAmountBoundary() {
        try {
            ih.determineDepositAmount(100.0, savingsAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("The deposit amount is 100.00, therefore exception should not be expected.");
        }
        assertEquals(savingsAccount.getBalance(), 65980.50, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testDetermineDepositAmountLargerThanAHundred() {
        try {
            ih.determineDepositAmount(500.0, savingsAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("The deposit amount is greater than 100.00, therefore exception should not be expected.");
        }
        assertEquals(savingsAccount.getBalance(), 66380.50, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testDetermineWithdrawAmountLessThanFifty() {
        try {
            ih.determineWithdrawAmount(20.0, savingsAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            System.out.println("NotGreaterOrEqualToFiftyException caught.");
        } catch (NotEnoughMoneyException e) {
            fail("Not this exception");
        }
        assertTrue(savingsAccount.getBalance() == 65880.50);
        assertFalse(savingsAccount.getBalance() == 65860.50);
        assertEquals(savingsAccount.getInterestRate(), 0.05, delta);
    }

    @Test
    public void testDetermineWithdrawAmountBoundary() {
        try {
            ih.determineWithdrawAmount(50.0, savingsAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("Withdraw amount is 50.0. Exception not expected here.");
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw amount is less than current amount in balance. Exception not expected here.");
        }
        assertTrue(savingsAccount.getBalance() == 65810.50);
        assertEquals(savingsAccount.getInterestRate(), 0.02, delta);
    }

    @Test
    public void testDetermineWithdrawAmountGreaterThanFifty() {
        try {
            ih.determineWithdrawAmount(1450.0, savingsAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("Withdraw amount is 1450.0. Exception not expected here.");
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw amount is less than current amount in balance. Exception not expected here.");
        }
        assertEquals(savingsAccount.getBalance(), 64410.5, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.02, delta);
    }

    @Test
    public void testDetermineWithdrawAmountAllAmount() {
        try {
            ih.determineWithdrawAmount(65880.50, savingsAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("Withdraw amount is 64380.5. Exception not expected here.");
        } catch (NotEnoughMoneyException e) {
            //do nothing
        }
        assertEquals(savingsAccount.getBalance(), -20.0, delta);
        assertEquals(savingsAccount.getInterestRate(), 0.02, delta);
    }
}
