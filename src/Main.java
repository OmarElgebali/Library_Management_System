import java.util.*;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("  -: Library_Management_System :-");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Book b1 = new Book("b1","aut1",427,550);
        Book b2 = new Book("z2","aut2",280,320);
        Book b3 = new Book("y3","aut3",212,169);
        Book b4 = new Book("t4","aut4",654,621);
        Book b5 = new Book("i5","aut5",1485,800);
        DataSets.Books.put(b2.getName(),b2);
        DataSets.Books.put(b3.getName(),b3);
        DataSets.Books.put(b1.getName(),b1);
        DataSets.Books.put(b4.getName(),b4);
        DataSets.Books.put(b5.getName(),b5);
        Reader reader1 = new Reader("1","1","Male","Ali","Ahmed","123st.","01010922507","alitest@gmail.com");
        Reader reader2 = new Reader("2","2","Female","Sally","aaaaa","546st.","01280127878","sallyTest@gmail.com");
        DataSets.Readers.put(reader1.getId(),reader1);
        DataSets.Readers.put(reader2.getId(),reader2);
        Page currentPage = Page.MAIN_MENU;
        while (currentPage != Page.EXIT)
        {
            switch (currentPage)
            {
                // System Pages
                case MAIN_MENU -> {
                    currentPage = Menus.mainMenuPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case LOGIN_PAGE -> {
                    OutputOperations.display(TypePrint.TITLE,"Log-in Menu");
                    currentPage = Menus.loginPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                // Librarian Pages
                case LIBRARIAN_MENU -> {
                    currentPage = Menus.librarianPage();
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
                    currentPage = Menus.readerPage();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_SEARCH_BOOK -> {
                    OutputOperations.display(TypePrint.TITLE,"Search For A Book");
                    currentPage = DataSets.currentReader.searchBook();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_VIEW_ALL_BOOKS -> {
                    currentPage = DataSets.currentReader.viewAllBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_VIEW_RENT_BOOKS -> {
                    currentPage = DataSets.currentReader.viewRentedBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_VIEW_ORDER_BOOKS -> {
                    currentPage = DataSets.currentReader.viewOrderBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
                case READER_VIEW_OWNED_BOOKS -> {
                    currentPage = DataSets.currentReader.viewOwnedBooks();
                    System.out.println(new String(new char[180]).replace('\0', '='));
                }
            }
        }

    }


}

