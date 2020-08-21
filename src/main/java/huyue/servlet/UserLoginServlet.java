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
 * Description: 用户登录
 * User: HHH.Y
 * Date: 2020-08-21
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private UserService userService;

    public void init() throws ServletException{
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userService.login(username, password);

            if(user == null) {
                // 用户登录失败
                resp.sendRedirect("/login.html");
                return;
            }

            // 将用户信息存储到 session 中
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // 跳转回首页
            resp.sendRedirect("/");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
