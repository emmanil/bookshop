package bookstore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class theShop {
    
    int choice = 0;
    
    //constructor
    theShop(){
    }
    
    public void bookshop() throws FileNotFoundException{
        Order shopping = new Order("i", "j", 1, 1);

        Scanner scan = new Scanner(System.in);

        shopping.createBookshopBooks();
        System.out.println("Welcome to Bookstore. \nThese are the books that are on sale");

        boolean buyBooks = true;
        while (buyBooks == true) {
            shopping.displayBookshopBooks();

            Order.askQuestioncheckInputIsCorrect("Press the nr of the book you want to buy: ", "1,2,3,4,5,6,7,8");
            shopping.addThatBook();

            Order.askQuestioncheckInputIsCorrect("Press 1 if you want to continue buying \nPress 2 to go to cash out. ", "1,2");
            System.out.println(" choice " + choice);
            
            if (choice == 2) { 
            System.out.println("B test blabla");
            buyBooks = !true;
            }
        }

        System.out.println("\nYour order: ");
        shopping.getOrderNr();
        shopping.displayCustomersOrder();
        shopping.getWeightOfOrder();
        shopping.totalPrice();
        System.out.print(" kronor.\n");

        Order.askQuestioncheckInputIsCorrect("Enter 1 if you want to collect your books, "
                + "\nenter 2 if you want them delivered. ", "1,2");
      //  Order.updateNum(choice);

        //create a new file, new file has the same name as the ordernr.
        PrintStream out = new PrintStream(new FileOutputStream(shopping.getInvoiceName("gettingNameFromMethod")));

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
