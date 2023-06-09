package controller;

import model.RoleModel;
import model.UserModel;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "UserController", urlPatterns = {"/user", "/user/add", "/user/delete", "/user/update"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy đường dẫn servlet ng dùng gọi trên browser
        String path = req.getServletPath();
        switch (path) {
            case "/user":
                getAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            case "/user/update":
                updateUser(req, resp);
                break;
            default:

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy đường dẫn servlet ng dùng gọi trên browser
        String path = req.getServletPath();
        switch (path) {
            case "/user":

                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            case "/user/update":
                updateUser(req, resp);
                break;
            default:

                break;
        }
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> listUser = userService.getAllUser();
        req.setAttribute("listUsers", listUser);
        req.getRequestDispatcher("user-table.jsp").forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();// để biết khi nào dùng post hay get

        if (method.equalsIgnoreCase("post")) {
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int role_id = Integer.parseInt(req.getParameter("role"));
            userService.insertUser(email, password, fullname, role_id);
        }

        if (method.equalsIgnoreCase("get")) {
            List<RoleModel> listRole = roleService.getRole();
            req.setAttribute("list_Roles", listRole);
        }
        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = userService.deleteUser(id);
    }

//    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String method = req.getMethod();
//        if (method.equalsIgnoreCase("post")) {
//            String fullname = req.getParameter("fullname");
//            String email = req.getParameter("email");
//            int user_id = Integer.parseInt(req.getParameter("id_update"));
//            System.out.println("ten la " + fullname);
//            System.out.println("mail la " + email);
//            System.out.println("id la " + user_id);
//            userService.updateUser(user_id, email, fullname);
//
//        }
//
//        req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
//
//
//    }
private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String method = req.getMethod();
    if (method.equalsIgnoreCase("post")) {
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        int user_id = Integer.parseInt(req.getParameter("id_update"));
        System.out.println("ten la " + fullname);
        System.out.println("mail la " + email);
        System.out.println("id la " + user_id);
        userService.updateUser(user_id, email, fullname);

        // Redirect to user table page after updating the user
        resp.sendRedirect(req.getContextPath() + "/user");
        return;
    }

    req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
}

}
