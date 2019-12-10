/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Virajee
 */
public abstract class Student {

    private int stu_id;
    private String stu_fName;
    private String stu_lName;
    private String stu_currentAdd;
    private String stu_tele;
    private String email;

    public Student() {
    }

    public Student(int stu_id, String stu_fName, String stu_lName, String stu_currentAdd, String stu_tele, String email) {
        this.stu_id = stu_id;
        this.stu_fName = stu_fName;
        this.stu_lName = stu_lName;
        this.stu_currentAdd = stu_currentAdd;
        this.stu_tele = stu_tele;
        this.email = email;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public void setStu_fName(String stu_fName) {
        this.stu_fName = stu_fName;
    }

    public void setStu_lName(String stu_lName) {
        this.stu_lName = stu_lName;
    }

    public void setStu_currentAdd(String stu_currentAdd) {
        this.stu_currentAdd = stu_currentAdd;
    }

    public void setStu_tele(String stu_tele) {
        this.stu_tele = stu_tele;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStu_id() {
        return stu_id;
    }

    public String getStu_fName() {
        return stu_fName;
    }

    public String getStu_lName() {
        return stu_lName;
    }

    public String getStu_currentAdd() {
        return stu_currentAdd;
    }

    public String getStu_tele() {
        return stu_tele;
    }

    public String getEmail() {
        return email;
    }

}
