package tests;

import model.BalanceAccount;
import model.ListOfBalanceAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestListOfBalanceAccount {

    private ListOfBalanceAccount bl;
    private BalanceAccount balanceAccount1;
    private BalanceAccount balanceAccount2;
    private static final int ACCT_NUMBER1 = 1234567;
    private static final int ACCT_NUMBER2 = 2468101;

    @BeforeEach
    public void setUp() {
        bl = new ListOfBalanceAccount();
        balanceAccount1 = new BalanceAccount("Anthony", ACCT_NUMBER1, 5500.00);
        balanceAccount2 = new BalanceAccount("Eric", ACCT_NUMBER2, 2200.88);
        bl.addToList(balanceAccount1);
        bl.addToList(balanceAccount2);
    }

    @Test
    public void testAddToList() {
        assertEquals(bl.size(), 2);
        BalanceAccount z = new BalanceAccount("", 0, 0);
        bl.addToList(z);
        assertTrue(bl.size() == 3);
        bl.addToList(balanceAccount2);
        assertEquals(bl.size(), 3);
    }


    @Test
    public void testRemoveOneFromList() {
        assertEquals(bl.size(), 2);
        bl.removeFromList(balanceAccount1);
        assertEquals(bl.size(), 1);
    }

    @Test
    public void testRemoveMoreFromList() {
        assertEquals(bl.size(), 2);
        bl.removeFromList(balanceAccount1);
        assertEquals(bl.size(), 1);
        bl.removeFromList(balanceAccount2);
        assertEquals(bl.size(), 0);

        //this is now an empty list, we are testing whether an empty array list is really a size of 0
        bl.removeFromList(balanceAccount1);
        assertFalse(bl.size() == 1);
        assertTrue(bl.size() == 0);
    }

    @Test
    public void testSelectAccountOneOrMoreElement() {
        assertTrue(bl.size() == 2);
        assertTrue(bl.contains(balanceAccount1));
        assertTrue(bl.contains(balanceAccount2));
        bl.selectAccount("Anthony", ACCT_NUMBER1);
        assertEquals(bl.selectAccount("Anthony", ACCT_NUMBER1), balanceAccount1);
        bl.selectAccount("Eric", ACCT_NUMBER2);
        assertEquals(bl.selectAccount("Eric", ACCT_NUMBER2), balanceAccount2);
        assertEquals(bl.selectAccount("Jonathan", 1928), null);
        assertEquals(bl.selectAccount("Anthony", 12345), null);
        assertEquals(bl.selectAccount("Anthony", ACCT_NUMBER2), null);
        bl.removeFromList(balanceAccount1);
        bl.removeFromList(balanceAccount2);
        assertFalse(bl.contains(balanceAccount1));
        assertFalse(bl.contains(balanceAccount2));
        assertTrue(bl.size() == 0);
        assertEquals(bl.selectAccount("Anthony", ACCT_NUMBER1), null);

    }

    @Test
    public void testSelectAccountNoElement() {
        bl.removeFromList(balanceAccount1);
        bl.removeFromList(balanceAccount2);
        assertEquals(bl.size(), 0);
        assertFalse(bl.size() == 1);
    }

    @Test
    public void testGetAccount() {
        assertEquals(bl.getAccount(0), balanceAccount1);
        assertEquals(bl.getAccount(1), balanceAccount2);
    }
}

