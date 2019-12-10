/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import business.View;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Virajee
 */
public class ViewDAO implements DAOInterface{
    private Connection cn;

    public ViewDAO() {
        try {
            cn = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    } 
    

    @Override
    public int insert(Object ob) {
        int result=0;
        View v=(View) ob;
        String insert="INSERT INTO view(view_id,viewing_date,viewing_time,confirm_stu,confirm_landlord,p_id,stu_localID,stu_internationalID)VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(insert);
            ps.setInt(1,v.getViewID());
            ps.setDate(2,v.getViewDate());
            ps.setTime(3,v.getViewTime());
            ps.setString(4,v.getConfirmStu());
            ps.setString(5,v.getConfirmLandlord());
            ps.setInt(6,v.getpID());
            ps.setInt(7,v.getStuLocalID());
            ps.setInt(8,v.getStuInternationalID());
            
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
    
    public int updateConfirm(int ID,String userType){
        int result = 0;
        
        String query = "";
        
        if(userType.equals("Landlord")){
           query="UPDATE view SET confirm_landlord='Yes' WHERE view_id='"+ID+"'";
        }else if(userType.equals("Student")){
           query="UPDATE view SET confirm_stu='Yes' WHERE view_id='"+ID+"'";
        }
        try {            
            PreparedStatement ps = cn.prepareStatement(query);           
            
            result = ps.executeUpdate();            
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }
    
    public int updateConfirmDateTime(int ID,String userType,Date date,Time time){
        int result = 0;
                       
        String query = "";
        
        if(userType.equals("Landlord")){
           query="UPDATE view SET confirm_landlord='Yes',confirm_stu='No',viewing_date='"+date+"',viewing_time='"+time+"' WHERE view_id='"+ID+"'";
        }else if(userType.equals("Student")){           
           query="UPDATE view SET confirm_landlord='No',confirm_stu='Yes',viewing_date='"+date+"',viewing_time='"+time+"' WHERE view_id='"+ID+"'";
        }
        try {            
            PreparedStatement ps = cn.prepareStatement(query);           
            
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

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<View> getByLandlordAndConfirmation(int lID){
       ArrayList<View> vList=new ArrayList<>();
        String selectSt = "SELECT * FROM view WHERE p_id IN(SELECT p_id FROM property WHERE l_id='"+lID+"') AND confirm_landlord='No'";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);           

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int vID=rs.getInt("view_id");
                Date viewDate=rs.getDate("viewing_date");
                Time viewTime=rs.getTime("viewing_time");
                int propertyID=rs.getInt("p_id");
                int localStuID=rs.getInt("stu_localID");
                int interStuID=rs.getInt("stu_internationalID");
                String confirmStu=rs.getString("confirm_stu");
                String confirmLand=rs.getString("confirm_landlord");
                View v=new View(vID, viewDate, viewTime, confirmStu, confirmLand,propertyID, localStuID, interStuID);
                vList.add(v);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return vList;
    }
    
    public ArrayList<View> getByStudentAndConfirmation(int sLocalID,int sInternationalID){
       ArrayList<View> vList=new ArrayList<>();
        String selectSt = "SELECT * FROM view WHERE stu_localID='"+sLocalID+"' AND stu_internationalID='"+sInternationalID+"' AND confirm_stu='No'";

        try {
            PreparedStatement ps = cn.prepareStatement(selectSt);           

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int vID=rs.getInt("view_id");
                Date viewDate=rs.getDate("viewing_date");
                Time viewTime=rs.getTime("viewing_time");
                int propertyID=rs.getInt("p_id");
                int localStuID=rs.getInt("stu_localID");
                int interStuID=rs.getInt("stu_internationalID");
                String confirmStu=rs.getString("confirm_stu");
                String confirmLand=rs.getString("confirm_landlord");
                View v=new View(vID, viewDate, viewTime, confirmStu, confirmLand,propertyID, localStuID, interStuID);
                vList.add(v);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return vList;
    }
    
    public int getNxtId() {
        int maxId = 0;
        int nextStuId = 0;
        String selectSt = "select MAX(view_id) from view";

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
