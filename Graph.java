package it.tdt.edu.vn;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    private List<String> BestSolution;

    public void setWeight(HashMapIdStudent HashStudent, HashMapCourse HashCourse) {
        for (String tmp : HashStudent.HashMapIdStudent.keySet()) {
            Students st = HashStudent.HashMapIdStudent.get(tmp);
            {
                for (int i = 0; i < st.ListCourse.size(); i++) {
                    String vertex = st.ListCourse.get(i).getIdCourse();
                    for (int j = i + 1; j < st.ListCourse.size(); j++) {
                        String neighbor = st.ListCourse.get(j).getIdCourse();
                        HashCourse.addNeighbor(vertex, neighbor);
                    }
                }
            }
        }

    }

    public List<Integer> arrangeWeight(HashMapCourse HashCourse) {
        List<Integer> ListWeight = new ArrayList<>();
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
            ListWeight.add(cs.getWeight());
        }
        Collections.sort(ListWeight);
        Collections.reverse(ListWeight);
        return ListWeight;
    }

    public Pair[] matchWeight2Course(List<Integer> ListWeight, HashMapCourse HashCourse) {
        Pair<Integer, String>[] Pair = new Pair[HashCourse.HashMapCourse.size()];
        for (int i = 0; i < ListWeight.size(); i++) {
            int Weight = (int) ListWeight.get(i);
            String Value = matchWeight(HashCourse, Weight);
            Pair[i] = new Pair<>(Weight, Value);
        }
        return Pair;
    }

    private String matchWeight(HashMapCourse HashCourse, int Weight) {
        String res = "";
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
            if (Weight == cs.getWeight() && cs.visited == -1) {
                res += cs.getIdCourse();
                cs.visited = 0;
                break;
            }
        }
        return res;
    }

    public void buildConnection(boolean flag, HashMapCourse HashCourse, ProcessTime PrTime, SchedulingExam Schedule) {
        BestSolution = Schedule.BestSolution;
        if (!flag) {
            int Hour = 0;
            int i = 0;
            while (i < BestSolution.size()) {
                HashCourse.HashMapCourse.get(BestSolution.get(i)).setHour(Hour);
                Hour++;
                if (Hour >= PrTime.MidTermTest.length) {
                    Hour = 0;
                }
                i++;
            }
        } else {
            int Hour = 0;
            int i = 0;
            while (i < BestSolution.size()) {
                HashCourse.HashMapCourse.get(BestSolution.get(i)).setHour(Hour);
                Hour++;
                if (Hour >= PrTime.FinalTest.length) {
                    Hour = 0;
                }
                i++;
            }
        }
    }

    public void GraphScheduling(HashMapCourse HashCourse, ProcessRoom ExamRoom, Room[] room, SchedulingExam Schedule, ProcessTime Prtime, boolean flag) {
        buildConnection(flag, HashCourse, Prtime, Schedule);
        List<Room> RoomLab = ExamRoom.groupLab(room);
        List<Room> RooomClass = ExamRoom.groupClass(room);
        Room[] Class = converseArray(RooomClass);
        Room[] Lab = converseArray(RoomLab);

        ExamRoom.bubbleSort(Class);
        ExamRoom.bubbleSort(Lab);
        if (flag == true) {
            Schedule.scheduleFinalTest(HashCourse, Class, ExamRoom);
        } else {
            Schedule.scheduleMidTermTest(HashCourse, Class, Lab, ExamRoom);
        }


    }

    private Room[] converseArray(List<Room> room) {
        Room[] Room = new Room[room.size()];
        int i = 0;
        while (i < room.size()) {
            Room[i] = room.get(i);
            i++;
        }
        return Room;
    }

    public void processTimeTable(HashMapCourse HashCourse, HashMapIdStudent HashStudent) {
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
            int IndexOfRoom = 0;
            int count = 0;
            int i = 0;

            while (i < cs.ListStudent.size()) {
                if (IndexOfRoom == cs.ListRoom.size()) {
                    IndexOfRoom = 0;
                }
                HashStudent.HashMapIdStudent.get(cs.ListStudent.get(i).getId()).addHour(cs.getHour());
                HashStudent.HashMapIdStudent.get(cs.ListStudent.get(i).getId()).addRoom(cs.nameCourse, cs.ListRoom.get(IndexOfRoom).getIdRoom());

                if (count == cs.ListRoom.get(IndexOfRoom).getCapacity()) {
                    count = 0;
                    IndexOfRoom++;
                }
                i++;
            }
        }
    }

}


