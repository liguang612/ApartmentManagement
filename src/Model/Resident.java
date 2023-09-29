package Model;

import java.util.Date;

public class Resident {
    private int id;
    private String name;
    private Date birthday;
    private int phoneNumber;
    private String nationality;
    private int status;
    private int floor;
    private int room;

    public Resident() {}
    public Resident(int id, String name, Date birthday, int phoneNumber, String nationality, int status, int floor, int room) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.status = status;
        this.floor = floor;
        this.room = room;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public Date getBirthday() {return birthday;}
    public int getPhoneNumber() {return phoneNumber;}
    public String getNationality() {return nationality;}
    public int getStatus() {return status;}
    public int getFloor() {return floor;}
    public int getRoom() {return room;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setBirthday(Date birthday) {this.birthday = birthday;}
    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setNationality(String nationality) {this.nationality = nationality;}
    public void setStatus(int status) {this.status = status;}
    public void setFloor(int floor) {this.floor = floor;}
    public void setRoom(int room) {this.room = room;}
}
