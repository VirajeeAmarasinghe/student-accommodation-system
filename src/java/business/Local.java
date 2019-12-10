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
public class Local extends Student {

    private String nic;

    public Local() {
    }

    public Local(int stu_id, String stu_fName, String stu_lName, String stu_currentAdd, String stu_tele, String email, String nic) {
        super(stu_id, stu_fName, stu_lName, stu_currentAdd, stu_tele, email);
        this.nic = nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getNic() {
        return nic;
    }

}
