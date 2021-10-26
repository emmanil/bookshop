package bookstore;

import java.util.ArrayList;

public class Book {

    //Array med object Book i sin collection. 
    ArrayList<Book> theBookshop = new ArrayList<>();

    String bookname;
    String author;
    int nrOfPages;
    double bookprice;

    Book(String author, String bookname, int pages, double price) {
        this.author = author;
        this.bookname = bookname;
        this.nrOfPages = pages;
        this.bookprice = price;
    }

    public void createBookshopBooks() {
        //ArrayList med object Book i sin collection.

        Book HP1 = new Book("JK Rowling", "Harry Potter and the Philosopher's stone", 309, 151.99);
        Book HP2 = new Book("JK Rowling", "Harry Potter and the Chamber of secrets", 341, 152.99);
        Book HP3 = new Book("JK Rowling", "Harry Potter and the Prisoner of Azkaban", 448, 153.99);
        Book HP4 = new Book("JK Rowling", "Harry Potter and the Goblet of Fire", 752, 154.99);
        Book HP5 = new Book("JK Rowling", "Harry Potter and the Order of the Phoenix", 896, 155.99);
        Book HP6 = new Book("JK Rowling", "Harry Potter and the Half-Blood Prince", 652, 156.99);
        Book HP7 = new Book("JK Rowling", "Harry Potter and the Deathly Hallows", 784, 157.99);
        Book HP8 = new Book("JK Rowling", "Harry Potter and the Cursed Child", 336, 158.99);

        theBookshop.add(HP1);
        theBookshop.add(HP2);
        theBookshop.add(HP3);
        theBookshop.add(HP4);
        theBookshop.add(HP5);
        theBookshop.add(HP6);
        theBookshop.add(HP7);
        theBookshop.add(HP8);
    }

    public void displayBookshopBooks() {
        int j;
        for (int i = 0; i < theBookshop.size(); i++) {
            j = i + 1; //index displayed for customer starts at 1.   
            System.out.print("Nr " + j + ". " + theBookshop.get(i).author + ", "
                    + theBookshop.get(i).bookname + ", " + theBookshop.get(i).bookprice
                    + " kronor, " + theBookshop.get(i).nrOfPages + " pages. \n");
        }
    }

}
