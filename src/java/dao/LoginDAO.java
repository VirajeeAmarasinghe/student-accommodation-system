/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Virajee
 */
public class LoginDAO implements DAOInterface {

    private Connection cn;

    public LoginDAO() {
        try {
            cn = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int insert(Object ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int insert(Object ob, String tableName) {
        int result = 0;
        Login l = (Login) ob;

        String query = "INSERT INTO " + tableName + "(email,password)VALUES(?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, l.getEmail());
            ps.setString(2, l.getPass());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Object ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int update(Object ob, String tableName) {
        int result = 0;
        Login l = (Login) ob;

        String query = "UPDATE " + tableName + " SET password=? where email=?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, l.getPass());
            ps.setString(2, l.getEmail());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int delete(String email,String tableName){
        int result = 0;

        String query = "DELETE FROM "+tableName+" where email=?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setString(1, email);
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Object> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object get(String email, String tableName) {
        Login login = null;
        String selectSt = "select password from " + tableName + " where email=?";
        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");

                login = new Login(email, password);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return login;
    }

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean alreadyHasEmail(String email, String tableName) {
        boolean result = false;

        String selectSt = "select * from " + tableName + " where email=?";
        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

}
