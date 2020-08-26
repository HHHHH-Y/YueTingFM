package huyue.servlet;

import huyue.model.Audio;
import huyue.service.AudioService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description: 声音获取播放
 * User: HHH.Y
 * Date: 2020-08-27
 */
@WebServlet("/audio/get")
public class AudioGetServlet extends HttpServlet {
    private AudioService audioService;

    @Override
    public void init() throws ServletException {
        audioService = new AudioService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String uuid = req.getParameter("uuid");

        // 从数据库中拿到 audio 信息
        Audio audio = null;
        try {
            audio = audioService.get(uuid);
            if(audio == null) {
                resp.sendError(404, "没有这个声音");
                return;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        // 创建一个存放结果的输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] buf = new byte[1024];
        int len;

        // 说明一直在读取内容
        while ((len = audio.inputStream.read(buf)) != -1) {
            // 将读取的内容写入到结果中
            outputStream.write(buf, 0, len);
        }

        audio.inputStream.close();
    }
}
