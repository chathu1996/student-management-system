package lk.itsei.fx.sms.dto;

import java.sql.Date;
import java.time.LocalDate;

public class RegistrationDetailsDTO {

    private String rid;
    private String sid;
    private String studentName;
    private String cid;
    private String name;
    private String status;
    private double price;
    private String courseDuration;

    public RegistrationDetailsDTO() {
    }

    public RegistrationDetailsDTO(String rid, String sid, String studentName, String cid, String name, String status, double price, String courseDuration) {
        this.rid = rid;
        this.sid = sid;
        this.studentName = studentName;
        this.cid = cid;
        this.name = name;
        this.status = status;
        this.price = price;
        this.courseDuration = courseDuration;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    @Override
    public String toString() {
        return "RegistrationDetailsDTO{" +
                "rid='" + rid + '\'' +
                ", sid='" + sid + '\'' +
                ", studentName='" + studentName + '\'' +
                ", cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", courseDuration='" + courseDuration + '\'' +
                '}';
    }
}
