/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.Payment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Virajee
 */
public class PaymentDAO implements DAOInterface {

    private Connection cn;

    public PaymentDAO() {
        try {
            cn = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int insert(Object ob) {
        int result=0;
        Payment p=(Payment) ob;
        String insert="INSERT INTO payment(pay_id,amount,date_of_payment,p_id)VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(insert);
            ps.setInt(1,p.getPayID());
            ps.setDouble(2,p.getAmount());
            ps.setDate(3,p.getPayDate());
            ps.setInt(4,p.getpID());            
            
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Object ob) {
        int result = 0;
        Payment p = (Payment) ob;
        String query = "UPDATE payment SET amount=?,date_of_payment=? where pay_id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setDouble(1,p.getAmount());
            ps.setDate(2,p.getPayDate());          
            ps.setInt(3,p.getPayID());           

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {            
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;

        String query = "DELETE FROM payment where pay_id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setInt(1, id);
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object get(int id) {
        Payment p = null;

        String select = "select pay_id,amount,date_of_payment,p_id from payment where pay_id=?";

        try {
            PreparedStatement ps = cn.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int payID = rs.getInt("pay_id");
                double amount = rs.getDouble("amount");
                Date date = rs.getDate("date_of_payment");
                int pID = rs.getInt("p_id");
                p = new Payment(payID, pID, date, amount);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    
    public Object getByProperty(int pId) {
        Payment p = null;

        String select = "select pay_id,amount,date_of_payment,p_id from payment where p_id=?";

        try {
            PreparedStatement ps = cn.prepareStatement(select);
            ps.setInt(1, pId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int payID = rs.getInt("pay_id");
                double amount = rs.getDouble("amount");
                Date date = rs.getDate("date_of_payment");
                int pID = rs.getInt("p_id");
                p = new Payment(payID, pID, date, amount);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public ArrayList<Payment> getPayments(int lID) {
        ArrayList<Payment> paymentList = new ArrayList<>();
        String select = "select pay_id,amount,date_of_payment,p_id from payment where p_id in "
                + "(select p_id from property where l_id='" + lID + "')";
        try {
            PreparedStatement ps = cn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment p = new Payment();
                p.setPayID(rs.getInt("pay_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setPayDate(rs.getDate("date_of_payment"));
                p.setpID(rs.getInt("p_id"));
                paymentList.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    public Payment get(int propID, int lID) {
        Payment p = null;
        String select1 = "select p_id from property where p_id='" + propID + "' and l_id='" + lID + "'";
        String select2 = "select pay_id,amount,date_of_payment from payment where p_id=?";

        try {
            PreparedStatement ps = cn.prepareStatement(select1);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PreparedStatement ps2 = cn.prepareStatement(select2);
                ps2.setInt(1, propID);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    int payID = rs2.getInt("pay_id");
                    double amount = rs2.getDouble("amount");
                    Date date = rs2.getDate("date_of_payment");
                    p = new Payment(payID, propID, date, amount);
                }
                ps2.close();
                rs2.close();
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> paymentList = new ArrayList<>();
        String select = "select pay_id,amount,date_of_payment,p_id from payment";
        try {
            PreparedStatement ps = cn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment p = new Payment();
                p.setPayID(rs.getInt("pay_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setPayDate(rs.getDate("date_of_payment"));
                p.setpID(rs.getInt("p_id"));
                paymentList.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentList;
    
    }

    public int getNxtId() {
        int maxId = 0;
        int nextStuId = 0;
        String selectSt = "select MAX(pay_id) from payment";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt(1);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
            return 0;
        }

        nextStuId = maxId + 1;
        return nextStuId;
    }
}
