//package tugasakhir.library.service;
//
///**
// * @author Putri Mele
// * on 13/06/2024
// */
//@Service
//public class BookService {
//    @Autowired
//    private BookRepository bookRepository;
//
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
//
//    public Book getBookById(Integer bookId) {
//        return bookRepository.findById(bookId).orElse(null);
//    }
//
//    public Book addBook(Book book) {
//        return bookRepository.save(book);
//    }
//
//    public Book updateBook(Integer bookId, Book bookDetails) {
//        Book book = bookRepository.findById(bookId).orElse(null);
//        if (book != null) {
//            book.setBookTitle(bookDetails.getBookTitle());
//            // Set other fields...
//            return bookRepository.save(book);
//        }
//        return null;
//    }
//
//    public void deleteBook(Integer bookId) {
//        bookRepository.deleteById(bookId);
//    }
//}
