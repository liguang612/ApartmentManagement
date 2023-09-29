package Model;

import java.util.Date;

public class Activity {
    private int id;
    private int residentId;
    private int status;
    private Date timeIn;
    private Date timeOut;
    private String note;

    public Activity() {}
    public Activity(int id, int residentId, int status, Date timeIn, Date timeOut) {
        this.id = id;
        this.residentId = residentId;
        this.status = status;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public int getId() {return id;}
    public int getResidentId() {return residentId;}
    public int getStatus() {return status;}
    public Date getTimeIn() {return timeIn;}
    public Date getTimeOut() {return timeOut;}
    public String getNote() {return note;}

    public void setId(int id) {this.id = id;}
    public void setResidentId(int residentId) {this.residentId = residentId;}
    public void setStatus(int status) {this.status = status;}
    public void setTimeIn(Date timeIn) {this.timeIn = timeIn;}
    public void setTimeOut(Date timeOut) {this.timeOut = timeOut;}
    public void setNote(String note) {this.note = note;}
}
