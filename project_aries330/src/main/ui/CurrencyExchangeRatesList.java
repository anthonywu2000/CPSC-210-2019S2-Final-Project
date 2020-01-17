package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static ui.CurrencyExchangeRates.jsonObject3;

public class CurrencyExchangeRatesList {

    private JFrame contactsFrame;
    private JPanel panel;
    private JLabel listOfBranchesAndContacts;

    public CurrencyExchangeRatesList() {

        String enterCurrencyExchange = JOptionPane.showInputDialog("Please enter "
                + "the currency name in the format 'USD(currency name code)', \nfor example, to search "
                + "the exchange rate for Australian dollar, enter 'USDAUD'"
                + "(the source of conversion must be from 1 USD). "
                +  mouseClicked(null));
        if (!CurrencyExchangeRates.jsonObject3.containsKey(enterCurrencyExchange))
            JOptionPane.showMessageDialog(null, "The currency is not available or "
                    + "the format is not correct.");
        else {
            JOptionPane.showMessageDialog(null,
                    "$1 USD to "
                            + (enterCurrencyExchange.substring(enterCurrencyExchange.length() - 3) + " is: "
                            + jsonObject3.get(enterCurrencyExchange)));
        }
    }

    public static void main(String[] args) {
        new CurrencyExchangeRatesList();
    }

    private static String mouseClicked(MouseEvent e) {
        try {
            Desktop.getDesktop().browse(new URI("https://www1.oanda.com/currency/help/currency-iso-code-country"));
            return "\nAn ISC reference table website is provided.";
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
            return "ERROR!";
        }
    }
}
