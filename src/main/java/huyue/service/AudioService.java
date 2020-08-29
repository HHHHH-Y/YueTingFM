package huyue.service;

import huyue.dao.AudioDao;
import huyue.model.Audio;

import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: 音频
 * User: HHH.Y
 * Date: 2020-08-26
 */
public class AudioService {
    private AudioDao audioDao;

    public AudioService() {
        audioDao = new AudioDao();
    }


    public String save(int sid, Part audio) throws IOException, SQLException {
        // 直接生成 uuid
        String uuid = UUID.randomUUID().toString();

        AudioDao.insert(sid, uuid, audio.getContentType(), audio.getInputStream());

        return uuid;

    }

    // 从数据库中获取 audio 信息
    public Audio get(String uuid) throws SQLException {
        return AudioDao.select(uuid);
    }
}
