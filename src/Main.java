import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static HashMap<String, Reader> Readers = new HashMap<>();
    public static HashMap<String, Book> Books = new HashMap<>();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("  -: Library_Management_System :-");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Page currentPage = Page.MAIN_MENU;
//        OutputOperations.slowClearMsg(id);
//        while (currentPage != Page.EXIT)
//        {
//            switch (currentPage)
//            {
//                case MAIN_MENU -> {
//                    currentPage = mainMenuPage();
//                    System.out.println("======================================================================");
//                }
//                case LOGIN_PAGE -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case SIGN_UP -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
//                case LIBRARIAN_MENU -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
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
//                case READER_MENU -> {
//                    currentPage = ;
//                    System.out.println("======================================================================");
//                }
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
//            }
//        }

    }

//    public static Page mainMenuPage() throws InterruptedException {
//        OutputOperations.display(TypePrint.TITLE,"Main Menu");
//        System.out.println();
//    }
//    public static Page loginPage() throws InterruptedException {
//        OutputOperations.display(TypePrint.TITLE,"Log-in Menu");
//
//    }
//    public static Page signUpPage() throws InterruptedException {
//        OutputOperations.display(TypePrint.TITLE,"Main Menu");
//        return Librarian.addReaderPage();
//    }
}
