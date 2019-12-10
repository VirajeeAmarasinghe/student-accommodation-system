/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.Landlord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Virajee
 */
public class LandlordDAO implements DAOInterface{

    private Connection cn;

    public LandlordDAO() {
        try {
            cn = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int insert(Object ob) {
        int result = 0;
        Landlord l = (Landlord) ob;
        String query = "INSERT INTO landlord(l_id,l_fName,l_lName,l_address,l_landTeleNo,l_mobTeleNo,email)VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, l.getL_id());
            ps.setString(2, l.getL_fName());
            ps.setString(3, l.getL_lName());
            ps.setString(4, l.getAdd());
            ps.setString(5, l.getTele_land());
            ps.setString(6, l.getTele_mob());
            ps.setString(7, l.getEmail());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    @Override
    public int update(Object ob) {
        int result = 0;
        Landlord l = (Landlord) ob;
        String query = "UPDATE landlord SET l_fName=?,l_lName=?,l_address=?,l_landTeleNo=?,l_mobTeleNo=?,email=? where l_id=?";
        try {            
            PreparedStatement ps = cn.prepareStatement(query);
           
            ps.setString(1, l.getL_fName());
            ps.setString(2, l.getL_lName());
            ps.setString(3, l.getAdd());
            ps.setString(4, l.getTele_land());
            ps.setString(5, l.getTele_mob());
            ps.setString(6, l.getEmail());
            ps.setInt(7, l.getL_id());
            result = ps.executeUpdate();            
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;

        String query = "DELETE FROM landlord where l_id=?";
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
        Landlord l = null;        
        String selectSt = "select * from landlord where l_id=?";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fName = rs.getString("l_fName");
                String lName = rs.getString("l_lName");
                String lAdd = rs.getString("l_address");                
                String teleLand = rs.getString("l_landTeleNo");
                String teleMob = rs.getString("l_mobTeleNo");
                String email = rs.getString("email");
                
                l = new Landlord(id, fName, lName, lAdd, teleLand, teleMob, email);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return l;
    }

    public Object get(String email) {
        Landlord l = null;
        String selectSt = "select l_id,l_fName,l_lName,l_address,l_landTeleNo,l_mobTeleNo,email from landlord where email=?";
        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("l_id");
                String fName = rs.getString("l_fName");
                String lName = rs.getString("l_lName");
                String add = rs.getString("l_address");
                String tele_land = rs.getString("l_landTeleNo");
                String tele_mob = rs.getString("l_mobTeleNo");

                l = new Landlord(id, fName, lName, add, tele_land, tele_mob, email);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return l;
    }

    public Landlord getByPropID(int id) {
        Landlord l = null;        
        String selectSt = "select * from landlord where l_id=(select l_id from property where p_id=?)";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fName = rs.getString("l_fName");
                String lName = rs.getString("l_lName");
                String lAdd = rs.getString("l_address");                
                String teleLand = rs.getString("l_landTeleNo");
                String teleMob = rs.getString("l_mobTeleNo");
                String email = rs.getString("email");
                
                l = new Landlord(id, fName, lName, lAdd, teleLand, teleMob, email);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return l;
    }
    
    public String getName(String email) {
        String name = "";
        String selectSt = "select l_fName,l_lName from landlord where email=?";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("l_fName") + " " + rs.getString("l_lName");
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return name;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> lanlordList=new ArrayList<>();
        String selectSt = "select * from landlord";
        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id=rs.getInt("l_id");
                String fName = rs.getString("l_fName");
                String lName = rs.getString("l_lName");
                String lAdd = rs.getString("l_address");                
                String teleLand = rs.getString("l_landTeleNo");
                String teleMob = rs.getString("l_mobTeleNo");
                String email = rs.getString("email");
                
                Landlord l = new Landlord(id, fName, lName, lAdd, teleLand, teleMob, email);
                lanlordList.add(l);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return lanlordList;
        
    }
    
    public ArrayList<Landlord> getAll2(){
       ArrayList<Landlord> lanlordList=new ArrayList<>();
        String selectSt = "select * from landlord";
        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("l_id");
                String fName = rs.getString("l_fName");
                String lName = rs.getString("l_lName");
                String lAdd = rs.getString("l_address");                
                String teleLand = rs.getString("l_landTeleNo");
                String teleMob = rs.getString("l_mobTeleNo");
                String email = rs.getString("email");
                
                Landlord l = new Landlord(id, fName, lName, lAdd, teleLand, teleMob, email);
                lanlordList.add(l);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return lanlordList;
    }

    public int getNxtId() {
        int maxId = 0;
        int nextStuId = 0;
        String selectSt = "select MAX(l_id) from landlord";

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
