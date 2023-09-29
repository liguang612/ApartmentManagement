package Model;

import java.util.Date;

public class Fee {
    private int id;
    private String name;
    private int cost;
    private boolean mandatory;
    private Date expiration;

    public Fee() {}
    public Fee(int id, String name, int cost, boolean mandatory, Date expiration) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.mandatory = mandatory;
        this.expiration = expiration;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public int getCost() {return cost;}
    public boolean getMandatory() {return mandatory;}
    public Date getDate() {return expiration;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCost(int cost) {this.cost = cost;}
    public void setMandatory(boolean mandatory) {this.mandatory = mandatory;}
    public void setDate(Date expiration) {this.expiration = expiration;}
}