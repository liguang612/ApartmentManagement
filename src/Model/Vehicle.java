package Model;

public class Vehicle {
    private int licensePlates;
    private int floor;
    private int room;
    private int type;

    public Vehicle(int licensePlates, int floor, int room, int type) {
        this.licensePlates = licensePlates;
        this.floor = floor;
        this.room = room;
        this.type = type;
    }

    public int getLicensePlates() {return licensePlates;}
    public int getFloor() {return floor;}
    public int getRoom() {return room;}
    public int getType() {return type;}

    public void setLicensePlates(int licensePlates) {this.licensePlates = licensePlates;}
    public void setFloor(int floor) {this.floor = floor;}
    public void setRoom(int room) {this.room = room;}
    public void setType(int type) {this.type = type;}
}
