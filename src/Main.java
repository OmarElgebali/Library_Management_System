import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Reader currentReader = null;
    public static HashMap<String, Reader> Readers = new HashMap<>();
    public static HashMap<String, Book> Books = new HashMap<>();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("  -: Library_Management_System :-");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Page currentPage = Page.MAIN_MENU;
        while (currentPage != Page.EXIT)
        {
            switch (currentPage)
            {
                case MAIN_MENU -> {
                    currentPage = mainMenuPage();
                    System.out.println("======================================================================");
                }
                case LOGIN_PAGE -> {
                    OutputOperations.display(TypePrint.TITLE,"Log-in");
                    System.out.println("======================================================================");
                    currentPage = Page.MAIN_MENU;
                }
                case SIGN_UP -> {
                    OutputOperations.display(TypePrint.TITLE,"Sign-up");
                    System.out.println("======================================================================");
                    currentPage = Page.MAIN_MENU;
                }
                case LIBRARIAN_MENU -> {
                    currentPage = librarianPage();
                    System.out.println("======================================================================");
                }
//                case LIBRARIAN_ADD_READER -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case LIBRARIAN_REMOVE_READER -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case LIBRARIAN_BLOCK_READER -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case LIBRARIAN_ADD_BOOK -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case LIBRARIAN_REMOVE_BOOK -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case LIBRARIAN_BOOK_ORDER_LIST -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case LIBRARIAN_SEARCH_BOOK -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
                case READER_MENU -> {
                    currentPage = readerPage();
                    System.out.println("======================================================================");
                }
//                case READER_ADD_BOOK -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case READER_REMOVE_BOOK -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case READER_SEARCH_BOOK -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case READER_RENT_BOOK -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
            }
        }

    }

    public static Page mainMenuPage() throws InterruptedException {
        OutputOperations.display(TypePrint.TITLE,"Main Menu");
        String[] mainMenuOptionsLabel= {
                "Log-in", "Sign-Up", "Exit", "LIB"
        };
        Page[] mainMenuOptionsPages= {
                Page.LOGIN_PAGE,Page.SIGN_UP,Page.EXIT,Page.LIBRARIAN_MENU
        };
        OutputOperations.displayMenuOptions(mainMenuOptionsLabel);
        return OutputOperations.decideBetweenOptions(mainMenuOptionsPages);
    }
    public static Page librarianPage() throws InterruptedException {
        OutputOperations.display(TypePrint.TITLE,"Librarian Menu");
        String[] mainMenuOptionsLabel= {
                "Add Reader", "Remove Reader","Add Book", "Remove Book",
                "Search a Book", "View Book-Order List", "View Late Readers",
                "Log-Out"
        };
        Page[] mainMenuOptionsPages= {
                Page.LIBRARIAN_ADD_READER,Page.LIBRARIAN_REMOVE_READER,Page.LIBRARIAN_ADD_BOOK,Page.LIBRARIAN_REMOVE_BOOK,
                Page.LIBRARIAN_SEARCH_BOOK,Page.LIBRARIAN_BOOK_ORDER_LIST, Page.LIBRARIAN_BLOCK_READER,
                Page.LOGIN_PAGE
        };
        OutputOperations.displayMenuOptions(mainMenuOptionsLabel);
        return OutputOperations.decideBetweenOptions(mainMenuOptionsPages);

    }
    public static Page readerPage() throws InterruptedException {
        OutputOperations.display(TypePrint.TITLE,"Reader Menu");
        String[] mainMenuOptionsLabel= {
                "Add Book", "Remove Book",
                "Search a Book", "Request to Order a Book", "Rent a Book",
                "Log-Out"
        };
        Page[] mainMenuOptionsPages= {
                Page.READER_ADD_BOOK,Page.READER_REMOVE_BOOK,
                Page.READER_SEARCH_BOOK,Page.READER_RENT_BOOK,Page.READER_REQUEST_ORDER_BOOK,
                Page.LOGIN_PAGE
        };
        OutputOperations.displayMenuOptions(mainMenuOptionsLabel);
        Page returnedDecide = OutputOperations.decideBetweenOptions(mainMenuOptionsPages);
        if (returnedDecide == Page.LOGIN_PAGE)
            currentReader = null;
        return returnedDecide;
    }

    public static Page loginPage() throws InterruptedException {
        OutputOperations.display(TypePrint.TITLE,"Log-in Menu");
        String logged_id;
        String logged_password;
        boolean ifFound;
        do {
            ifFound = false;
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
                ifFound = true;
                OutputOperations.display(TypePrint.INVALID,"Invalid Id and Password, try again ");
            }
        }while (ifFound);
        return Librarian.addReaderPage();
    }
    public static Page signUpPage() throws InterruptedException {
        OutputOperations.display(TypePrint.TITLE,"Sign-Up Menu");
        return Librarian.addReaderPage();
    }
}

