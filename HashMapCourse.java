package it.tdt.edu.vn;

import java.util.HashMap;
import java.util.List;

public class HashMapCourse {
    public HashMap<String, Course> HashMapCourse;


    public HashMapCourse() {
        HashMapCourse = new HashMap<>();
    }

    public void addHashMapCourse(Course c, Students IdStudent) {
        if (!HashMapCourse.containsKey(c.idCourse)) {
            HashMapCourse.put(c.idCourse, c);
        } else {
            addCourseStudent(c.idCourse, IdStudent);
        }
    }

    private void addCourseStudent(String IdCourse, Students IdStudent) {
        HashMapCourse.get(IdCourse).addStudent(IdStudent);
    }


    public void addNeighbor(String Vertex, String Neighbor) {
        HashMapCourse.get(Vertex).connectVertex(Neighbor);
        HashMapCourse.get(Neighbor).connectVertex(Vertex);
    }


    public List<String> getListNeighborVertex(String IdCourse) {
        return HashMapCourse.get(IdCourse).NeighborVertex;
    }

    public int getWeight(String IdCourse) {
        return HashMapCourse.get(IdCourse).NeighborVertex.size();
    }

    public void printElement() {
        int count = 0;
        for (String c : HashMapCourse.keySet()) {
            count++;
            String key = c.toString();
            String value = HashMapCourse.get(c).idCourse.toString();
            System.out.println(key + " " + value);
        }
        System.out.println(count);
    }

    void()
    {
        
    }


}
