package it.tdt.edu.vn;

import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    private boolean exists(int number, int[] array) {
        if (number == -1)
            return true;

        for (int i = 0; i < array.length; i++) {
            if (number == array[i])
                return true;
        }
        return false;
    }
    public void buildConnection(boolean flag, HashMapCourse HashCourse, ProcessTime PrTime, SchedulingExam Schedule) {
        BestSolution = Schedule.BestSolution;
        if (!flag) {
            int i = 0;
            List<Integer> listInt = new ArrayList<>();
            for (int y = 0; y < 70; y++) {
                listInt.add(y + 1);
            }
            Random r = new Random();
            int Low = 1;
            int High = 70;

            while (i < BestSolution.size()) {
                int Result = r.nextInt(High - Low) + Low;
                HashCourse.HashMapCourse.get(BestSolution.get(i)).setHour(listInt.get(Result));
                i++;
            }
        } else {
            int i = 0;
            List<Integer> listInt = new ArrayList<>();
            for (int y = 0; y < 48; y++) {
                listInt.add(y + 1);
            }
            Random r = new Random();
            int Low = 1;
            int High = 48;
            while (i < BestSolution.size()) {
                int Result = r.nextInt(High - Low) + Low;
                HashCourse.HashMapCourse.get(BestSolution.get(i)).setHour(listInt.get(Result));
                i++;
            }
        }
    }

    public void GraphScheduling(HashMapCourse HashCourse, ProcessRoom ExamRoom, Room[] room, SchedulingExam Schedule, ProcessTime Prtime, boolean flag) {
        buildConnection(flag, HashCourse, Prtime, Schedule);
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
            System.out.println(cs.getIdCourse() + " " + cs.getHour());
        }
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
                HashStudent.HashMapIdStudent.get(cs.ListStudent.get(i).getId()).addRoom(cs, cs.ListRoom.get(IndexOfRoom).getIdRoom());

                if (count == cs.ListRoom.get(IndexOfRoom).getCapacity()) {
                    count = 0;
                    IndexOfRoom++;
                }
                i++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String PathDKMH = "D:/PTTK Giai Thuat/Assignment_2017_2018/Assignment_2017_2018/KetQuaDangKyMonHoc.xlsx";
        String PathDSPT = "D:/PTTK Giai Thuat/Assignment_2017_2018/Assignment_2017_2018/DanhSachPhongThi.xlsx";
        Processfile pr = new Processfile(true, PathDKMH, PathDSPT);
    }
    
    void()
    {
        String a = "";
    }

}


