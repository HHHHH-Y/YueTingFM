package huyue.service;

import huyue.dao.BookDao;
import huyue.dao.SectionDao;
import huyue.model.Book;


import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-08-21
 */
public class BookService {
    private BookDao bookDao;
    private SectionDao sectionDao;

    public BookService() {
        bookDao = new BookDao();
        sectionDao = new SectionDao();
    }
    public List<Book> list() throws SQLException {
        List<Book> books = bookDao.selectAll();
        System.out.println(books);
        return books;
//        return bookDao.selectAll();
    }

}
