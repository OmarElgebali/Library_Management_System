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
    public Book() {
        Main.printTitle("New Book");
        Scanner input = new Scanner(System.in);
        String name;
        String author;
        do {
            ifFound = false;
            System.out.print("Enter User ID: ");
            id = input.next();
            for (int i = 0; i < Main.Users.size(); i++) {
                if (id.equals(Main.Users.get(i).id)){
                    ifFound = true;
                    System.out.println("!! User Found, try again");
                    break;
                }
            }
        }while (ifFound);
        this.name = name;
        this.author = author;
        System.out.print("Enter Book Pages: ");
        pages = input.nextInt();
        System.out.print("Enter Book Price: ");
        price = input.nextDouble();
        System.out.println("User Added to System");
    }
}
