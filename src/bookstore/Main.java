package bookstore;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        int choice;

        Order shopping = new Order("i", "j", 1, 1);
        shopping.createBookshopBooks();

        System.out.println("Welcome to Bookstore. \nThese are the books that are on sale");

        boolean buyBooks = true;
        while (buyBooks = true) {
            shopping.displayBookshopBooks();

            System.out.println("Press the nr of the book you want to buy: ");
            choice = scan.nextInt();
            shopping.addThatBook(choice);

            System.out.println("Press 1 if you want to continue buying \nPress 2 to go to cash out. ");
            choice = scan.nextInt();

            if (choice == 2) {
                buyBooks = !true;
                break;
            }
        }

        System.out.println("\nYour order: ");
        shopping.getOrderNr();
        shopping.displayCustomersOrder();
        shopping.getWeightOfOrder();
        shopping.totalPrice();
        System.out.print(" kronor.\n");

        System.out.println("Enter 1 if you want to collect your books, "
                + "\nenter 2 if you want them delivered. ");
        choice = scan.nextInt();

        //create a new file, new file has the same name as the ordernr.
        PrintStream out = new PrintStream(new FileOutputStream(shopping.getInvoiceName("whateverText")));

        switch (choice) {
            case 1:
                shopping.MessageCollect();

                out.print(shopping.createInvoice("collect your books"));
                out.close();
                break;

            case 2:
                shopping.MessageDelivery();
                System.out.println("To confirm your purchase press 1");
                choice = scan.nextInt();

                if (choice == 1) {
                    System.out.println("\nThank you for shopping at Bookstore.\nYour invoice:");
                    shopping.MessageDelivery();
                    out.print(shopping.createInvoice("have your books delivered"));
                    out.close();
                } else {
                    System.out.println("You have not confirmed your purchase. To confirm "
                            + "your purchase press 1.\nIf you want to cancel your order press 2.");
                    choice = scan.nextInt();

                    if (choice == 1) {
                        System.out.println("\nThank you for shopping at Bookstore.");
                        shopping.MessageDelivery();
                        out.print(shopping.createInvoice("have your books delivered"));
                        out.close();
                    } else {
                        break;
                    }
                }
        }

    }
}
