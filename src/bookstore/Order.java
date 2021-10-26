package bookstore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Order extends Book {

    //en medlemsvariabe är inte OP: (int choice = 0;) tas bort.
    double shippingFee = 0;
    double totalPriceOfBooks = 0;
    double totalWeight = 0;
    String orderNr = "01";

    int quantity;    //int quantity är ny 23/10.

    //ArrayList med object Order i sin collection.
    ArrayList<Book> theCustomersOrder = new ArrayList<>();

    //kanske kasta nedan, om den ändå inte används.
    public Order(String author, String bookname, int pages, double price, int quantity) {
        super(author, bookname, pages, price);
        this.quantity = 1;
    }

    public void askQuestionCheckInput(String theQuestion, String theRealOptions, String whatsGoingOn) {
        String[] theIndividualOptions = theRealOptions.split(",");
        int lengthIO = theIndividualOptions.length;

        boolean intIsOk = !true;
        while (intIsOk == !true) {
            System.out.println(theQuestion);
            Scanner scan = new Scanner(System.in);

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();

                for (int i = 0; i < lengthIO; i++) {
                    int IOi = Integer.valueOf(theIndividualOptions[i]);
                    if (choice == IOi) {
                        if (whatsGoingOn.equals("buyBook")) {
                            //användaren får se +1 på index, vilket inte stämmer med böckernas eg placering.
                            addThatBook(choice - 1);
                        } else if (whatsGoingOn.equals("continueBuyingOrNot")) {
                            if (choice == 1) {
                                continueBuyingBooks();
                                // fortsätta köpa böcker
                            } else if (choice == 2) {
                                //sluta köpa böcker
                            }
                        }
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

    public void addThatBook(int val) {
        theCustomersOrder.add(theBookshop.get(val));

        //skapa nytt object från book och lägga till quantity + antal 
        //obs antal som hela tiden stämms av
        //kolla alla object som finns i kundens order och om några är samma adda de.  
        //ATT GÖRA 
        askQuestionCheckInput("Press 1 if you want to continue "
                + "buying books \nPress 2 to go to cash out. ", "1,2", "continueBuyingOrNot");
    }

    public void continueBuyingBooks() {
        displayBookshopBooks();
        askQuestionCheckInput("Press the nr of the book you want "
                + "to buy: ", "1,2,3,4,5,6,7,8", "buyBook");
        // buyBooks = true;
        //fortsätta köpa böcker
    }

    public void deliveryOrCollectAndInvoice() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        PrintStream out = new PrintStream(new FileOutputStream(this.orderNr + ".txt"));
        boolean keepGoing = true;

        while (keepGoing == true) {
            System.out.println("Enter 1 if you want to collect your books, "
                    + "\nEnter 2 if you want them delivered.");
            String choice = scan.nextLine();
            switch (choice) {
                case "1":
                    MessageCollect();
                    out.print(createInvoice("collect your books"));
                    out.close();
                    keepGoing = !true;
                    break;
                case "2":
                    MessageDelivery();
                    System.out.println("To confirm your purchase press 1.\nIf you want "
                            + "to cancel your order press 2.");
                    choice = scan.nextLine();
                    
                    boolean goOn = true;
                    while (goOn == true){
                    switch (choice) {
                        case "1":
                            System.out.println("\nThank you for shopping at Bookstore.\nYour invoice:");
                            MessageDelivery();
                            out.print(createInvoice("have your books delivered"));
                            out.close();
                            goOn = !true;
                            keepGoing = !true;
                            break;

                        case "2":
                            System.out.println("Your order is cancelled.");
                            goOn = !true;
                            keepGoing = !true;
                            break;
        
                            default:
                            System.out.println("Your input was wrong. ");
                            System.out.println("To confirm your purchase press 1.\nIf you want "
                            + "to cancel your order press 2.");
                            choice = scan.nextLine();
                            break;
                        }
                    }

                    default:
                    if (keepGoing == true)  {
                    System.out.println("Your input was wrong. ");
                    break;
                    }
            }
        }
    }

    public void MessageCollect() {
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
        int nrOfBoxesNeeded = (int) (theCustomersOrder.size() / 5 + 0.9);

        if (nrOfBoxesNeeded < 5) {
            this.shippingFee = nrOfBoxesNeeded * 150;
        } else { //5 boxes or more.
            this.shippingFee = nrOfBoxesNeeded * 90;
        }
    }

}
