package Model;

import javax.swing.ImageIcon;

public class User {
    private int id;
    private String name;
    private String birthday;
    private ImageIcon img;
    private String phoneNumber;
    private String abName;

    public User() {}
    public User(int id, String name, String phoneNumber, String birthday, String abName) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.abName = abName;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getBirthday() {return birthday;}
    public ImageIcon getImg() {return img;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getAbName() {return abName;}


    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setBirthday(String birthday) {this.birthday = birthday;}
    public void setImg(ImageIcon img) {this.img = img;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setAbName(String abName) {this.abName = abName;}
}
