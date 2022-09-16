public class Book_Order {
    public Book orderedBook;
    public Reader orderReader;
    public String orderedBookName;
    public String orderReaderID;

    public Book_Order(Book orderedBook, Reader orderReader) {
        this.orderedBook = orderedBook;
        this.orderReader = orderReader;
        this.orderedBookName = orderedBook.getName();
        this.orderReaderID = orderReader.getId();
    }
}
