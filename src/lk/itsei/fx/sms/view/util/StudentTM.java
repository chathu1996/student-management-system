package lk.itsei.fx.sms.view.util;


public class StudentTM {
    private String sid;
    private String name;
    private String teleNumber;
    private String email;
    private String address;

    public StudentTM() {
    }

    public StudentTM(String sid, String name, String  teleNumber, String email, String address) {
        this.sid = sid;
        this.name = name;
        this.teleNumber = teleNumber;
        this.email = email;
        this.address = address;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeleNumber() {
        return teleNumber;
    }

    public void setTeleNumber(String teleNumber) {
        this.teleNumber = teleNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
