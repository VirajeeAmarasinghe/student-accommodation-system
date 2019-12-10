/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.Property;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Virajee
 */
public class PropertyDAO implements DAOInterface {

    private Connection cn;

    public PropertyDAO() {
        try {
            cn = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int insert(Object ob) {
        int result = 0;
        Property p = (Property) ob;
        String query = "";
        if ("".equals(p.getImage())) {
            query = "INSERT INTO property(p_id,p_addNo,p_addStreet,p_add_city,max_num_of_tenants,no_of_bedrooms,no_of_bathrooms,kitchen,electricity,water,rent,availability,additional_info,l_id,t_id)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        } else {
            query = "INSERT INTO property(p_id,p_addNo,p_addStreet,p_add_city,max_num_of_tenants,no_of_bedrooms,no_of_bathrooms,kitchen,electricity,water,rent,availability,additional_info,image,l_id,t_id)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, p.getPropID());
            ps.setString(2, p.getAddNo());
            ps.setString(3, p.getAddStreet());
            ps.setString(4, p.getAddCity());
            ps.setInt(5, p.getNumOfTenants());
            ps.setInt(6, p.getNoOfBedrooms());
            ps.setInt(7, p.getNoOfBathrooms());
            ps.setString(8, p.getKitchen());
            ps.setString(9, p.getElectricity());
            ps.setString(10, p.getWater());
            ps.setDouble(11, p.getRent());
            ps.setString(12, p.getAvailability());
            ps.setString(13, p.getAddInfo());

            if ("".equals(p.getImage())) {
                ps.setInt(14, p.getlID());
                ps.setInt(15, p.gettID());
            } else {
                File f = new File(p.getImage());
                FileInputStream fis = new FileInputStream(f);
                ps.setBinaryStream(14, (InputStream) fis, (int) (f.length()));

                ps.setInt(15, p.getlID());
                ps.setInt(16, p.gettID());
            }

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException | FileNotFoundException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    
    public int updateAvailability(int pID,String availability) {
        int result = 0;
        
        String query = "UPDATE property SET availability='"+availability+"' where p_id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setInt(1,pID);         
         
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
        Property l = (Property) ob;
        String query = "UPDATE property SET p_addNo=?,p_addStreet=?,p_add_city=?,max_num_of_tenants=?,no_of_bedrooms=?,no_of_bathrooms=?,kitchen=?,electricity=?,water=?,rent=?,additional_info=?,l_id=?,t_id=? where p_id=? and l_id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setString(1, l.getAddNo());
            ps.setString(2, l.getAddStreet());
            ps.setString(3, l.getAddCity());
            ps.setInt(4, l.getNumOfTenants());
            ps.setInt(5, l.getNoOfBedrooms());
            ps.setInt(6, l.getNoOfBathrooms());
            ps.setString(7, l.getKitchen());
            ps.setString(8, l.getElectricity());
            ps.setString(9, l.getWater());
            ps.setDouble(10, l.getRent());
            ps.setString(11, l.getAddInfo());
            ps.setInt(12, l.getlID());
            ps.setInt(13, l.gettID());
            ps.setInt(14, l.getPropID());
            ps.setInt(15, l.getlID());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("comes here");
            e.printStackTrace();
        }
        return result;
    }

    public int updateImage(String saveFile, int pID) {
        int result = 0;

        String query = "UPDATE property SET image=? where p_id=?";

        FileInputStream fis;
        try {
            File f = new File(saveFile);
            fis = new FileInputStream(f);
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setBinaryStream(1, (InputStream) fis, (int) (f.length()));
            ps.setInt(2, pID);
            result = ps.executeUpdate();
            ps.close();
        } catch (FileNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;

        String query = "DELETE FROM property where p_id=?";
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

    public int delete(int id, int lID) {
        int result = 0;

        String query = "DELETE FROM property where p_id=? and l_id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ps.setInt(1, id);
            ps.setInt(2, lID);
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object get(int id) {
        Property p = null;
        InputStream in = null;
        String selectSt = "select * from property where p_id=?";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(id, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent, addInfo, lID, tID, availability, in);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return p;
    }

    public Property getByPIDAndLID(int PId, int LId) {
        Property p = null;
        InputStream in = null;
        String selectSt = "select * from property where p_id=? and l_id=?";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setInt(1, PId);
            ps.setInt(2, LId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(PId, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent, addInfo, lID, tID, availability, in);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return p;
    }

    public ArrayList<Property> getByLandordID(int lId) {
        ArrayList<Property> pList = new ArrayList<>();
        Property p = null;
        InputStream in = null;
        String selectSt = "select * from property where l_id=?";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ps.setInt(1, lId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(id, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent, addInfo, lID, tID, availability, in);
                pList.add(p);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return pList;
    }

    public Set<String> getStreets() {
        Set<String> s = new HashSet<String>();
        //executing stored procedure-new HashSet<String>();
        CallableStatement proc = null;
        try {
            proc = cn.prepareCall("{call GetAllStreets()}");
            proc.execute();
            ResultSet rs = proc.getResultSet();
            while (rs.next()) {
                s.add(rs.getString("p_addStreet"));
            }
            rs.close();
            proc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public ArrayList<Property> getPropertiesByStreetAndType(String street, int typeID) {
        ArrayList<Property> a = new ArrayList<>();
        CallableStatement proc = null;
        InputStream in = null;
        Property p = null;
        try {
            proc = cn.prepareCall("{call getPropertyAccordingToStreetAndType(?,?)}");
            proc.setString(1, street);
            proc.setInt(2, typeID);
            proc.execute();
            ResultSet rs = proc.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(id, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent, addInfo, lID, tID, availability, in);
                a.add(p);
            }
            rs.close();
            proc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public ArrayList<Property> getPropertiesByStreetAndTypeAndMinRent(String street, int typeID, double rent) {
        ArrayList<Property> a = new ArrayList<>();
        CallableStatement proc = null;
        InputStream in = null;
        Property p = null;
        try {
            proc = cn.prepareCall("{call getPropertiesAccordingToStreetTypeMinRent(?,?,?)}");
            proc.setString(1, street);
            proc.setInt(2, typeID);
            proc.setDouble(3, rent);
            proc.execute();
            ResultSet rs = proc.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent2 = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(id, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent2, addInfo, lID, tID, availability, in);
                a.add(p);
            }
            rs.close();
            proc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public ArrayList<Property> getPropertiesByStreetAndTypeAndMinMaxRent(String street, int typeID, double minRent, double maxRent) {
        ArrayList<Property> a = new ArrayList<>();
        CallableStatement proc = null;
        InputStream in = null;
        Property p = null;
        try {
            proc = cn.prepareCall("{call getPropertiesAccordingToStreetTypeMinMAxRent(?,?,?,?)}");
            proc.setString(1, street);
            proc.setInt(2, typeID);
            proc.setDouble(3, minRent);
            proc.setDouble(4, maxRent);
            proc.execute();
            ResultSet rs = proc.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent2 = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(id, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent2, addInfo, lID, tID, availability, in);
                a.add(p);
            }
            rs.close();
            proc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getNxtId() {
        int maxId = 0;
        int nextStuId = 0;
        String selectSt = "select MAX(p_id) from property";

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

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> propertyList = new ArrayList<>();
        Property p = null;
        InputStream in = null;
        String selectSt = "select * from property where availability='Yes'";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(id, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent, addInfo, lID, tID, availability, in);
                propertyList.add(p);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return propertyList;
    }
    
    public ArrayList<Object> getAllNonAvailableProperties() {
        ArrayList<Object> propertyList = new ArrayList<>();
        Property p = null;
        InputStream in = null;
        String selectSt = "select * from property where availability='No'";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(id, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent, addInfo, lID, tID, availability, in);
                propertyList.add(p);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return propertyList;
    }

    public ArrayList<Property> getAllProps() {
        ArrayList<Property> pList = new ArrayList<>();
        Property p = null;
        InputStream in = null;
        String selectSt = "select * from property";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String addNo = rs.getString("p_addNo");
                String addStreet = rs.getString("p_addStreet");
                String addCity = rs.getString("p_add_city");
                int noOfTenants = rs.getInt("max_num_of_tenants");
                int noOfBedrooms = rs.getInt("no_of_bedrooms");
                int noOfBathrooms = rs.getInt("no_of_bathrooms");
                String kitchen = rs.getString("kitchen");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                double rent = rs.getDouble("rent");
                String availability = rs.getString("availability");
                String addInfo = rs.getString("additional_info");
                in = rs.getBinaryStream(14);
                int lID = rs.getInt("l_id");
                int tID = rs.getInt("t_id");
                p = new Property(id, addNo, addStreet, addCity, noOfTenants, noOfBedrooms, noOfBathrooms, kitchen, electricity, water, rent, addInfo, lID, tID, availability, in);
                pList.add(p);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return pList;
    }
}
