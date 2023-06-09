import model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

//@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {


    //Phương thức
    //Get: Mặc định khi gọi đường dẫn, không có bảo mật các thông số mà client truyền lên
    // Cách truyền tham số: ?tenthamso=giatri&tenthamso=giatri
    // Có giới hạn về tham số gọi trên trình duyệt


    //Post: Tham số sẽ được truyền ngầm, tham số sẽ được bảo mật
    // Tham số sẽ được truyền thông qua code và thẻ form
    // Không giới hạn về tham số gọi trên trình duyệt

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        int a = 5;
//        int b = 10;
//        int result = a + b;
//        req.setAttribute("kq", result); // Servlet truyền biến ra file jsp

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = "";
        String password = req.getParameter("password");

        String username = req.getParameter("username");

        PrintWriter wr = resp.getWriter();
        if(username.toLowerCase().equals("nguyenvana@gmail.com")) {
            message ="Hello" + username;
        }
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);

        List<String> list = Arrays.asList("Cybersoft", "Java 21", "JSP");

        req.setAttribute("msg",message);
        req.setAttribute("user",users);
        req.setAttribute("list", list);

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}


//       String username = req.getParameter("username");
//       String password = req.getParameter("password");
////        http://localhost:8080/demoservlet_cybersoft/demo?username=cybersoft&password=123456
//
//        resp.setContentType("text/html;charset=UTF-8");
//        //        req.setCharacterEncoding("UTF-8");
//        PrintWriter wr = resp.getWriter();
//        wr.println("Hello " + username + "password " + password);