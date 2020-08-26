package huyue.dao;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SectionDaoTest {

    @Test
    public void testInsert() throws SQLException {
        SectionDao sectionDao = new SectionDao();
        sectionDao.insert(3, "命中注定还是一见钟情？");
    }

    @Test
    public void TestSelectByBid() throws SQLException {
        SectionDao sectionDao = new SectionDao();
        sectionDao.selectByBid(3);
    }
}