package huyue.util;


import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class DBTest {

    @Test
    public void getConnection() throws SQLException {
        Assert.assertNotNull(DB.getConnection());
    }
}