package it.tdt.edu.vn;

public class SchedulingExam {


    public void scheduleFinalTest(HashMapCourse HashCourse,Room[] Class,ProcessRoom PrRoom){
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
           if(cs.Group.length == 1){
               int AmountOfSt = cs.Group[0].size();
               PrRoom.greedyRoom(AmountOfSt,Class, cs);
           }else {
                int i = 0;
                while(i < cs.Group.length){
                    int AmountOfSt = cs.Group[i].size();
                    Room BestRoom = PrRoom.searchRoom(Class,AmountOfSt);
                    if(BestRoom == null)
                        BestRoom = PrRoom.SearchRoom(Class,AmountOfSt,cs);
                    cs.addRoom(BestRoom);
                    i++;
                }
           }
        }
    }


    public void resetRoom(Room[] r, int flag) {
        if (flag == 1) {
            int i = 0;
            while (i < r.length) {
                if ("LT".equals(r[i].getProperties())) {
                    r[i].visited = 1;
                }
            }
        } else {
            int i = 0;
            while (i < r.length) {
                r[i].visited = 1;
            }
        }
    }

}
