import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Librarian {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
    private static final String id = "a";
    private static final String password = "a";
    public static Scanner input = new Scanner(System.in);

    public static Page addReaderPage(){
        OutputOperations.display(TypePrint.TITLE,"New Reader");
        boolean ifFound;
        String reader_temp_id;
        do {
            ifFound = false;
            System.out.print("Enter Reader ID: ");
            reader_temp_id = input.next();
            if (Main.Readers.get(reader_temp_id) != null){
                ifFound = true;
                OutputOperations.display(TypePrint.LOADING, "Checking if reader exists");
                OutputOperations.display(TypePrint.INVALID, "Reader found, try again");
            }
        }while (ifFound);
        System.out.print("Enter Reader Password: ");
        String reader_temp_password = input.next();
        Reader newReader = new Reader(reader_temp_id,reader_temp_password);
        OutputOperations.display(TypePrint.LOADING, "Adding Reader");
        Main.Readers.put(newReader.getId(), newReader);
        OutputOperations.display(TypePrint.FINISH, "Reader added to system");
        return Page.LIBRARIAN_MENU;
    }

    public static Page addBookPage(){
        OutputOperations.display(TypePrint.TITLE,"New Book");
        Book newBook = new Book();
        OutputOperations.display(TypePrint.LOADING, "Adding Book");
        Main.Books.put(newBook.getName(), newBook);
        OutputOperations.display(TypePrint.FINISH, "Book added to system");
        return Page.LIBRARIAN_MENU;
    }

    public static Page searchBook(){
        System.out.println("-> Book Name");
        String bookName = input.next();
        OutputOperations.display(TypePrint.LOADING,"Searching for Book",2);
        if (Main.Books.get(bookName) == null){
            return invalidBookAfterSearch("Book not found", "Search Again", Page.LIBRARIAN_SEARCH_BOOK);
        }
        OutputOperations.display(TypePrint.FINISH,"Book Found");
        OutputOperations.displayMenuOptions(new String[]{"Remove","Return to Librarian Menu"});
        Page currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                Page.LIBRARIAN_REMOVE_BOOK,
                Page.LIBRARIAN_MENU});
        if (currentDecision == Page.LIBRARIAN_REMOVE_BOOK) //Remove
            removeBook(bookName);
        return Page.LIBRARIAN_MENU; //Cancel
    }
    public static Page viewAllBooks(){
        OutputOperations.display(TypePrint.TITLE,"List of Books");
        OutputOperations.display(TypePrint.LOADING,"Loading Books",2);
        if (Main.Books.isEmpty())
        {
            OutputOperations.display(TypePrint.INVALID,"Library is empty now, wanna add a book?");
            OutputOperations.displayMenuOptions(new String[]{"YES","NO"});
            int currentDecision = OutputOperations.decideBetweenOptions(2);
            if(currentDecision == 1)
                return Page.LIBRARIAN_ADD_BOOK;
            return Page.LIBRARIAN_MENU; //Cancel
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
            System.out.println("<!> Enter -1 to Return to Librarian Menu");
            decideNum = OutputOperations.decideBetweenOptions(bookList.size());
            if (decideNum == -1)
                return Page.LIBRARIAN_MENU;
            String decidedBookName = bookList.get(decideNum-1);
            OutputOperations.displayMenuOptions(new String[]{"Remove Book","Choose Again","Return to Librarian Menu"});
            currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                    Page.LIBRARIAN_REMOVE_BOOK,
                    Page.LIBRARIAN_VIEW_ALL_BOOKS,
                    Page.LIBRARIAN_MENU});
            if (currentDecision == Page.LIBRARIAN_REMOVE_BOOK)  //Remove Book
                removeBook(decidedBookName);
        }while (currentDecision == Page.LIBRARIAN_VIEW_ALL_BOOKS);
        return Page.LIBRARIAN_MENU; //Cancel
    }

    public static void removeBook(String bookName){
        OutputOperations.display(TypePrint.LOADING, "Removing Book");
        for (Map.Entry<String, Reader> set : Main.Readers.entrySet()) {
            if (!Main.Readers.get(set.getKey()).rentBooks.isEmpty() && Main.Readers.get(set.getKey()).rentBooks.get(bookName) != null){
                Main.Readers.get(set.getKey()).rentBooks.remove(bookName);
            }
            if (!Main.Readers.get(set.getKey()).orderBook.isEmpty() && Main.Readers.get(set.getKey()).orderBook.get(bookName) != null){
                Main.Readers.get(set.getKey()).orderBook.remove(bookName);
            }
        }
        for (int i = 0; i < Main.Book_Rent_List.size(); i++) {
            if (Main.Book_Rent_List.get(i).rentedBookName.equals(bookName)){
                Main.Book_Rent_List.remove(i);
                i--;
            }
        }
        for (int i = 0; i < Main.Book_Order_List.size(); i++) {
            if (Main.Book_Order_List.get(i).orderedBookName.equals(bookName)){
                Main.Book_Order_List.remove(i);
                i--;
            }
        }
        Main.Books.remove(bookName);
        OutputOperations.display(TypePrint.FINISH, "Book removed from system");
    }
    public static Page searchReader(){
        System.out.println("-> Reader ID");
        String readerID = input.next();
        OutputOperations.display(TypePrint.LOADING,"Searching for Reader",2);
        if (Main.Readers.get(readerID) == null){
            return invalidBookAfterSearch("Reader not found", "Search Again", Page.LIBRARIAN_SEARCH_READER);
        }
        OutputOperations.display(TypePrint.FINISH,"Reader Found");
        OutputOperations.displayMenuOptions(new String[]{"Remove Reader","Return to Librarian Menu"});
        Page currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                Page.LIBRARIAN_REMOVE_READER,
                Page.LIBRARIAN_MENU});
        if (currentDecision == Page.LIBRARIAN_REMOVE_READER) //Remove
            removeReader(readerID);
        return Page.LIBRARIAN_MENU; //Cancel
    }
    public static void removeReader(String readerID){
        OutputOperations.display(TypePrint.LOADING, "Removing Reader");
        for (int i = 0; i < Main.Book_Rent_List.size(); i++) {
            if (Main.Book_Rent_List.get(i).rentReaderID.equals(readerID)){
                Main.Book_Rent_List.remove(i);
                i--;
            }
        }
        for (int i = 0; i < Main.Book_Order_List.size(); i++) {
            if (Main.Book_Order_List.get(i).orderReaderID.equals(readerID)){
                Main.Book_Order_List.remove(i);
                i--;
            }
        }
        Main.Readers.get(readerID).orderBook.clear();
        Main.Readers.get(readerID).rentBooks.clear();
        Main.Readers.remove(readerID);
        OutputOperations.display(TypePrint.FINISH, "Reader removed from system");
    }
    public static Page invalidBookAfterSearch(String msg, String tryAgainMsg, Page tryAgainPage){
        OutputOperations.display(TypePrint.INVALID,msg);
        OutputOperations.displayMenuOptions(new String[]{tryAgainMsg,"Return to Librarian Menu"});
        Page currentDecision =  OutputOperations.decideBetweenOptions(new Page[]{tryAgainPage,Page.LIBRARIAN_MENU});
        if (currentDecision == tryAgainPage)
        {
            if (tryAgainPage == Page.LIBRARIAN_SEARCH_BOOK)
                return searchBook();
            else if (tryAgainPage == Page.LIBRARIAN_SEARCH_READER)
                return searchReader();
            return Page.LIBRARIAN_VIEW_ALL_BOOKS;
        }
        return Page.LIBRARIAN_MENU;
    }

    public static Page viewAllReaders(){
        OutputOperations.display(TypePrint.TITLE,"List of Readers");
        OutputOperations.display(TypePrint.LOADING,"Loading Readers",2);
        if (Main.Readers.isEmpty())
        {
            OutputOperations.display(TypePrint.INVALID,"No Readers found, wanna add a reader?");
            OutputOperations.displayMenuOptions(new String[]{"YES","NO"});
            int currentDecision = OutputOperations.decideBetweenOptions(2);
            if(currentDecision == 1)
                return Page.LIBRARIAN_ADD_READER;
            return Page.LIBRARIAN_MENU; //Cancel
        }
        TreeMap<String, Reader> sortedMap = new TreeMap<>(Main.Readers);
        ArrayList<String> readerList = new ArrayList<>();
        System.out.println(new String(new char[110]).replace('\0', '*'));
        for (Map.Entry<String, Reader> set : sortedMap.entrySet()) {
            readerList.add(set.getValue().getId());
            System.out.println("Reader #" + readerList.size() + " " + set.getValue());
        }
        System.out.println(new String(new char[110]).replace('\0', '*'));
        Page currentDecision;
        int decideNum;
        do {
            System.out.println("<!> Enter #ReaderNumber to choose an action on it");
            System.out.println("<!> Enter -1 to Return to Librarian Menu");
            decideNum = OutputOperations.decideBetweenOptions(readerList.size());
            if (decideNum == -1)
                return Page.LIBRARIAN_MENU;
            String decidedReaderID = readerList.get(decideNum-1);
            String[] viewAllReadersLabels = new String[]{"Remove Reader","Choose Again","Return to Librarian Menu"};
            Page[] viewAllReadersActions = new Page[]{
                    Page.LIBRARIAN_REMOVE_READER,
                    Page.LIBRARIAN_VIEW_ALL_READERS,
                    Page.LIBRARIAN_MENU};
            if (Main.Readers.get(decidedReaderID).gotBlocked){
                viewAllReadersLabels = new String[]{"Remove Reader","Unblock Reader","Choose Again","Return to Librarian Menu"};
                viewAllReadersActions = new Page[]{
                        Page.LIBRARIAN_REMOVE_READER,
                        Page.LIBRARIAN_UNBLOCK_READER,
                        Page.LIBRARIAN_VIEW_ALL_READERS,
                        Page.LIBRARIAN_MENU};
            }
            OutputOperations.displayMenuOptions(viewAllReadersLabels);
            currentDecision = OutputOperations.decideBetweenOptions(viewAllReadersActions);
            if (currentDecision == Page.LIBRARIAN_REMOVE_READER)  //Remove Reader
                removeReader(decidedReaderID);
            else if (currentDecision == Page.LIBRARIAN_UNBLOCK_READER)  //Remove Reader
                Main.Readers.get(decidedReaderID).unBlockReader();
        }while (currentDecision == Page.LIBRARIAN_VIEW_ALL_READERS);
        return Page.LIBRARIAN_MENU; //Cancel
    }
    public static Page viewBookOrderList(){
        OutputOperations.display(TypePrint.TITLE,"List of Book-Orders");
        OutputOperations.display(TypePrint.LOADING,"Loading Book-Orders",2);
        if (Main.Book_Order_List.isEmpty())
        {
            OutputOperations.display(TypePrint.INVALID,"No Book-Orders found");
            return Page.LIBRARIAN_MENU;
        }
        System.out.println(new String(new char[120]).replace('\0', '*'));
        for (int i = 0; i < Main.Book_Order_List.size()-1; i++) {
            System.out.println("| Order #" + (i+1) + " :");
            System.out.println("| <@> Reader: " + Main.Book_Order_List.get(i).orderReader);
            System.out.println("| <@> Book: " + Main.Book_Order_List.get(i).orderedBook);
            System.out.println(new String(new char[80]).replace('\0', '~'));
        }
        if (!Main.Book_Order_List.isEmpty()){
            System.out.println("| Order #" + (Main.Book_Order_List.size()) + " :");
            System.out.println("| <@> Reader: " + Main.Book_Order_List.get(Main.Book_Order_List.size()-1).orderReader);
            System.out.println("| <@> Book: " + Main.Book_Order_List.get(Main.Book_Order_List.size()-1).orderedBook);
        }
        System.out.println(new String(new char[120]).replace('\0', '*'));
        Page currentDecision;
        int decideNum;
        do {
            System.out.println("<!> Enter #OrderNumber to choose an action on it");
            System.out.println("<!> Enter -1 to Return to Librarian Menu");
            decideNum = OutputOperations.decideBetweenOptions(Main.Book_Order_List.size());
            if (decideNum == -1)
                return Page.LIBRARIAN_MENU;
            String decidedReaderID = Main.Book_Order_List.get(decideNum-1).orderReaderID;
            String decidedBookName = Main.Book_Order_List.get(decideNum-1).orderedBookName;
            OutputOperations.displayMenuOptions(new String[]{"Accept Order","Decline Order","Choose Again","Return to Librarian Menu"});
            currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                    Page.LIBRARIAN_ACCEPT_ORDER,
                    Page.LIBRARIAN_DECLINE_ORDER,
                    Page.LIBRARIAN_BOOK_ORDER_LIST,
                    Page.LIBRARIAN_MENU});
            if (currentDecision == Page.LIBRARIAN_ACCEPT_ORDER){
                OutputOperations.display(TypePrint.LOADING, "Accepting Order for User:'"+decidedReaderID+"'");
                Main.Readers.get(decidedReaderID).orderBook.remove(decidedBookName);
                Main.Readers.get(decidedReaderID).ownedBook.put(decidedBookName,Main.Books.get(decidedBookName));
                Main.Book_Order_List.remove(decideNum-1);
                OutputOperations.display(TypePrint.FINISH, "Order Accepted for User:'"+decidedReaderID+"'");
            }
            else if (currentDecision == Page.LIBRARIAN_DECLINE_ORDER){
                OutputOperations.display(TypePrint.LOADING, "Declining Order for User:'"+decidedReaderID+"'");
                Main.Readers.get(decidedReaderID).orderBook.remove(decidedBookName);
                Main.Book_Order_List.remove(decideNum-1);
                OutputOperations.display(TypePrint.FINISH, "Order Declined for User:'"+decidedReaderID+"'");
            }
        }while (currentDecision == Page.LIBRARIAN_BOOK_ORDER_LIST);
        return Page.LIBRARIAN_MENU;
    }

    public static Page viewBookRentList(){
        OutputOperations.display(TypePrint.TITLE,"List of Book-Rents");
        OutputOperations.display(TypePrint.LOADING,"Loading Book-Rents",2);
        if (Main.Book_Rent_List.isEmpty())
        {
            OutputOperations.display(TypePrint.INVALID,"No Book-Rents found");
            return Page.LIBRARIAN_MENU;
        }
        ArrayList<Book_Rent> safeRents = new ArrayList<>();
        ArrayList<Book_Rent> blockRents = new ArrayList<>();
        for (int i = 0; i < Main.Book_Rent_List.size()-1; i++) {
            if (Main.Book_Rent_List.get(i).dateToReturn.isBefore(LocalDate.now())){
                safeRents.add(Main.Book_Rent_List.get(i));
            }
            else {
                blockRents.add(Main.Book_Rent_List.get(i));
            }
        }
        System.out.println("<#> Safe Rents: ");
        System.out.println(new String(new char[120]).replace('\0', '*'));
        for (int i = 0; i < safeRents.size()-1; i++) {
            System.out.println("| Rent #" + (i+1) +" :");
            System.out.println("| <@> Date(yyyy-m-d): " +  safeRents.get(i).dateToReturn);
            System.out.println("| <@> Reader: " + safeRents.get(i).rentReader);
            System.out.println("| <@> Book: " + safeRents.get(i).rentedBook);
            System.out.println(new String(new char[80]).replace('\0', '~'));
        }
        if (!safeRents.isEmpty()){
            System.out.println("| Rent #" + (safeRents.size()) + " :");
            System.out.println("| <@> Date(yyyy-m-d): " +  safeRents.get(safeRents.size()-1).dateToReturn);
            System.out.println("| <@> Reader: " + safeRents.get(safeRents.size()-1).rentReader);
            System.out.println("| <@> Book: " + safeRents.get(safeRents.size()-1).rentedBook);
        }
        System.out.println(new String(new char[120]).replace('\0', '*'));
        System.out.println("<#> Non-Safe Rents: ");
        System.out.println(new String(new char[120]).replace('\0', '*'));
        for (int i = 0; i < blockRents.size()-1; i++) {
            System.out.println("| Rent #" + (i+1) +" :");
            System.out.println("| <@> Date(yyyy-m-d): " +  blockRents.get(i).dateToReturn);
            System.out.println("| <@> Reader: " + blockRents.get(i).rentReader);
            System.out.println("| <@> Book: " + blockRents.get(i).rentedBook);
            System.out.println(new String(new char[80]).replace('\0', '~'));
        }
        if (!blockRents.isEmpty()){
            System.out.println("| Rent #" + (blockRents.size()) + " :");
            System.out.println("| <@> Date(yyyy-m-d): " +  blockRents.get(blockRents.size()-1).dateToReturn);
            System.out.println("| <@> Reader: " + blockRents.get(blockRents.size()-1).rentReader);
            System.out.println("| <@> Book: " + blockRents.get(blockRents.size()-1).rentedBook);
        }
        System.out.println(new String(new char[120]).replace('\0', '*'));
        Page currentDecision;
        int decideNum;
        do {
            System.out.println("<!> Enter Non-Safe #RentNumber to choose an action on it");
            System.out.println("<!> Enter -1 to Return to Librarian Menu");
            decideNum = OutputOperations.decideBetweenOptions(blockRents.size());
            if (decideNum == -1)
                return Page.LIBRARIAN_MENU;
            String decidedReaderID = blockRents.get(decideNum-1).rentReaderID;
            String decidedBookName = blockRents.get(decideNum-1).rentedBookName;
            OutputOperations.displayMenuOptions(new String[]{"Block Reader and Cancel Rent","Choose Again","Return to Librarian Menu"});
            currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                    Page.LIBRARIAN_BLOCK_READER,
                    Page.LIBRARIAN_BOOK_RENT_LIST,
                    Page.LIBRARIAN_MENU});
            if (currentDecision == Page.LIBRARIAN_BLOCK_READER){
                OutputOperations.display(TypePrint.LOADING, "Blocking User:'"+decidedReaderID+"'");
                Main.Readers.get(decidedReaderID).blockReader();
                Main.Readers.get(decidedReaderID).rentBooks.remove(decidedBookName);
                for (int i = 0; i < Main.Book_Rent_List.size(); i++) {
                    if (Main.Book_Rent_List.get(i).rentReaderID.equals(decidedReaderID) && Main.Book_Rent_List.get(i).rentedBookName.equals(decidedBookName)){
                        Main.Book_Rent_List.remove(i);
                        break;
                    }
                }
                OutputOperations.display(TypePrint.FINISH, "User:'"+decidedReaderID+"' Blocked");
            }
        }while (currentDecision == Page.LIBRARIAN_BOOK_RENT_LIST);
        return Page.LIBRARIAN_MENU;
    }

    public static String getId() {
        return id;
    }
    public static String getPassword() {
        return password;
    }
}
