package Model;

public class Fee {
    private int id;
    private String name;
    private int cost;
    private boolean mandatory;
    private String expiration;
    private int cycle;

    public Fee() {}
    public Fee(int id, String name, int cost, boolean mandatory, int cycle, String expiration) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.mandatory = mandatory;
        this.expiration = expiration;
        this.cycle = cycle;
    }

    public int getCycle() {return cycle;}
    public int getId() {return id;}
    public String getName() {return name;}
    public int getCost() {return cost;}
    public boolean getMandatory() {return mandatory;}
    public String getExpirationDate() {return expiration;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setCost(int cost) {this.cost = cost;}
    public void setMandatory(boolean mandatory) {this.mandatory = mandatory;}
    public void setDate(String expiration) {this.expiration = expiration;}
    public void setExpirationDate(int cycle) {this.cycle = cycle;}
}