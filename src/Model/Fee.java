package Model;

public class Fee {
    private int id;
    private String name;
    private int cost;
    private boolean mandatory;
    private long expiration;
    private int cycle;

    public Fee() {}
    public Fee(int id, String name, int cost, boolean mandatory, long expiration, int cycle) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.mandatory = mandatory;
        this.expiration = expiration;
        this.cycle = cycle;
    }

    public int getCycle() {
        return cycle;
    }
    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public int getCost() {return cost;}
    public boolean getMandatory() {return mandatory;}
    public long getDate() {return expiration;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCost(int cost) {this.cost = cost;}
    public void setMandatory(boolean mandatory) {this.mandatory = mandatory;}
    public void setDate(long expiration) {this.expiration = expiration;}
}