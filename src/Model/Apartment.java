package Model;

public class Apartment {
    private int floor;
    private int room;
    private int ownerID;

    public Apartment() {};

    public Apartment(int floor, int room, int ownerID) {
        this.floor = floor;
        this.room = room;
        this.ownerID = ownerID;
    }

    public int getFloor() {return floor;}
    public int getRoom() {return room;}
    public int getOwnerId() {return ownerID;}

    public void setFloor(int floor) {this.floor = floor;}
    public void setRoom (int room) {this.room = room;}
    public void setOwnerId(int ownerID) {this.ownerID = ownerID;}
}