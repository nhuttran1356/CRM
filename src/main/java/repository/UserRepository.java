package repository;

import config.MysqlConfig;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository {

    public List<UserModel> findByEmailAndPassword(String email, String password) {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();

        try {
            String sql = "select * from users u where u.email = ? and password = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                // Lấy giá trị của cột chỉ định
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);

            }
        } catch (Exception e) {
            System.out.println("Error findByEmailAndPassword: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findByEmailAndPassword: " + e.getMessage());
                }
            }
        }
        return userModelList;
    }

    public List<UserModel> findByRole(String email) {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();

        try {
            String sql = "select role_id from users u where u.email = ? ";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                // Lấy giá trị của cột chỉ định
                userModel.setRoleId(resultSet.getInt("role_id"));
                userModelList.add(userModel);
            }
        } catch (Exception e) {
            System.out.println("Error findByRole: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findByRole: " + e.getMessage());
                }
            }
        }
        return userModelList;
    }
    public List<UserModel> findAll() {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();

        try {
            String sql = "SELECT users.id,\n" +
                    "users.email, \n" +
                    "users.fullname , \n" +
                    "users.avatar, \n"+
                    "roles.name \n" +
                    "FROM users\n" +
                    "INNER JOIN roles ON  users.role_id = roles.id;";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                // Lấy giá trị của cột chỉ định
                userModel.setId(resultSet.getInt(1));
                userModel.setEmail(resultSet.getString(2));
                userModel.setFullname(resultSet.getString(3));
                userModel.setAvatar(resultSet.getString(4));
                userModel.setRoleName(resultSet.getString(5));

                userModelList.add(userModel);
            }
        } catch (Exception e) {
            System.out.println("Error findAllUser: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findAllUser: " + e.getMessage());
                }
            }
        }
        return userModelList;
    }


    public boolean insertUser(String fullname, String email, String password, int role_id) {
        Connection connection = null;
        boolean isSucess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT into users(email,password,fullname,role_id) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setInt(4, role_id);
            //statement.executeUpdate();// neu thanh cong tra ve 1 so khac 0
            isSucess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error insertUser: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối insertUser: " + e.getMessage());
                }
            }

        }
        return isSucess;
    }


    public boolean deleteByIdUser(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "delete from users u where u.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleteById: " + e.getMessage());
        } finally {
            // Try catch chạy xong sẽ chạy vào finally
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối deleteById: " + e.getMessage());
                }
            }

            return isSuccess;
        }
    }
    public boolean updateByIdUser(int id, String newEmail, String newFullName) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "UPDATE users SET email = ?, fullname = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newEmail);
            statement.setString(2, newFullName);
            statement.setInt(3, id);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updateUser: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection in updateUser: " + e.getMessage());
                }
            }
            return isSuccess;
        }
    }


}


