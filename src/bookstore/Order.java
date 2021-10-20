package bookstore;

public class Order extends Book {

    double shippingFee = 0;
    double totalPriceOfBooks = 0;
    double totalWeight = 0;
    String orderNr = "01";

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
                    + theCustomersOrder.get(i).bookname + ", " + theCustomersOrder.get(i).bookprice + " £, "
                    + theCustomersOrder.get(i).nrOfPages + " pages. \n");
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
        //PROBLEM  % .2d  don't work
        System.out.println("The total weight is " + totalWeight + " kilo.");
    }

    public void totalPrice() {
        totalPriceOfBooks = 0;
        for (int i = 0; i < theCustomersOrder.size(); i++) {
            totalPriceOfBooks = totalPriceOfBooks + theCustomersOrder.get(i).bookprice;
        }
        System.out.print("The total price is " + (totalPriceOfBooks + this.shippingFee));
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
            sb.append(theBookshop.get(i).author + ", ");
            sb.append(theBookshop.get(i).bookname + ", ");
            sb.append(theBookshop.get(i).bookprice + "£, ");
            sb.append(theBookshop.get(i).nrOfPages + " pages. \n");
        }
        sb.append("The total weight is " + totalWeight % .2f + " kilo.");
        sb.append("\nThe total price is " + (totalPriceOfBooks + this.shippingFee) + " kronor. ");
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

    public void addThatBook(int choice) {
        //in menue for customer books start at index 1, but irl it's index 0. Hence -1.   
        //Collections.copy(theCustomersOrder, theBookshop.subList(choice, choice));
        theCustomersOrder.add(theBookshop.get(choice - 1));
    }
}
