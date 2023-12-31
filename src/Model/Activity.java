package Model;

import java.util.Date;

public class Activity {
    private int id;
    private Resident resident;
    private int status;
    private Date timeIn;
    private Date timeOut;
    private String note;

    public Activity() {}
    public Activity(int id, Resident resident, int status, Date timeIn, Date timeOut) {
        this.id = id;
        this.resident = resident;
        this.status = status;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public int getId() {return id;}
    public Resident getResident() {return resident;}
    public int getStatus() {return status;}
    public Date getTimeIn() {return timeIn;}
    public Date getTimeOut() {return timeOut;}
    public String getNote() {return note;}

    public void setId(int id) {this.id = id;}
    public void setResidentId(Resident resident) {this.resident = resident;}
    public void setStatus(int status) {this.status = status;}
    public void setTimeIn(Date timeIn) {this.timeIn = timeIn;}
    public void setTimeOut(Date timeOut) {this.timeOut = timeOut;}
    public void setNote(String note) {this.note = note;}
}
