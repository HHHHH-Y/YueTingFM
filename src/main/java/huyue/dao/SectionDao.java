package huyue.dao;

import huyue.model.Section;
import huyue.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-08-21
 */
public class SectionDao {

    // 插入一个章节
    public Section insert(int bid, String name) {
        return null;
    }

    // 根据 bid 查找一本书中的所有章节
    public List<Section> selectByBid(int bid) throws SQLException {
        String sql = "select name from sections where bid = ? order by sid";

        List<Section> sections = new ArrayList<>();
        try(Connection c = DB.getConnection()) {
            try(PreparedStatement s = c.prepareStatement(sql)) {
                s.setInt(1, bid);

                try(ResultSet r = s.executeQuery()) {
                    while (r.next()) {
                        Section section = new Section(r.getString("name"));
                        sections.add(section);
                    }
                }
            }
        }
        return sections;
    }
}
