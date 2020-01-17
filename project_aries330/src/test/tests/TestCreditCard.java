package tests;

import exceptions.NegativeAmountException;
import exceptions.PaymentPaidOrBalanceNotEnoughForPaymentException;
import exceptions.ZeroException;
import model.BalanceAccount;
import model.CreditCardAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class TestCreditCard {

    private CreditCardAccount creditCardAccount;
    private BalanceAccount balanceAccount;
    private final int creditCardNumber = 451088976;
    private final int balanceAccountNumber = 123456789;
    private static final double delta = 0.001;

    @BeforeEach
    public void setUp() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                0.0, 0, false);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 5930.45);
    }

    @Test
    public void testConstructor() {
        assertNotNull(creditCardAccount);
    }

    @Test
    public void testAddCreditPoints() {
        creditCardAccount.addCreditPoints(100.00);
        assertTrue(creditCardAccount.getcreditPoints() == 0);
        creditCardAccount.addCreditPoints(500.00);
        assertTrue(creditCardAccount.getcreditPoints() == 160);
        creditCardAccount.addCreditPoints(1000.00);
        assertTrue(creditCardAccount.getcreditPoints() == 340);
        creditCardAccount.addCreditPoints(1500.00);
        assertTrue(creditCardAccount.getcreditPoints() == 590);
        creditCardAccount.addCreditPoints(700.00);
        assertTrue(creditCardAccount.getcreditPoints() == 750);
        creditCardAccount.addCreditPoints(1255.00);
        assertTrue(creditCardAccount.getcreditPoints() == 930);
        creditCardAccount.addCreditPoints(2000.00);
        assertTrue(creditCardAccount.getcreditPoints() == 1180);
    }

    @Test
    public void testAddSpentToPaymentZero() {
        try {
            creditCardAccount.addSpentToPayment(0.0);
        } catch (ZeroException e) {
            System.out.println("The amount spent from using credit card cannot be 0.0.");
        } catch (NegativeAmountException e) {
            fail("The amount spent is not negative. This exception is not expected.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
    }

    @Test
    public void testAddSpentToPaymentNegative() {
        try {
            creditCardAccount.addSpentToPayment(-190.00);
        } catch (ZeroException e) {
            fail("The amount spent is not 0.0. This exception is not expected.");
        } catch (NegativeAmountException e) {
            System.out.println("The amount spent from using credit card cannot be negative.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
        assertNotEquals(creditCardAccount.getPaymentNeededSoFar(), -190.00);
    }

    @Test
    public void testAddSpentToPaymentWithoutCreditPointIncrease() {
        try {
            creditCardAccount.addSpentToPayment(345.42);
        } catch (ZeroException e) {
            fail("The amount spent is not 0.0. This exception is not expected.");
        } catch (NegativeAmountException e) {
            fail("The amount spent is not negative. This exception is not expected.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 345.42, delta);
        assertEquals(creditCardAccount.getPayment(), false);
        assertEquals(creditCardAccount.getcreditPoints(), 0);
    }

    @Test
    public void testAddSpentToPaymentWithCreditPointIncrease() {
        try {
            creditCardAccount.addSpentToPayment(2000.00);
        } catch (ZeroException e) {
            fail("The amount spent is not 0.0. This exception is not expected.");
        } catch (NegativeAmountException e) {
            fail("The amount spent is not negative. This exception is not expected.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 2000.00, delta);
        assertEquals(creditCardAccount.getPayment(), false);
        assertEquals(creditCardAccount.getcreditPoints(), 250);
    }

    @Test
    public void testPaidPaymentFalseBalanceNotEnough() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                3500.89, 0, false);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 1000.00);
        try {
            creditCardAccount.paid(creditCardAccount, balanceAccount);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            System.out.println("Balance amount is not enough to pay for the credit card payment.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 3500.89, delta);
        assertEquals(creditCardAccount.getPayment(), false);
        assertEquals(balanceAccount.getBalance(), 1000.00, delta);
    }

    @Test
    public void testPaidPaymentFalseBalanceEnough() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                1590.81, 0, false);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 5000.54);
        try {
            creditCardAccount.paid(creditCardAccount, balanceAccount);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            fail("Exception should not be thrown here. The account hasn't been paid and there is sufficient amount in "
                    + "balance o pay for it.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
        assertEquals(creditCardAccount.getPayment(), true);
        assertEquals(balanceAccount.getBalance(), 3409.73, delta);
    }

    @Test
    public void testPaidPaymentTrueBalanceNotEnough() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                3500.89, 0, true);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 1000.00);
        try {
            creditCardAccount.paid(creditCardAccount, balanceAccount);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            System.out.println("You have already paid for this payment.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 3500.89, delta);
        assertEquals(creditCardAccount.getPayment(), false);
        assertEquals(balanceAccount.getBalance(), 1000.00, delta);
    }

    @Test
    public void testPaidPaymentTrueBalanceEnough() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                3500.89, 0, true);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 9000.00);
        try {
            creditCardAccount.paid(creditCardAccount, balanceAccount);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            System.out.println("You have already paid for this payment.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
        assertEquals(creditCardAccount.getPayment(), true);
        assertEquals(balanceAccount.getBalance(), 5499.11, delta);
    }

    @Test
    public void testPaidPaymentTrueBalanceEqualsCreditPaymentNeeded() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                3500.89, 0, true);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 3500.89);
        try {
            creditCardAccount.paid(creditCardAccount, balanceAccount);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            System.out.println("You have already already paid for this payment.");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
        assertEquals(creditCardAccount.getPayment(), true);
        assertEquals(balanceAccount.getBalance(), 0.0, delta);
    }

    @Test
    public void testPaidPaymentFalseBalanceEqualsCreditPaymentNeeded() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                3500.89, 0, false);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 3500.89);
        try {
            creditCardAccount.paid(creditCardAccount, balanceAccount);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            fail("The payment has not been made and the balance is sufficient for the payment to be processed");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
        assertEquals(creditCardAccount.getPayment(), true);
        assertEquals(balanceAccount.getBalance(), 0.0, delta);
    }

    @Test
    public void testPaidTruePaymentNeededIsZero() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                0.0, 0, true);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 3500.89);
        try {
            creditCardAccount.paid(creditCardAccount, balanceAccount);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            System.out.println("Payment has been made, exception is expected here!");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
        assertEquals(creditCardAccount.getPayment(), true);
        assertEquals(balanceAccount.getBalance(), 3500.89, delta);
    }

    @Test
    public void testPaidFalsePaymentNeededIsZero() {
        creditCardAccount = new CreditCardAccount("Dennis", creditCardNumber,
                0.0, 0, false);
        balanceAccount = new BalanceAccount("Dennis", balanceAccountNumber, 3500.89);
        try {
            creditCardAccount.paid(creditCardAccount, balanceAccount);
        } catch (PaymentPaidOrBalanceNotEnoughForPaymentException e) {
            System.out.println("Payment has been made, exception is expected here!");
        }
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
        assertEquals(creditCardAccount.getPayment(), true);
        assertEquals(balanceAccount.getBalance(), 3500.89, delta);
    }

    
    @Test
    public void testGetName() {
        assertEquals(creditCardAccount.getName(), "Dennis");
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals(creditCardAccount.getCardNumber(), creditCardNumber);
    }

    @Test
    public void testGetPaymentNeededSoFar() {
        assertEquals(creditCardAccount.getPaymentNeededSoFar(), 0.0, delta);
    }

    @Test
    public void testGetCreditPoints() {
        assertEquals(creditCardAccount.getcreditPoints(), 0);
    }

    @Test
    public void getPayment() {
        assertFalse(creditCardAccount.getPayment());
    }
}
