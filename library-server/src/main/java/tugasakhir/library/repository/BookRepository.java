package tugasakhir.library.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tugasakhir.library.config.properties.ApplicationProperties;
import tugasakhir.library.config.variable.ApplicationConstant;
import tugasakhir.library.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Putri Mele
 * on 09/06/2024
 */

@Repository
@Slf4j
//public class BookRepository {
//    @Autowired
//    @Qualifier(ApplicationConstant.BEAN_DS)
//    protected NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Autowired
//    @Qualifier
//    protected ApplicationProperties applicationProperties;
//
//    private static final class BookRowMapper implements RowMapper<Book> {
//        @Override
//        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Book book = new Book();
//            book.setBookId(rs.getString("book_id"));
//            book.setBookTitle(rs.getString("book_title"));
//            book.setCategoryId(rs.getString("category_id"));
//            book.setPublisherId(rs.getString("publisher_id"));
//            book.setAuthorId(rs.getString("author_id"));
//            book.setLanguage(rs.getString("language"));
//            book.setIsbn(rs.getString("isbn"));
//            book.setNumberOfPages(rs.getInt("number_of_pages"));
//            book.setPublicationYear(rs.getString("publication_year"));
//            book.setSynopsis(rs.getString("synopsis"));
//            return book;
//        }
//    }
//
//    // Add a book
//    public void addBook(Book book) {
//        try{
//            log.info("[ADD BOOK][{}]", applicationProperties.getINSERT_BOOK());
//            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
//            jdbcTemplate.update(applicationProperties.getINSERT_BOOK(), paramSource);
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//    }
//
//    // Get a book by ID
//    public Book getBookById(String bookId) {
//        try{
//            log.info("[GET BOOK BY ID][{}][{}]", bookId, applicationProperties.getGET_BOOK_BY_ID());
//            SqlParameterSource paramSource = new MapSqlParameterSource("bookId", bookId);
//            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOK_BY_ID(), paramSource, new BookRowMapper());
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return null;
//        }
//    }
//
//    public Book getBookByBookTitle(String bookTitle) {
//        try{
//            log.info("[GET BOOK BY TITLE][{}][{}]", bookTitle, applicationProperties.getGET_BOOK_BY_TITLE());
//            SqlParameterSource paramSource = new MapSqlParameterSource("bookTitle", bookTitle);
//            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOK_BY_TITLE(), paramSource, new BookRowMapper());
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return null;
//        }
//    }
//
//    // Update a book
//    public void updateBook(Book book) {
//        try{
//            log.info("[UPDATE BOOK BY ID][{}][{}]", book.getBookId(), applicationProperties.getUPDATE_BOOK_BY_ID());
//            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
//            jdbcTemplate.update(applicationProperties.getUPDATE_BOOK_BY_ID(), paramSource);
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//    }
//
//    // Delete a book
//    public void deleteBook(String bookId) {
//        try{
//            log.info("[DELETE BOOK BY ID][{}][{}]", bookId, applicationProperties.getDELETE_BOOK_BY_ID());
//            SqlParameterSource paramSource = new MapSqlParameterSource("bookId", bookId);
//            jdbcTemplate.update(applicationProperties.getDELETE_BOOK_BY_ID(), paramSource);
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//    }
//
//    // Get all books
//    public List<Book> getAllBooks() {
//        try{
//            log.info("[GET ALL BOOK][{}]", applicationProperties.getGET_ALL_BOOK());
//            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOK(), new BookRowMapper());
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return null;
//        }
//    }
//
//    //Get all books with status (available, ordered, borrowed)
//    public List<Book> getAllBooks(String status) {
//        try{
//            log.info("[GET ALL BOOK][{}][{}]", applicationProperties.getGET_ALL_BOOK_BY_STATUS(), status);
//            SqlParameterSource paramSource = new MapSqlParameterSource("status", status);
//            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOK_BY_STATUS(), paramSource, new BookRowMapper());
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return null;
//        }
//    }
//
//    public String generateBookId() {
//        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_BOOK(), (SqlParameterSource) null, Integer.class);
//        int suffix = count + 1;
//        return String.format("BUK%03d", suffix);
//    }
//}

public class BookRepository {
    @Autowired
    @Qualifier(ApplicationConstant.BEAN_DS)
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier
    protected ApplicationProperties applicationProperties;

