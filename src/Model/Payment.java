package Model;

import java.sql.Date;

public class Payment {
    private int floor;
    private int room;
    private int feeId;
    private int quantity;
    private Date timeValidate;
    private int month;
    private int year;

    public Payment() {}
    public Payment(int floor, int room, int feeId, int quantity, Date timeValidate) {
        this.floor = floor;
        this.room = room;
        this.feeId = feeId;
        this.quantity = quantity;
        this.timeValidate = timeValidate;
        this.month = 0;
        this.year = 0;
    }

    public int getFloor() {return floor;}
    public int getRoom() {return room;}
    public int getFeeId() {return feeId;}
    public int getQuantity() {return quantity;}
    public Date getTimeValidate() {return timeValidate;}
    public int getMonth() {return month;}
    public int getYear() {return year;}

    public void setFloor(int floor) {this.floor = floor;}
    public void setRoom(int room) {this.room = room;}
    public void setFeeId(int feeId) {this.feeId = feeId;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setTimeValidate(Date timeValiDate) {this.timeValidate = timeValiDate;}
    public void setMonth(int month) {this.month = month;}
    public void setYear(int year) {this.year = year;}
}
