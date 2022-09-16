import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Librarian {
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
        if (currentDecision == Page.LIBRARIAN_REMOVE_BOOK){ //Remove
            OutputOperations.display(TypePrint.LOADING, "Removing Book");
            Main.Books.remove(bookName);
            for (Map.Entry<String, Reader> set : Main.Readers.entrySet()) {
                if (!Main.Readers.get(set.getKey()).rentBooks.isEmpty() && Main.Readers.get(set.getKey()).rentBooks.get(bookName) != null){
                    Main.Readers.get(set.getKey()).rentBooks.remove(bookName);
                }
                if (!Main.Readers.get(set.getKey()).selfBooks.isEmpty() && Main.Readers.get(set.getKey()).selfBooks.get(bookName) != null){
                    Main.Readers.get(set.getKey()).selfBooks.remove(bookName);
                }
            }
            OutputOperations.display(TypePrint.FINISH, "Book removed from system");
        }
        return Page.LIBRARIAN_MENU; //Cancel
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
            if (currentDecision == Page.LIBRARIAN_REMOVE_BOOK) {  //Remove Book
                OutputOperations.display(TypePrint.LOADING, "Removing Book");
                Main.Books.remove(decidedBookName);
                for (Map.Entry<String, Reader> set : Main.Readers.entrySet()) {
                    if (!Main.Readers.get(set.getKey()).rentBooks.isEmpty() && Main.Readers.get(set.getKey()).rentBooks.get(decidedBookName) != null){
                        Main.Readers.get(set.getKey()).rentBooks.remove(decidedBookName);
                    }
                    if (!Main.Readers.get(set.getKey()).selfBooks.isEmpty() && Main.Readers.get(set.getKey()).selfBooks.get(decidedBookName) != null){
                        Main.Readers.get(set.getKey()).selfBooks.remove(decidedBookName);
                    }
                }
                OutputOperations.display(TypePrint.FINISH, "Book removed from system");
            }
        }while (currentDecision == Page.LIBRARIAN_VIEW_ALL_BOOKS);
        return Page.LIBRARIAN_MENU; //Cancel
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
        if (currentDecision == Page.LIBRARIAN_REMOVE_READER){ //Remove
            OutputOperations.display(TypePrint.LOADING, "Removing Reader");
            Main.Readers.get(readerID).selfBooks.clear();
            Main.Readers.get(readerID).rentBooks.clear();
            Main.Readers.remove(readerID);
            OutputOperations.display(TypePrint.FINISH, "Reader removed from system");
        }
        return Page.LIBRARIAN_MENU; //Cancel
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
            OutputOperations.displayMenuOptions(new String[]{"Remove Reader","Choose Again","Return to Librarian Menu"});
            currentDecision = OutputOperations.decideBetweenOptions(new Page[]{
                    Page.LIBRARIAN_REMOVE_READER,
                    Page.LIBRARIAN_VIEW_ALL_READERS,
                    Page.LIBRARIAN_MENU});
            if (currentDecision == Page.LIBRARIAN_REMOVE_BOOK) {  //Remove Reader
                OutputOperations.display(TypePrint.LOADING, "Removing Reader");
                Main.Readers.get(decidedReaderID).selfBooks.clear();
                Main.Readers.get(decidedReaderID).rentBooks.clear();
                Main.Readers.remove(decidedReaderID);
                OutputOperations.display(TypePrint.FINISH, "Reader removed from system");
            }
        }while (currentDecision == Page.LIBRARIAN_VIEW_ALL_READERS);
        return Page.LIBRARIAN_MENU; //Cancel
    }

    public static String getId() {
        return id;
    }
    public static String getPassword() {
        return password;
    }
}
