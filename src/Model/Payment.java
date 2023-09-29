package Model;

import java.util.Date;

public class Payment {
    private int floor;
    private int room;
    private int feeId;
    private int number;
    private Date timeValidate;

    public Payment() {}
    public Payment(int floor, int room, int feeId, int number, Date timeValidate) {
        this.floor = floor;
        this.room = room;
        this.feeId = feeId;
        this.number = number;
        this.timeValidate = timeValidate;
    }

    public int getFloor() {return floor;}
    public int getRoom() {return room;}
    public int getFeeId() {return feeId;}
    public int getNumber() {return number;}
    public Date getTimeValidate() {return timeValidate;}

    public void setFloor(int floor) {this.floor = floor;}
    public void setRoom(int room) {this.room = room;}
    public void setFeeId(int feeId) {this.feeId = feeId;}
    public void setNumber(int number) {this.number = number;}
    public void setTimeValidate(Date timeValiDate) {this.timeValidate = timeValiDate;}
}
