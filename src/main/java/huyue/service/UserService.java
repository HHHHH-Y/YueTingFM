package huyue.service;

import huyue.dao.UserDao;
import huyue.model.User;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description: 主要提供 一些业务角度的方法
 * 提供 注册和登录 的方法
 * User: HHH.Y
 * Date: 2020-08-21
 */
public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User register(String username, String password) throws SQLException {
        return userDao.insert(username, password);
    }

    public User login(String username, String password) throws SQLException {
        return userDao.select(username, password);
    }
}
