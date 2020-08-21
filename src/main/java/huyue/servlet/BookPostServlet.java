package huyue.servlet;

import huyue.model.Book;
import huyue.model.User;
import huyue.service.BookService;

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
 * Description: 上传书籍内容 (需要判断用户是否登录)
 * User: HHH.Y
 * Date: 2020-08-22
 */
@WebServlet("/post-book")
public class BookPostServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookService();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            resp.sendRedirect("/login.html");
            return;
        }

        try {
            Book book = bookService.post(title, user);
            if(book != null) {
                resp.sendRedirect("/book.jsp?bid=" + book.bid);
            } else {
                // 如果 book == null
                resp.sendRedirect("/add-book.jsp");
            }
        }catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
