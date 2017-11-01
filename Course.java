package it.tdt.edu.vn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Course {
    public String nameCourse;
    public String idCourse;
    public LinkedList<String> NeighborVertex;
    public LinkedList<Students> ListStudent;
    public int visited;
    private int NumberOfGroup = 0;
    private int NumberOfTeam = 0;
    private List<String> ListNumberOfGroup;
    public LinkedList<Students>[] Group;
    public LinkedList<Students>[] Team;
    private String tmpTeam = "";
    public List<Room> ListRoom;
    private int Hour = -1;
    public boolean inDead;
    public boolean Choose;
    public Course() {
        nameCourse = "";
        idCourse = "";
    }

    public Course(String idCourse, String nameCourse) {
        this.nameCourse = nameCourse;
        this.idCourse = idCourse;
        NeighborVertex = new LinkedList<>();
        ListStudent = new LinkedList<>();
        visited = -1;
        ListNumberOfGroup = new ArrayList<>();
        ListRoom = new ArrayList<>();
        inDead = false;
        Choose = false;
    }

    public void addStudent(Students Student) {
        if (Student.getTeam() != null && !tmpTeam.equals(Student.getTeam())) {
            tmpTeam = Student.getTeam();
            NumberOfTeam += 1;
        }
        if (ListNumberOfGroup.contains(Student.getGroup()) == false) {
            ListNumberOfGroup.add(Student.getGroup());
        }
        ListStudent.add(Student);
    }

    public int getHour() {
        return this.Hour;
    }

    public void setHour(int hour) {
        this.Hour = hour;
    }

    public String getNameCourse() {
        return nameCourse;
    }


    public String getIdCourse() {
        return idCourse;
    }


    public void connectVertex(String Vertex) {
        if (NeighborVertex.isEmpty()) {
            NeighborVertex.add(Vertex);
        } else if (!checkConnection(Vertex)) {
            NeighborVertex.add(Vertex);
        }

    }

    private boolean checkConnection(String Vertex) {
        for (int i = 1; i < NeighborVertex.size(); i++) {
            if (Vertex.equals(NeighborVertex.get(i))) {
                return true;
            }
        }
        return false;
    }

    public int getWeight() {
        return NeighborVertex.size();
    }

    public void addGroup() {
        NumberOfGroup = ListNumberOfGroup.size();
        Group = new LinkedList[NumberOfGroup];
        int count = 0;
        Group[0] = new LinkedList<>();
        Group[0].add(ListStudent.get(0));
        int i = 1;
        while (i < ListStudent.size()) {
            if (Group[count].get(0).getGroup().equals(ListStudent.get(i).getGroup())) {
                Group[count].add(ListStudent.get(i));
            } else {
                count++;
                Group[count] = new LinkedList<>();
                Group[count].add(ListStudent.get(i));
            }
            i++;
        }
    }

    public void addTeam() {
        Team = new LinkedList[NumberOfTeam];
        if ("".equals(ListStudent.get(0).getTeam()))
            return;
        else {
            int count = 0;
            Team[0] = new LinkedList<>();
            Team[0].add(ListStudent.get(0));
            int i = 1;
            while (i < ListStudent.size()) {
                if (Team[count].get(0).getTeam().equals(ListStudent.get(i).getTeam())) {
                    Team[count].add(ListStudent.get(i));
                } else {
                    count++;
                    Team[count] = new LinkedList<>();
                    Team[count].add(ListStudent.get(i));
                }
                i++;
            }
        }
    }

    public void addRoom(Room r) {
        /*if (r == null)
            return;*/
        ListRoom.add(r);
    }

}
