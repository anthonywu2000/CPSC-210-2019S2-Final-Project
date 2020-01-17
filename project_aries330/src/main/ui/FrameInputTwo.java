package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameInputTwo extends JFrame {

    private static JFrame inputTwoframe;
    private static JPanel panel;
    private static JLabel selectBalanceOrSavings;
    private static JLabel message;
    private static JButton balance;
    private static JButton savings;

    public FrameInputTwo() {

        inputTwoframe = new JFrame("Making Online Deposits");
        panel = new JPanel();
        selectBalanceOrSavings = new JLabel();
        selectBalanceOrSavings.setText("You have selected to make deposits. "
                + "Please select to make deposits from balance or savings account: ");
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

        inputTwoframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        inputTwoframe.add(panel, BorderLayout.CENTER);
        inputTwoframe.setTitle("Making Online Deposits");
        inputTwoframe.setSize(1050, 140);
        inputTwoframe.setLocation(250, 250);
        inputTwoframe.setVisible(true);

        balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepositOnBalance();
                dispose();
                new SecondFrame();
            }
        });

        savings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepositOnSavings();
                dispose();
                new SecondFrame();
            }
        });
    }

    public static void main(String[] args) {
        new FrameInputTwo();
    }
}
