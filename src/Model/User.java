package Model;

public class User {
    private int id;
    private String name;
    private String birthday;
    private String img;
    private int phoneNumber;

    public User() {}
    public User(int id, String name, String birthday, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getBirthday() {return birthday;}
    public String getImg() {return img;}
    public int getPhoneNumber() {return phoneNumber;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setBirthday(String birthday) {this.birthday = birthday;}
    public void setImg(String img) {this.img = img;}
    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}
}
