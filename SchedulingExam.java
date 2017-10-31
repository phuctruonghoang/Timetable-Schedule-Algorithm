package it.tdt.edu.vn;

public class SchedulingExam {

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
            }else{
                int max = 42;
                int i = 0;
                while(i < cs.Team.length){
                    int AmountOfStudent = cs.Team[i].size();
                    if(AmountOfStudent >= max){
                        Room FisrtRoom = PrRoom.searchRoom(Class,AmountOfStudent/2);
                        cs.addRoom(FisrtRoom);
                        Room SecondRoom = PrRoom.searchRoom(Class,AmountOfStudent - AmountOfStudent/2);
                        cs.addRoom(SecondRoom);
                    }
                    Room BestRoom = PrRoom.searchRoom(Lab,AmountOfStudent);
                    if(BestRoom == null)
                        BestRoom = PrRoom.SearchRoom(Lab,AmountOfStudent,cs);
                    if(BestRoom == null)
                        BestRoom = PrRoom.SearchRoom(Lab,AmountOfStudent,cs,0);
                    if(BestRoom != null)
                        cs.addRoom(BestRoom);
                    i++;
                }
                PrRoom.resetRoom(Lab,0);
            }
        }
    }


}
