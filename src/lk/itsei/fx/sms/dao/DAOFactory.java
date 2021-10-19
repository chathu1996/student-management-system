package lk.itsei.fx.sms.dao;

import lk.itsei.fx.sms.dao.custom.impl.CourseDAOImpl;
import lk.itsei.fx.sms.dao.custom.impl.RegitrationDetailsDAOImpl;
import lk.itsei.fx.sms.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;

    public enum DAOTypes {
        STUDENT, COURSE, PAYMENT, REGISTRATION_DETAILS, SEARCH_STUDENTS
    }

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory ();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes) {
            case STUDENT: return new StudentDAOImpl();
            case COURSE: return new CourseDAOImpl();
            case REGISTRATION_DETAILS: return new RegitrationDetailsDAOImpl ();
//            case PAYMENT: return  new PaymentDAOImpl();
//            case SEARCH_STUDENTS: return new SearchStudentsDAOImpl();
            default: return null;

        }
    }
}
