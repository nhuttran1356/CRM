package repository;

import config.MysqlConfig;
import model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public boolean insertTask(String name, String start_date, String end_date, int user_id, int job_id, int status_id) {
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT into tasks (name, start_date, end_date, user_id, job_id, status_id)value (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, start_date);
            statement.setString(3, end_date);
            statement.setInt(4, user_id);
            statement.setInt(5, job_id);
            statement.setInt(6, status_id);
            //statement.executeUpdate();// neu thanh cong tra ve 1 so khac 0
            isSucess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error insertTask: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối insertTask: " + e.getMessage());
                }
            }

        }
        return isSucess;
    }

    public List<TaskModel> findTask() {
        Connection connection = null;
        List<TaskModel> taskModelList = new ArrayList<>();

        try {
            String sql = "SELECT tasks.id, " + "tasks.name , " + "jobs.name , " + "users.fullname, " + "tasks.start_date , " + "tasks.end_date , " + "status.name " + "FROM tasks " + "INNER JOIN jobs ON tasks.job_id = jobs.id " + "INNER JOIN users ON tasks.user_id = users.id " + "INNER JOIN status ON tasks.status_id = status.id";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Duyệt từng dòng dữ liệu
                TaskModel taskModel = new TaskModel();
                // Lấy giá trị của cột chỉ định

                taskModel.setId(resultSet.getInt(1));
                taskModel.setName(resultSet.getString(2));
                taskModel.setNameJob(resultSet.getString(3));
                taskModel.setNameUser(resultSet.getString(4));
                taskModel.setStart_date(resultSet.getDate(5));
                taskModel.setEnd_date(resultSet.getDate(6));
                taskModel.setNameStatus(resultSet.getString(7));
                taskModelList.add(taskModel);
            }
        } catch (Exception e) {
            System.out.println("Error findTask: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findTask: " + e.getMessage());
                }
            }

        }
        return taskModelList;

    }
}
