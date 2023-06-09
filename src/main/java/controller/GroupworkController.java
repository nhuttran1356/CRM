package controller;

import model.GroupworkModel;
import model.RoleModel;
import service.GroupworkService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GroupworkController", urlPatterns = {"/groupwork", "/groupwork/add"})
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

}
