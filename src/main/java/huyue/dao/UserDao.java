package huyue.dao;

import huyue.model.User;
import huyue.util.DB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 用于处理关于用户的数据库操作
 * java 代码表示的 INSERT/UPDATE/DELETE/SELECT
 *
 * 关于用户的密码, 不要保存明文密码.
 * 如果数据泄露, 则所有用户的密码也全部跟着泄露了
 * 一般是保存做完 hash 的密码 (选择使用 sha-256 这个 hash 算法)
 * User: HHH.Y
 * Date: 2020-08-21
 */
public class UserDao {
    // 插入用户
    public User insert(String username, String plainPassword) throws SQLException {
        // 对密码进行加密
        String password = encrypt(plainPassword);
        String sql = "insert into users (username, password) values (?, ?)";
        try(Connection c = DB.getConnection()) {
            // 还需要知道用户的 id, 所以使用 Statement.RETURN_GENERATED_KEYS 获取
            try(PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                s.setString(1, username);
                s.setString(2, password);

                s.executeUpdate();

                try(ResultSet r = s.getGeneratedKeys()) {
                    if(!r.next()) {
                        return null;
                    }

                    return new User(r.getInt(1), username);
                }

            }
        }
    }

    // 查询用户
    public User select(String username, String plainPassword) throws SQLException {
        String password = encrypt(plainPassword);
        String sql = "select uid from users where username = ? and password = ?";
        try(Connection c = DB.getConnection()) {
            try(PreparedStatement s = c.prepareStatement(sql)) {
                s.setString(1, username);
                s.setString(2, password);

                try(ResultSet r = s.executeQuery()) {
                    if(!r.next()) {
                        return null;
                    }

                    return new User(r.getInt(1), username);
                }
            }
        }
    }


    /**
     * 使用 sha-256 加密算法对用户的密码进行加密
     *
     * 这个做法实际上也不适合生产环境真正使用
     * 但至少比明文的情况要安全一些
     * @param plain
     * @return
     */
    private String encrypt(String plain) {
        try {
            // MessageDigest 类 为应用程序专门为加密算法提供的类
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // 将明文密码以字节的形式转换出来
            byte[] bytes = plain.getBytes();
            byte[] digest = messageDigest.digest(bytes); // 得到一个 hash 计算完成的字节数组
            StringBuilder sb = new StringBuilder();
            for (byte b:digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void main(String[] args) {
        String a = "123";
        UserDao userDao = new UserDao();
        String encrypt = userDao.encrypt(a);
        System.out.println(encrypt);
    }*/
}
