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
public class Landlord {
    private int l_id; //lanlord id
    private String l_fName; //first name
    private String l_lName; //last name
    private String add;   //address
    private String tele_land; //land phone no
    private String tele_mob; //mobile no 
    private String email;  //email 

    public Landlord() {
    }

    public Landlord(int l_id, String l_fName, String l_lName, String add, String tele_land, String tele_mob, String email) {
        this.l_id = l_id;
        this.l_fName = l_fName;
        this.l_lName = l_lName;
        this.add = add;
        this.tele_land = tele_land;
        this.tele_mob = tele_mob;       
        this.email = email;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }

    public void setL_fName(String l_fName) {
        this.l_fName = l_fName;
    }

    public void setL_lName(String l_lName) {
        this.l_lName = l_lName;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setTele_land(String tele_land) {
        this.tele_land = tele_land;
    }

    public void setTele_mob(String tele_mob) {
        this.tele_mob = tele_mob;
    }    

    public void setEmail(String email) {
        this.email = email;
    }

    public int getL_id() {
        return l_id;
    }

    public String getL_fName() {
        return l_fName;
    }

    public String getL_lName() {
        return l_lName;
    }

    public String getAdd() {
        return add;
    }

    public String getTele_land() {
        return tele_land;
    }

    public String getTele_mob() {
        return tele_mob;
    }    

    public String getEmail() {
        return email;
    }
    
    
}
