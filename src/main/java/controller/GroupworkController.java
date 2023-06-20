package controller;

import model.GroupworkModel;
import model.RoleModel;
import service.GroupworkService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GroupworkController", urlPatterns = {"/groupwork", "/groupwork/add", "/groupwork/delete", "/groupwork/update"})
public class GroupworkController extends HttpServlet {
    GroupworkService groupworkService = new GroupworkService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/groupwork":
                getAllGroup(request, response);
                break;
            case "/groupwork/add":
                addGroup(request, response);
                break;
            case "/groupwork/delete":
                deleteGroup(request, response);
                break;
            case "/groupwork/update":
                updateGroup(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/groupwork":
                getAllGroup(request, response);
                break;
            case "/groupwork/add":
                addGroup(request, response);
                break;
            case "/groupwork/delete":
                deleteGroup(request, response);
                break;
            case "/groupwork/update":
                updateGroup(request, response);
                break;
        }
    }

    protected void addGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String jobname = request.getParameter("jobname");
            String start_date = request.getParameter("start_date");
            String end_date = request.getParameter("end_date");
//            System.out.println(jobname + start_date + end_date);
            groupworkService.insertGroupwork(jobname, start_date, end_date);
        }

        request.getRequestDispatcher("/groupwork-add.jsp").forward(request, response);
    }

    protected void getAllGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GroupworkModel> list = groupworkService.getGroupwork();
        request.setAttribute("listGroup", list);
        request.getRequestDispatcher("groupwork.jsp").forward(request, response);
    }

    private void deleteGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = groupworkService.deleteGroupwork(id);
    }

    private void updateGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String jobname = req.getParameter("jobname");
            String start_date = req.getParameter("start_date");
            String end_date = req.getParameter("end_date");
            int group_id = Integer.parseInt(req.getParameter("id_update"));
//            System.out.println("ten la " + jobname);
//            System.out.println("start_date la " + start_date);
//            System.out.println("end_date la " + end_date);
//            System.out.println("id la " + group_id);
            groupworkService.updateUser(group_id, jobname, start_date, end_date);

            // Redirect to user table page after updating the user
            resp.sendRedirect(req.getContextPath() + "/groupwork");
            return;
        }

        req.getRequestDispatcher("/groupwork-update.jsp").forward(req, resp);

    }
}
