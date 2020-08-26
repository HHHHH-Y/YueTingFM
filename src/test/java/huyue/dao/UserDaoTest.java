package huyue.dao;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void TestInsert() throws SQLException {
        UserDao userDao = new UserDao();
        userDao.insert("huyueyue", "161017");
    }

    @Test
    public void TestSelect() throws SQLException {
        UserDao userDao = new UserDao();
        userDao.select("huyueyue", "161017");
    }
}