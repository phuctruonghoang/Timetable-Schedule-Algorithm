package it.tdt.edu.vn;

public class Room {
    private String IdRoom;
    private int Capacity;
    private String Properties;
    private String Note;

    public Room() {
        IdRoom = "";
        Capacity = 0;
        Properties = "";
        Note = "";
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
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

    public void setIdRoom(String idRoom) {
        this.IdRoom = idRoom;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }

    public String getProperties() {
        return Properties;
    }

    public void setProperties(String properties) {
        this.Properties = properties;
    }


}
