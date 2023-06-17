package controller;

import config.MysqlConfig;
import model.UserModel;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "logincontroller", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


// logout
        String action = req.getParameter("action");
        if (action != null && !action.isEmpty()) {
            HttpSession session = req.getSession();
            session.removeAttribute("email");
            session.removeAttribute("password");
            session.invalidate();
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("rememberme");
//        System.out.println(email + " 1 controller  " + password);
        List<UserModel> role = loginService.checkRole(email);
//        System.out.println("Day la role" + role.get(0).getRoleId());

        boolean isSuccess = loginService.checkLogin(email, password);
        if (isSuccess && email != null && password != null) {
            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            session.setAttribute("password", password);

            String roleS = String.valueOf(role.get(0).getRoleId());
            session.setAttribute("role", roleS);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/index");
        } else {
            req.setAttribute("error", "Tài khoảng hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }


    }
}



