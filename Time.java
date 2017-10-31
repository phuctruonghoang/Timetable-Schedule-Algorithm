package it.tdt.edu.vn;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private List<String> ListCourse;
    private int Hour = 0;

    public Time(String Course) {
        ListCourse = new ArrayList<>();
        ListCourse.add(Course);
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }
}
