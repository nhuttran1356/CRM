package model;

import java.util.Date;

public class TaskModel {

//"SELECT tasks.id, " +
//        "tasks.name , " +
//        "jobs.name , " +
//        "users.fullname, " +
//        "tasks.start_date , " +
//        "tasks.end_date , " +
//        "status.name " +
//        "FROM tasks " +
//        "INNER JOIN jobs ON tasks.job_id = jobs.id " +
//        "INNER JOIN users ON tasks.user_id = users.id " +
//        "INNER JOIN status ON tasks.status_id = status.id";

    private int id;
    private String name;
    private String nameJob;
    private String nameUser;
    private Date start_date;
    private Date end_date;
    private String nameStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }
}
