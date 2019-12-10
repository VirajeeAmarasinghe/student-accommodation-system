/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import business.International;
import business.Local;
import business.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Virajee
 */
public class StudentDAO implements DAOInterface {

    private Connection cn;

    public StudentDAO() {
        try {
            cn = DAOConnect.getInsatnce().connect();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int insert(Object ob) {
        int result = 0;
        Student obj = (Student) ob;
        String query = "";

        try {
            if (obj instanceof Local) {
                query = "INSERT INTO local(stu_id,stu_fName,stu_lName,stu_current_address,stu_tele,nic,email)VALUES(?,?,?,?,?,?,?)";
            } else if (obj instanceof International) {
                query = "INSERT INTO international(stu_id,stu_fName,stu_lName,stu_current_address,stu_tele,passport_id,country,email)VALUES(?,?,?,?,?,?,?,?)";
            }

            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, obj.getStu_id());
            ps.setString(2, obj.getStu_fName());
            ps.setString(3, obj.getStu_lName());
            ps.setString(4, obj.getStu_currentAdd());
            ps.setString(5, obj.getStu_tele());

            if (obj instanceof Local) {
                Local l = (Local) obj;
                ps.setString(6, l.getNic());
                ps.setString(7, obj.getEmail());
            } else if (obj instanceof International) {
                International i = (International) obj;
                ps.setString(6, i.getPassport());
                ps.setString(7, i.getCountry());
                ps.setString(8, i.getEmail());
            }

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
        Student s = (Student) ob;
        String query_1 = "UPDATE local SET stu_fName=?,stu_lName=?,stu_current_address=?,stu_tele=?,nic=?,email=? where stu_id=?";
        String query_2 = "UPDATE international SET stu_fName=?,stu_lName=?,stu_current_address=?,stu_tele=?,passport_id=?,country=?,email=? where stu_id=?";
        try {
            if (s instanceof Local) {
                Local l = (Local) s;
                PreparedStatement ps = cn.prepareStatement(query_1);

                ps.setString(1, l.getStu_fName());
                ps.setString(2, l.getStu_lName());
                ps.setString(3, l.getStu_currentAdd());
                ps.setString(4, l.getStu_tele());
                ps.setString(5, l.getNic());
                ps.setString(6, l.getEmail());
                ps.setInt(7, l.getStu_id());

                result = ps.executeUpdate();
                ps.close();
            } else if (s instanceof International) {
                International i = (International) s;

                PreparedStatement ps = cn.prepareStatement(query_2);

                ps.setString(1, i.getStu_fName());
                ps.setString(2, i.getStu_lName());
                ps.setString(3, i.getStu_currentAdd());
                ps.setString(4, i.getStu_tele());
                ps.setString(5, i.getPassport());
                ps.setString(6, i.getCountry());
                ps.setString(7, i.getEmail());
                ps.setInt(8, i.getStu_id());

                result = ps.executeUpdate();
                ps.close();
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int delete(int id, String tableName) {
        int result = 0;

        String query = "DELETE FROM " + tableName + " where stu_id=?";
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
        //Student object to return 
        Student s = null;
        String selectSt_1 = "select stu_id,stu_fName,stu_lName,"
                + "stu_current_address,stu_tele,nic,email from local where stu_id=?";
        String selectSt_2 = "select stu_id,stu_fName,stu_lName,"
                + "stu_current_address,stu_tele,passport_id,"
                + "country,email from international where stu_id=?";
        try {
            PreparedStatement ps_1 = cn.prepareStatement(selectSt_1);
            ps_1.setInt(1, id);

            ResultSet rs_1 = ps_1.executeQuery();
            if (rs_1.next()) {
                int stu_id = rs_1.getInt("stu_id");
                String fName = rs_1.getString("stu_fName");
                String lName = rs_1.getString("stu_lName");
                String add = rs_1.getString("stu_current_address");
                String tele = rs_1.getString("stu_tele");
                String nic = rs_1.getString("nic");
                String email = rs_1.getString("email");
                s = new Local(stu_id, fName, lName, add, tele, email, nic);
            } else {
                PreparedStatement ps_2 = cn.prepareStatement(selectSt_2);
                ps_2.setInt(1, id);

                ResultSet rs_2 = ps_2.executeQuery();
                if (rs_2.next()) {
                    int stu_id = rs_2.getInt("stu_id");
                    String fName = rs_2.getString("stu_fName");
                    String lName = rs_2.getString("stu_lName");
                    String add = rs_2.getString("stu_current_address");
                    String tele = rs_2.getString("stu_tele");
                    String passport = rs_2.getString("passport_id");
                    String country = rs_2.getString("country");
                    String email = rs_2.getString("email");
                    s = new International(stu_id, fName, lName, add, tele, email, passport, country);
                }
                rs_2.close();
                ps_2.close();
            }
            rs_1.close();
            ps_1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }
    /*
     *This method is for getting student data
     *according to the email
     */

    public Object get(String email) {
        //Student object to return 
        Student s = null;
        String selectSt_1 = "select stu_id,stu_fName,stu_lName,"
                + "stu_current_address,stu_tele,nic from local where email=?";
        String selectSt_2 = "select stu_id,stu_fName,stu_lName,"
                + "stu_current_address,stu_tele,passport_id,"
                + "country from international where email=?";
        try {
            PreparedStatement ps_1 = cn.prepareStatement(selectSt_1);
            ps_1.setString(1, email);

            ResultSet rs_1 = ps_1.executeQuery();
            if (rs_1.next()) {
                int stu_id = rs_1.getInt("stu_id");
                String fName = rs_1.getString("stu_fName");
                String lName = rs_1.getString("stu_lName");
                String add = rs_1.getString("stu_current_address");
                String tele = rs_1.getString("stu_tele");
                String nic = rs_1.getString("nic");
                s = new Local(stu_id, fName, lName, add, tele, email, nic);
            } else {
                PreparedStatement ps_2 = cn.prepareStatement(selectSt_2);
                ps_2.setString(1, email);

                ResultSet rs_2 = ps_2.executeQuery();
                if (rs_2.next()) {
                    int stu_id = rs_2.getInt("stu_id");
                    String fName = rs_2.getString("stu_fName");
                    String lName = rs_2.getString("stu_lName");
                    String add = rs_2.getString("stu_current_address");
                    String tele = rs_2.getString("stu_tele");
                    String passport = rs_2.getString("passport_id");
                    String country = rs_2.getString("country");
                    s = new International(stu_id, fName, lName, add, tele, email, passport, country);
                }
                rs_2.close();
                ps_2.close();
            }
            rs_1.close();
            ps_1.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return s;
    }

    public String getName(String email) {
        String name = "";
        String selectSt_1 = "select stu_fName,stu_lName from local where email=?";
        String selectSt_2 = "select stu_fName,stu_lName from international where email=?";
        try {
            PreparedStatement ps_1 = cn.prepareStatement(selectSt_1);
            ps_1.setString(1, email);

            ResultSet rs_1 = ps_1.executeQuery();
            if (rs_1.next()) {
                name = rs_1.getString("stu_fName") + " " + rs_1.getString("stu_lName");
            } else {
                PreparedStatement ps_2 = cn.prepareStatement(selectSt_2);
                ps_2.setString(1, email);

                ResultSet rs_2 = ps_2.executeQuery();
                if (rs_2.next()) {
                    name = rs_2.getString("stu_fName") + " " + rs_2.getString("stu_lName");
                }
                rs_2.close();
                ps_2.close();
            }

            rs_1.close();
            ps_1.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return name;
    }

    public String getStudentType(String email) {
        String studentType = "";
        String selectSt_1 = "select stu_id from local where email=?";
        String selectSt_2 = "select stu_id from international where email=?";
        try {
            PreparedStatement ps_1 = cn.prepareStatement(selectSt_1);
            ps_1.setString(1, email);

            ResultSet rs_1 = ps_1.executeQuery();
            if (rs_1.next()) {
                studentType = "Local";
            } else {
                PreparedStatement ps_2 = cn.prepareStatement(selectSt_2);
                ps_2.setString(1, email);

                ResultSet rs_2 = ps_2.executeQuery();
                if (rs_2.next()) {
                    studentType = "International";
                }
                rs_2.close();
                ps_2.close();
            }

            rs_1.close();
            ps_1.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return studentType;
    }

    @Override
    public ArrayList<Object> getAll() {
        //Student object to return 
        ArrayList<Object> s = new ArrayList<>();
        String selectSt_1 = "select stu_id,stu_fName,stu_lName,"
                + "stu_current_address,stu_tele,nic,email from local";
        String selectSt_2 = "select stu_id,stu_fName,stu_lName,"
                + "stu_current_address,stu_tele,passport_id,"
                + "country,email from international";
        try {
            PreparedStatement ps_1 = cn.prepareStatement(selectSt_1);

            ResultSet rs_1 = ps_1.executeQuery();
            while (rs_1.next()) {
                int stu_id = rs_1.getInt("stu_id");
                String fName = rs_1.getString("stu_fName");
                String lName = rs_1.getString("stu_lName");
                String add = rs_1.getString("stu_current_address");
                String tele = rs_1.getString("stu_tele");
                String nic = rs_1.getString("nic");
                String email = rs_1.getString("email");
                Local l = new Local(stu_id, fName, lName, add, tele, email, nic);
                s.add(l);
            }
            PreparedStatement ps_2 = cn.prepareStatement(selectSt_2);

            ResultSet rs_2 = ps_2.executeQuery();
            while (rs_2.next()) {
                int stu_id = rs_2.getInt("stu_id");
                String fName = rs_2.getString("stu_fName");
                String lName = rs_2.getString("stu_lName");
                String add = rs_2.getString("stu_current_address");
                String tele = rs_2.getString("stu_tele");
                String passport = rs_2.getString("passport_id");
                String country = rs_2.getString("country");
                String email = rs_2.getString("email");
                International i = new International(stu_id, fName, lName, add, tele, email, passport, country);
                s.add(i);
            }
            rs_2.close();
            ps_2.close();

            rs_1.close();
            ps_1.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return s;
    }

    public int getNxtId() {
        int stuIDFromLoal = 0;
        int stuIDFromInternational = 0;
        int maxId = 0;
        int nextStuId = 0;
        String selectSt_1 = "select MAX(stu_id) from local";
        String selectSt_2 = "select MAX(stu_id) from international";
        try {
            PreparedStatement ps_1 = cn.prepareStatement(selectSt_1);

            ResultSet rs_1 = ps_1.executeQuery();
            if (rs_1.next()) {
                stuIDFromLoal = rs_1.getInt(1);
            }

            PreparedStatement ps_2 = cn.prepareStatement(selectSt_2);

            ResultSet rs_2 = ps_2.executeQuery();
            if (rs_2.next()) {
                stuIDFromInternational = rs_2.getInt(1);
            }

            rs_1.close();
            rs_2.close();
            ps_1.close();
            ps_2.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
        if (stuIDFromLoal > stuIDFromInternational) {
            maxId = stuIDFromLoal;
        } else {
            maxId = stuIDFromInternational;
        }
        nextStuId = maxId + 1;
        return nextStuId;
    }

}
