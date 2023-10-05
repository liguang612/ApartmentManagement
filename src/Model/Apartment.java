package Model;

public class Apartment {

    private int id;
    private int floor, room;
    private float area;
    private String ownerName, ownerPhone;

    public Apartment() {
        
    }

    public Apartment(int id, int floor, int room, float area, String ownerName, String ownerPhone) {
        this.id = id;
        this.floor = floor;
        this.room = room;
        this.area = area;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }
    
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
    
}