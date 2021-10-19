package lk.itsei.fx.sms.dao.custom.impl;

import lk.itsei.fx.sms.dao.custom.CourseDAO;
import lk.itsei.fx.sms.db.DBConnection;
import lk.itsei.fx.sms.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean add(Course entity) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("INSERT into course values (?, ?, ?, ?, ?)");
        pstm.setObject (1, entity.getCid ());
        pstm.setObject (2, entity.getDuration ());
        pstm.setObject (3, entity.getName ());
        pstm.setObject (4, entity.getPrice ());
        pstm.setObject (5, entity.getStatus ());
        return pstm.executeUpdate () > 0;
    }

    @Override
    public Course find(String key) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("SELECT * from course where cid = ?");
        pstm.setObject (1, key);
        ResultSet rst = pstm.executeQuery ();
        while (rst.next ()) {
            return new Course (
                    rst.getString (1),
                    rst.getString (2),
                    rst.getString (3),
                    rst.getDouble (4),
                    rst.getString (5)
            );
        }
        return null;
    }

    @Override
    public List<Course> findAll() throws Exception {
        ArrayList<Course> alCourse = new ArrayList <> ();
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("SELECT * from course");
        ResultSet rst = pstm.executeQuery ();
       while (rst.next ()) {
           String cid = rst.getString (1);
           String duration = rst.getString (2);
           String cName = rst.getString (3);
           double price = rst.getDouble (4);
           String status= rst.getString (5);
           Course course = new Course (cid, duration, cName, price, status);
           alCourse.add (course);
       }
       return alCourse;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("Update course set duration = ?, name = ?, price = ?, status = ? where cid = ?");
        pstm.setObject (5, entity.getCid ());
        pstm.setObject (1, entity.getDuration ());
        pstm.setObject (2, entity.getName ());
        pstm.setObject (3, entity.getPrice ());
        pstm.setObject (4, entity.getStatus ());
        return pstm.executeUpdate () > 0;
    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("Delete from course where cid = ?");
        pstm.setObject (1, key);
        return pstm.executeUpdate () > 0;
    }

    public static List<Course> findBlockCourse() throws Exception {
        ArrayList<Course> blockCourse = new ArrayList <> ();
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("SELECT * from course where status like '%Block%'");
        ResultSet rst = pstm.executeQuery ();
        while (rst.next ()) {
            String bcid = rst.getString (1);
            String duration = rst.getString (2);
            String cName = rst.getString (3);
            double price = rst.getDouble (4);
            String status= rst.getString (5);
            Course course = new Course (bcid, duration, cName, price, status);
            blockCourse.add (course);
        }
        return blockCourse;
    }

    public static List<Course> findPendingCourse() throws Exception {
        ArrayList<Course> pendingCourse = new ArrayList <> ();
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("SELECT * from course where status like '%Pending%'");
        ResultSet rst = pstm.executeQuery ();
        while (rst.next ()) {
            String bcid = rst.getString (1);
            String duration = rst.getString (2);
            String cName = rst.getString (3);
            double price = rst.getDouble (4);
            String status= rst.getString (5);
            Course course = new Course (bcid, duration, cName, price, status);
            pendingCourse.add (course);
        }
        return pendingCourse;
    }
}
