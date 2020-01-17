package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOfBalanceAccount implements Saveable, Loadable {

    private List<BalanceAccount> accountList;

    public ListOfBalanceAccount() {
        accountList = new ArrayList<>();
    }

    //EFFECTS: adds the bank account to the list accountList
    public void addToList(BalanceAccount b) {
        if (!accountList.contains(b)) {
            accountList.add(b);
        }
    }

    //REQUIRES: the list must contain at least one element
    //MODIFIES: this
    //EFFECTS: delete the user's bank account from th e list accountList
    public void removeFromList(BalanceAccount b) {
        if (accountList.size() > 0) {
            accountList.remove(b);
        }
        System.out.println("The account " + b.getName() + " with the account number " + b.getAccountNumber() + " is "
                + "removed. Thank you!");
    }

    //REQUIRES: the list must contain at least one element
    //EFFECTS: selects the account based on the name and the account number, if the corresponding account is not in the
    //         list, return null
    public BalanceAccount selectAccount(String name, int accNum) {
        if (accountList.size() > 0) {
            for (BalanceAccount b : accountList) {
                if (name.equals(b.getName()) && accNum == b.getAccountNumber()) {
                    return b;
                }
            }
        }
        return null;
    }

    //EFFECTS: returns the size of the list of bank account, which gives the number of accounts
    public int size() {
        return accountList.size();
    }

    //REQUIRES: the list must contain at least one element
    //EFFECTS: returns true if the list contains the given bank account, otherwise false
    public boolean contains(BalanceAccount b) {
        return accountList.contains(b);
    }

    //REQUIRES: the list must contain at least one element
    //EFFECTS: returns gets the account at the given index
    public BalanceAccount getAccount(int index) {
        return accountList.get(index);
    }

    @Override
    public void save(String txtFile) throws IOException {

        PrintWriter printWriter = new PrintWriter("outputFile.txt", "UTF-8");

        for (BalanceAccount a : accountList) {
            System.out.println(a.getName() + " " + a.getAccountNumber());
            printWriter.println(a.getName() + ":" + a.getAccountNumber() + ":"
                    + a.getBalance());
        }
        printWriter.close();
    }

    @Override
    public void load(String txtFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(txtFile));
        for (String line : lines) {
            List<String> partsOfLine = splitOnSpace(line);
            BalanceAccount a = new BalanceAccount(partsOfLine.get(0), Integer.parseInt(partsOfLine.get(1)),
                    Double.parseDouble(partsOfLine.get(2)));
            accountList.add(a);
        }
    }

    private static List<String> splitOnSpace(String line) {
        String[] splits = line.split(":");
        return new ArrayList<>(Arrays.asList(splits));
    }
}

