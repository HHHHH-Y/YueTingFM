package huyue.dao;

import huyue.model.Audio;
import huyue.util.DB;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:音频数据库
 * User: HHH.Y
 * Date: 2020-08-22
 */
public class AudioDao {
    public static void insert(int sid, String uuid, String contentType, InputStream inputStream) throws SQLException {
        String sql = "insert into audios (sid, uuid, type, content) values (?,?,?,?)";

        try(Connection c= DB.getConnection()) {
            try(PreparedStatement s = c.prepareStatement(sql)) {
                s.setInt(1, sid);
                s.setString(2, uuid);
                s.setString(3, contentType);
                s.setBlob(4, inputStream);

                s.executeUpdate();
            }
        }
    }

    // 查询 uuid = uuid 的音频信息
    public static Audio select(String uuid) throws SQLException {
        String sql = "select type, content from audios where uuid = ?";
        try(Connection c = DB.getConnection()) {
            try(PreparedStatement s = c.prepareStatement(sql)) {
                s.setString(1, uuid);

                try(ResultSet r = s.executeQuery()) {
                    if(!r.next()) {
                        return null;
                    }

                    return new Audio(r.getString("type"), r.getBinaryStream("content"));
                }
            }

        }
    }
}
