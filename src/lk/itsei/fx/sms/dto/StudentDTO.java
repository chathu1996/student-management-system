package lk.itsei.fx.sms.dto;

public class StudentDTO {

    private String sid;
    private String studentNAme;
    private String stdentTelNum;
    private String studentEmail;
    private String studentAddress;

    public StudentDTO() {
    }

    public StudentDTO(String sid, String studentNAme, String stdentTelNum, String studentEmail, String studentAddress) {
        this.sid = sid;
        this.studentNAme = studentNAme;
        this.stdentTelNum = stdentTelNum;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStudentNAme() {
        return studentNAme;
    }

    public void setStudentNAme(String studentNAme) {
        this.studentNAme = studentNAme;
    }

    public String getStdentTelNum() {
        return stdentTelNum;
    }

    public void setStdentTelNum(String stdentTelNum) {
        this.stdentTelNum = stdentTelNum;
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

    @Override
    public String toString() {
        return "StudentDTO{" +
                "sid='" + sid + '\'' +
                ", studentNAme='" + studentNAme + '\'' +
                ", stdentTelNum='" + stdentTelNum + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                '}';
    }
}
