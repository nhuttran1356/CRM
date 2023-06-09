package repository;

import config.MysqlConfig;
import model.StatusModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
    public List<StatusModel> findStatus(){
        Connection connection = null;
        List<StatusModel> statusModelList = new ArrayList<>();

        try {
            String sql = "select * from status s ";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Duyệt từng dòng dữ liệu
                StatusModel statusModel = new StatusModel();
                // Lấy giá trị của cột chỉ định

                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));


                statusModelList.add(statusModel);
            }
        } catch (Exception e) {
            System.out.println("Error findStatus: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findStatus: " + e.getMessage());
                }
            }

        }
        return statusModelList;
    }
}
