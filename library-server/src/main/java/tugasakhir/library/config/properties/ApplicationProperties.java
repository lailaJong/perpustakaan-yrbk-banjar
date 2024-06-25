package tugasakhir.library.config.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Putri Mele
 * on 13/06/2024
 */

@Data
@NoArgsConstructor
public class ApplicationProperties {
    //AUTHOR
    private String INSERT_AUTHOR = "INSERT INTO author (author_id, author_name) VALUES (:authorId, :authorName)";
    private String GET_ALL_AUTHOR = "SELECT * FROM authors";
    private String GET_AUTHOR_BY_ID = "SELECT * FROM author WHERE author_id = :authorId";
    private String GET_AUTHOR_BY_NAME = "SELECT * FROM authors WHERE author_name = :authorName";
    private String GET_AUTHORS_BY_NAME = "SELECT * FROM authors WHERE author_name LIKE :authorName";
    private String UPDATE_AUTHOR_BY_ID = "UPDATE author SET author_name = :authorName WHERE author_id = :authorId";
    private String DELETE_AUTHOR_BY_ID = "DELETE FROM authors WHERE author_id = :authorId";
    private String GET_COUNT_ALL_AUTHOR = "SELECT COUNT(*) FROM author";
    //MEMBER
    private String INSERT_MEMBER = "INSERT INTO member (member_id, user_id, member_status_id, score_detail_id, name, gender, phone_number, place_of_birth, date_of_birth, address, point, registration_date) " +
            "VALUES (:memberId, :userId, :memberStatusId, :scoreDetailId, :name, :gender, :phoneNumber, :placeOfBirth, :dateOfBirth, :address, point, registrationDate)";
    private String GET_ALL_MEMBER = "SELECT * FROM member";
    private String GET_MEMBER_BY_ID = "SELECT * FROM member WHERE member_id = :memberId";
    private String GET_MEMBER_BY_USER_ID = "SELECT * FROM member WHERE user_id = :userId";
    private String GET_MEMBER_BY_STATUS_ID = "SELECT * FROM members WHERE member_status_id = :statusId";
    private String GET_MEMBER_BY_NAME = "SELECT * FROM member WHERE name = :name";
    private String UPDATE_MEMBER_BY_ID = "UPDATE member SET user_id = :userId, member_status_id = :memberStatusId, score_detail_id = :scoreDetailId, name = :name, gender = :gender, " +
            "phone_number = :phoneNumber, place_of_birth = :placeOfBirth, date_of_birth = :dateOfBirth, address = :address, point = :point, registration_date = :registrationDate WHERE member_id = :memberId";
    private String DELETE_MEMBER_BY_ID = "DELETE FROM member WHERE member_id = :memberId";
    private String GET_COUNT_ALL_MEMBER = "SELECT COUNT(*) FROM member";
    //BOOK
    private String INSERT_BOOK = "INSERT INTO book (book_id, book_title, category_id, publisher_id, author_id, book_shelf_id, language, isbn, number_of_pages, publication_year, synopsis)" +
            "VALUES (:bookId, :bookTitle, :categoryId, :publisherId, :authorId, :bookShelfId, :language, :isbn, :numberOfPages, :publicationYear, :synopsis)";
    private String GET_BOOK_BY_ID = "SELECT * FROM book WHERE book_id = :bookId";
    private String GET_BOOK_DETAIL_BY_ID = "SELECT b.book_id, b.book_title, a.author_name, p.publisher_name, c.category_name, b.publication_year, b.stock, b.language, b.isbn, b.number_of_pages, b.book_shelf_id, b.synopsis" +
            " FROM" +
            " books b" +
            " INNER JOIN authors a ON b.author_id = a.author_id" +
            " INNER JOIN publishers p ON b.publisher_id = p.publisher_id" +
            " INNER JOIN categories c ON b.category_id = c.category_id" +
            " INNER JOIN book_shelves bs ON b.book_shelf_id = bs.book_shelf_id";
    private String GET_BOOK_BY_TITLE = "SELECT * FROM book WHERE book_title LIKE :bookTitle";
    private String UPDATE_BOOK_BY_ID = "UPDATE book SET book_title = :bookTitle, category_id = :categoryId, publisher_id = :publisherId, author_id = :authorId, book_shelf_id = :bookShelfId, language = :language, isbn = :isbn, number_of_pages = :numberOfPages, publication_year = :publicationYear, synopsis = :synopsis WHERE book_id = :bookId";
    private String DELETE_BOOK_BY_ID = "DELETE FROM book WHERE book_id = :bookId";
    private String GET_ALL_BOOK = "SELECT * FROM book";
    private String GET_COUNT_ALL_BOOK = "SELECT COUNT(*) FROM book";
    private String GET_BOOKS_BY_AUTHOR = "SELECT * FROM book WHERE author_id = (SELECT author_id FROM authors WHERE author_name = :authorName)";
    private String GET_BOOKS_BY_PUBLISHER = "SELECT * FROM book WHERE publisher_id = (SELECT publisher_id FROM publishers WHERE publisher_name = :publisherName)";
    private String GET_BOOKS_BY_CATEGORY = "SELECT * FROM book WHERE category_id = (SELECT category_id FROM categories WHERE category_name = :categoryName)";
    private String GET_BOOKS_STOCK_GREATER_THAN_ONE = "SELECT b.* FROM book b JOIN book_stock bs ON b.book_id = bs.book_id WHERE bs.stock > 1";
    //BOOKSHLEF
    private String INSERT_BOOKSHELF = "INSERT INTO book_shelf (book_shelf_id, book_shelf_code) VALUES (:bookShelfId, :bookShelfCode)";
    private String GET_BOOKSHELF_BY_ID = "SELECT * FROM book_shelf WHERE book_shelf_id = :bookShelfId";
    private String UPDATE_BOOKSHELF_BY_ID = "UPDATE book_shelf SET book_shelf_code = :bookShelfCode WHERE book_shelf_id = :bookShelfId";
    private String DELETE_BOOKSHELF_BY_ID = "DELETE FROM book_shelf WHERE book_shelf_id = :bookShelfId";
    private String GET_ALL_BOOKSHELF = "SELECT * FROM book_shelves";
    private String GET_COUNT_ALL_BOOKSHELF = "SELECT COUNT(*) FROM book_shelf";
    private String GET_BOOK_SHELF_BY_CODE = "SELECT * FROM book_shelves WHERE book_shelf_code LIKE :bookShelfCode";
    //BOOK STOCK
    private String INSERT_BOOK_STOCK = "INSERT INTO book_stock (book_stock_id, book_id, stock) VALUES (:bookStockId, :bookId, :stock)";
    private String GET_BOOK_STOCK_BY_ID = "SELECT * FROM book_stock WHERE book_stock_id = :bookStockId";
    private String GET_BOOK_STOCK_BY_BOOK_ID = "SELECT * FROM book_stock WHERE book_id = :bookId";
    private String UPDATE_BOOK_STOCK_BY_ID = "UPDATE book_stock SET book_id = :bookId, stock = :stock WHERE book_stock_id = :bookStockId";
    private String DELETE_BOOK_STOCK_BY_ID = "DELETE FROM book_stock WHERE book_stock_id = :bookStockId";
    private String GET_ALL_BOOK_STOCK = "SELECT * FROM book_stock";
    private String GET_ALL_BOOK_STOCK_DETAILS = "SELECT bs.book_stock_id AS bookStockId, bs.book_id AS bookId, b.book_title AS bookTitle, bs.stock AS stock FROM book_stock bs JOIN book b ON bs.book_id = b.book_id";
    private String GET_ALL_BOOK_STOCK_DETAILS_BY_BOOK_TITLE = "SELECT bs.book_stock_id AS bookStockId, bs.book_id AS bookId, b.book_title AS bookTitle, bs.stock AS stock FROM book_stock bs JOIN book b ON bs.book_id = b.book_id WHERE b.book_title = :bookTitle";
    private String GET_COUNT_ALL_BOOK_STOCK = "SELECT COUNT(*) FROM book_stock";
    //BORROWING DETAIL
    private String INSERT_BORROWING_DETAIL = "INSERT INTO borrowing_detail (borrowing_id, user_id, book_id, borrowing_date, return_date, actual_return_date, status) " +
            "VALUES (:borrowingId, :userId, :bookId, :borrowingDate, :returnDate, :actualReturnDate, :status)";
    private String GET_BORROWING_DETAIL_BY_ID = "SELECT * FROM borrowing_detail WHERE borrowing_id = :borrowingId";
    private String GET_COUNT_RETURN_STATUS_BY_USER_ID = "SELECT COUNT(*) FROM borrowing_detail WHERE user_id = :userId AND status = :returnStatus OR status = :lostStatus";
    private String GET_COUNT_BORROWING_LATE_STATUS_BY_USER_ID = "SELECT COUNT(*) FROM borrowing_detail WHERE user_id = :userId AND status = :borrowingStatus";
    private String UPDATE_BORROWING_DETAIL_BY_ID = "UPDATE borrowing_detail SET user_id = :userId, book_id = :bookId, borrowing_date = :borrowingDate, " +
            "return_date = :returnDate, actual_return_date = :actualReturnDate, status = :status " +
            "WHERE borrowing_id = :borrowingId";
    private String DELETE_BORROWING_DETAIL_BY_ID = "DELETE FROM borrowing_detail WHERE borrowing_id = :borrowingId";
    private String GET_ALL_BORROWING_DETAIL = "SELECT * FROM borrowing_detail";
    private String GET_ALL_BORROWING_DETAILS_BY_USER_ID = "SELECT b.borrowing_id, bk.book_title, b.status, b.borrowing_date, b.return_date " +
            "FROM borrowing b INNER JOIN book bk ON b.book_id = bk.book_id WHERE b.status = :status";
    private String GET_ALL_BORROWING_DETAILS_BY_USER_ID_AND_BOOK_TITLE = "SELECT b.borrowing_id, bk.book_title, b.status, b.borrowing_date, b.return_date " +
            "FROM borrowing b INNER JOIN book bk ON b.book_id = bk.book_id WHERE b.status = :status AND bk.book_title LIKE :bookTitle";
    private String GET_ALL_BORROWING_DETAILS_BY_STATUS = "SELECT bd.borrowingId, m.title, bd.bookId, bd.status, bd.borrowingDate, bd.returnDate, bd.actualReturnDate FROM BorrowingDetail bd JOIN Member m ON bd.userId = m.userId WHERE " +
            "bd.status = :status";
    private String GET_ALL_BORROWING_DETAILS_USER_BY_USER_ID = "SELECT bd.borrowingId, b.bookTitle, bd.borrowingDate, bd.returnDate, bd.actualReturnDate FROM BorrowingDetail bd JOIN Book b ON bd.bookId = b.bookId WHERE " +
            "bd.userId = :userId AND bd.status = :returnedStatus OR bd.status = :lostStatus";
    private String GET_ALL_BORROWING_DETAILS_USER_BY_USER_ID_AND_BOOK_TILTE = "SELECT bd.borrowingId, b.bookTitle, bd.borrowingDate, bd.returnDate, bd.actualReturnDate FROM BorrowingDetail bd JOIN Book b ON bd.bookId = b.bookId WHERE " +
            "bd.userId = :userId AND bd.status = :returnedStatus OR bd.status = :lostStatus AND b.bookTitle LIKE :bookTitle";
    private String GET_ALL_BORROWING_DETAILS_BY_STATUS_AND_MEMBER_NAME = "SELECT bd.borrowingId, m.name, bd.bookId, bd.status, bd.borrowingDate, bd.returnDate, bd.actualReturnDate FROM BorrowingDetail bd JOIN Member m ON bd.userId = m.userId WHERE " +
            "bd.status = :status AND m.name LIKE :name";
    private String GET_COUNT_ALL_BORROWING_DETAIL = "SELECT COUNT(*) FROM borrowing_detail";
    //CATEGORY
    private String INSERT_CATEGORY = "INSERT INTO category (category_id, category_name) VALUES (:categoryId, :categoryName)";
    private String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id = :categoryId";
    private String UPDATE_CATEGORY_BY_ID = "UPDATE category SET category_name = :categoryName WHERE category_id = :categoryId";
    private String DELETE_CATEGORY_BY_ID = "DELETE FROM category WHERE category_id = :categoryId";
    private String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    private String GET_COUNT_ALL_CATEGORY = "SELECT COUNT(*) FROM category";
    private String GET_CATEGORY_BY_NAME = "SELECT * FROM category WHERE category_name LIKE :categoryName";
    //MEMBER STATUS
    private String INSERT_MEMBER_STATUS = "INSERT INTO member_status (member_status_id, status) VALUES (:memberStatusId, :status)";
    private String GET_MEMBER_STATUS_BY_ID = "SELECT * FROM member_status WHERE member_status_id = :memberStatusId";
    private String GET_STATUS_BY_MEMBER_STATUS_ID = "SELECT status FROM member_status WHERE member_status_id = :memberStatusId";
    private String GET_MEMBER_STATUS_BY_STATUS = "SELECT * FROM member_status WHERE status = :status";
    private String UPDATE_MEMBER_STATUS_BY_ID = "UPDATE member_status SET status = :status WHERE member_status_id = :memberStatusId";
    private String DELETE_MEMBER_STATUS_BY_ID = "DELETE FROM member_status WHERE member_status_id = :memberStatusId";
    private String GET_ALL_MEMBER_STATUS = "SELECT * FROM member_status";
    private String GET_COUNT_ALL_MEMBER_STATUS = "SELECT COUNT(*) FROM member_status";
    //OFFICER
    private String INSERT_OFFICER = "INSERT INTO officer (officer_id, user_id, name) VALUES (:officerId, :userId, :name)";
    private String GET_OFFICER_BY_ID = "SELECT * FROM officer WHERE officer_id = :officerId";
    private String UPDATE_OFFICER_BY_ID = "UPDATE officer SET user_id = :userId, name = :name WHERE officer_id = :officerId";
    private String DELETE_OFFICER_BY_ID = "DELETE FROM officer WHERE officer_id = :officerId";
    private String GET_ALL_OFFICER = "SELECT * FROM officer";
    private String GET_COUNT_ALL_OFFICER = "SELECT COUNT(*) FROM officer";
    //ORDER DETAIL
    private String INSERT_ORDER_DETAIL = "INSERT INTO order_detail (order_id, user_id, book_id, order_date, status) VALUES (:order_id, :userId, :bookId, :orderDate, :status)";
    private String GET_ORDER_DETAIL_BY_ID = "SELECT * FROM order_detail WHERE order_id = :orderId";
    private String UPDATE_ORDER_DETAIL_BY_ID = "UPDATE order_detail SET user_id = :userId, book_id = :bookId, order_date = :orderDate, status = :status WHERE order_id = :orderId";
    private String DELETE_ORDER_DETAIL_BY_ID = "DELETE FROM order_detail WHERE order_id = :orderId";
    private String GET_COUNT_ORDER_DETAIL_BY_USER_ID = "SELECT COUNT(*) FROM order_detail WHERE user_id = :userId AND status = :orderStatus";
    private String GET_ALL_ORDER_DETAIL = "SELECT * FROM order_detail";
    private String GET_ALL_ORDER_DETAILS_BY_USER_ID = "SELECT o.order_id, b.book_title, o.status, o.order_date, o.taking_date " +
            "FROM orders o INNER JOIN books b ON o.book_id = b.book_id WHERE o.user_id = :userId AND o.status = :status";
    private String GET_ALL_ORDER_DETAILS_BY_USER_ID_AND_BOOK_TITLE = "SELECT o.order_id, b.book_title, o.status, o.order_date, o.taking_date " +
            "FROM orders o INNER JOIN books b ON o.book_id = b.book_id WHERE o.user_id = :userId AND o.status = :status AND b.book_title LIKE :bookTitle";
    private String GET_COUNT_ALL_ORDER_DETAIL = "SELECT COUNT(*) FROM order_detail";
    //PUBLISHER
    private String INSERT_PUBLISHER = "INSERT INTO publisher (publisher_id, publisher_name) VALUES (:publisherId, :publisherName)";
    private String GET_PUBLISHER_BY_ID = "SELECT * FROM publisher WHERE publisher_id = :publisherId";
    private String UPDATE_PUBLISHER_BY_ID = "UPDATE publisher SET publisher_name = :publisherName WHERE publisher_id = :publisherId";
    private String DELETE_PUBLISHER_BY_ID = "DELETE FROM publisher WHERE publisher_id = :publisherId";
    private String GET_ALL_PUBLISHER = "SELECT * FROM publisher";
    private String GET_COUNT_ALL_PUBLISHER = "SELECT COUNT(*) FROM publisher";
    private String GET_PUBLISHER_BY_NAME = "SELECT * FROM publishers WHERE publisher_name LIKE :publisherName";
    //ROLE
    private String INSERT_ROLE = "INSERT INTO role (role_id, role_name) VALUES (:roleId, :roleName)";
    private String GET_ROLE_BY_ID = "SELECT * FROM role WHERE role_id = :roleId";
    private String GET_ROLE_BY_NAME = "SELECT * FROM role WHERE role_name = :roleName";
    private String UPDATE_ROLE_BY_ID = "UPDATE role SET role_name = :roleName WHERE role_id = :roleId";
    private String DELETE_ROLE_BY_ID = "DELETE FROM role WHERE role_id = :roleId";
    private String GET_ALL_ROLE = "SELECT * FROM role";
    private String GET_COUNT_ALL_ROLE = "SELECT COUNT(*) FROM role";
    //SCORE DETAIL
    private String INSERT_SCORE_DETAIL = "INSERT INTO score_detail (score_detail_id, point, extra_borrow_time, extra_books_quota) VALUES (:scoreDetailId, :point, :extraBorrowTime, :extraBooksQuota)";
    private String GET_SCORE_DETAIL_BY_ID = "SELECT * FROM score_detail WHERE score_detail_id = :scoreDetailId";
    private String UPDATE_SCORE_DETAIL_BY_ID = "UPDATE score_detail SET point = :point, extra_borrow_time = :extraBorrowTime, extra_books_quota = : extraBooksQuota WHERE book_stock_id = :bookStockId";
    private String DELETE_SCORE_DETAIL_BY_ID = "DELETE FROM score_detail WHERE score_detail_id = :scoreDetailId";
    private String GET_ALL_SCORE_DETAIL = "SELECT * FROM score_detail";
    private String GET_COUNT_ALL_SCORE_DETAIL = "SELECT COUNT(*) FROM score_detail";
    //USER
    private String INSERT_USER = "INSERT INTO user (user_id, role_id, username, password) VALUES (:userId, :roleId, :username, :password)";
    private String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id = :userId";
    private String GET_USER_BY_USERNAME = "SELECT * FROM user WHERE username = :username";
    private String UPDATE_USER_BY_ID = "UPDATE user SET role_id = :roleId, username = :username, password = :password WHERE user_id = :userId";
    private String DELETE_USER_BY_ID = "DELETE FROM user WHERE user_id = :userId";
    private String GET_ALL_USER = "SELECT * FROM user";
    private String GET_COUNT_ALL_USER = "SELECT COUNT(*) FROM user";
    private String GET_EXIST_USERNAME = "SELECT COUNT(*) FROM user WHERE username = :username";
    //OTHERS
    private String orderedStatus = "Dipesan";
    private String cancelledStatus = "Dibatalkan";
    private String completedStatus = "Selesai";
    private String borrowedStatus = "Dipinjam";
    private String lostStatus = "Hilang";
    private String returnedStatus = "Selesai";
}
