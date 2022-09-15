public class Librarian {
    private static final String id = "a";
    private static final String password = "a";

    public static Page addReaderPage() throws InterruptedException {
        OutputOperations.display(TypePrint.TITLE,"New Reader");
        Reader newReader = new Reader();
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
