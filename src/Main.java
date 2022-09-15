import java.util.ArrayList;
import java.util.Scanner;

enum Pages{
    EXIT,MAIN_MENU,LOGIN_PAGE,SIGN_UP
}
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Librarian> Librarians = new ArrayList<>();
    public static ArrayList<User> Users = new ArrayList<>();
    public static ArrayList<Librarian> Books = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("  -: Library_Management_System :-");
        System.out.println("###################################");
        Pages currentPage = Pages.MAIN_MENU;
        while (currentPage != Pages.EXIT)
        {
            switch (currentPage)
            {
                case MAIN_MENU -> {

                }
                case LOGIN_PAGE -> {

                }
                case SIGN_UP -> {

                }
            }
        }

    }
    public static void printTitle(String title)
    {
        System.out.println("* "+title+" *");
        for (int i = 0; i < title.length(); i++) {
            System.out.print("~");
        }
    }
}
//        System.out.println("===================================");
