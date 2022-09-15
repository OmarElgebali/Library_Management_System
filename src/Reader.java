import java.util.Scanner;

public class Reader {
    private final String id;
    private final String password;
    private final String type;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String cellPhone;
    private final String email;
    public Book[] selfBooks;
    public Book[] rentedBooks;
    public boolean gotBlocked;

    public Reader() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        gotBlocked = false;
        boolean ifFound;
        String id;
        do {
            ifFound = false;
            System.out.print("Enter Reader ID: ");
            id = input.next();
            if (Main.Readers.get(id) != null){
                ifFound = true;
                OutputOperations.display(TypePrint.LOADING, "Checking if reader exists");
                OutputOperations.display(TypePrint.INVALID, "Reader found, try again");
            }
        }while (ifFound);
        this.id = id;
        System.out.print("Enter Reader Password: ");
        password = input.next();
        System.out.print("Enter Reader Type: ");
        type = input.next();
        System.out.print("Enter Reader First Name: ");
        firstName = input.next();
        System.out.print("Enter Reader Last Name: ");
        lastName = input.next();
        System.out.print("Enter Reader Address: ");
        address = input.next();
        System.out.print("Enter Reader CellPhone: ");
        cellPhone = input.next();
        System.out.print("Enter Reader Email: ");
        email = input.next();


    }
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getType() {
        return type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getEmail() {
        return email;
    }
    public void searchBook() throws InterruptedException {
        OutputOperations.display(TypePrint.TITLE,"List of Books");

    }

}
