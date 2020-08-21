package huyue.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-08-21
 */
public class DB {
    private static volatile DataSource dataSource = null;

    public static Connection getConnection() throws SQLException {
        if(dataSource == null) {
            synchronized (DB.class) {
                if(dataSource == null) {
                    dataSource = initDataSource();
                }
            }
        }

        return dataSource.getConnection();
    }

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("huyuelover1017");
        mysqlDataSource.setDatabaseName("yuetingfm");
        mysqlDataSource.setCharacterEncoding("utf8");
        mysqlDataSource.setUseSSL(false);

        return mysqlDataSource;
    }

}
