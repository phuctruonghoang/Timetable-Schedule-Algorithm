package it.tdt.edu.vn;

import java.util.ArrayList;
import java.util.List;

public class ProcessRoom {
    public Room[] Room;

    public ProcessRoom(List<Room> ListRoom) {
        Room = new Room[ListRoom.size()];
        Room = ListRoom.toArray(Room);
        bubbleSort(Room);
    }

    public void bubbleSort(Room[] room) {
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

    public List<Room> groupClass(Room[] room) {
        List<Room> Class = new ArrayList<>();
        int i = 0;
        while (i < room.length) {
            if ("LT".equals(room[i].getProperties())) {
                Class.add(room[i]);
            }
            i++;
        }

        return Class;
    }

    public List<Room> groupLab(Room[] room) {
        List<Room> Lab = new ArrayList<>();
        int i = 0;
        while (i < room.length) {
            if ("PM".equals(room[i].getProperties())) {
                Lab.add(room[i]);
            }
            i++;
        }
        return Lab;
    }

    public void greedyRoom(int AmountOfSt, Room[] room, Course c) {
        int IndexOfRoom = room.length - 1;
        int MaxSize = room[IndexOfRoom].getCapacity();
        if (AmountOfSt == MaxSize && room[IndexOfRoom].checkVisition()) {
            c.addRoom(searchRoom(room, AmountOfSt / 2));
            c.addRoom(searchRoom(room, AmountOfSt / 2 + 1));
        } else if (AmountOfSt == MaxSize && !room[IndexOfRoom].checkVisition()) {
            c.addRoom(room[IndexOfRoom]);
        } else if (AmountOfSt > MaxSize) {
            c.addRoom(room[IndexOfRoom]);
            greedyRoom(AmountOfSt - MaxSize, room, c);
        } else {
            c.addRoom(searchRoom(room, AmountOfSt));
        }

    }

    public Room searchRoom(Room[] room, int Capacity) {
        for (int i = 0; i < room.length; i++) {
            if (room[i].getCapacity() >= Capacity && room[i].checkVisition() == false) {
                room[i].visited = 0;
                return room[i];
            }
        }
        return null;
    }

    public Room SearchRoom(Room[] r, int Capacity, Course c){
        if(c.Group.length > 1 && c.ListRoom.isEmpty() ==false){
            for(int i = 0; i > r.length;i++){
                if(!checkRoom(r[i],c) && r[i].visited != 0 && r[i].getCapacity() >= Capacity ){
                    r[i].visited = 0;
                    return r[i];
                }
            }
        }
        return null;
    }

    private boolean checkRoom(Room r, Course c){
        if (r == null) return false;
        int i = 0;
        while (i < c.ListRoom.size()){
            if(r.getIdRoom().equals(c.ListRoom.get(i))){
                return true;
            }
            i++;
        }
        return false;
    }

}
