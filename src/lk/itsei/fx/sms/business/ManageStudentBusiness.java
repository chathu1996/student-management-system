package lk.itsei.fx.sms.business;

import lk.itsei.fx.sms.dao.DAOFactory;
import lk.itsei.fx.sms.dao.custom.StudentDAO;
import lk.itsei.fx.sms.dto.StudentDTO;
import lk.itsei.fx.sms.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ManageStudentBusiness {

    private static StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance ().getDAO (DAOFactory.DAOTypes.STUDENT);

    public static List<StudentDTO> getStudents() throws Exception {
        List<Student> studentList = studentDAO.findAll ();
        List<StudentDTO> studentDTOList = new ArrayList <> ();
        for (Student student : studentList) {
            studentDTOList.add (new StudentDTO (
                    student.getSid (),
                    student.getStudentName (),
                    student.getStudentTelNum (),
                    student.getStudentEmail (),
                    student.getStudentAddress ()
            ));
        }
        return studentDTOList;
    }

    public static boolean createStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.add (
                new Student (
                        studentDTO.getSid (),
                        studentDTO.getStudentNAme (),
                        studentDTO.getStdentTelNum (),
                        studentDTO.getStudentEmail (),
                        studentDTO.getStudentAddress ()
                )
        );
    }

    public static boolean updateStudent(int sid, StudentDTO studentDTO) throws Exception{
        return studentDAO.update (new Student (
                studentDTO.getSid (),
                studentDTO.getStudentNAme (),
                studentDTO.getStdentTelNum (),
                studentDTO.getStudentEmail (),
                studentDTO.getStudentAddress ()
        ));
    }

    public static boolean deleteStudent(String sid) throws  Exception{
        return studentDAO.delete (sid);
    }

    public static StudentDTO findStudent(String sid) throws Exception {
        Student student = studentDAO.find (sid);
        if (student==null){
            return null;
        }
        return new StudentDTO (
                student.getSid (),
                student.getStudentName (),
                student.getStudentTelNum (),
                student.getStudentEmail (),
                student.getStudentAddress ()
        );
    }

}
