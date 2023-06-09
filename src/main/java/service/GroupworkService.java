package service;

import model.GroupworkModel;
import repository.GroupworkRepository;
import repository.UserRepository;

import java.util.List;

public class GroupworkService {
    private GroupworkRepository groupworkRepository = new GroupworkRepository();

    public List<GroupworkModel> getGroupwork() {
        return groupworkRepository.findGroupwork();
    }

    public boolean insertGroupwork(String name, String start_date, String end_date) {
        return groupworkRepository.insertGroupwork(name, start_date, end_date);
    }
}
