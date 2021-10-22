package bookstore;

import java.util.ArrayList;
import java.util.Scanner;

public class Order extends Book {

    public static int choice = 0;

    double shippingFee = 0;
    double totalPriceOfBooks = 0;
    double totalWeight = 0;
    String orderNr = "01";

    //kanske kasta nedan, om den ändå inte används.
    public Order(String author, String bookname, int pages, double price) {
        super(author, bookname, pages, price);
    }

    public void MessageCollect() {
        //All the methods gets called and the variables gets correct. Output displayed in console. 
        System.out.println("\nYou have choosen to collect your books. "
                + "Thank you for shopping at Bookstore. \nYour purchase:");
        thePurchase();
        System.out.print(" kronor.\n");

    }

    public void MessageDelivery() {
        //Calling shipiingfee so that the shipping gets correct.
        getshippingFee();

        System.out.println("You have choosen to have your books delivered. ");
        thePurchase();
        System.out.print(" kronor including shippingfee.\n");
    }

    public void thePurchase() {
        getOrderNr();
        displayCustomersOrder();
        getWeightOfOrder();
        totalPrice();
    }

    public void getOrderNr() {
        System.out.println("Your ordernumber is " + orderNr + ".");
    }

    public void displayCustomersOrder() {
        for (int i = 0; i < theCustomersOrder.size(); i++) {
            int j = i + 1; //index displayed for customer starts at 1.   
            System.out.print("Nr " + j + ". " + theCustomersOrder.get(i).author + ", "
                    + theCustomersOrder.get(i).bookname + ", " + theCustomersOrder.get(i).bookprice
                    + " kronor, " + theCustomersOrder.get(i).nrOfPages + " pages. \n");
        }
        /* the total weight and an order number. 
        A message is then displayed  showing a summary of the order and the order number.*/
    }

    public void getWeightOfOrder() {
        double totalNrPages = 0;
        for (int i = 0; i < theCustomersOrder.size(); i++) {
            totalNrPages = totalNrPages + theCustomersOrder.get(i).nrOfPages;
        }
        totalWeight = totalNrPages / 500; //weight in kilos 
        System.out.println("The total weight is " + String.format("%.2f", totalWeight) + " kilo.");
    }

    public void totalPrice() {
        totalPriceOfBooks = 0;
        for (int i = 0; i < theCustomersOrder.size(); i++) {
            totalPriceOfBooks = totalPriceOfBooks + theCustomersOrder.get(i).bookprice;
        }
        System.out.print("The total price is " + String.format("%.2f", (totalPriceOfBooks + this.shippingFee)));
    }

    public String getInvoiceName(String anything) {
        return orderNr + ".txt";
    }

    public String createInvoice(String howBooksAredelivered) {

        StringBuilder sb = new StringBuilder();
        sb.append("You have choosen to " + howBooksAredelivered + ". Thank you for shopping "
                + "at Bookstore.");
        sb.append("\nYour ordernumber is " + orderNr + ". \nYour purchase: \n");
        for (int i = 0; i < theCustomersOrder.size(); i++) {
            sb.append(theCustomersOrder.get(i).author + ", ");
            sb.append(theCustomersOrder.get(i).bookname + ", ");
            sb.append(theCustomersOrder.get(i).bookprice + " kronor, ");
            sb.append(theCustomersOrder.get(i).nrOfPages + " pages. \n");
        }
        sb.append("The total weight is " + String.format("%.2f", totalWeight) + " kilo.");
        sb.append("\nThe total price is " + String.format("%.2f", (totalPriceOfBooks + this.shippingFee)) + " kronor. ");
        String invoice = sb.toString();
        return invoice;
    }

    public void getshippingFee() {
        int nrOfBoxesNeeded = (int) (theCustomersOrder.size() / 5 + 1.9);

        if (nrOfBoxesNeeded < 5) {
            this.shippingFee = nrOfBoxesNeeded * 150;
        } else { //5 boxes or more.
            this.shippingFee = nrOfBoxesNeeded * 90;
        }
    }

    public void addThatBook() {
        //in menue for customer books start at index 1, but irl it's index 0. Hence -1.   
       
        theCustomersOrder.add(theBookshop.get(choice-1));

        //kolla alla object som finns i kundens order och om några är samma adda de.  
        //ATT GÖRA 
    }

    public static void askQuestioncheckInputIsCorrect(String theQuestion, String theRealOptions) {
        String[] theIndividualOptions = theRealOptions.split(",");
        int lengthIO = theIndividualOptions.length;

        boolean intIsOk = !true;
        while (intIsOk == !true) {
            System.out.println(theQuestion);
            Scanner scan = new Scanner(System.in);

            if (scan.hasNextInt()) {
                choice = scan.nextInt();

                for (int i = 0; i < lengthIO; i++) {
                    int IOi = Integer.valueOf(theIndividualOptions[i]);
                    if (choice == IOi) {
                        //if inuti for loop.num motsvarar något av theIndividualOptions men... 
                        //användaren får se +1 på index, vilket inte stämmer med böckernas eg placering.
                       // num = num-1;
                        updateChoice(choice);
                        intIsOk = true;
                    }
                }
                if (intIsOk == !true) {
                    //input var fel int.
                    System.out.println("Your input was wrong. ");
                }
            } else {
                //input är inte en int.
                System.out.println("Your input was wrong. ");
            }
        }
    }

    public static int updateChoice(int val) {
      return choice;
   }
}
