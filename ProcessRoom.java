package it.tdt.edu.vn;

import java.util.List;

public class ProcessRoom {
    public Room[] Room;

    public ProcessRoom(List<Room> ListRoom) {
        Room = new Room[ListRoom.size()];
        Room = ListRoom.toArray(Room);
        bubbleSort(Room);
    }

    private void bubbleSort(Room[] room) {
        int n = room.length;
        Room r;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (room[j - 1].getCapacity() > room[j].getCapacity()) {
                    //swap elements
                    r = room[j - 1];
                    room[j - 1] = room[j];
                    room[j] = r;
                }

            }
        }
    }


}
