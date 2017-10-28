package it.tdt.edu.vn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapStudent {
    public HashMap<String, List<Students>> HashMashStudent;

    public HashMapStudent() {
        HashMashStudent = new HashMap<>();
    }

    public void addHashMapStudent(String IdCourse, Students st) {
        List<Students> ListSudent = HashMashStudent.get(IdCourse);

        // if list does not exist create it
        if (ListSudent == null) {
            ListSudent = new ArrayList<Students>();
            ListSudent.add(st);
            HashMashStudent.put(IdCourse, ListSudent);
        } else {
            // add if item is not already in list
            if (!ListSudent.contains(st)) {
                ListSudent.add(st);
            }
        }
    }

    public List<Students> getListStdent(String IdStudent) {
        return HashMashStudent.get(IdStudent);
    }
}
