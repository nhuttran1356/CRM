package controller;

import model.RoleModel;
import model.UserModel;
import service.RoleService;

import javax.management.relation.Role;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleController", urlPatterns = {"/role", "/role/add","/role/delete", "/role/update"})
public class RoleController extends HttpServlet {
    RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        switch (path) {
            case "/role":
                getAllRole(request, response);
                break;
            case "/role/add":
                addRole(request, response);
                break;
            case "/role/delete":
                deleteRole(request, response);
                break;
            case "/role/update":
                updateRole(request,response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/role":
                getAllRole(request, response);
                break;
            case "/role/add":
                addRole(request, response);
                break;
            case "/role/delete":
                deleteRole(request, response);
                break;
            case "/role/update":
                updateRole(request,response);
                break;
        }
    }

    private void getAllRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RoleModel> list = roleService.getRole();
        request.setAttribute("listRoles", list);
        request.getRequestDispatcher("role-table.jsp").forward(request, response);
    }

    private void addRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if (method.equalsIgnoreCase("post")) {
            String role = request.getParameter("role");
            String des = request.getParameter("des");
            roleService.addRole(role, des);
        }
        request.getRequestDispatcher("/role-add.jsp").forward(request, response);
    }

    private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isSuccess = roleService.deleteRole(id);
    }

    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int role_id = Integer.parseInt(request.getParameter("id_update"));
            System.out.println("name " + name + " des " + description + " id " + role_id);
            roleService.updateRole(role_id, name, description);

            response.sendRedirect(request.getContextPath() + "/role");
            return;
        }
        request.getRequestDispatcher("/role-update.jsp").forward(request, response);


    }
}
