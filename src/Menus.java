import java.util.Scanner;

public class Menus {
    public static Scanner input = new Scanner(System.in);
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
        System.out.println("<@> Your Data: " + DataSets.currentReader);
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
        if (DataSets.currentReader.gotBlocked) {
            OutputOperations.display(TypePrint.INVALID, "User Got Blocked");
            System.out.println("<!> All Actions is Blocked except Log-Out .. Contact the Librarian");
        }
        OutputOperations.displayMenuOptions(readerMenuOptionsLabel);
        Page returnedDecide = OutputOperations.decideBetweenOptions(readerMenuOptionsPages);
        if (DataSets.currentReader.gotBlocked) {
            while (returnedDecide != Page.MAIN_MENU) {
                OutputOperations.display(TypePrint.INVALID, "This Actions is Blocked");
                returnedDecide = OutputOperations.decideBetweenOptions(readerMenuOptionsPages);
            }
            DataSets.currentReader = null;
            return Page.MAIN_MENU;
        }
        if (returnedDecide == Page.MAIN_MENU)
            DataSets.currentReader = null;
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
        else if(DataSets.Readers.get(logged_id) != null && logged_password.equals(DataSets.Readers.get(logged_id).getPassword())){
            DataSets.currentReader = DataSets.Readers.get(logged_id);
            return Page.READER_MENU;
        }
        else{
            OutputOperations.display(TypePrint.INVALID,"Invalid Id and Password, try again");
            return loginPage();
        }

    }
}