    private static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookId(rs.getString("book_id"));
            book.setBookTitle(rs.getString("book_title"));
            book.setCategoryId(rs.getString("category_id"));
            book.setPublisherId(rs.getString("publisher_id"));
            book.setAuthorId(rs.getString("author_id"));
            book.setBookShelfId(rs.getString("book_shelf_id"));
            book.setLanguage(rs.getString("language"));
            book.setIsbn(rs.getString("isbn"));
            book.setNumberOfPages(rs.getInt("number_of_pages"));
            book.setPublicationYear(rs.getString("publication_year"));
            book.setSynopsis(rs.getString("synopsis"));
            return book;
        }
    }

    // Add a book
    public void addBook(Book book) {
        try{
            log.info("[ADD BOOK][{}]", applicationProperties.getINSERT_BOOK());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
            jdbcTemplate.update(applicationProperties.getINSERT_BOOK(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get a book by ID
    public Book getBookById(String bookId) {
        try{
            log.info("[GET BOOK BY ID][{}][{}]", bookId, applicationProperties.getGET_BOOK_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookId", bookId);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOK_BY_ID(), paramSource, new BookRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public Book getBookByBookTitle(String bookTitle) {
        try{
            log.info("[GET BOOK BY TITLE][{}][{}]", bookTitle, applicationProperties.getGET_BOOK_BY_TITLE());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookTitle", bookTitle);
            return jdbcTemplate.queryForObject(applicationProperties.getGET_BOOK_BY_TITLE(), paramSource, new BookRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    // Update a book
    public void updateBook(Book book) {
        try{
            log.info("[UPDATE BOOK BY ID][{}][{}]", book.getBookId(), applicationProperties.getUPDATE_BOOK_BY_ID());
            SqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
            jdbcTemplate.update(applicationProperties.getUPDATE_BOOK_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Delete a book
    public void deleteBook(String bookId) {
        try{
            log.info("[DELETE BOOK BY ID][{}][{}]", bookId, applicationProperties.getDELETE_BOOK_BY_ID());
            SqlParameterSource paramSource = new MapSqlParameterSource("bookId", bookId);
            jdbcTemplate.update(applicationProperties.getDELETE_BOOK_BY_ID(), paramSource);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    // Get all books
    public List<Book> getAllBooks() {
        try{
            log.info("[GET ALL BOOK][{}]", applicationProperties.getGET_ALL_BOOK());
            return jdbcTemplate.query(applicationProperties.getGET_ALL_BOOK(), new BookRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get books by author
    public List<Book> getBooksByAuthor(String authorName) {
        try{
            log.info("[GET BOOKS BY AUTHOR][{}][{}]", authorName, applicationProperties.getGET_BOOKS_BY_AUTHOR());
            SqlParameterSource paramSource = new MapSqlParameterSource("authorName", authorName);
            return jdbcTemplate.query(applicationProperties.getGET_BOOKS_BY_AUTHOR(), paramSource, new BookRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get books by publisher
    public List<Book> getBooksByPublisher(String publisherName) {
        try{
            log.info("[GET BOOKS BY PUBLISHER][{}][{}]", publisherName, applicationProperties.getGET_BOOKS_BY_PUBLISHER());
            SqlParameterSource paramSource = new MapSqlParameterSource("publisherName", publisherName);
            return jdbcTemplate.query(applicationProperties.getGET_BOOKS_BY_PUBLISHER(), paramSource, new BookRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get books by category
    public List<Book> getBooksByCategory(String categoryName) {
        try{
            log.info("[GET BOOKS BY CATEGORY][{}][{}]", categoryName, applicationProperties.getGET_BOOKS_BY_CATEGORY());
            SqlParameterSource paramSource = new MapSqlParameterSource("categoryName", categoryName);
            return jdbcTemplate.query(applicationProperties.getGET_BOOKS_BY_CATEGORY(), paramSource, new BookRowMapper());
        }catch (Exception e){
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    // Get all books where stock is greater than 1
    public List<Book> getBooksWhereStockGreaterThanOne() {
        try {
            log.info("[GET BOOKS WHERE STOCK > 1][{}]", applicationProperties.getGET_BOOKS_STOCK_GREATER_THAN_ONE());
            return jdbcTemplate.query(applicationProperties.getGET_BOOKS_STOCK_GREATER_THAN_ONE(), new BookRowMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

        public String generateBookId() {
        int count = jdbcTemplate.queryForObject(applicationProperties.getGET_COUNT_ALL_BOOK(), (SqlParameterSource) null, Integer.class);
        int suffix = count + 1;
        return String.format("BUK%03d", suffix);
    }
}

