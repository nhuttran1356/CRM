package repository;

import config.MysqlConfig;
import model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public boolean insertRole(String role, String des) {
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT INTO roles( name, description ) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role);
            statement.setString(2, des);

            isSucess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error insertRole: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối insertRole: " + e.getMessage());
                }
            }

        }
        return isSucess;
    }
    public List<RoleModel> findRole() {
        Connection connection = null;
        List<RoleModel> roleModelList = new ArrayList<>();

        try {
            String sql = "select * from roles r ";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Duyệt từng dòng dữ liệu
                RoleModel roleModel = new RoleModel();
                // Lấy giá trị của cột chỉ định

                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));

                roleModelList.add(roleModel);
            }
        } catch (Exception e) {
            System.out.println("Error findRole: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findRole: " + e.getMessage());
                }
            }

        }
        return roleModelList;
    }
    public boolean deleteByIdRole(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "delete from roles r where r.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleteByIdRole: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối deleteByIdRole: " + e.getMessage());
                }
            }

            return isSuccess;
        }
    }
    public boolean updateByIdRole(int id, String name, String description) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "UPDATE roles SET name = ?, description = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, id);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updateByIdRole: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection in updateByIdRole: " + e.getMessage());
                }
            }
            return isSuccess;
        }
    }
}
