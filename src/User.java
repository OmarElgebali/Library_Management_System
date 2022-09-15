import java.util.Scanner;

public class User {
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

    public User() {
        Main.printTitle("New User");
        Scanner input = new Scanner(System.in);
        gotBlocked = false;
        boolean ifFound;
        String id;
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
        this.id = id;
        System.out.print("Enter User Password: ");
        password = input.next();
        System.out.print("Enter User Type: ");
        type = input.next();
        System.out.print("Enter User First Name: ");
        firstName = input.next();
        System.out.print("Enter User Last Name: ");
        lastName = input.next();
        System.out.print("Enter User Address: ");
        address = input.next();
        System.out.print("Enter User CellPhone: ");
        cellPhone = input.next();
        System.out.print("Enter User Email: ");
        email = input.next();
        System.out.println("User Added to System");
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
    public void searchBook()
    {
        Main.printTitle("List of Books");

    }

}
