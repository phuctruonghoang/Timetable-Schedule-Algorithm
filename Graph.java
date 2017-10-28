package it.tdt.edu.vn;

import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {

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


    public static void main(String[] args) throws IOException {
        Processfile pr = new Processfile();
        pr.readXLSXFileDKMH();
        pr.readXLSXFileDSPT();
        //pr.writeXLSXFile();
    }
}
