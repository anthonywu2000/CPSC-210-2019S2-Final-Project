package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondFrame extends JFrame {

    private JPanel panel;
    private JFrame frame2;
    private JLabel listSelectionOptionsLabel;
    private JLabel message;
    private JButton zero;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;

    public SecondFrame() {

        frame2 = new JFrame("Options One");
        message =  new JLabel();

        listSelectionOptionsLabel = new JLabel();
        listSelectionOptionsLabel.setText("Hello HelloWorld! Select any service to get started!");


        zero = new JButton("Exit This Service");
        zero.setBounds(20, 20, 20, 20);
        changeLooks(zero, Color.orange);
        one = new JButton("Make Transactions");
        one.setBounds(20, 20, 20, 20);
        changeLooks(one, Color.pink);
        two = new JButton("Deposit Money to Accounts");
        two.setBounds(20, 20, 20, 20);
        changeLooks(two, new Color(189,183,107));
        three = new JButton("Credit Card Payment");
        three.setBounds(20, 20, 20, 20);
        changeLooks(three, Color.green);
        four = new JButton("Other Service");
        four.setBounds(20, 20, 20, 20);
        changeLooks(four, new Color(0, 191, 255));


        panel = new JPanel(new GridLayout(3, 1));
        panel.add(listSelectionOptionsLabel);
        panel.add(zero);
        panel.add(one);
        panel.add(two);
        panel.add(three);
        panel.add(four);
        panel.add(message);



        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Adding the listeners to components..
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JOptionPane.showMessageDialog(null, "Thank you for "
                        + "using SmartBanking operated by the LA West Coast Bank!");
            }
        });

        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameInputOne();
                dispose();
            }
        });

        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameInputTwo();
                dispose();
            }
        });

        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PaymentWithBalanceOnly();
            }
        });

        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ThirdFrame();
            }
        });

        add(panel, BorderLayout.CENTER);
        setTitle("Options One");
        setSize(1050, 140);
        setLocation(250, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SecondFrame();
    }

    public static void changeLooks(JButton b, Color c) {
        b.setBackground(c);
        b.setOpaque(true);
        b.setBorderPainted(false);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
