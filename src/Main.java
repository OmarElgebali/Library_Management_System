import java.util.*;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Reader currentReader = null;
    public static HashMap<String, Reader> Readers = new HashMap<>();
    public static HashMap<String, Book> Books = new HashMap<>();
    public static ArrayList<Book_Order> Book_Order_List = new ArrayList<>();
    public static ArrayList<Book_Rent> Book_Rent_List = new ArrayList<>();
    public static void main(String[] args){
        System.out.println("  -: Library_Management_System :-");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Book b1 = new Book("b1","aut1",427,550);
        Book b2 = new Book("z2","aut2",280,320);
        Book b3 = new Book("y3","aut3",212,169);
        Book b4 = new Book("t4","aut4",654,621);
        Book b5 = new Book("i5","aut5",1485,800);
        Books.put(b2.getName(),b2);
        Books.put(b3.getName(),b3);
        Books.put(b1.getName(),b1);
        Books.put(b4.getName(),b4);
        Books.put(b5.getName(),b5);
        Reader reader1 = new Reader("1","1","Male","Ali","Ahmed","123st.","01010922507","alitest@gmail.com");
        Reader reader2 = new Reader("2","2","Female","Sally","aaaaa","546st.","01280127878","sallyTest@gmail.com");
        Readers.put(reader1.getId(),reader1);
        Readers.put(reader2.getId(),reader2);
        Page currentPage = Page.MAIN_MENU;
        while (currentPage != Page.EXIT)
        {
            switch (currentPage)
            {
                // System Pages
                case MAIN_MENU -> {
                    currentPage = mainMenuPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LOGIN_PAGE -> {
                    OutputOperations.display(TypePrint.TITLE,"Log-in Menu");
                    currentPage = loginPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                // Librarian Pages
                case LIBRARIAN_MENU -> {
                    currentPage = librarianPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LIBRARIAN_ADD_READER -> {
                    currentPage = Librarian.addReaderPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LIBRARIAN_VIEW_ALL_READERS -> {
                    currentPage = Librarian.viewAllReaders();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LIBRARIAN_SEARCH_READER -> {
                    OutputOperations.display(TypePrint.TITLE,"Search For A Reader");
                    currentPage = Librarian.searchReader();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LIBRARIAN_ADD_BOOK -> {
                    currentPage = Librarian.addBookPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LIBRARIAN_VIEW_ALL_BOOKS -> {
                    currentPage = Librarian.viewAllBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LIBRARIAN_BOOK_ORDER_LIST -> {
                    currentPage = Librarian.viewBookOrderList();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LIBRARIAN_BOOK_RENT_LIST -> {
                    currentPage = Librarian.viewBookRentList();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LIBRARIAN_SEARCH_BOOK -> {
                    OutputOperations.display(TypePrint.TITLE,"Search For A Book");
                    currentPage = Librarian.searchBook();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                // Reader Pages
                case READER_MENU -> {
                    currentPage = readerPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_SEARCH_BOOK -> {
                    OutputOperations.display(TypePrint.TITLE,"Search For A Book");
                    currentPage = currentReader.searchBook();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_VIEW_ALL_BOOKS -> {
                    currentPage = currentReader.viewAllBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_VIEW_RENT_BOOKS -> {
                    currentPage = currentReader.viewRentedBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_VIEW_ORDER_BOOKS -> {
                    currentPage = currentReader.viewOrderBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_VIEW_OWNED_BOOKS -> {
                    currentPage = currentReader.viewOwnedBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
            }
        }

    }

    public static Page mainMenuPage(){
        OutputOperations.display(TypePrint.TITLE,"Main Menu");
        String[] mainMenuOptionsLabel= {
                "Log-in", "Exit"
        };
        Page[] mainMenuOptionsPages= {
                Page.LOGIN_PAGE,Page.EXIT
        };
        OutputOperations.displayMenuOptions(mainMenuOptionsLabel);
        return OutputOperations.decideBetweenOptions(mainMenuOptionsPages);
    }
    public static Page librarianPage(){
        OutputOperations.display(TypePrint.TITLE,"Librarian Menu");
        String[] librarianMenuOptionsLabel= {
                "Add Reader", "Search a Reader","View Readers",
                "Add Book","Search a Book", "View All Books",
                "View Book-Order List", "View Rent Book List",
                "Log-Out"
        };
        Page[] librarianMenuOptionsPages= {
                Page.LIBRARIAN_ADD_READER,Page.LIBRARIAN_SEARCH_READER,Page.LIBRARIAN_VIEW_ALL_READERS,
                Page.LIBRARIAN_ADD_BOOK,Page.LIBRARIAN_SEARCH_BOOK,Page.LIBRARIAN_VIEW_ALL_BOOKS,
                Page.LIBRARIAN_BOOK_ORDER_LIST, Page.LIBRARIAN_BOOK_RENT_LIST,
                Page.MAIN_MENU
        };
        OutputOperations.displayMenuOptions(librarianMenuOptionsLabel);
        return OutputOperations.decideBetweenOptions(librarianMenuOptionsPages);

    }
    public static Page readerPage() {
        OutputOperations.display(TypePrint.TITLE, "Reader Menu");
        System.out.println("<@> Your Data: " + currentReader);
        String[] readerMenuOptionsLabel = {
                "Search a Book", "View Owned Books",
                "View Ordered Books","View Rented Books",
                "View All Books","Log-Out"
        };
        Page[] readerMenuOptionsPages = {
                Page.READER_SEARCH_BOOK, Page.READER_VIEW_OWNED_BOOKS,
                Page.READER_VIEW_ORDER_BOOKS,Page.READER_VIEW_RENT_BOOKS,
                Page.READER_VIEW_ALL_BOOKS,Page.MAIN_MENU
        };
        if (currentReader.gotBlocked) {
            OutputOperations.display(TypePrint.INVALID, "User Got Blocked");
            System.out.println("<!> All Actions is Blocked except Log-Out .. Contact the Librarian");
        }
        OutputOperations.displayMenuOptions(readerMenuOptionsLabel);
        Page returnedDecide = OutputOperations.decideBetweenOptions(readerMenuOptionsPages);
        if (currentReader.gotBlocked) {
            while (returnedDecide != Page.MAIN_MENU) {
                OutputOperations.display(TypePrint.INVALID, "This Actions is Blocked");
                returnedDecide = OutputOperations.decideBetweenOptions(readerMenuOptionsPages);
            }
            currentReader = null;
            return Page.MAIN_MENU;
        }
        if (returnedDecide == Page.MAIN_MENU)
            currentReader = null;
        return returnedDecide;
    }
    public static Page loginPage(){
        String logged_id;
        String logged_password;
        System.out.print("-> ID: ");
        logged_id = input.next();
        System.out.print("-> Password: ");
        logged_password = input.next();
        if (logged_id.equals(Librarian.getId()) && logged_password.equals(Librarian.getPassword())){
            return Page.LIBRARIAN_MENU;
        }
        else if(Readers.get(logged_id) != null && logged_password.equals(Readers.get(logged_id).getPassword())){
            currentReader = Readers.get(logged_id);
            return Page.READER_MENU;
        }
        else{
            OutputOperations.display(TypePrint.INVALID,"Invalid Id and Password, try again");
            return loginPage();
        }

    }
}

