package lk.itsei.fx.sms.business;

import lk.itsei.fx.sms.dao.DAOFactory;
import lk.itsei.fx.sms.dao.custom.CourseDAO;
import lk.itsei.fx.sms.dao.custom.impl.CourseDAOImpl;
import lk.itsei.fx.sms.dto.CourseDTO;
import lk.itsei.fx.sms.entity.Course;


import java.util.ArrayList;
import java.util.List;

public class ManageCourseBusiness {

    private static CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance ().getDAO (DAOFactory.DAOTypes.COURSE);

    public static List<CourseDTO> getCourse() throws Exception {
        List<Course> courseList = courseDAO.findAll ();
        List<CourseDTO> courseDTOList = new ArrayList <> ();
        for (Course course : courseList) {
            courseDTOList.add (new CourseDTO (
                    course.getCid (),
                    course.getDuration (),
                    course.getName (),
                    course.getPrice (),
                    course.getStatus ()
            ));
        }
        return courseDTOList;
    }

    public static boolean createCourse(CourseDTO courseDTO) throws Exception {
        return courseDAO.add (
                new Course (
                        courseDTO.getCid (),
                        courseDTO.getDuration (),
                        courseDTO.getName (),
                        courseDTO.getPrice (),
                        courseDTO.getStatus ()
                )
        );
    }

    public static boolean updateCourse(String cid, CourseDTO courseDTO) throws Exception {
        return courseDAO.update (new Course (
                courseDTO.getCid (),
                courseDTO.getDuration (),
                courseDTO.getName (),
                courseDTO.getPrice (),
                courseDTO.getStatus ()
        ));
    }

    public static boolean deleteCourse(String cid) throws Exception {
        return courseDAO.delete (cid);
    }

    public static CourseDTO findCourse(String cid) throws Exception {
        Course course = courseDAO.find (cid);
        return  new CourseDTO (
                course.getCid (),
                course.getDuration (),
                course.getName (),
                course.getPrice (),
                course.getStatus ()
        );
    }

    public static List<CourseDTO> getBlockCourse() throws Exception {
        List<Course> blockCourseList = CourseDAOImpl.findBlockCourse ();
        List<CourseDTO> blockCourseDTOList = new ArrayList <> ();
        for (Course course : blockCourseList) {
            blockCourseDTOList.add (new CourseDTO (
                    course.getCid (),
                    course.getDuration (),
                    course.getName (),
                    course.getPrice (),
                    course.getStatus ()
            ));
        }
        return  blockCourseDTOList;
    }

    public static List<CourseDTO> getPendingCourse() throws Exception {
        List<Course> pendingCourseList = CourseDAOImpl.findPendingCourse ();
        List<CourseDTO> blockCourseDTOList = new ArrayList <> ();
        for (Course course : pendingCourseList) {
            blockCourseDTOList.add (new CourseDTO (
                    course.getCid (),
                    course.getDuration (),
                    course.getName (),
                    course.getPrice (),
                    course.getStatus ()
            ));
        }
        return  blockCourseDTOList;
    }




}
