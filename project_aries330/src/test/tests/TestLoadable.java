package tests;

import model.ListOfBalanceAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestLoadable {

    private ListOfBalanceAccount testBA;

    @BeforeEach
    public void setUp() {
        testBA = new ListOfBalanceAccount();
    }

    @Test
    public void testLoad() throws IOException {
        testBA.load("./src/outputFile.txt");
        assertTrue(testBA.getAccount(0).getName().equals("James Cook"));
        assertTrue(testBA.getAccount(0).getAccountNumber() == 12345);
        assertTrue(testBA.getAccount(0).getBalance() == 5500.0);
        assertTrue(testBA.getAccount(1).getName().equals("Joyce Yang"));
        assertTrue(testBA.getAccount(1).getAccountNumber() == 24681);
        assertTrue(testBA.getAccount(1).getBalance() == 2450.0);
    }
}
