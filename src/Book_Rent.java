import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Book_Rent {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
    public Book rentedBook;
    public Reader rentReader;
    public String rentReaderID;
    public String rentedBookName;
    public LocalDate dateToReturn;

    public Book_Rent(Book rentedBook, Reader rentReader, LocalDate dateToReturn) {
        this.rentedBook = rentedBook;
        this.rentReader = rentReader;
        this.rentReaderID = rentReader.getId();
        this.rentedBookName = rentedBook.getName();
        this.dateToReturn = dateToReturn;
    }
}
