package ui;

import javax.swing.*;

import static ui.Operations.helloWorld;

public class ContactsList {

    private JFrame contactsFrame;
    private JPanel panel;
    private JLabel listOfBranchesAndContacts;

    public ContactsList() {

        contactsFrame = new JFrame("Available Branches and Contact Number");
        panel = new JPanel();

        JOptionPane.showMessageDialog(null,"Bank Name: LA West Coast Bank. "
                + "\nBelow are the list of branches available: "
                + "\n0000 Madrona Ave, Torrance, CA 92786    Phone: 111-111-1111"
                + "\n710 Maple Ave, LA, CA 54637    Phone: 222-222-2222"
                + "\n1000 Platt Blvd, Claremont, CA 89874   Phone: 333-333-3333"
                + "\n300 N E St, San Bernardino, CA 65432    Phone: 444-444-4444"
                + "\n4113 Pereira Dr, Irvine, CA 12345   Phone: 555-555-5555"
                + "\n308 Westwood Plaza, LA, CA 90095   Phone: 666-666-6666"
                + "\n3131 S Hoover St #1910, LA, CA 90089   Phone: 777-777-7777"
                + "\n635 S Broadway, LA, CA 90014   Phone: 888-888-8888"
                + "\n5301 Whittier Blvd, East LA, CA 90022   Phone: 999-999-9999"
                + "\nYour current address is: " + helloWorld.getAddress()
                + "\nAll branches have the same opening and closing times from 8AM to 6:30PM (Monday "
                + "to Saturday respectively). Phone service is only available until 5PM.");
    }

    public static void main(String[] args) {
        new ContactsList();
    }
}
