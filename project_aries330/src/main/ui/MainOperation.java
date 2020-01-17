package ui;

import java.util.Scanner;

public class MainOperation {

    public static Operations o = new Operations();

    public static void main(String[] args) {
        while (true) {
            o.enterAccount();
            break;
        }
        Scanner selector = new Scanner(System.in);
        System.out.println(printSelections());
        String input = selector.nextLine();
        Operation operation = Operation.parse(input);
        operation.run();
        Scanner viewOrassistance = new Scanner(System.in);
        printSelections1();
        String vora = viewOrassistance.nextLine();
        Operation operation1 = Operation.parse(vora);
        operation1.run();
    }

    public static String printSelections() {
        return "To get started, please select '1' for making transactions, '2' for deposit/withdraw, '3' "
                + "for making a credit card payment, or '0' to skip this service: ";
    }

    public static String printSelections1() {
        return "To view your accounts summary, please select '5'. For further assistance or inquiries"
                + ", such as bank information, please select '6'. To view currency exchange rates, "
                + " please select '7'. To exit, please select '8': ";
    }
}
