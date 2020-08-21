package huyue.service;

import huyue.dao.BookDao;
import huyue.dao.SectionDao;
import huyue.model.Book;
import huyue.model.Section;
import huyue.model.User;


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
        return bookDao.selectAll();
    }

    public Book post(String title, User user) throws SQLException {
        return bookDao.insert(user, title);
    }

    public Book get(int bid) throws SQLException{
        // 拿到书籍的基本信息
        Book book =  bookDao.selectByBid(bid);
        if(book == null) {
            return null;
        }
        // 拿到书籍的章节信息
        book.sections = sectionDao.selectByBid(bid);

        return book;
    }
}
