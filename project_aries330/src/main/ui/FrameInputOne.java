package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameInputOne extends JFrame {

    private static JFrame inputOneframe;
    private static JPanel panel;
    private static JLabel selectBalanceOrSavings;
    private static JLabel message;
    private static JButton balance;
    private static JButton savings;

    public FrameInputOne() {

        inputOneframe = new JFrame("Making Online Transactions");
        panel = new JPanel();
        selectBalanceOrSavings = new JLabel();
        selectBalanceOrSavings.setText("You have selected to make transactions. "
                + "Please select to make transaction from balance or savings account: ");
        message = new JLabel();
        balance = new JButton("Balance Account");
        balance.setBounds(50,50,50,50);
        savings = new JButton("Savings Account");
        savings.setBounds(50,50,50,50);

        panel = new JPanel(new GridLayout(3, 1));

        panel.add(selectBalanceOrSavings);
        panel.add(message);
        panel.add(balance);
        panel.add(savings);

        inputOneframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        inputOneframe.add(panel, BorderLayout.CENTER);
        inputOneframe.setTitle("Making Online Transactions");
        inputOneframe.setLocation(250, 250);
        inputOneframe.setSize(1050, 140);
        inputOneframe.setVisible(true);

        balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransactionsOnBalance();
                new SecondFrame();
            }
        });

        savings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransactionsOnSavings();
                new SecondFrame();
            }
        });

    }

    public static void main(String[] args) {
        new FrameInputTwo();
    }
}
