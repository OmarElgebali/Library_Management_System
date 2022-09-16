import java.util.ArrayList;
import java.util.HashMap;

public class DataSets {
    public static Reader currentReader = null;
    public static HashMap<String, Reader> Readers = new HashMap<>();
    public static HashMap<String, Book> Books = new HashMap<>();
    public static ArrayList<Book_Order> Book_Order_List = new ArrayList<>();
    public static ArrayList<Book_Rent> Book_Rent_List = new ArrayList<>();
}
