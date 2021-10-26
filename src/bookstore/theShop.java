package bookstore;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class theShop {

    theShop() {
    }

    public void bookshop() throws FileNotFoundException {
        Order shopping = new Order("i", "j", 1, 1, 1);

        shopping.createBookshopBooks();
        System.out.println("Welcome to Bookstore. \nThese are the books that are on sale");

        shopping.displayBookshopBooks();

        shopping.askQuestionCheckInput("Press the nr of the book you want "
                + "to buy: ", "1,2,3,4,5,6,7,8", "buyBook");

        System.out.println("\nYour order: ");
        shopping.getOrderNr();
        shopping.displayCustomersOrder();
        shopping.getWeightOfOrder();
        shopping.totalPrice();
        System.out.print(" kronor.\n");

        shopping.deliveryOrCollectAndInvoice();

    }
}
