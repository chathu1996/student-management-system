package lk.itsei.fx.sms.dao.custom.impl;

import lk.itsei.fx.sms.dao.custom.StudentDAO;
import lk.itsei.fx.sms.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lk.itsei.fx.sms.entity.Student;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student entity) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("INSERT INTO student VALUES (?, ?, ?, ?, ?)");
        pstm.setObject (1, entity.getSid ());
        pstm.setObject (2, entity.getStudentName ());
        pstm.setObject (3, entity.getStudentTelNum ());
        pstm.setObject (4, entity.getStudentEmail ());
        pstm.setObject (5, entity.getStudentAddress ());
        System.out.println (entity.getSid ());
        return pstm.executeUpdate () > 0;
    }

    @Override
    public Student find(String key) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("SELECT * FROM student WHERE sid = ?");
        pstm.setObject (1, key);
        ResultSet rst = pstm.executeQuery ();
        while (rst.next ()) {
            return new Student (
                    rst.getString (1),
                    rst.getString (2),
                    rst.getString (3),
                    rst.getString (4),
                    rst.getString (5)
            );
        }
        return null;
    }

    @Override
    public List <Student> findAll() throws Exception {
        ArrayList<Student> alStudent = new ArrayList <> ();
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("SELECT * FROM student");
        ResultSet rst = pstm.executeQuery ();
        while (rst.next ()) {
            Student student = new Student (
                    rst.getString (1),
                    rst.getString (2),
                    rst.getString (3),
                    rst.getString (4),
                    rst.getString (5)
            );
            alStudent.add (student);
        }
        return alStudent;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("UPDATE student SET studentName = ?, studentTelNum = ?, studentEmail = ?, studentAddress = ? WHERE sid = ?");
        pstm.setObject (5, entity.getSid ());
        pstm.setObject (1, entity.getStudentName ());
        pstm.setObject (2, entity.getStudentTelNum ());
        pstm.setObject (3, entity.getStudentEmail ());
        pstm.setObject (4, entity.getStudentAddress ());
        return pstm.executeUpdate () > 0;
    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("DELETE FROM student WHERE sid = ?");
        pstm.setObject (1, key);
        return pstm.executeUpdate () > 0;
    }
}
