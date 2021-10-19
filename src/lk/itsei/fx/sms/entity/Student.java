package lk.itsei.fx.sms.entity;

public class Student {
    private  String sid;
    private String studentName;
    private String studentTelNum;
    private String studentEmail;
    private String studentAddress;

    public Student() {
    }

    public Student(String sid, String studentName, String studentTelNum, String studentEmail, String studentAddress) {
        this.sid = sid;
        this.studentName = studentName;
        this.studentTelNum = studentTelNum;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
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

    public String getStudentTelNum() {
        return studentTelNum;
    }

    public void setStudentTelNum(String studentTelNum) {
        this.studentTelNum = studentTelNum;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
}
