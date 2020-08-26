package huyue.dao;

import huyue.model.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class BookDaoTest {

    @Test
    public void testInsert() throws SQLException {
        User user = new User(14, "huyueyue");
        BookDao bookDao = new BookDao();
        bookDao.insert(user, "幻城");
    }

    @Test
    public void testSelectAll() throws SQLException {
        BookDao bookDao = new BookDao();
        bookDao.selectAll();
    }

    @Test
    public void testSelectByBid() throws SQLException {
        BookDao bookDao = new BookDao();
        bookDao.selectByBid(3);
    }
}