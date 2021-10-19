package lk.itsei.fx.sms.dao.custom.impl;

import lk.itsei.fx.sms.dao.custom.RegistrationDetailsDAO;
import lk.itsei.fx.sms.db.DBConnection;
import lk.itsei.fx.sms.entity.RegistrationDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegitrationDetailsDAOImpl implements RegistrationDetailsDAO {
    @Override
    public boolean add(RegistrationDetails entity) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("insert into registrationdetails values(?, ?, ?, ?, ?, ?, ?, ?)");
        pstm.setObject (1, entity.getRid ());
        pstm.setObject (2, entity.getSid ());
        pstm.setObject (3, entity.getStudentName ());
        pstm.setObject (4, entity.getCid ());
        pstm.setObject (5, entity.getName ());
        pstm.setObject (6, entity.getStatus ());
        pstm.setObject (7, entity.getPrice ());
        pstm.setObject (8, entity.getCourseDuration ());
        System.out.println (entity.toString ());
        return pstm.executeUpdate () >0;

    }

    @Override
    public RegistrationDetails find(String key) throws Exception {

        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("select * from registrationdetails where rid = ?");
        pstm.setObject (1, key);
        ResultSet rst = pstm.executeQuery ();
        while (rst.next ()) {
            return new RegistrationDetails (
                    rst.getString (1),
                    rst.getString (2),
                    rst.getString (3),
                    rst.getString (4),
                    rst.getString (5),
                    rst.getString (6),
                    rst.getDouble (7),
                    rst.getString (8)
            );
        }
        return null;
    }

    @Override
    public List<RegistrationDetails> findAll() throws Exception {
        Connection connection =  DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("SELECT * from registrationdetails");
        ResultSet rst = pstm.executeQuery ();
        List<RegistrationDetails> registrationDetailsList = new ArrayList <> ();
        while (rst.next ()) {
            String rid = rst.getString (1);
            String sid = rst.getString (2);
            String studentName = rst.getString (3);
            String cid = rst.getString (4);
            String name = rst.getString (5);
            String status = rst.getString (6);
            double price = rst.getDouble (7);
            String courseDuration = rst.getString (8);

            RegistrationDetails registrationDetails = new RegistrationDetails (rid, sid, studentName, cid, name, status, price, courseDuration);
            registrationDetailsList.add (registrationDetails);
        }
        return registrationDetailsList;
    }

    @Override
    public boolean update(RegistrationDetails entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        Connection connection = DBConnection.getConnection ();
        PreparedStatement pstm = connection.prepareStatement ("DELETE from registrationdetails where rid = ?");
        pstm.setObject (1, key);
        return pstm.executeUpdate () > 0;
    }
}
