import java.util.Scanner;

public class Book {
    private final String name;
    private final String author;
    private final int pages;
    private final double price;

    public Book(String name, String author, int pages, double price) {
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }
    public Book() throws InterruptedException {
        OutputOperations.display(TypePrint.TITLE,"New Book");
        Scanner input = new Scanner(System.in);
        String name;
        String author;
        boolean ifFound;
        do {
            ifFound = false;
            System.out.print("Enter Book Name: ");
            name = input.next();
            System.out.print("Enter Book Author: ");
            author = input.next();
            if (Main.Books.get(name) != null && author.equals(Main.Books.get(name).getAuthor())){
                ifFound = true;
                OutputOperations.display(TypePrint.LOADING, "Checking if book exists");
                OutputOperations.display(TypePrint.INVALID, "Book found, try again");
            }
        }while (ifFound);
        this.name = name;
        this.author = author;
        System.out.print("Enter Book Page: ");
        pages = input.nextInt();
        System.out.print("Enter Book Price: ");
        price = input.nextDouble();
        OutputOperations.display(TypePrint.LOADING, "Adding Book");
        OutputOperations.display(TypePrint.FINISH, "Book added to system");
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }
}
