package it.tdt.edu.vn;

public class Room {
    private String IdRoom;
    private int Capacity;
    private String Properties;
    private String Note;
    public boolean visited = false;
    /*public Room() {
        IdRoom = "";
        Capacity = 0;
        Properties = "";
        Note = "";
    }*/

    public String getNote() {
        return Note;
    }


    public Room(String IdRoom, int Capacity, String Properties, String Note) {
        this.Note = Note;
        this.IdRoom = IdRoom;
        this.Capacity = Capacity;
        this.Properties = Properties;

    }

    public String getIdRoom() {
        return IdRoom;
    }


    public int getCapacity() {
        return Capacity;
    }


    public String getProperties() {
        return Properties;
    }

    public void setProperties(String properties) {
        this.Properties = properties;
    }


}
