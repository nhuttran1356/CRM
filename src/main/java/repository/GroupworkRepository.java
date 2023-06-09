package repository;

import config.MysqlConfig;
import model.GroupworkModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupworkRepository {
    public boolean insertGroupwork(String name, String start_date, String end_date) {
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT into jobs (name, start_date, end_date) values(?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, start_date);
            statement.setString(3, end_date);
            //statement.executeUpdate();// neu thanh cong tra ve 1 so khac 0
            isSucess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error insertGroupwork: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối insertGroupwork: " + e.getMessage());
                }
            }

        }
        return isSucess;
    }

    public List<GroupworkModel> findGroupwork() {
        Connection connection = null;
        List<GroupworkModel> groupworkModelList = new ArrayList<>();

        try {
            String sql = "select * from jobs";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Duyệt từng dòng dữ liệu
                GroupworkModel groupworkModel = new GroupworkModel();
                // Lấy giá trị của cột chỉ định

                groupworkModel.setId(resultSet.getInt(1));
                groupworkModel.setName(resultSet.getString(2));
                groupworkModel.setStartDate(resultSet.getDate(3));
                groupworkModel.setEndDate(resultSet.getDate(4));

                groupworkModelList.add(groupworkModel);
            }
        } catch (Exception e) {
            System.out.println("Error findGroupwork: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findGroupwork: " + e.getMessage());
                }
            }

        }
        return groupworkModelList;
    }
}
