package it.tdt.edu.vn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Students {
    private String idCourse;
    private String nameCourse;
    private String group;
    private String team;
    private String class_;
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    public LinkedList<Course> ListCourse;
    public List<Integer> ListHour;
    public HashMap<String, String> TableRoom;

    public Students() {
        this.idCourse = "";
        this.nameCourse = "";
        this.group = "";
        this.team = "";
        this.class_ = "";
        this.id = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";

    }

    public Students(String idCourse, String nameCourse, String group, String team, String class_, String id, String firstName,
                    String lastName, String email) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.group = group;
        this.team = team;
        this.class_ = class_;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        ListCourse = new LinkedList<>();
        ListCourse.add(new Course(idCourse, nameCourse));
        ListHour = new ArrayList<>();
        TableRoom = new HashMap<>();
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void addCourse(Course Course) {
        ListCourse.add(Course);
    }

    public void setCourse(String IDCourse) {
        ListCourse.add(new Course(IDCourse, ""));
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public String getGroup() {
        return group;
    }

    public String getTeam() {
        return team;
    }

    public String getClass_() {
        return class_;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void addRoom(String nameCourse, String Room) {
        TableRoom.put(nameCourse,Room);
    }

    public void addHour(Integer Hour) {
        ListHour.add(Hour);
    }
}
