package tests;

import exceptions.NotEnoughMoneyException;
import exceptions.NotGreaterThanOrEqualAmountException;
import model.BalanceAccount;
import ui.InputHandlerForBalanceAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestInputHandlerForBalanceAccount {

    private BalanceAccount balanceAccount;
    private InputHandlerForBalanceAccount ih;
    private static final double delta = 0.001;

    @BeforeEach
    public void setUp() {
        balanceAccount = new BalanceAccount("Yu-chi", 456789, 65880.50);
        ih = new InputHandlerForBalanceAccount();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ih);
    }

    @Test
    public void testDetermineDepositAmountLessThanAHundred() {
        try {
            ih.determineDepositAmount(50.0, balanceAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            System.out.println("NotGreaterOrEqualToHundredException caught!");
        }
        assertTrue(balanceAccount.getBalance() == 65880.50);
        assertFalse(balanceAccount.getBalance() == 65930.50);
    }

    @Test
    public void testDetermineDepositAmountBoundary() {
        try {
            ih.determineDepositAmount(100.0, balanceAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("The deposit amount is 100.00, therefore exception should not be expected.");
        }
        assertEquals(balanceAccount.getBalance(), 65980.50, delta);
    }

    @Test
    public void testDetermineDepositAmountLargerThanAHundred() {
        try {
            ih.determineDepositAmount(500.0, balanceAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("The deposit amount is greater than 100.00, therefore exception should not be expected.");
        }
        assertEquals(balanceAccount.getBalance(), 66380.5, delta);
    }

    @Test
    public void testDetermineWithdrawAmountLessThanFifty() {
        try {
            ih.determineWithdrawAmount(20.0, balanceAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            System.out.println("NotGreaterOrEqualToFiftyException caught.");
        } catch (NotEnoughMoneyException e) {
            fail("Not this exception");
        }
        assertTrue(balanceAccount.getBalance() == 65880.50);
        assertFalse(balanceAccount.getBalance() == 65860.50);
    }

    @Test
    public void testDetermineWithdrawAmountBoundary() {
        try {
            ih.determineWithdrawAmount(50.0, balanceAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("Withdraw amount is 50.0. Exception not expected here.");
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw amount is less than current amount in balance. Exception not expected here.");
        }
        assertEquals(balanceAccount.getBalance(), 65830.50, delta);
    }

    @Test
    public void testDetermineWithdrawAmountGreaterThanFifty() {
        try {
            ih.determineWithdrawAmount(1450.0, balanceAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("Withdraw amount is 1450.0. Exception not expected here.");
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw amount is less than current amount in balance. Exception not expected here.");
        }
        assertEquals(balanceAccount.getBalance(), 64430.5, delta);
    }

    @Test
    public void testDetermineWithdrawAmountAllAmount() {
        try {
            ih.determineWithdrawAmount(65880.50, balanceAccount);
        } catch (NotGreaterThanOrEqualAmountException e) {
            fail("Withdraw amount is 64380.5. Exception not expected here.");
        } catch (NotEnoughMoneyException e) {
            fail("The withdraw amount is equal to the current amount in balance. Exception not expected here.");
        }
        assertEquals(balanceAccount.getBalance(), 0.0, delta);
    }
}
