package controller;

import model.*;
import service.GroupworkService;
import service.StatusService;
import service.TaskService;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "TaskController", urlPatterns = {"/task", "/task/add", "/task/delete", "/task/update"})
public class TaskController extends HttpServlet {
    TaskService taskService = new TaskService();
    StatusService statusService = new StatusService();
    UserService userService = new UserService();
    GroupworkService groupworkService = new GroupworkService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        switch (path) {
            case "/task":
                getAllTask(request, response);
                break;
            case "/task/add":
                addTask(request, response);
                break;
            case "/task/delete":
                deleteTask(request, response);
                break;
            case "/task/update":
                updateTask(request, response);
                break;
            default:

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/task":
                getAllTask(request, response);
                break;
            case "/task/add":
                addTask(request, response);
                break;
            case "/task/delete":
                deleteTask(request, response);
                break;
            case "/task/update":
                updateTask(request, response);
                break;
            default:

                break;
        }
    }

    private void getAllTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TaskModel> list = taskService.getTask();
        request.setAttribute("listTask", list);
        request.getRequestDispatcher("task.jsp").forward(request, response);
    }

    private void addTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equalsIgnoreCase("get")) {
            List<StatusModel> listStatus = statusService.getStatus();
            request.setAttribute("list_Status", listStatus);

            List<UserModel> listUser = userService.getAllUser();
            request.setAttribute("listUsers", listUser);

            List<GroupworkModel> listGroupwork = groupworkService.getGroupwork();
            request.setAttribute("listGroup", listGroupwork);
        }

        if (method.equalsIgnoreCase("post")) {
            String projectname = request.getParameter("projectname");
            int user_id = Integer.parseInt(request.getParameter("userworkname"));
            int job_id = Integer.parseInt(request.getParameter("workname"));
            int status_id = Integer.parseInt(request.getParameter("status"));
            String start_date = request.getParameter("start_date");
            String end_date = request.getParameter("end_date");
            System.out.println(projectname + "  " + user_id + " " + job_id + " " + status_id + " " + start_date + " " + end_date);
            taskService.insertTask(projectname, start_date, end_date, user_id, job_id, status_id);

        }

        request.getRequestDispatcher("/task-add.jsp").forward(request, response);

    }

    private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = taskService.deleteRole(id);
    }

    private void updateTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String projectname = req.getParameter("projectname");
            String start_date = req.getParameter("start_date");
            String end_date = req.getParameter("end_date");
            int task_id = Integer.parseInt(req.getParameter("id_update"));

            System.out.println("ten la " + projectname);
            System.out.println("mail la " + start_date);
            System.out.println("id la " + end_date);
            System.out.println("id la " + task_id);
            taskService.updateTask(task_id,projectname,start_date,end_date);

            // Redirect to user table page after updating the user
            resp.sendRedirect(req.getContextPath() + "/task");
            return;
        }

        req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
    }
}



