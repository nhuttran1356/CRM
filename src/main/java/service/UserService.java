package service;

import model.UserModel;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public List<UserModel> getAllUser() {
        return userRepository.findAll();
    }

    public boolean insertUser(String email, String password, String fullname, int role_id) {
        return userRepository.insertUser(fullname, email, password, role_id);
    }
    public boolean deleteUser(int id){
        return userRepository.deleteByIdUser(id);
    }
    public boolean updateUser(int id, String newEmail, String newFullName){
        return userRepository.updateByIdUser(id, newEmail, newFullName);
    }
}