package huyue.servlet;


import huyue.model.User;
import huyue.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户注册
 * User: HHH.Y
 * Date: 2020-08-21
 */
@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {
    private UserService userService;

    public void init() throws ServletException{
        // Servlet 生命周期的内容
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 每次都带上 utf-8 设置, 确保字符集不会出问题
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 没有做参数正确性检查


        // 注册
        try {
            User user = userService.register(username, password);

            if(user == null) {
                // 用户注册失败
                // 没有做太多易用性考虑: 没有告诉用户错误原因
                resp.sendRedirect("/register.html");
                return;
            }

            // 将用户信息存放到 session 中
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // 跳转回首页
            resp.sendRedirect("/");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
