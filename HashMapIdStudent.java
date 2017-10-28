package it.tdt.edu.vn;

import java.util.HashMap;

public class HashMapIdStudent {
    public HashMap<String, Students> HashMapIdStudent;

    public HashMapIdStudent() {
        HashMapIdStudent = new HashMap<>();
    }

    public void addStudent2HashMapIdStudent(Students st,Course Cs){
        if(checkExistence(st.getId()) == false){
            HashMapIdStudent.put(st.getId(),st);
        }
        else{
            addCourseStudent(st.getId(),Cs);
        }
    }

    public boolean checkExistence(String IdStudent) {
        if (HashMapIdStudent.containsKey(IdStudent)) {
            return true;
        }
        return false;

    }

    private void addCourseStudent(String IdStudent, Course Cs) {
        HashMapIdStudent.get(IdStudent).addCourse(Cs);
    }

}
