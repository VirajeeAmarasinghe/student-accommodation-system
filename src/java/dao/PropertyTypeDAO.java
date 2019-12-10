/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.PropertyType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Virajee
 */
public class PropertyTypeDAO implements DAOInterface {

    private Connection cn;

    public PropertyTypeDAO() {
        try {
            cn = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int insert(Object ob) {
        return 0;
    }

    @Override
    public int update(Object ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(int id) {
        PropertyType pType = null;
        String query = "SELECT * FROM typeofproperty where t_id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tName = rs.getString("type_name");
                pType = new PropertyType(id, tName);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pType;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> propTypeList = new ArrayList<>();
        String query = "SELECT * FROM typeofproperty";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("t_id");
                String tName = rs.getString("type_name");

                PropertyType p = new PropertyType(id, tName);
                propTypeList.add(p);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propTypeList;
    }

    public ArrayList<PropertyType> getAllObj() {
        ArrayList<PropertyType> propTypeList = new ArrayList<>();
        String query = "SELECT * FROM typeofproperty";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("t_id");
                String tName = rs.getString("type_name");

                PropertyType p = new PropertyType(id, tName);
                propTypeList.add(p);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propTypeList;
    }

}
