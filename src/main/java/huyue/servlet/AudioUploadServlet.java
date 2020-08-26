package huyue.servlet;

import huyue.service.AudioService;
import huyue.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description: 声音上传
 * User: HHH.Y
 * Date: 2020-08-26
 */
@MultipartConfig    // form 表单获取请求体
@WebServlet("/upload/audio")
public class AudioUploadServlet extends HttpServlet {
    private AudioService audioService;

    @Override
    public void init() throws ServletException {
        audioService = new AudioService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 获取 sid
        String s = req.getParameter("sid");
        int sid = Integer.parseInt(s);

        Part audio = req.getPart("audio");
        // 通过这个输入流，就可以读取到声音的所有数据
        // InputStream inputStream = audio.getInputStream();

        resp.setContentType("utf-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        // 保存声音，得到声音的 uuid，同时关联 sid
        try {
            String uuid = audioService.save(sid, audio);
            // 如果查询成功，将 uuid 以 json 的格式打印出来
            writer.printf("{\"uuid\": \"%s\"}%n", uuid);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(500);
            writer.printf("{\"reason\": \"%s\"}%n", e.getMessage());
        }

    }
}
