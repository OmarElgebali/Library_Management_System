import java.util.*;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Reader currentReader = null;
    public static HashMap<String, Reader> Readers = new HashMap<>();
    public static HashMap<String, Book> Books = new HashMap<>();
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
        Reader reader1 = new Reader("1","1","Male","Ali","Ahmed","123st.","01010922507","test@gmail.com");
        Readers.put(reader1.getId(),reader1);
        Page currentPage = Page.MAIN_MENU;
        while (currentPage != Page.EXIT)
        {
            switch (currentPage)
            {
                // System Pages
                case MAIN_MENU -> {
                    currentPage = mainMenuPage();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case LOGIN_PAGE -> {
                    OutputOperations.display(TypePrint.TITLE,"Log-in Menu");
                    currentPage = loginPage();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                // Librarian Pages
                case LIBRARIAN_MENU -> {
                    currentPage = librarianPage();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case LIBRARIAN_ADD_READER -> {
                    currentPage = Librarian.addReaderPage();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
//                case LIBRARIAN_BLOCK_READER -> {
//                    currentPage = ;
//                    System.out.println(new String(new char[150]).replace('\0', '='));
//                }
                case LIBRARIAN_ADD_BOOK -> {
                    currentPage = Librarian.addBookPage();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case LIBRARIAN_VIEW_ALL_BOOKS -> {
                    currentPage = Librarian.viewAllBooks();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case LIBRARIAN_VIEW_ALL_READERS -> {
                    currentPage = Librarian.viewAllReaders();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
//                case LIBRARIAN_BOOK_ORDER_LIST -> {
//                    currentPage = ;
//                    System.out.println(new String(new char[150]).replace('\0', '='));
//                }
                case LIBRARIAN_SEARCH_BOOK -> {
                    OutputOperations.display(TypePrint.TITLE,"Search for a book");
                    currentPage = Librarian.searchBook();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case LIBRARIAN_SEARCH_READER -> {
                    OutputOperations.display(TypePrint.TITLE,"Search for a reader");
                    currentPage = Librarian.searchReader();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                // Reader Pages
                case READER_MENU -> {
                    currentPage = readerPage();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case READER_SEARCH_BOOK -> {
                    OutputOperations.display(TypePrint.TITLE,"Search for a book");
                    currentPage = currentReader.searchBook();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case READER_VIEW_ALL_BOOKS -> {
                    currentPage = currentReader.viewAllBooks();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case READER_VIEW_RENT_BOOKS -> {
                    currentPage = currentReader.viewRentedBooks();
                    System.out.println(new String(new char[150]).replace('\0', '='));
                }
                case READER_VIEW_SELF_BOOKS -> {
                    currentPage = currentReader.viewSelfBooks();
                    System.out.println(new String(new char[150]).replace('\0', '='));
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
                "Add Reader", "Remove Reader","Add Book", "Remove Book",
                "Search a Book", "View Book-Order List", "View Late Readers",
                "Log-Out"
        };
        Page[] librarianMenuOptionsPages= {
                Page.LIBRARIAN_ADD_READER,Page.LIBRARIAN_REMOVE_READER,Page.LIBRARIAN_ADD_BOOK,Page.LIBRARIAN_REMOVE_BOOK,
                Page.LIBRARIAN_SEARCH_BOOK,Page.LIBRARIAN_BOOK_ORDER_LIST, Page.LIBRARIAN_BLOCK_READER,
                Page.MAIN_MENU
        };
        OutputOperations.displayMenuOptions(librarianMenuOptionsLabel);
        return OutputOperations.decideBetweenOptions(librarianMenuOptionsPages);

    }
    public static Page readerPage(){
        OutputOperations.display(TypePrint.TITLE,"Reader Menu");
        System.out.println(currentReader);
        String[] readerMenuOptionsLabel= {
                "Search a Book","View All Books",
                "View Owned Books","View Rented Books",
                "Log-Out"
        };
        Page[] readerMenuOptionsPages= {
                Page.READER_SEARCH_BOOK,Page.READER_VIEW_ALL_BOOKS,
                Page.READER_VIEW_SELF_BOOKS,Page.READER_VIEW_RENT_BOOKS,
                Page.MAIN_MENU
        };
        OutputOperations.displayMenuOptions(readerMenuOptionsLabel);
        Page returnedDecide = OutputOperations.decideBetweenOptions(readerMenuOptionsPages);
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
            OutputOperations.display(TypePrint.INVALID,"Invalid Id and Password, try again ");
            return loginPage();
        }

    }
}

