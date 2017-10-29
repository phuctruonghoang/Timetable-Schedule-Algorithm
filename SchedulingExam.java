package it.tdt.edu.vn;

public class SchedulingExam {


    public void scheduleFinalTest(HashMapCourse HashCourse,Room[] Class,ProcessRoom PrRoom){
        for (String tmp : HashCourse.HashMapCourse.keySet()) {
            Course cs = HashCourse.HashMapCourse.get(tmp);
           if(cs.Group.length == 1){
               int AmountOfSt = cs.Group[0].size();
               PrRoom.greedyRoom(AmountOfSt,Class, cs);
           }else if(cs.Group.length > 1) {
                int i = 0;
                while(i < cs.Group.length){
                    int AmountOfSt = cs.Group[i].size();
                    Room BestRoom = PrRoom.searchRoom(Class,AmountOfSt);
                    if(BestRoom == null) {
                        System.out.println("-_-");
                        BestRoom = PrRoom.SearchRoomI(Class, AmountOfSt, cs);
                    }
                    if (BestRoom != null)
                        cs.addRoom(BestRoom);
                    i++;
                }
           }
            PrRoom.resetRoom(Class,1);
        }
    }




}
