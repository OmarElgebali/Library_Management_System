import java.util.Scanner;

public class Librarian {
    private static final String id = "a";
    private static final String password = "a";

    public static Page addReaderPage(){
        OutputOperations.display(TypePrint.TITLE,"New Reader");
        Scanner input = new Scanner(System.in);
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

    public static String getId() {
        return id;
    }

    public static String getPassword() {
        return password;
    }
}
