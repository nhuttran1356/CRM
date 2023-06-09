package service;

import model.RoleModel;
import model.TaskModel;
import repository.TaskRepository;
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TaskService {
    private TaskRepository taskRepository = new TaskRepository();

    public List<TaskModel> getTask() {
        return taskRepository.findTask();

    }
    public boolean insertTask(String name, String start_date, String end_date, int user_id, int job_id, int status_id){
        return taskRepository.insertTask(name, start_date , end_date, user_id, job_id, status_id);

    }

}