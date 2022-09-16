import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Reader extends Person{
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
    private final String  type, firstName, lastName, address, cellPhone, email;
    public HashMap<String, Book> orderBook = new HashMap<>();
    public HashMap<String, Book> ownedBook = new HashMap<>();
    public HashMap<String, Book> rentBooks = new HashMap<>();
    static Scanner input = new Scanner(System.in);
    public boolean gotBlocked;
    public void blockReader(){
        this.gotBlocked = true;
    }
    public void unBlockReader(){
        this.gotBlocked = false;
    }

    public Reader(String id, String password){
        super(id, password);
        gotBlocked = false;
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

    public Page viewAllBooks(){
        OutputOperations.display(TypePrint.TITLE,"List of Books");
        OutputOperations.display(TypePrint.LOADING,"Loading Books",2);
        if (Main.Books.isEmpty())
        {
            OutputOperations.display(TypePrint.INVALID,"Library is empty now, come again later");
            return Page.READER_MENU; //Cancel
        }
        TreeMap<String, Book> sortedMap = new TreeMap<>(Main.Books);
        ArrayList<String> bookList = new ArrayList<>();
        System.out.println(new String(new char[80]).replace('\0', '*'));
        for (Map.Entry<String, Book> set : sortedMap.entrySet()) {
            bookList.add(set.getValue().getName());
            System.out.println("Book #" + bookList.size() + " " + set.getValue());
        }
        System.out.println(new String(new char[80]).replace('\0', '*'));
        Page currentDecision;
        int decideNum;
        do {
            System.out.println("<!> Enter #BookNumber to choose an action on it");
            System.out.println("<!> Enter -1 to Return to Reader Menu");
            decideNum = OutputOperations.decideBetweenOptions(bookList.size());
            if (decideNum == -1)
                return Page.READER_MENU;
            String decidedBookName = bookList.get(decideNum-1);
            System.out.println("<@> Book's Data: " + Main.Books.get(decidedBookName));
            OutputOperations.displayMenuOptions(new String[]{"Order","Rent","Choose Again","Return to Reader Menu"});
            currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                    Page.READER_REQUEST_ORDER_BOOK,
                    Page.READER_RENT_BOOK,
                    Page.READER_VIEW_ALL_BOOKS,
                    Page.READER_MENU});
            if (currentDecision == Page.READER_REQUEST_ORDER_BOOK)  //Order
                return addBook(decidedBookName,"View and Choose Again", Page.READER_VIEW_ALL_BOOKS);   // READER_VIEW_ALL_BOOKS || READER_MENU
            else if (currentDecision == Page.READER_RENT_BOOK)      //Rent
                return rentBook(decidedBookName,"View and Choose Again", Page.READER_VIEW_ALL_BOOKS);  // READER_VIEW_ALL_BOOKS || READER_MENU
        }while (currentDecision == Page.READER_VIEW_ALL_BOOKS);
        return Page.READER_MENU; //Cancel
    }

    public Page viewRentedBooks(){
        OutputOperations.display(TypePrint.TITLE,"List of Rented Books");
        OutputOperations.display(TypePrint.LOADING,"Loading Rented Books",2);
        if (rentBooks.isEmpty())
        {
            OutputOperations.display(TypePrint.INVALID,"No Rented Books Found");
            return Page.READER_MENU; //Cancel
        }
        TreeMap<String, Book> sortedMap = new TreeMap<>(rentBooks);
        ArrayList<String> bookList = new ArrayList<>();
        System.out.println(new String(new char[80]).replace('\0', '*'));
        for (Map.Entry<String, Book> set : sortedMap.entrySet()) {
            bookList.add(set.getValue().getName());
            System.out.println("Book #" + bookList.size() + " " + set.getValue());
        }
        System.out.println(new String(new char[80]).replace('\0', '*'));
        Page currentDecision;
        int decideNum;
        do {
            System.out.println("<!> Enter #BookNumber to choose an action on it");
            System.out.println("<!> Enter -1 to Return to Reader Menu");
            decideNum = OutputOperations.decideBetweenOptions(bookList.size());
            if (decideNum == -1)
                return Page.READER_MENU;
            String decidedBookName = bookList.get(decideNum-1);
            System.out.println("<@> Book's Data: " + Main.Books.get(decidedBookName));
            OutputOperations.displayMenuOptions(new String[]{"Cancel Rent","Choose Again","Return to Reader Menu"});
            currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                    Page.READER_REMOVE_BOOK,
                    Page.READER_VIEW_RENT_BOOKS,
                    Page.READER_MENU});
            if (currentDecision == Page.READER_REMOVE_BOOK){  //Cancel Rent
                OutputOperations.display(TypePrint.LOADING,"Cancelling Book Rent",2);
                rentBooks.remove(decidedBookName);
                for (int i = 0; i < Main.Book_Rent_List.size(); i++) {
                    if (Main.Book_Rent_List.get(i).rentReaderID.equals(this.id) && Main.Book_Rent_List.get(i).rentedBookName.equals(decidedBookName)){
                        Main.Book_Rent_List.remove(i);
                        break;
                    }
                }
                OutputOperations.display(TypePrint.FINISH,"Book Rent Cancelled");
            }
        }while (currentDecision == Page.READER_VIEW_RENT_BOOKS);
        return Page.READER_MENU; //Cancel
    }

    public Page viewOwnedBooks(){
        OutputOperations.display(TypePrint.TITLE,"List of Owned Books");
        OutputOperations.display(TypePrint.LOADING,"Loading Owned Books",2);
        if (rentBooks.isEmpty())
        {
            OutputOperations.display(TypePrint.INVALID,"No Owned Books Found");
            return Page.READER_MENU; //Cancel
        }
        TreeMap<String, Book> sortedMap = new TreeMap<>(ownedBook);
        ArrayList<String> bookList = new ArrayList<>();
        System.out.println(new String(new char[80]).replace('\0', '*'));
        for (Map.Entry<String, Book> set : sortedMap.entrySet()) {
            bookList.add(set.getValue().getName());
            System.out.println("Book #" + bookList.size() + " " + set.getValue());
        }
        System.out.println(new String(new char[80]).replace('\0', '*'));
        int decideNum;
        do {
            System.out.println("<!> Enter -1 to Return to Reader Menu");
            decideNum = OutputOperations.decideBetweenOptions(bookList.size());
        }while (decideNum != -1);
        return Page.READER_MENU; //Cancel
    }

    public Page viewOrderBooks(){
        OutputOperations.display(TypePrint.TITLE,"List of Ordered Books");
        OutputOperations.display(TypePrint.LOADING,"Loading Books Ordered for Read",2);
        if (orderBook.isEmpty())
        {
            OutputOperations.display(TypePrint.INVALID,"No Ordered Books Found");
            return Page.READER_MENU; //Cancel
        }
        TreeMap<String, Book> sortedMap = new TreeMap<>(orderBook);
        ArrayList<String> bookList = new ArrayList<>();
        System.out.println(new String(new char[80]).replace('\0', '*'));
        for (Map.Entry<String, Book> set : sortedMap.entrySet()) {
            bookList.add(set.getValue().getName());
            System.out.println("Book #" + bookList.size() + " " + set.getValue());
        }
        System.out.println(new String(new char[80]).replace('\0', '*'));
        Page currentDecision;
        int decideNum;
        do {
            System.out.println("<!> Enter #BookNumber to choose an action on it");
            System.out.println("<!> Enter -1 to Return to Reader Menu");
            decideNum = OutputOperations.decideBetweenOptions(bookList.size());
            if (decideNum == -1) {
                return Page.READER_MENU;
            }
            String decidedBookName = bookList.get(decideNum-1);
            System.out.println("<@> Book's Data: " + Main.Books.get(decidedBookName));
            OutputOperations.displayMenuOptions(new String[]{"Remove Order","Choose Again","Return to Reader Menu"});
            currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                    Page.READER_REMOVE_BOOK,
                    Page.READER_VIEW_ORDER_BOOKS,
                    Page.READER_MENU});
            if (currentDecision == Page.READER_REMOVE_BOOK){  //Remove
                OutputOperations.display(TypePrint.LOADING,"Removing Book from your Ordered Reading list",2);
                orderBook.remove(decidedBookName);
                for (int i = 0; i < Main.Book_Order_List.size(); i++) {
                    if (Main.Book_Order_List.get(i).orderReaderID.equals(this.id) && Main.Book_Order_List.get(i).orderedBookName.equals(decidedBookName)){
                        Main.Book_Order_List.remove(i);
                        break;
                    }
                }
                OutputOperations.display(TypePrint.FINISH,"Book Removed from your Ordered Reading list");
            }
        }while (currentDecision == Page.READER_VIEW_ORDER_BOOKS);
        return Page.READER_MENU; //Cancel
    }

    public Page searchBook(){
        System.out.println("-> Book Name: ");
        String bookName = input.next();
        OutputOperations.display(TypePrint.LOADING,"Searching for Book",2);
        if (Main.Books.get(bookName) == null){
            return invalidBookAfterSearch("Book not found", "Search Again", Page.READER_SEARCH_BOOK);    // READER_SEARCH_BOOK || READER_MENU
        }
        OutputOperations.display(TypePrint.FINISH,"Book Found");
        System.out.println("<@> Book's Data: " + Main.Books.get(bookName));
        OutputOperations.displayMenuOptions(new String[]{"Order","Rent","Return to Reader Menu"});
        Page currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                Page.READER_REQUEST_ORDER_BOOK,
                Page.READER_RENT_BOOK,
                Page.READER_MENU});
        if (currentDecision == Page.READER_REQUEST_ORDER_BOOK) //Order
            return addBook(bookName, "Search Again", Page.READER_SEARCH_BOOK);   // READER_SEARCH_BOOK || READER_MENU
        else if (currentDecision == Page.READER_RENT_BOOK) //Rent
            return rentBook(bookName, "Search Again", Page.READER_SEARCH_BOOK);  // READER_SEARCH_BOOK || READER_MENU
        return Page.READER_MENU; //Cancel
    }

    public Page addBook(String bookNameToBeAdd, String tryAgainMsg, Page tryAgainPage){
        if (orderBook.get(bookNameToBeAdd) != null){
            return invalidBookAfterSearch("You already have that book", tryAgainMsg,tryAgainPage);    // READER_SEARCH_BOOK || READER_MENU
        }
        OutputOperations.display(TypePrint.LOADING,"Adding Book to your Reading list",2);
        orderBook.put(bookNameToBeAdd, Main.Books.get(bookNameToBeAdd));
        Main.Book_Order_List.add(new Book_Order(Main.Books.get(bookNameToBeAdd), this));
        OutputOperations.display(TypePrint.FINISH,"Book Added to your Reading list");
        return Page.READER_MENU;
    }

    public Page rentBook(String bookNameToBeRented, String tryAgainMsg, Page tryAgainPage){
        if (rentBooks.get(bookNameToBeRented) != null){
            return invalidBookAfterSearch("You already rented that book", tryAgainMsg, tryAgainPage);  // READER_SEARCH_BOOK || READER_MENU
        }
        rentBooks.put(bookNameToBeRented, Main.Books.get(bookNameToBeRented));
        System.out.println("<!> Rent Return Date Format is (yyyy-M-d)");
        Main.Book_Rent_List.add(new Book_Rent(Main.Books.get(bookNameToBeRented), this, dateInputForRent()));
        OutputOperations.display(TypePrint.LOADING,"Renting Book",2);
        OutputOperations.display(TypePrint.FINISH,"Book rented and added to your renting list");
        return Page.READER_MENU;
    }

    public static LocalDate dateInputForRent(){
        System.out.print("-> Enter Rent Return Date: ");
        String date_wait;
        LocalDate dateToReturn;
        try {
            date_wait = input.next();
            dateToReturn = LocalDate.parse(date_wait, dateFormat);
        }
        catch (DateTimeParseException e)
        {
            OutputOperations.display(TypePrint.INVALID, "Date should be in format (yyyy-M-d)");
            return dateInputForRent();
        }
        if (dateToReturn.isBefore(LocalDate.now())){
            OutputOperations.display(TypePrint.INVALID, "Date shouldn't be earlier date");
            return dateInputForRent();
        }
        return dateToReturn;
    }

    public Page invalidBookAfterSearch(String msg, String tryAgainMsg, Page tryAgainPage){
        OutputOperations.display(TypePrint.INVALID,msg);
        OutputOperations.displayMenuOptions(new String[]{tryAgainMsg,"Return to Reader Menu"});
        Page currentDecision =  OutputOperations.decideBetweenOptions(new Page[]{tryAgainPage,Page.READER_MENU});
        if (currentDecision == tryAgainPage)
        {
            if (tryAgainPage == Page.READER_SEARCH_BOOK)
                return searchBook();
            return Page.READER_VIEW_ALL_BOOKS;
        }
        return Page.READER_MENU;
    }


    // Usual Methods //
    public Reader(String id, String password, String type, String firstName, String lastName, String address, String cellPhone, String email) {
        super(id,password);
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cellPhone = cellPhone;
        this.email = email;
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
    @Override
    public String toString() {
        return "{" +
                "ID='" + id + '\'' +
                ", Name='" + firstName + ' ' + lastName + '\'' +
                ", Email='" + email + '\'' +
                ", CellPhone='" + cellPhone + '\'' +
                ", Address='" + address + '\'' +
                ", Type='" + type + '\'' +
                '}';
    }

}
