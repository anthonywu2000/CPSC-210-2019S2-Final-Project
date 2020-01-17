package tests;

import model.BalanceAccount;
import model.ListOfBalanceAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSaveable {
    private ListOfBalanceAccount testList;
    private BalanceAccount balanceAccount1;
    private BalanceAccount balanceAccount2;

    @BeforeEach
    public void setUp() {
        testList = new ListOfBalanceAccount();
        balanceAccount1 = new BalanceAccount("James Cook", 12345, 5500.0);
        balanceAccount2 = new BalanceAccount("Joyce Yang", 24681, 2450.0);
        testList.addToList(balanceAccount1);
        testList.addToList(balanceAccount2);
    }

    @Test
    public void testSave() throws IOException {
        testList.save("./src/outputFile.txt");
        List<String> lines = Files.readAllLines(Paths.get("outputFile.txt"));
        assertEquals(lines.get(0), "James Cook:12345:5500.0");
        assertEquals(lines.get(1), "Joyce Yang:24681:2450.0");
    }
}
