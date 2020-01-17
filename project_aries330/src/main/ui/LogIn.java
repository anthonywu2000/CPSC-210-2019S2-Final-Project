package ui;

//shamelessly quoting from: https://www.onlinetutorialspoint.com/java/java-swing-login-example.html

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.SecondFrame.changeLooks;

public class LogIn extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel message;
    private JTextField userNameText;
    private JPasswordField passwordText;
    private JButton submit;

    public LogIn() {

        userLabel = new JLabel();
        userLabel.setText("User Name:");
        userNameText = new JTextField();

        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordText = new JPasswordField();

        submit = new JButton("Login");
        changeLooks(submit, Color.yellow);

        panel = new JPanel(new GridLayout(3, 1));

        panel.add(userLabel);
        panel.add(userNameText);
        panel.add(passwordLabel);
        panel.add(passwordText);

        message = new JLabel();
        panel.add(message);
        panel.add(submit);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Adding the listeners to components..
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(1050, 140);
        setLocation(250, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LogIn();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userNameText.getText();
        String password = passwordText.getText();
        if (userName.trim().equals("helloworld") && password.trim().equals("helloworld1234")) {
            dispose();
            new SecondFrame();
        } else {
            message.setText("Invalid username or password.");
        }
    }
}


