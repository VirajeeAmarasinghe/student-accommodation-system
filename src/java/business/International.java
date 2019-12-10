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
public class International extends Student{
    private String passport;
    private String country;

    public International() {
    }

    public International(int stu_id, String stu_fName, String stu_lName, String stu_currentAdd, String stu_tele, String email,String passport, String country) {
        super(stu_id, stu_fName, stu_lName, stu_currentAdd, stu_tele, email);
        this.passport = passport;
        this.country = country;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassport() {
        return passport;
    }

    public String getCountry() {
        return country;
    }
    
    
}
