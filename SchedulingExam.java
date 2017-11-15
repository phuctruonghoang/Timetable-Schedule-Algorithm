package it.tdt.edu.vn;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SchedulingExam {
    public List<String> BestSolution;
    private List<String> Dead;

    public SchedulingExam() {
        BestSolution = new ArrayList<>();
        Dead = new ArrayList<>();
    }

    // add phong cho thi cuoi ky
    public void scheduleFinalTest(HashMapCourse HashCourse, Room[] Class, ProcessRoom PrRoom) {
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
            if (cs.Group.length == 1) {
                int AmountOfSt = cs.Group[0].size();
                PrRoom.greedyRoom(AmountOfSt, Class, cs);
            } else {
                int i = 0;
                while (i < cs.Group.length) {
                    int AmountOfSt = cs.Group[i].size();
                    Room BestRoom = PrRoom.searchRoom(Class, AmountOfSt);
                    if (BestRoom == null) {
                        BestRoom = PrRoom.SearchRoom(Class, AmountOfSt, cs);
                    } else
                        cs.addRoom(BestRoom);
                    i++;
                }
            }
            PrRoom.resetRoom(Class, 1);
        }
    }

    // add phong cho thi giua ky
    public void scheduleMidTermTest(HashMapCourse HashCourse, Room[] Class, Room[] Lab, ProcessRoom PrRoom) {
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
            if (cs.Team.length <= 0) {
                if (cs.Group.length == 1) {
                    int AmountOfSt = cs.Group[0].size();
                    PrRoom.greedyRoom(AmountOfSt, Class, cs);
                } else {
                    int i = 0;
                    while (i < cs.Group.length) {
                        int AmountOfSt = cs.Group[i].size();
                        Room BestRoom = PrRoom.searchRoom(Class, AmountOfSt);
                        if (BestRoom == null) {
                            BestRoom = PrRoom.SearchRoom(Class, AmountOfSt, cs);
                        } else
                            cs.addRoom(BestRoom);
                        i++;
                    }
                }
                PrRoom.resetRoom(Class, 1);
            } else {
                int max = 42;
                int i = 0;
                while (i < cs.Team.length) {
                    int AmountOfStudent = cs.Team[i].size();
                    if (AmountOfStudent >= max) {
                        Room FisrtRoom = PrRoom.searchRoom(Lab, AmountOfStudent / 2);
                        cs.addRoom(FisrtRoom);
                        Room SecondRoom = PrRoom.searchRoom(Lab, AmountOfStudent - AmountOfStudent / 2);
                        cs.addRoom(SecondRoom);
                    }
                    Room BestRoom = PrRoom.searchRoom(Lab, AmountOfStudent);
                    if (BestRoom == null)
                        BestRoom = PrRoom.SearchRoom(Lab, AmountOfStudent, cs);
                    if (BestRoom == null)
                        BestRoom = PrRoom.SearchRoom(Lab, AmountOfStudent, cs, 0);
                    if (BestRoom != null)
                        cs.addRoom(BestRoom);
                    i++;
                }
                PrRoom.resetRoom(Lab, 0);
            }
        }
    }

    public List<String> loadData(Pair<Integer, String>[] Pr, int Start, HashMapCourse HashCourse) {
        if (Start >= Pr.length && BestSolution.size() < Pr.length) {
            BestSolution.addAll(Dead);
            return BestSolution;

        }

        Course cs = HashCourse.HashMapCourse.get(Pr[Start].getValue());
        if (cs.Choose == false && cs.inDead == false) {
            BestSolution.add(cs.getIdCourse());
            int i = 0;
            while (i < cs.NeighborVertex.size()) {
                String Neighbor = cs.NeighborVertex.get(i);
                if (check(Dead, Neighbor) == true) {
                    Dead.add(Neighbor);
                    HashCourse.HashMapCourse.get(Neighbor).inDead = true;
                }
                i++;
            }
        }
        return loadData(Pr, Start + 1, HashCourse);
    }

    private boolean check(List<String> Dead, String value) {
        return Dead.stream().noneMatch((list) -> (list.equals(value)));
    }
}