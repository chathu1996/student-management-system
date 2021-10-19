package lk.itsei.fx.sms.dto;

public class CourseDTO {
    private String cid;
    private String duration;
    private String name;
    private double price;
    private String status;

    public CourseDTO(String txtCourseIdText, String text, String txtCourseDurationText, double v) {
    }

    public CourseDTO(String cid, String duration, String name, double price, String status) {
        this.cid = cid;
        this.duration = duration;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
