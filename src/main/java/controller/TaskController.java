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

@WebServlet(name = "TaskController", urlPatterns = {"/task","/task/add"})
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
//            case "/user/delete":
//                deleteUser(req, resp);
//                break;
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
//            case "/user/delete":
//                deleteUser(req, resp);
//                break;
            default:

                break;
        }
    }

    private void getAllTask (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            if( method.equalsIgnoreCase("post")){
                String projectname = request.getParameter("projectname");
                int user_id = Integer.parseInt(request.getParameter("userworkname"));
                int job_id = Integer.parseInt(request.getParameter("workname"));
                int status_id = Integer.parseInt(request.getParameter("status"));
                String start_date = request.getParameter("start_date");
                String end_date = request.getParameter("end_date");
                System.out.println(projectname + "  " +  user_id + " " + job_id + " " + status_id + " " + start_date + " " + end_date );
                taskService.insertTask(projectname,start_date,end_date,user_id,job_id,status_id );

            }

            request.getRequestDispatcher("/task-add.jsp").forward(request,response);

        }
    }



