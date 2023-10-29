package Model;

import java.sql.Date;

public class Resident {
    private long id;
    private String name;
    private Date birthday;
    private int phoneNumber;
    private String nationality;
    private int floor;
    private int room;
    private String relationship;

    public Resident() {}
    public Resident(long id, String name, Date birthday, int phoneNumber, String nationality, int floor, int room, String relationship) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.floor = floor;
        this.room = room;
        this.relationship = relationship;
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public Date getBirthday() {return birthday;}
    public int getPhoneNumber() {return phoneNumber;}
    public String getNationality() {return nationality;}
    public int getFloor() {return floor;}
    public int getRoom() {return room;}
    public String getRelationship() {return relationship;}

    public void setId(long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setBirthday(Date birthday) {this.birthday = birthday;}
    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setNationality(String nationality) {this.nationality = nationality;}
    public void setFloor(int floor) {this.floor = floor;}
    public void setRoom(int room) {this.room = room;}
    public void setRelationship(String relationship) {this.relationship = relationship;}

    @Override
    public String toString() {
        return name + " (Tầng " + floor + ", phòng" + room + ", " + relationship + ")";
    }
}
