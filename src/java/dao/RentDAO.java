/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.Rent;
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
public class RentDAO implements DAOInterface {

    private Connection cn;

    public RentDAO() {
        try {
            cn = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int insert(Object ob) {
        int result=0;
        Rent r=(Rent) ob;
        String insert="INSERT INTO rent(r_id,start_date,end_date,p_id,stu_id_local,stu_id_international)VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(insert);
            ps.setInt(1,r.getrID());
            ps.setDate(2,r.getStartDate());
            ps.setDate(3,r.getEndDate());
            ps.setInt(4,r.getpID());
            ps.setInt(5,r.getsIDLocal());
            ps.setInt(6,r.getsIDInternational());
            
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

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNxtId() {
        int maxId = 0;
        int nextStuId = 0;
        String selectSt = "select MAX(r_id) from rent";

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

    public boolean canRent(int pID, Date startDate, Date endDate) {
        boolean canRent = false;
        try {
            String selectSt_1 = "SELECT * FROM rent where p_id=?";
            PreparedStatement ps_1 = cn.prepareStatement(selectSt_1);
            ps_1.setInt(1, pID);
            ResultSet rs_1 = ps_1.executeQuery();
            if (rs_1.next()) {
                //rest of the logic
                String selectSt_3 = "SELECT * FROM rent where start_date='" + startDate + "' AND end_date='" + endDate + "' AND p_id='" + pID + "'";
                PreparedStatement ps_3 = cn.prepareStatement(selectSt_3);
                ResultSet rs_3 = ps_3.executeQuery();
                if (rs_3.next()) {
                    canRent = false;
                } else {
                    String select_4 = "SELECT * FROM rent where start_date='" + startDate + "' AND end_date>'" + endDate + "' AND p_id='" + pID + "'";
                    PreparedStatement ps_4 = cn.prepareStatement(select_4);
                    ResultSet rs_4 = ps_4.executeQuery();
                    if (rs_4.next()) {
                        canRent = false;
                    } else {
                        String select_5 = "SELECT * FROM rent where start_date='" + startDate + "' AND end_date<'" + endDate + "' AND p_id='" + pID + "'";
                        PreparedStatement ps_5 = cn.prepareStatement(select_5);
                        ResultSet rs_5 = ps_5.executeQuery();
                        if (rs_5.next()) {
                            canRent = false;
                        } else {
                            String select_6 = "SELECT * FROM rent where start_date<'" + startDate + "' AND end_date<'" + endDate + "' AND end_date>'" + startDate + "' AND p_id='" + pID + "'";
                            PreparedStatement ps_6 = cn.prepareStatement(select_6);
                            ResultSet rs_6 = ps_6.executeQuery();
                            if (rs_6.next()) {
                                canRent = false;
                            } else {
                                String selectSt_7 = "SELECT * FROM rent where start_date<'" + startDate + "' AND end_date='" + endDate + "' AND p_id='" + pID + "'";
                                PreparedStatement ps_7 = cn.prepareStatement(selectSt_7);
                                ResultSet rs_7 = ps_7.executeQuery();
                                if (rs_7.next()) {
                                    canRent = false;
                                } else {
                                    String selectSt_8 = "SELECT * FROM rent where start_date<'" + startDate + "' AND end_date>'" + endDate + "' AND p_id='" + pID + "'";
                                    PreparedStatement ps_8 = cn.prepareStatement(selectSt_8);
                                    ResultSet rs_8 = ps_8.executeQuery();
                                    if (rs_8.next()) {
                                        canRent = false;
                                    } else {
                                        String selectSt_9 = "SELECT * FROM rent where start_date>'" + startDate + "' AND end_date='" + endDate + "' AND p_id='" + pID + "'";
                                        PreparedStatement ps_9 = cn.prepareStatement(selectSt_9);
                                        ResultSet rs_9 = ps_9.executeQuery();
                                        if (rs_9.next()) {
                                            canRent = false;
                                        } else {
                                            String selectSt_10 = "SELECT * FROM rent where start_date>'" + startDate + "' AND end_date>'" + endDate + "' AND p_id='" + pID + "'";
                                            PreparedStatement ps_10 = cn.prepareStatement(selectSt_10);
                                            ResultSet rs_10 = ps_10.executeQuery();
                                            if (rs_10.next()) {
                                                canRent = false;
                                            } else {
                                                String selectSt_11 = "SELECT * FROM rent where start_date>'" + startDate + "' AND end_date<'" + endDate + "' AND p_id='" + pID + "'";
                                                PreparedStatement ps_11 = cn.prepareStatement(selectSt_11);
                                                ResultSet rs_11 = ps_11.executeQuery();
                                                if (rs_11.next()) {
                                                    canRent = false;
                                                } else {
                                                    canRent = true;
                                                }
                                                rs_11.close();
                                                ps_11.close();
                                            }
                                            rs_10.close();
                                            ps_10.close();
                                        }
                                        rs_9.close();
                                        ps_9.close();
                                    }
                                    rs_8.close();
                                    ps_8.close();
                                }
                                rs_7.close();
                                ps_7.close();
                            }
                            rs_6.close();
                            ps_6.close();
                        }
                        rs_5.close();
                        ps_5.close();
                    }
                    rs_4.close();
                    ps_4.close();
                }
                rs_3.close();
                ps_3.close();
            } else {
                String availability = "Yes";
                String selectSt_2 = "SELECT * FROM property where p_id=? AND availability='" + availability + "'";
                PreparedStatement ps_2 = cn.prepareStatement(selectSt_2);
                ps_2.setInt(1,pID);
                ResultSet rs_2 = ps_2.executeQuery();
                if (rs_2.next()) {
                    canRent = true;
                } else {
                    canRent = false;
                }
                rs_2.close();
                ps_2.close();
            }

            rs_1.close();
            ps_1.close();            
        } catch (SQLException e) {
            e.printStackTrace();
            canRent = false;
        }
        return canRent;
    }

    @Override
    public ArrayList<Object> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
