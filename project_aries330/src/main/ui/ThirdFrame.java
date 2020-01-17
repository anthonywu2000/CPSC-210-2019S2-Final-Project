package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.SecondFrame.changeLooks;


public class ThirdFrame {

    private static JPanel panel;
    private static JFrame frame3;
    private static JLabel listOfAdvancedService;
    private static JLabel message;
    private static JButton five;
    private static JButton six;
    private static JButton seven;
    private static JButton eight;

    public ThirdFrame() {

        frame3 = new JFrame("Options Two");
        frame3.setLocation(500, 250);
        message =  new JLabel();

        listOfAdvancedService = new JLabel();
        listOfAdvancedService.setText("Please select any other service to get started!");

        five = new JButton("View Accounts");
        five.setBounds(20, 20, 20, 20);
        changeLooks(five, new Color(221,160,221));
        six = new JButton("Contacts Service");
        six.setBounds(20, 20, 20, 20);
        changeLooks(six, new Color(64,224,208));
        seven = new JButton("Newest Currency Exchange Rates");
        seven.setBounds(20, 20, 20, 20);
        changeLooks(seven, new Color(212,175,55));
        eight = new JButton("Go back to previous page");
        eight.setBounds(20, 20, 20, 20);
        changeLooks(eight, Color.lightGray);


        frame3.getContentPane().setBackground(Color.yellow);
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(listOfAdvancedService);
        panel.add(five);
        panel.add(six);
        panel.add(seven);
        panel.add(eight);
        panel.add(message);

        frame3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowAccounts();
            }
        });

        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactsList();
            }
        });

        seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CurrencyExchangeRatesList();
            }
        });

        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
                new SecondFrame();
            }
        });

        frame3.add(panel, BorderLayout.CENTER);
        frame3.setTitle("Options Two");
        frame3.setSize(1050, 140);
        frame3.setLocation(250, 250);
        frame3.setVisible(true);
    }

    public static void main(String[] args) {
        new ThirdFrame();
    }
}
