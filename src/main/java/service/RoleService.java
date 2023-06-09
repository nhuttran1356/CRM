package service;

import model.RoleModel;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();

    public List<RoleModel> getRole() {
        return roleRepository.findRole();
    }
    public boolean addRole(String role, String des){
        return roleRepository.insertRole(role, des);
    }
    public boolean deleteRole(int id){
        return roleRepository.deleteByIdRole(id);
    }
    public boolean updateRole(int id, String name, String description){
        return roleRepository.updateByIdRole(id, name, description);
    }
}
